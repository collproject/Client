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
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 *
 * @author Madness
 */
public class AddProductController implements ActionListener{

    Component c;
    public AddProductController() {
    }

    public AddProductController(Component c) {
        this.c = c;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame frame = (MainFrame) c;
        final ProductsTableModel tableModel = (ProductsTableModel) 
                frame.getResultPanel().getResultTable().getModel();
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    new ProductConfDialog(c,tableModel);
                }
            });
    }
}
