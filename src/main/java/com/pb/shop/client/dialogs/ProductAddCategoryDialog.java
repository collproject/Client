/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.dialogs;

import com.pb.shop.client.panels.CategoryPanel;
import javax.swing.JComponent;
import javax.swing.JPanel;



/**
 *
 * @author Madness
 */
public class ProductAddCategoryDialog extends ProductAddDialog{

    private CategoryPanel categoryPanel;
    
    public ProductAddCategoryDialog(JComponent c) {
        super(c, new CategoryPanel());
        categoryPanel = (CategoryPanel) getContentPanel();
    }
    
}