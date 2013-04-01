/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.action;

import com.pb.shop.client.Client;
import com.pb.shop.client.frames.MainFrame;
import com.pb.shop.model.Product;
import com.pb.shop.model.ProductsList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Madness
 */
public class SearchController implements ActionListener{

    private MainFrame mainFrame;

    public SearchController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        new SwingWorker<List<Product>, Void>(){

            @Override
            protected List<Product> doInBackground() throws Exception {
                Client c = new Client("http://localhost:7375/shop-app-server/admin");
                return c.getAllProducts();
                
            }

            @Override
            protected void done() {
                JTable table = mainFrame.getResultPanel().getResultTable();
                try {
                    List<Product> l = get();
                    String t = "test";
                } catch (InterruptedException ex) {
                    Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
            
            
        }.execute();
    }
    
}
