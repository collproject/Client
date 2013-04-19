/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.action;

import com.pb.shop.client.api.Client;
import com.pb.shop.client.frames.MainFrame;
import com.pb.shop.model.Category;
import com.pb.shop.data.models.CategoriesTreeModel;
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
        new ClientSwingWorker<CategoriesTreeModel, Void>(mainFrame) {
            @Override
            protected CategoriesTreeModel doClientQuery() throws Exception {
                List<Category> allCategories = getClient().getAllCategories();
                return new CategoriesTreeModel(allCategories);
            }

            @Override
            protected void doneQuery() {
                mainFrame.getCategoryPanel().setTreeModel(getResponse());

            }
        }.execute();
    }
}
