/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Madness
 */
public class MakerConfigPanel extends AbstractPanel {

    private JLabel labelId;
    private JLabel labelName;
    private JLabel labelDescr;
    private JTextField fieldId;
    private JTextField fieldName;
    private JTextArea textAreaDescr;

    public MakerConfigPanel() {
    }

    public MakerConfigPanel(Component c) {
        setParentComponent(c);
    }
    

    @Override
    protected void initComponents() {
        labelId = new JLabel("ИН:");
        labelName = new JLabel("Название:");
        labelDescr = new JLabel("Описание:");
        fieldId = new JTextField(10);
        fieldName = new JTextField(10);
        textAreaDescr = new JTextArea(5, 10);
    }

    @Override
    protected void configComponents() {
        setLayout(new MigLayout("", "[]15[]", "[][][]"));
    }

    @Override
    protected void addComponents() {
        add(labelId);
        add(fieldId, "growx, push, wrap");
        add(labelName);
        add(fieldName, "growx, push, wrap");
        add(labelDescr);
        add(new JScrollPane(textAreaDescr,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), "grow, push");
    }

    public JTextField getFieldId() {
        return fieldId;
    }

    public JTextField getFieldName() {
        return fieldName;
    }

    public JTextArea getTextAreaDescr() {
        return textAreaDescr;
    }

    public static void main(String[] args) {
        JFrame test = new JFrame();
        test.setLayout(new BorderLayout());
        test.add(new MakerConfigPanel());
        test.setVisible(true);
    }
}
