/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.action;

import com.pb.shop.client.dialogs.ProductConfDialog;
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
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    new ProductConfDialog(c);
                }
            });
    }
    
}
