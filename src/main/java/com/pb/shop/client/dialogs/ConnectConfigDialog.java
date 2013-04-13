/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.dialogs;

import com.pb.shop.client.panels.ConnectConfigPanel;
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
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 *
 * @author Madness
 */
public class ConnectConfigDialog extends DefaultDialog {

    private ConnectConfigPanel confPanel;

    public ConnectConfigDialog(Component c) {
        super(c, new ConnectConfigPanel(), new Dimension(300, 150));
        confPanel = (ConnectConfigPanel) getContentPanel();
        configComponents();
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

        getButtonOk().addActionListener(new ActionListener() {
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
    }
}
