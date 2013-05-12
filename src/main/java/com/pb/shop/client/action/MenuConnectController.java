/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.action;

import com.pb.shop.client.api.Client;
import com.pb.shop.client.frames.MainFrame;
import com.pb.shop.model.Category;
import com.pb.shop.data.models.CategoriesTreeModel;
import com.pb.shop.data.models.MakersJListModel;
import com.pb.shop.model.Maker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
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
        new ClientSwingWorker<Void, Void>(mainFrame) {
            @Override
            protected Void doClientQuery() throws Exception {
                List<Category> allCategories = getClient().getAllCategories();
                List<Maker> allMakers = getClient().getAllMakers();
                
                CategoriesTreeModel treeModel = new CategoriesTreeModel(allCategories);
                MakersJListModel listModel = new MakersJListModel(allMakers);
                
                mainFrame.getCategoryPanel().setTreeModel(treeModel);
                mainFrame.getMakerPanel().getMakerList().setModel(listModel);
                return null;
                
            }

            @Override
            protected void doneQuery() {
                getResponse();
            }
        }.execute();
    }
}
