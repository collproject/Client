/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.models.table;

import com.pb.shop.model.Category;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Madness
 */
public class CategoriesTreeModel extends DefaultMutableTreeNode{
    private List<Category> categories;
    private Map<Integer, Integer> mapParentCategories;

    public CategoriesTreeModel(List<Category> categories) {
        this.categories = categories;
        initMapParentCategories();
        initNodes();
    }

    private void initNodes() {
        
    }

    private void initMapParentCategories() {
        mapParentCategories = new HashMap<Integer, Integer>();
        for (Category c : categories) {
            mapParentCategories.put(c.getCatID(), c.getParentCatID());
        }
    }
}
