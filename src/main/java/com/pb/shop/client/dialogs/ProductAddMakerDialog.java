/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.dialogs;

import com.pb.shop.client.panels.MakerPanel;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Madness
 */
public class ProductAddMakerDialog extends ProductAddDialog{

    private MakerPanel makerPanel;
    public ProductAddMakerDialog(JComponent c) {
        super(c, new MakerPanel());
        makerPanel = (MakerPanel) getContentPanel();
    }
}
