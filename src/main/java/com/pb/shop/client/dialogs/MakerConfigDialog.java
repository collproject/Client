/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.dialogs;

import com.pb.shop.client.panels.MakerConfigPanel;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Madness
 */
public class MakerConfigDialog extends DefaultDialog {

    private MakerConfigPanel configPanel;

    public MakerConfigDialog(Component c) {
        super(c, new MakerConfigPanel(), new Dimension(300, 150));
        configPanel = (MakerConfigPanel) getContentPanel();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MakerConfigDialog(null);
            }
        });
    }
}
