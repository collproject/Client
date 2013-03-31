/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.action;

import com.pb.shop.client.dialogs.ProductAddCategoryDialog;
import com.pb.shop.client.dialogs.ProductAddMakerDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 *
 * @author Madness
 */
public class ProductAddMakerController implements ActionListener{
    
    private JComponent c;

    public ProductAddMakerController(JComponent c) {
        this.c = c;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new ProductAddMakerDialog(c);
            }
        });
    }
}
