/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.action;

import com.pb.shop.client.Client;
import com.pb.shop.client.frames.MainFrame;
import com.pb.shop.model.Category;
import com.pb.shop.model.Maker;
import com.pb.shop.model.Product;
import com.pb.shop.model.ProductsList;
import com.pb.shop.models.table.ProductsTableModel;
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
        new SwingWorker<ProductsTableModel, Void>(){

            @Override
            protected ProductsTableModel doInBackground() throws Exception {
                Client c = new Client("http://localhost:7375/shop-app-server/admin");
                List<Category> allCategories = c.getAllCategories();
                List<Maker> allMakers = c.getAllMakers();
                List<Product> allProducts = c.getAllProducts();
                return new ProductsTableModel(allProducts, allMakers, allCategories);
            }

            @Override
            protected void done() {
                JTable table = mainFrame.getResultPanel().getResultTable();
                try {
                     table.setModel(get());                 
                } catch (InterruptedException ex) {
                    Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.execute();
    }
    
}
