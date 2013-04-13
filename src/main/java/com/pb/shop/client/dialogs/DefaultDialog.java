/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.dialogs;

import com.pb.shop.client.panels.CategoryPanel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class DefaultDialog extends JDialog {

    private Component c;
    private JPanel contentPanel;
    private JButton buttonOk;
    private JButton buttonCancel;
    private JPanel panelButton;
    private JPanel panel;

    public DefaultDialog(Component c, JPanel contentPanel, Dimension size) {
        this.c = c;
        this.contentPanel = contentPanel;
        initComponents();
        configComponents();
        addComponents();
        setSize(size);
        setLocationRelativeTo(c);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        buttonCancel = new JButton("Отмена");
        buttonOk = new JButton("Ок");
        panelButton = new JPanel();
        panel = new JPanel();
    }

    private void configComponents() {
        panelButton.setLayout(new GridLayout(1, 1, 10, 10));
        panel.setLayout(new MigLayout("", "[grow]", "[]10[]"));
        
        getButtonCancel().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultDialog.this.dispose();
            }
        });
    }

    private void addComponents() {
        panelButton.add(buttonOk);
        panelButton.add(buttonCancel);
        panel.add(contentPanel, "grow, push, wrap");
        panel.add(panelButton, "align right");
        add(panel);
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public JButton getButtonOk() {
        return buttonOk;
    }

    public JButton getButtonCancel() {
        return buttonCancel;
    }

    public static void main(String[] args) {
        new DefaultDialog(null, new JPanel(), new Dimension(350, 400));
    }
}