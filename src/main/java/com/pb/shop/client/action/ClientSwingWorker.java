/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.action;

import com.pb.shop.client.api.Client;
import com.pb.shop.client.dialogs.MakerConfigDialog;
import com.pb.shop.client.frames.MainFrame;
import com.pb.shop.exception.GeneralException;
import com.pb.shop.exception.ServiceException;
import java.awt.Component;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author Madness
 */
public abstract class ClientSwingWorker<T, V> extends SwingWorker<T, V> {

    private Client client;
    private JProgressBar progressBar;
    private T response;
    private Component mainFrame;

    public ClientSwingWorker(Component mainFrame) {
        this.mainFrame = mainFrame;
        if(mainFrame instanceof MainFrame){
            this.progressBar = ((MainFrame) mainFrame).getProgressPanel().getProgressBar();
        }
    }

    public Client getClient() {
        return client;
    }

    @Override
    protected T doInBackground() throws Exception {
        if (progressBar != null) {
            progressBar.setIndeterminate(true);
        }
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/connectionUrl.properties"));
        String url = properties.getProperty("url");
        client = new Client(url);
        return doClientQuery();
    }

    @Override
    protected void done() {
        if (progressBar != null) {
            progressBar.setIndeterminate(false);
        }
        try {
            response = get();
            doneQuery();
        } catch (InterruptedException ex) {
            Logger.getLogger(MakerConfigDialog.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(mainFrame, ex.getMessage(), "Ошибка приложения!",
                        JOptionPane.ERROR_MESSAGE);
        } catch (ExecutionException ex) {
            Logger.getLogger(MakerConfigDialog.class.getName()).log(Level.SEVERE, null, ex);
            if (ex.getCause() instanceof GeneralException) {
                JOptionPane.showMessageDialog(mainFrame, ex.getCause().getMessage(), "Ошибка приложения!",
                        JOptionPane.ERROR_MESSAGE);
            } else if (ex.getCause() instanceof ServiceException) {
                JOptionPane.showMessageDialog(mainFrame, ex.getCause().getMessage(), "Ошибка сервера!",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    protected abstract T doClientQuery() throws Exception;

    protected abstract void doneQuery();

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
