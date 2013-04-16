/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.panels;

import com.pb.shop.model.Category;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
    private JComboBox<Category> comboBoxCategories;
    private JCheckBox checkBoxRootCategory;
    private List<Category> categories;

    public CategoryConfigPanel() {
    }

    public CategoryConfigPanel(Component c, List<Category> categories) {
        setParentComponent(c);
        this.categories = categories;
        if (categories == null) {
            checkBoxRootCategory.setSelected(true);
            checkBoxRootCategory.setEnabled(false);
            comboBoxCategories.setEnabled(false);
        }
    }

    @Override
    protected void initComponents() {
        labelId = new JLabel("ИН:");
        labelName = new JLabel("Название:");
        labelParentName = new JLabel("Родитель:");
        labelRoot = new JLabel("Корневая:");
        checkBoxRootCategory = new JCheckBox();
        comboBoxCategories = new JComboBox<Category>();
        fieldId = new JTextField(10);
        fieldName = new JTextField(10);
    }

    @Override
    protected void configComponents() {
        setLayout(new MigLayout("", "[]15[]", "[][][][]"));
        comboBoxCategories.setModel(new DefaultComboBoxModel<Category>());
        checkBoxRootCategory.addActionListener(changeStatusRootCategory());
    }

    @Override
    protected void addComponents() {
        add(labelId);
        add(fieldId, "growx, push, wrap");
        add(labelName);
        add(fieldName, "growx, push, wrap");
        add(labelParentName);
        add(comboBoxCategories, "growx, push, wrap");
        add(labelRoot);
        add(checkBoxRootCategory, "growx, push, span");
    }

    public JTextField getFieldId() {
        return fieldId;
    }

    public JTextField getFieldName() {
        return fieldName;
    }

    public JComboBox<Category> getComboBoxCategories() {
        return comboBoxCategories;
    }

    public JCheckBox getCheckBoxRootCategory() {
        return checkBoxRootCategory;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Category getParentCategory() {
        if (checkBoxRootCategory.isSelected()) {
            return null;
        } else {
            return (Category) comboBoxCategories.getSelectedItem();
        }
    }

    public static void main(String[] args) {
        JFrame test = new JFrame();
        test.setLayout(new BorderLayout());
        test.add(new CategoryConfigPanel());
        test.setVisible(true);
    }

    private ActionListener changeStatusRootCategory() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (((JCheckBox) e.getSource()).isSelected()) {
                    comboBoxCategories.setEnabled(false);
                } else {
                    comboBoxCategories.setEnabled(true);
                }

            }
        };
    }
}
