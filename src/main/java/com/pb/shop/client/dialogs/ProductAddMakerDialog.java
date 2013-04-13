/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.dialogs;

import com.pb.shop.client.panels.MakerPanel;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Madness
 */
public class ProductAddMakerDialog extends DefaultDialog{

    private MakerPanel makerPanel;
    public ProductAddMakerDialog(Component c) {
        super(c, new MakerPanel(), new Dimension(350,400));
        makerPanel = (MakerPanel) getContentPanel();
        setVisible(true);
    }
}
