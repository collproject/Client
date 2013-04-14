/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.dialogs;

import com.pb.shop.client.panels.ProductConfPanel;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 *
 * @author Madness
 */
public class ProductConfDialog extends DefaultDialog{
    
    private ProductConfPanel confPanel;
    public ProductConfDialog(Component c) {
        super(c, new ProductConfPanel(), new Dimension(700,400));
        confPanel = (ProductConfPanel) getContentPanel();
        confPanel.setParentComponent(c);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new ProductConfDialog(null);
            }
        });
    }
}
