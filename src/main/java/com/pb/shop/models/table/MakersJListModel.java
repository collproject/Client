/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.models.table;

import com.pb.shop.model.Maker;
import com.pb.shop.model.MakersList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author Дмитрий
 */
public class MakersJListModel extends AbstractListModel<String> {

    private List<Maker> list;

    public MakersJListModel(List<Maker> list) {
        this.list = list;
    }
    
    public int getSize() {
        return list.size();
    }

    public String getElementAt(int index) {
       return list.get(index).getMakName();
    }

}
