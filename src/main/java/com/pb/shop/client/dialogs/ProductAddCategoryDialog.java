/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.dialogs;

import com.pb.shop.client.frames.MainFrame;
import com.pb.shop.client.panels.CategoryPanel;
import com.pb.shop.client.panels.ProductConfPanel;
import com.pb.shop.data.models.CategoriesTreeModel;
import com.pb.shop.model.Category;
import com.pb.shop.model.Maker;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreeModel;

/**
 *
 * @author Madness
 */
public class ProductAddCategoryDialog extends DefaultDialog {

    private CategoryPanel categoryPanel;
    private ProductConfPanel confPanel;

    public ProductAddCategoryDialog(Component c, ProductConfPanel confPanel) {
        super(c, new CategoryPanel(), new Dimension(350, 400));
        categoryPanel = (CategoryPanel) getContentPanel();
        this.confPanel = confPanel;
        configComponents();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProductAddCategoryDialog(null, null);
            }
        });
    }

    private void configComponents() {
        TreeModel model = ((MainFrame) mainFrame).getCategoryPanel().getTree().getModel();
        categoryPanel.setTreeModel((CategoriesTreeModel) model);
        getButtonOk().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 Category category;
                 category = categoryPanel.getSelectedCategory();
                 if(category != null){
                     confPanel.setCategory(category);
                     ProductAddCategoryDialog.this.dispose();
                 }
            }
        });
    }
}