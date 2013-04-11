/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.action;

import com.pb.shop.client.dialogs.ConnectConfigDialog;
import com.pb.shop.client.frames.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

/**
 *
 * @author Madness
 */
public class MenuConfigController implements ActionListener{

    private MainFrame mainFrame;

    public MenuConfigController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                new ConnectConfigDialog(mainFrame);
            }
        });
    }
    
}
