/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.action;

import com.pb.shop.client.api.Client;
import com.pb.shop.client.frames.MainFrame;
import com.pb.shop.model.Maker;
import com.pb.shop.data.models.MakersJListModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.SwingWorker;

/**
 *
 * @author Дмитрий
 */
public class TestListContrller implements ActionListener{

    private MainFrame mainFrame;

    public TestListContrller(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
        
    public void actionPerformed(ActionEvent e) {
       new SwingWorker<MakersJListModel, Void>(){

            @Override
            protected MakersJListModel doInBackground() throws Exception {
                Client c = new Client("http://localhost:7375/shop-app-server/admin");
                List<Maker> allMakers = c.getAllMakers();
                return new MakersJListModel(allMakers);
            }

            @Override
            protected void done() {
               JList list = mainFrame.getMakerPanel().getMakerList();
               try {
                   list.setModel(get());                 
               } catch (InterruptedException ex) {
                   Logger.getLogger(TestListContrller.class.getName()).log(Level.SEVERE, null, ex);
               } catch (ExecutionException ex) {
                   Logger.getLogger(TestListContrller.class.getName()).log(Level.SEVERE, null, ex);
               }
           
            }
           
            
       }.execute();
    }
    
}
