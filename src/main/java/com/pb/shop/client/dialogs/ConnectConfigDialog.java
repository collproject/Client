/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.dialogs;

import com.pb.shop.client.panels.ConnectConfigPanel;
import com.pb.shop.client.panels.ProductConfPanel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

/**
 *
 * @author Madness
 */
public class ConnectConfigDialog extends JDialog {

    private ConnectConfigPanel confPanel;

    public ConnectConfigDialog(Component c) {
        initComponents();
        configComponents();
        addComponents();
        setModal(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(new Dimension(300, 130));
        setLocationRelativeTo(c);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ConnectConfigDialog(null);
            }
        });
    }

    private void initComponents() {
        confPanel = new ConnectConfigPanel();
    }

    private void configComponents() {
        final Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/connectionUrl.properties"));
            String url = properties.getProperty("url");
            confPanel.getFieldUrl().setText(url);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConnectConfigDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConnectConfigDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

        confPanel.getButtonOk().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String newUrl = confPanel.getFieldUrl().getText();
                    properties.setProperty("url", newUrl);
                    properties.store(new FileOutputStream("src/main/resources/connectionUrl.properties"), "");
                    ConnectConfigDialog.this.dispose();
                } catch (IOException ex) {
                    Logger.getLogger(ConnectConfigDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        confPanel.getButtonCancel().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConnectConfigDialog.this.dispose();
            }
        });
    }

    private void addComponents() {
        setContentPane(confPanel);
    }
}
