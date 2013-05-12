/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.action;

import com.pb.shop.client.api.Client;
import com.pb.shop.client.frames.MainFrame;
import com.pb.shop.model.Category;
import com.pb.shop.model.Maker;
import com.pb.shop.model.Product;
import com.pb.shop.model.ProductsList;
import com.pb.shop.data.models.ProductsTableModel;
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
import sun.security.pkcs11.P11TlsKeyMaterialGenerator;

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
                new ClientSwingWorker<Void, Void>(mainFrame) {
            @Override
            protected Void doClientQuery() throws Exception {
                
                Maker selectedMaker = mainFrame.getMakerPanel().getSelectedMaker();
                Category selectedCategory = mainFrame.getCategoryPanel().getSelectedCategory();
                
                String catId = null;
                String makId = null;
                
                if(selectedMaker != null){
                    makId = selectedMaker.getMakID().toString();
                }
                if(selectedCategory != null){
                    catId = selectedCategory.getCatID().toString();
                }
                
                String name = mainFrame.getSearchPanel().getProdNameField().getText();
                String fromPrice = mainFrame.getSearchPanel().getPriceFromField().getText();
                String toPrice = mainFrame.getSearchPanel().getPriceToField().getText();
                        
                List<Product> products = getClient().getProducts(catId, makId, name, fromPrice, toPrice);
                
                List<Maker> makers = mainFrame.getMakerPanel().getMakers();
                List<Category> categories = mainFrame.getCategoryPanel().getCategories();
                
                mainFrame.getResultPanel()
                        .getResultTable()
                        .setModel(new ProductsTableModel(products, makers, categories));
                
                return null;
            }
            @Override
            protected void doneQuery() {
                getResponse();
            }
        }.execute();
    }
    
}
