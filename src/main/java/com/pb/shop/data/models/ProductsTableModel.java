/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.data.models;

import com.pb.shop.model.Category;
import com.pb.shop.model.Maker;
import com.pb.shop.model.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Дмитрий
 */
public class ProductsTableModel extends AbstractTableModel {

    private List<Product> products;
    private List<Maker> makers;
    private List<Category> categories;
    private Map<Integer, String> makersNames;
    private Map<Integer, String> categoriesNames;
    private static String [] columnsNames = new String[]{"ИН", "Название", "Категория", "Производитель", "Цена", "Наличие"}; 

    public ProductsTableModel(List<Product> products, List<Maker> makers, List<Category> categorys) {
        this.products = products;
        this.makers = makers;
        this.categories = categorys;
        makersNames = new HashMap<Integer, String>();
        categoriesNames=new HashMap<Integer, String>();
        initmakersNames();
        initcategorysNames();

    }

    private void initmakersNames() {
        for (Maker m : makers) {
            makersNames.put(m.getMakID(), m.getMakName());
        }
    }

    private void initcategorysNames() {
        for (Category c : categories) {
            categoriesNames.put(c.getCatID(), c.getCatName());
        }
    }

    public int getRowCount() {
        return products.size();
    }

    public int getColumnCount() {
        return 6;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return products.get(rowIndex).getProdID();
            case 1:
                return products.get(rowIndex).getProdName();
            case 2:
                return categoriesNames.get(products.get(rowIndex).getCatID());
            case 3:
                return makersNames.get(products.get(rowIndex).getMakID());
            case 4:
                return products.get(rowIndex).getProdPrice();
            case 5:
                return products.get(rowIndex).getProdExist().toString();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnsNames[column];
    }
    
    
}

