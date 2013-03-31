/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author Madness
 */
public class SearchController implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showInputDialog("Идет поиск!");
       // new SwingWorker<ProductsList, Object>()
    }
    
}
