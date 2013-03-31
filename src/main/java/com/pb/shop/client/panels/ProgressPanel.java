/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.panels;

import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author Madness
 */
public class ProgressPanel extends AbstractPanel {

    private JProgressBar progressBar;
    private JPanel panel;

    @Override
    protected void initComponents() {
        progressBar = new JProgressBar(0, 100);
        panel = new JPanel();
    }

    @Override
    protected void configComponents() {
        panel.setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        progressBar.setIndeterminate(true);
//        new Thread (new Runnable() {
//
//            @Override
//            public void run() {
//                try {
//                    progressBar.setIndeterminate(false);
//                    Thread.sleep(5000);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(ProgressPanel.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                progressBar.setIndeterminate(true);
//            }
//        }).start();
//        
    }

    @Override
    protected void addComponents() {
        panel.add(progressBar, BorderLayout.EAST);
        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame test = new JFrame();
        test.setLayout(new BorderLayout());
        test.add(new ProgressPanel());
        test.setVisible(true);
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }    
}
