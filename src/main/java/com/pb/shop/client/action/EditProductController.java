/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.action;

import com.pb.shop.client.dialogs.ProductConfDialog;
import com.pb.shop.client.frames.MainFrame;
import com.pb.shop.data.models.ProductsTableModel;
import com.pb.shop.model.Product;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

/**
 *
 * @author Madness
 */
public class EditProductController implements ActionListener{

    Component c;
    public EditProductController() {
    }

    public EditProductController(Component c) {
        this.c = c;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
            MainFrame frame = (MainFrame) c;
            final Product product = frame.getResultPanel().getSelectedProduct();
            final ProductsTableModel tableModel = (ProductsTableModel) 
                frame.getResultPanel().getResultTable().getModel();
            final int index = frame.getResultPanel().getSelectedIndex();
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    new ProductConfDialog(c, product, tableModel, index);
                }
            });
    }
    
}
