/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.panels;

import javax.swing.JPanel;

/**
 *
 * @author Madness
 */
public abstract class AbstractPanel  extends JPanel{

    public AbstractPanel() {
        initComponents();
        configComponents();
        addComponents();
    }
    
    protected abstract void initComponents();
    protected abstract void configComponents();
    protected abstract void addComponents();
    
}
