/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.data.models;

import com.pb.shop.model.Maker;
import com.pb.shop.model.MakersList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author Дмитрий
 */
public class MakersJListModel extends AbstractListModel<Maker> {

    private List<Maker> list;

    public MakersJListModel(List<Maker> list) {
        this.list = list;
    }
    
    public int getSize() {
        return list.size();
    }

    public Maker getElementAt(int index) {
       return list.get(index);
    }

    public List<Maker> getList() {
        return list;
    }
    
    public void update(){
       fireContentsChanged(this, 0, list.size()-1); 
    }
    
    public void setList(List<Maker> list) {
        this.list = list;
        update();
    }

}
