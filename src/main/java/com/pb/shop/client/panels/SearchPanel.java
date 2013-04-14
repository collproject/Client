/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Дмитрий
 */
public class SearchPanel extends AbstractPanel{

    private JLabel prodNameLabel;
    private JTextField prodNameField;
    private JLabel priceFromLabel;
    private JTextField priceFromField;
    private JLabel priceToLabel;
    private JTextField priceToField;
    private JButton searchButton;

    public SearchPanel() {
    }

    public SearchPanel(Component c) {
        setParentComponent(c);
    }
    
    
    
    @Override
    protected void initComponents() {
        prodNameLabel = new JLabel();
        prodNameField = new JTextField();
        priceFromLabel = new JLabel();
        priceFromField = new JTextField();
        priceToLabel = new JLabel();
        priceToField = new JTextField();
        searchButton = new JButton(); 
    }

    @Override
    protected void configComponents() {
        setLayout(new MigLayout());      
        setBorder(BorderFactory.createTitledBorder("Поиск товаров:"));
      
        prodNameLabel.setText("Название:");
        priceFromLabel.setText("цена от:");
        priceFromField.setColumns(6);
        priceToLabel.setText("до:");
        priceToField.setColumns(6);
        searchButton.setText("Поиск");
    }

    @Override
    protected void addComponents() {
        add(prodNameLabel);
        add(prodNameField, "growx, push");
        add(priceFromLabel, "gap unrelated, growx");
        add(priceFromField, "growx");
        add(priceToLabel, "gap unrelated, growx");
        add(priceToField, "growx");
        add(searchButton, "gap unrelated, growx"); 
    }

    public JTextField getProdNameField() {
        return prodNameField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    
    public JTextField getPriceFromField() {
        return priceFromField;
    }

    public JTextField getPriceToField() {
        return priceToField;
    }
        
}
