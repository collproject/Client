/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.action;

import com.pb.shop.client.api.Client;
import java.io.FileInputStream;
import java.util.Properties;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author Madness
 */
public abstract class ClientSwingWorker<T, V> extends SwingWorker<T, V> {

    private Client client;
    private JProgressBar progressBar;

    public ClientSwingWorker(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    
    public Client getClient() {
        return client;
    }

    @Override
    protected T doInBackground() throws Exception {
        progressBar.setIndeterminate(true);
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/connectionUrl.properties"));
        String url = properties.getProperty("url");
        client = new Client(url);
        return doClientQuery();
    }

    @Override
    protected void done() {
        progressBar.setIndeterminate(false);
        doneQuery();
    }

    protected abstract T doClientQuery() throws Exception;

    protected abstract void doneQuery();
}
