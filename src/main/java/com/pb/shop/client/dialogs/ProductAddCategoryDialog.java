/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.dialogs;

import com.pb.shop.client.panels.CategoryPanel;
import com.pb.shop.client.panels.ProductConfPanel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
        getButtonOk().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }
}