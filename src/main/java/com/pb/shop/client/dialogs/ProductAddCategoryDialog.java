/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.dialogs;

import com.pb.shop.client.panels.CategoryPanel;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Madness
 */
public class ProductAddCategoryDialog extends DefaultDialog {

    private CategoryPanel categoryPanel;

    public ProductAddCategoryDialog(Component c) {
        super(c, new CategoryPanel(), new Dimension(350, 400));
        categoryPanel = (CategoryPanel) getContentPanel();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProductAddCategoryDialog(null);
            }
        });
    }
}