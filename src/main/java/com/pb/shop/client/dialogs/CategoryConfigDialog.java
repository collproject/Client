/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.dialogs;

import com.pb.shop.client.panels.CategoryConfigPanel;
import com.pb.shop.client.panels.MakerConfigPanel;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Madness
 */
public class CategoryConfigDialog extends DefaultDialog{
    
    private CategoryConfigPanel configPanel;

    public CategoryConfigDialog(Component c) {
        super(c, new CategoryConfigPanel(), new Dimension(300, 200));
        configPanel = (CategoryConfigPanel) getContentPanel();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CategoryConfigDialog(null);
            }
        });
    }
}
