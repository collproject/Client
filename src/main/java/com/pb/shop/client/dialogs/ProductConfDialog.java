/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.dialogs;

import com.pb.shop.client.panels.ProductConfPanel;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.SwingUtilities;

/**
 *
 * @author Madness
 */
public class ProductConfDialog extends JDialog{
    
    private ProductConfPanel confPanel;
            
    public ProductConfDialog(Component c) {
        initComponents();
        configComponents();
        addComponents();
        setModal(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(new Dimension(700, 400));
        setLocationRelativeTo(c);
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

    private void initComponents() {
        confPanel = new ProductConfPanel();
    }

    private void configComponents() {
    }

    private void addComponents() {
        setContentPane(confPanel);
    }
}
