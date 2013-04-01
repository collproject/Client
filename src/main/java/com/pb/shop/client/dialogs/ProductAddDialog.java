/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.dialogs;

import com.pb.shop.client.panels.CategoryPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Madness
 */
public class ProductAddDialog extends JDialog{
    private JComponent c;
    private JPanel contentPanel;
    private JButton buttonOk;
    private JButton buttonCancel;
    private JPanel panelButton;
    private JPanel panel;
    
    public ProductAddDialog(JComponent c, JPanel contentPanel) {
        this.c = c;
        this.contentPanel = contentPanel;
        initComponents();
        configComponents();
        addComponents();
        setSize(350,400);
        setLocationRelativeTo(c);
        setModal(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
    }
    
    private void initComponents() {
        buttonCancel = new JButton("Отмена");
        buttonOk = new JButton("Ок");
        panelButton = new JPanel();
        panel = new JPanel();
    }

    private void configComponents() {
        panelButton.setLayout(new GridLayout(1,1, 10, 10));
        panel.setLayout(new MigLayout("","[grow]","[]10[]"));
    }

    private void addComponents() {
        panelButton.add(buttonOk);
        panelButton.add(buttonCancel);
        panel.add(contentPanel,"grow, push, wrap");
        panel.add(panelButton,"align right");
        add(panel);
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
    

    
}