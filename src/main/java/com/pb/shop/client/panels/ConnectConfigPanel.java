/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Madness
 */
public class ConnectConfigPanel extends AbstractPanel {

    private JLabel labelUrl;
    private JTextField fieldUrl;
    private JPanel panelOkCancel;
    private JPanel panelMain;

    @Override
    protected void initComponents() {
        labelUrl = new JLabel("URL: ");
        fieldUrl = new JTextField(20);
        panelOkCancel = new JPanel();
        panelMain = new JPanel();
    }

    @Override
    protected void configComponents() {
        panelOkCancel.setLayout(new GridLayout(1, 2, 10, 0));
        panelMain.setLayout(new MigLayout("", "[]", "[][]10[]"));
        setLayout(new BorderLayout());
    }

    @Override
    protected void addComponents() {

        panelMain.add(labelUrl, "growx, push, wrap");
        panelMain.add(fieldUrl, "growx, push, wrap");
        panelMain.add(panelOkCancel, "align right");

        add(panelMain);
    }


    public JTextField getFieldUrl() {
        return fieldUrl;
    }

    public static void main(String[] args) {
        JFrame test = new JFrame();
        test.setLayout(new BorderLayout());
        test.add(new ConnectConfigPanel());
        test.setVisible(true);
    }
}
