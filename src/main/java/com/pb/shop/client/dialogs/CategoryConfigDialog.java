/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.dialogs;

import com.pb.shop.client.action.ClientSwingWorker;
import com.pb.shop.client.panels.CategoryConfigPanel;
import com.pb.shop.client.panels.MakerConfigPanel;
import com.pb.shop.exception.ServiceException;
import com.pb.shop.model.Category;
import com.pb.shop.model.UserBadMessage;
import com.pb.shop.model.UserGoodMessage;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Madness
 */
public class CategoryConfigDialog extends DefaultDialog {

    private CategoryConfigPanel configPanel;
    private Category category;
    private boolean updateCategory = false;
    private boolean statusOk = false;
    private List<Category> categories;

    public CategoryConfigDialog(Component c, List<Category> categories) {
        super(c, new CategoryConfigPanel(c, categories), new Dimension(300, 200));
        configPanel = (CategoryConfigPanel) getContentPanel();
        this.categories = categories;
        configComponents();
        setVisible(true);
    }

    public CategoryConfigDialog(Component c, List<Category> categories, Category category) {
        super(c, new CategoryConfigPanel(c, categories), new Dimension(300, 200));
        configPanel = (CategoryConfigPanel) getContentPanel();
        this.categories = categories;
        this.category = category;
        configComponents();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CategoryConfigDialog(null, null);
            }
        });
    }

    private void configComponents() {
        if (category != null) {
            updateCategory = true;
            configPanel.getFieldId().setText(category.getCatID().toString());
            configPanel.getFieldId().setEditable(false);
            configPanel.getFieldName().setText(category.getCatName());
            configPanel.getComboBoxCategories().setSelectedItem(category);
        }
        getButtonOk().addActionListener(sendNewData());
    }

    private void fillCategory() {
        category = new Category();
        category.setCatID(Integer.parseInt(configPanel.getFieldId().getText()));
        category.setCatName(configPanel.getFieldName().getText());

        if (configPanel.getParentCategory() == null) {
            category.setParentCatID(category.getCatID());
        } else {
            category.setParentCatID(configPanel.getParentCategory().getCatID());
        }
    }

    private ActionListener sendNewData() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fillCategory();
                new ClientSwingWorker<UserGoodMessage, Void>(mainFrame) {
                    @Override
                    protected UserGoodMessage doClientQuery() throws Exception {
                        Object response;
                        if (updateCategory) {
                            response = getClient().updateCategory(category);
                        } else {
                            response = getClient().addCategory(category);

                        }
                        if (response instanceof UserGoodMessage) {
                            return (UserGoodMessage) response;
                        } else {
                            throw new ServiceException((UserBadMessage) response);
                        }

                    }

                    @Override
                    protected void doneQuery() {
                        UserGoodMessage message = getResponse();
                        statusOk = true;
                        CategoryConfigDialog.this.dispose();
                        JOptionPane.showMessageDialog(mainFrame, message.getMessage(), "Подтверждение",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }.execute();
            }
        };
    }
}
