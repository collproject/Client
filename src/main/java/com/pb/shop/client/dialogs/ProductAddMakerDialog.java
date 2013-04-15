/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.dialogs;

import com.pb.shop.client.frames.MainFrame;
import com.pb.shop.client.panels.MakerPanel;
import com.pb.shop.client.panels.ProductConfPanel;
import com.pb.shop.model.Maker;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListModel;

/**
 *
 * @author Madness
 */
public class ProductAddMakerDialog extends DefaultDialog{

    private MakerPanel makerPanel;
    private ProductConfPanel confPanel;
    public ProductAddMakerDialog(Component c, ProductConfPanel confPanel) {
        super(c, new MakerPanel(), new Dimension(350,400));
        makerPanel = (MakerPanel) getContentPanel();
        this.confPanel = confPanel;
        makerPanel.setParentComponent(mainFrame);
        configComponents();
        setVisible(true);
    }

    private void configComponents() {
        ListModel model = ((MainFrame) mainFrame).getMakerPanel().getMakerList().getModel();
        makerPanel.getMakerList().setModel(model);
        getButtonOk().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                 Maker maker;
                 maker = (Maker) makerPanel.getMakerList().getSelectedValue();
                 if(maker != null){
                     confPanel.setMaker(maker);
                     ProductAddMakerDialog.this.dispose();
                 }
            }
        });
    }
}
