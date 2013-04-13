/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.panels;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Madness
 */
public class MakerConfigPanel extends AbstractPanel {

    private JLabel labelId;
    private JLabel labelName;
    private JTextField fieldId;
    private JTextField fieldName;

    @Override
    protected void initComponents() {
        labelId = new JLabel("ИН:");
        labelName = new JLabel("Название:");
        fieldId = new JTextField(10);
        fieldName = new JTextField(10);
    }

    @Override
    protected void configComponents() {
        setLayout(new MigLayout("", "[][]", "[][]"));
    }

    @Override
    protected void addComponents() {
        add(labelId);
        add(fieldId, "growx, push, wrap");
        add(labelName);
        add(fieldName, "growx, push");
    }

    public JTextField getFieldId() {
        return fieldId;
    }

    public JTextField getFieldName() {
        return fieldName;
    }

    public static void main(String[] args) {
        JFrame test = new JFrame();
        test.setLayout(new BorderLayout());
        test.add(new MakerConfigPanel());
        test.setVisible(true);
    }
}
