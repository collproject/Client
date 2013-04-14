/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.panels;

import java.awt.Component;
import javax.swing.JPanel;

/**
 *
 * @author Madness
 */
public abstract class AbstractPanel  extends JPanel{

    private Component parentComponent;
    public AbstractPanel() {
        initComponents();
        configComponents();
        addComponents();
    }
    protected abstract void initComponents();
    protected abstract void configComponents();
    protected abstract void addComponents();

    public Component getParentComponent() {
        return parentComponent;
    }

    public void setParentComponent(Component parentComponent) {
        this.parentComponent = parentComponent;
    }
    
    
}
