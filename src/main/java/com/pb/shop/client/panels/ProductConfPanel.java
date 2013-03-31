/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.panels;

import com.pb.shop.client.action.ProductAddCategoryController;
import com.pb.shop.client.action.ProductAddMakerController;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Madness
 */
public class ProductConfPanel extends AbstractPanel{
    
    private JButton buttonOk;
    private JButton buttonCancel;
    private JButton buttonCtegory;
    private JButton buttonMaker;
    
    private JCheckBox checkExist;
    
    private JTextField fieldPrice;
    private JTextField fieldId;
    private JTextField fieldName;
    
    private JLabel labelId;
    private JLabel labelCategory;
    private JLabel labelMaker;
    private JLabel labelCurrentCategory;
    private JLabel labelCurrentMaker;
    private JLabel labelPrice;
    private JLabel labelName;
    
    private JPanel panelProp;
    private JPanel panelOkCancel;
    private ModDescriptionPanel modDescriptionPanel;
   
    
    
    @Override
    protected void initComponents() {
        buttonCancel = new JButton("Отмена");
        buttonOk = new JButton("Ок");
        buttonCtegory = new JButton("Выбрать");
        buttonMaker = new JButton("Выбрать");
        
        checkExist = new JCheckBox("Есть на складе");
        
        labelId = new JLabel("ИД:");
        labelMaker = new JLabel("Производитель:");
        labelCategory = new JLabel("Категория:");
        labelPrice = new JLabel("Цена:");
        labelName = new JLabel("Название:");
        
        labelCurrentCategory = new JLabel("Не выбрано");
        labelCurrentMaker =  new JLabel("Не выбрано");
        
        fieldId = new JTextField(10);
        fieldName = new JTextField(10);
        fieldPrice = new JTextField(7);
        
        panelProp = new JPanel();
        modDescriptionPanel = new  ModDescriptionPanel();
        panelOkCancel = new JPanel();
        
    }

    @Override
    protected void configComponents() {
        panelProp.setLayout(new MigLayout());
        panelProp.setBorder(BorderFactory.createTitledBorder("Характеристики товара"));
        panelOkCancel.setLayout(new GridLayout(1, 2, 10, 0));
        setLayout(new BorderLayout());
    }
    
    @Override
    protected void addComponents() {
        panelProp.add(labelId);
        panelProp.add(fieldId,"growx, push");
        
        panelProp.add(labelCategory,"gap unrelated");
        panelProp.add(labelCurrentCategory);
        panelProp.add(buttonCtegory);
        
        panelProp.add(labelPrice, "gap unrelated");
        panelProp.add(fieldPrice, "growx, push, wrap");
        
        panelProp.add(labelName);
        panelProp.add(fieldName, "growx, push");
        
        panelProp.add(labelMaker, "gap unrelated");
        panelProp.add(labelCurrentMaker);
        panelProp.add(buttonMaker);
        
        panelProp.add(checkExist, "gap unrelated, span 2");
        
        panelOkCancel.add(buttonCancel);
        panelOkCancel.add(buttonOk);
        
        
        JPanel panelDown = new JPanel(new MigLayout("","[]","[]10[]"));
        
        panelDown.add(panelProp, "growx, push, wrap");
        panelDown.add(panelOkCancel,"align right");
        
        add(modDescriptionPanel, BorderLayout.CENTER);
        add(panelDown, BorderLayout.SOUTH);
        
    }
    
    private class ModDescriptionPanel extends DescriptionPanel{

        private JButton buttonLoadImg;
        private JButton buttonDelImg;
        private JPanel panelButton;

        public JButton getButtonLoadImg() {
            return buttonLoadImg;
        }

        public JButton getButtonDelImg() {
            return buttonDelImg;
        }
        
        @Override
        protected void initComponents() {
            super.initComponents();
            buttonDelImg = new JButton("Удалить");
            buttonLoadImg = new JButton("Установить");
            panelButton = new JPanel();
        }

        @Override
        protected void configComponents() {
            super.configComponents(); 
            panelButton.setLayout(new GridLayout(1, 2));
            buttonCtegory.addActionListener(new ProductAddCategoryController(this));
            buttonMaker.addActionListener(new ProductAddMakerController(this));
        }

        @Override
        protected void addComponents() {
            super.addComponents();
            panelButton.add(buttonLoadImg);
            panelButton.add(buttonDelImg);
            this.panelLeft.add(panelButton, BorderLayout.SOUTH);
        }
        
    }
    
    public static void main(String[] args) {
        JFrame test = new JFrame();
        test.setLayout(new BorderLayout());
        test.add(new ProductConfPanel());
        test.setVisible(true);
    }
}
