/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.panels;

import com.pb.shop.client.dialogs.ProductAddCategoryDialog;
import com.pb.shop.client.dialogs.ProductAddMakerDialog;
import com.pb.shop.model.Category;
import com.pb.shop.model.Maker;
import com.pb.shop.model.Product;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Madness
 */
public class ProductConfPanel extends AbstractPanel {

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
    private ModDescriptionPanel modDescriptionPanel;
    
    private Maker maker;
    private Category category;
    private String pathImage;
    private Product product;

    public ProductConfPanel() {
    }

    public ProductConfPanel(Component c) {
        setParentComponent(c);
    }

    @Override
    protected void initComponents() {
        buttonCtegory = new JButton("Выбрать");
        buttonMaker = new JButton("Выбрать");

        checkExist = new JCheckBox("Есть на складе");

        labelId = new JLabel("ИД:");
        labelMaker = new JLabel("Производитель:");
        labelCategory = new JLabel("Категория:");
        labelPrice = new JLabel("Цена:");
        labelName = new JLabel("Название:");

        labelCurrentCategory = new JLabel("Не выбрано");
        labelCurrentMaker = new JLabel("Не выбрано");

        fieldId = new JTextField(10);
        fieldName = new JTextField(10);
        fieldPrice = new JTextField(7);

        panelProp = new JPanel();
        modDescriptionPanel = new ModDescriptionPanel();

    }

    @Override
    protected void configComponents() {
        panelProp.setLayout(new MigLayout());
        panelProp.setBorder(BorderFactory.createTitledBorder("Характеристики товара"));
        setLayout(new BorderLayout());
    }

    @Override
    protected void addComponents() {
        panelProp.add(labelId);
        panelProp.add(fieldId, "growx, push");

        panelProp.add(labelCategory, "gap unrelated");
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

        JPanel panelDown = new JPanel(new MigLayout("", "[]", "[]10[]"));

        panelDown.add(panelProp, "growx, push, wrap");


        add(modDescriptionPanel, BorderLayout.CENTER);
        add(panelDown, BorderLayout.SOUTH);

    }

    public void setProduct(Product product) {
        this.product = product;
        
        fieldId.setText(product.getProdID().toString());
        fieldName.setText(product.getProdName());
        fieldPrice.setText(product.getProdPrice().toString());
        checkExist.setSelected(product.getProdExist());
        modDescriptionPanel.getDescText().setText(product.getProdDescription());
        
    }

    public JCheckBox getCheckExist() {
        return checkExist;
    }

    public JTextField getFieldPrice() {
        return fieldPrice;
    }

    public JTextField getFieldId() {
        return fieldId;
    }

    public JTextField getFieldName() {
        return fieldName;
    }

    public JLabel getLabelCurrentCategory() {
        return labelCurrentCategory;
    }

    public JLabel getLabelCurrentMaker() {
        return labelCurrentMaker;
    }

    public ModDescriptionPanel getModDescriptionPanel() {
        return modDescriptionPanel;
    }

    
    
    public int getProductId(){
        return Integer.parseInt(fieldId.getText());
    }
    
    public String getProductName(){
        return fieldName.getText();
    }
    
    public BigDecimal getProductPrice(){
        return new BigDecimal(fieldPrice.getText());
    }
    
    public int getProductCatId(){
        return category.getCatID();
    }
    
    public int getProductMakId(){
        return maker.getMakID();
    }
    
    public String getProductDescr(){
        return modDescriptionPanel.getDescText().getText();
    }
    
    public String getProductImg(){
        return new String(fieldId.getText()+".jpg");
    }
    
    public boolean isProductExist(){
        return checkExist.isSelected();
    }
            
    public Maker getMaker() {
        return maker;
    }
    
    public void setMaker(Maker maker) {
        this.maker = maker;
        labelCurrentMaker.setText(maker.getMakName());
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
        labelCurrentCategory.setText(category.getCatName());
    }

    public class ModDescriptionPanel extends DescriptionPanel {

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
            buttonCtegory.addActionListener(selectCategory());
            buttonMaker.addActionListener(selectMaker());
            buttonLoadImg.addActionListener(loadImage());
            buttonDelImg.addActionListener(deleteImage());
        }

        @Override
        protected void addComponents() {
            super.addComponents();
            panelButton.add(buttonLoadImg);
            panelButton.add(buttonDelImg);
            this.panelLeft.add(panelButton, BorderLayout.SOUTH);
        }

        private ActionListener selectMaker() {
            return new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new ProductAddMakerDialog(ProductConfPanel.this.getParentComponent(),
                            ProductConfPanel.this);
                }
            };
        }

        private ActionListener selectCategory() {
            return new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new ProductAddCategoryDialog(ProductConfPanel.this.getParentComponent(),
                            ProductConfPanel.this);
                }
            };
        }

        private ActionListener loadImage() {
            return new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    int i = fileChooser.showOpenDialog(null);

                    if (i == JFileChooser.APPROVE_OPTION) {
                        pathImage = fileChooser.getSelectedFile().getAbsolutePath();
                        ModDescriptionPanel.this.setImage(pathImage);
                    }
                }
            };
        }

        private ActionListener deleteImage() {
            return new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ModDescriptionPanel.this.delImage();
                }
            };
        }
    }

    public static void main(String[] args) {
        JFrame test = new JFrame();
        test.setLayout(new BorderLayout());
        test.add(new ProductConfPanel());
        test.setVisible(true);
    }
}
