/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.dialogs;

import com.pb.shop.client.action.ClientSwingWorker;
import com.pb.shop.client.panels.ProductConfPanel;
import com.pb.shop.data.models.ProductsTableModel;
import com.pb.shop.exception.ServiceException;
import com.pb.shop.model.Category;
import com.pb.shop.model.Maker;
import com.pb.shop.model.Product;
import com.pb.shop.model.UserBadMessage;
import com.pb.shop.model.UserGoodMessage;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Madness
 */
public class ProductConfDialog extends DefaultDialog {

    private ProductConfPanel configPanel;
    private Product product;
    private boolean updateProduct = false;
    private boolean statusOk = false;
    private ProductsTableModel tableModel;
    private int indexSelectedProduct;

    public ProductConfDialog(Component c, ProductsTableModel tableModel1) {
        super(c, new ProductConfPanel(), new Dimension(700, 400));
        configPanel = (ProductConfPanel) getContentPanel();
        configPanel.setParentComponent(c);
        configComponents();
        this.tableModel = tableModel1;
        setVisible(true);
    }

    public ProductConfDialog(Component c, Product product, ProductsTableModel tableModel, int index) {
        super(c, new ProductConfPanel(), new Dimension(700, 400));
        configPanel = (ProductConfPanel) getContentPanel();
        configPanel.setParentComponent(c);
        this.product = product;
        configComponents();
        this.tableModel = tableModel;
        this.indexSelectedProduct = index;
        setVisible(true);
    }

    public Product getProduct() {
        if (statusOk) {
            return product;
        } else {
            return null;
        }
    }

    private void configComponents() {
        if (product != null) {
            updateProduct = true;

            configPanel.getFieldId().setText(product.getProdID().toString());
            configPanel.getFieldId().setEditable(false);
            configPanel.getFieldName().setText(product.getProdName());
            configPanel.getFieldPrice().setText(product.getProdPrice().toString());
            configPanel.getCheckExist().setSelected(product.getProdExist());
            configPanel.getModDescriptionPanel().getDescText().setText(product.getProdDescription());

            final int makerId = product.getMakID();
            final int catId = product.getCatID();
            final int prodId = product.getProdID();

            //For Category
            new ClientSwingWorker<Category, Void>(mainFrame) {
                @Override
                protected Category doClientQuery() throws Exception {
                    Object response;
                    response = getClient().getCategoryById(catId);
                    if (response instanceof Category) {
                        return (Category) response;
                    } else {
                        throw new ServiceException((UserBadMessage) response);
                    }
                }

                @Override
                protected void doneQuery() {
                    Category cat = getResponse();
                    configPanel.setCategory(cat);
                    configPanel.getLabelCurrentCategory()
                            .setText(cat.getCatName());
                }
            }.execute();

            //For Maker
            new ClientSwingWorker<Maker, Void>(mainFrame) {
                @Override
                protected Maker doClientQuery() throws Exception {
                    Object response;
                    response = getClient().getMakerById(makerId);
                    if (response instanceof Maker) {
                        return (Maker) response;
                    } else {
                        throw new ServiceException((UserBadMessage) response);
                    }
                }

                @Override
                protected void doneQuery() {
                    Maker mak = getResponse();
                    configPanel.setMaker(mak);
                    configPanel.getLabelCurrentMaker()
                            .setText(mak.getMakName());
                }
            }.execute();

            //For Image
            new ClientSwingWorker<String, Void>(mainFrame) {
                @Override
                protected String doClientQuery() throws Exception {

                    String path = getClient().getUrlProductImage(prodId);
                    configPanel.getModDescriptionPanel()
                            .setUrlImage(path);
                    return path;
                }

                @Override
                protected void doneQuery() {
                    getResponse();
                }
            }.execute();

        }
        getButtonOk().addActionListener(sendNewData());
    }

    private void fillProduct() {
        product = new Product();
        product.setProdID(configPanel.getProductId());
        product.setProdName(configPanel.getProductName());
        product.setProdPrice(configPanel.getProductPrice());
        product.setProdDescription(configPanel.getProductDescr());
        product.setProdExist(configPanel.isProductExist());
        product.setProdImg(configPanel.getProductImg());
        product.setCatID(configPanel.getCategory().getCatID());
        product.setMakID(configPanel.getMaker().getMakID());
    }

    private ActionListener sendNewData() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fillProduct();

                //For Image
                if (!configPanel.getModDescriptionPanel().isImgIsUrl()) {
                    new ClientSwingWorker<UserGoodMessage, Void>(mainFrame) {
                        @Override
                        protected UserGoodMessage doClientQuery() throws Exception {

                            Object response = null;

                            if (updateProduct) {
                                if (configPanel.getModDescriptionPanel().getImgPath() == null) {
                                    try {
                                        response = getClient().delProductImage(product.getProdID());
                                    } catch (Exception ex) {
                                        //Если изображение не удалилось значит его не было
                                    }

                                } else {
                                    response = getClient()
                                            .uploadProductImage(product.getProdID().toString(),
                                            configPanel.getModDescriptionPanel().getImgPath());
                                }
                                //Если добавляем новый продукт
                            } else {
                                if (configPanel.getModDescriptionPanel().getImgPath() != null) {
                                    response = getClient()
                                            .uploadProductImage(product.getProdID().toString(),
                                            configPanel.getModDescriptionPanel().getImgPath());
                                }
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
                            ProductConfDialog.this.dispose();
                            JOptionPane.showMessageDialog(mainFrame, message.getMessage(), "Подтверждение",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }.execute();
                }
                //For Product
                new ClientSwingWorker<UserGoodMessage, Void>(mainFrame) {
                    @Override
                    protected UserGoodMessage doClientQuery() throws Exception {
                        Object response;
                        if (updateProduct) {
                            response = getClient().updateProduct(product);
                        } else {
                            response = getClient().addProduct(product);

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
                        if(updateProduct){
                            tableModel.addProduct(indexSelectedProduct, product);
                        } else{
                            tableModel.addProduct(product);
                        }
                        ProductConfDialog.this.dispose();
                        JOptionPane.showMessageDialog(mainFrame, message.getMessage(), "Подтверждение",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }.execute();
            }
        };
    }
}
