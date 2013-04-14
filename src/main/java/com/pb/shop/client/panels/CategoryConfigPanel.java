/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.panels;

import com.pb.shop.model.Category;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Madness
 */
public class CategoryConfigPanel extends AbstractPanel {

    private JLabel labelId;
    private JLabel labelName;
    private JLabel labelParentName;
    private JLabel labelRoot;
    private JTextField fieldId;
    private JTextField fieldName;
    private JComboBox<Category> listParents;
    private JCheckBox checkBoxRootCategory;

    public CategoryConfigPanel() {
    }

    public CategoryConfigPanel(Component c) {
        setParentComponent(c);
    }
    

    @Override
    protected void initComponents() {
        labelId = new JLabel("ИН:");
        labelName = new JLabel("Название:");
        labelParentName = new JLabel("Родитель:");
        labelRoot = new JLabel("Корневая:");
        checkBoxRootCategory = new JCheckBox();
        listParents = new JComboBox<Category>();
        fieldId = new JTextField(10);
        fieldName = new JTextField(10);
    }

    @Override
    protected void configComponents() {
        setLayout(new MigLayout("", "[]15[]", "[][][][]"));
        listParents.setModel(new DefaultComboBoxModel<Category>(
                new Category[] {new Category(23,"SDfsfsf"),
                           new Category(22,"ывапкуп")}
                ));
    }

    @Override
    protected void addComponents() {
        add(labelId);
        add(fieldId, "growx, push, wrap");
        add(labelName);
        add(fieldName, "growx, push, wrap");
        add(labelParentName);
        add(listParents, "growx, push, wrap");
        add(labelRoot);
        add(checkBoxRootCategory,"growx, push, span");
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
        test.add(new CategoryConfigPanel());
        test.setVisible(true);
    }
}
