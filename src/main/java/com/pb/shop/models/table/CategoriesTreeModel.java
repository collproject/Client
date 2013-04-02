/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.models.table;

import com.pb.shop.client.panels.CategoryPanel;
import com.pb.shop.model.Category;
import java.awt.BorderLayout;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JFrame;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

/**
 *
 * @author Madness
 */
public class CategoriesTreeModel extends DefaultTreeModel {

    private List<Category> categories;
    private Map<Integer, Integer> mapParentCategories;
    private Map<Integer, Category> mapCategoriesById;
    private DefaultMutableTreeNode rootNode;

    public CategoriesTreeModel(List<Category> categories) {
        super(null);
        this.categories = categories;
        initMapParentCategories();
        initMapCategoriesById();
        initNodes();
        setRoot(rootNode);
    }

    private void initNodes() {
        rootNode = new DefaultMutableTreeNode("Все");
        for(Category c : categories){
            if(c.getCatID() == c.getParentCatID()){
                rootNode.add(addNodes(c));
            }
        }
    }
    
    private void initMapCategoriesById() {
        mapCategoriesById = new TreeMap<Integer, Category>();
        for (Category c : categories) {
            mapCategoriesById.put(c.getCatID(), c);
        }
    }

    private void initMapParentCategories() {
        mapParentCategories = new TreeMap<Integer, Integer>();
        for (Category c : categories) {
            mapParentCategories.put(c.getCatID(), c.getParentCatID());
        }
    }

    /*
     * Возвращает все ключи по значинию
     * Используется для получения всех подкатегорий
     */
    private <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
        Set<T> keys = new HashSet<T>();
        for (Entry<T, E> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    private DefaultMutableTreeNode addNodes(Category c) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(c);
        Set<Integer> childrens = getKeysByValue(mapParentCategories, c.getCatID());
        
        
            for(Integer id: childrens){
                if(id != c.getCatID())
                    node.add(addNodes(mapCategoriesById.get(id)));
            }
        
        
        return node;
    }
    
    public static void main(String[] args) {
        List<Category> list = new ArrayList<Category>();
        for (int i = 0; i < 10; i++) {
            Category c = new Category();
            c.setCatID(i);
            c.setCatName("Cat"+i);
            c.setParentCatID(i);
            list.add(c);
        }
        
        Category c = new Category();
            c.setCatID(30);
            c.setCatName("Cat"+30);
            c.setParentCatID(3);
            
         list.add(c);
         
       Category c2 = new Category();
            c2.setCatID(31);
            c2.setCatName("Cat"+31);
            c2.setParentCatID(30);
            
         list.add(c2);
        
        CategoryPanel panel = new CategoryPanel();
        panel.getTree().setModel(new CategoriesTreeModel(list));
        JFrame test = new JFrame();
        test.setLayout(new BorderLayout());
        test.add(panel);
        test.setVisible(true);
    }
}
