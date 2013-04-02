/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.action;

import com.pb.shop.client.Client;
import com.pb.shop.client.frames.MainFrame;
import com.pb.shop.model.Category;
import com.pb.shop.models.table.CategoriesTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTree;
import javax.swing.SwingWorker;

/**
 *
 * @author Madness
 */
public class MenuConnectController implements ActionListener {

    private MainFrame mainFrame;

    public MenuConnectController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void actionPerformed(ActionEvent e) {
        new SwingWorker<CategoriesTreeModel, Void>(){

            @Override
            protected CategoriesTreeModel doInBackground() throws Exception {
                Client c = new Client("http://localhost:7375/shop-app-server/admin");
                List<Category> allCategories = c.getAllCategories();
                return new CategoriesTreeModel(allCategories);
            }

            @Override
            protected void done() {
                JTree tree = mainFrame.getCategoryPanel().getTree();
                try {
                     tree.setModel(get());                 
                } catch (InterruptedException ex) {
                    Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.execute();
    }
}
