/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.panels;

import com.pb.shop.client.action.ClientSwingWorker;
import com.pb.shop.data.models.ProductsTableModel;
import com.pb.shop.exception.GeneralException;
import com.pb.shop.exception.ServiceException;
import com.pb.shop.model.Product;
import com.pb.shop.model.UserBadMessage;
import com.pb.shop.model.UserGoodMessage;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Дмитрий
 */
public class ResultPanel extends AbstractPanel {

    private JButton dellButton;
    private JButton addButton;
    private JButton editButton;
    private JTable resultTable;
    private JPanel buttonsPanel;
    private JScrollPane tablePanel;

    public ResultPanel() {
    }

    public ResultPanel(Component c) {
        setParentComponent(c);
    }
    
    

    @Override
    protected void initComponents() {
        dellButton = new JButton();
        addButton = new JButton();
        editButton = new JButton();
        resultTable = new JTable();

        buttonsPanel = new JPanel();

    }

    @Override
    protected void configComponents() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Результат поиска:"));
        buttonsPanel.setLayout(new GridLayout(1, 3, 5, 5));
        dellButton.setText("Удалить");
        addButton.setText("Добавить");
        editButton.setText("Редактировать");

        DefaultTableModel dtm = new DefaultTableModel(new Object[]{"ИД", "Название",
            "Производитель", "Категория", "Наличие"}, 5);

        resultTable.setModel(dtm);

        tablePanel = new JScrollPane(resultTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        dellButton.addActionListener(delProduct());
    }

    @Override
    protected void addComponents() {
        buttonsPanel.add(dellButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(addButton);

        add(tablePanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    public JButton getDellButton() {
        return dellButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JTable getResultTable() {
        return resultTable;
    }
    
    public Product getSelectedProduct(){
        int row = resultTable.getSelectedRow();
        if(row < 0) return null;
        return ((ProductsTableModel) resultTable.getModel()).getProduct(row);
    }

    public int getSelectedIndex(){
        return resultTable.getSelectedRow();
    }
    private ActionListener delProduct() {
         return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ClientSwingWorker<UserGoodMessage, Void>(getParentComponent()) {
                    @Override
                    protected UserGoodMessage doClientQuery() throws Exception {
                        Object response;

                        Product selectedProduct = getSelectedProduct();
                        if (selectedProduct == null) {
                            throw new GeneralException("Продукт не выбран!");
                        }

                        response = getClient()
                                .deleteProductById(selectedProduct
                                .getProdID().toString());
                        
                        //Удаляем изображение
                        try{
                            getClient().delProductImage(selectedProduct.getProdID());
                        } catch(Exception ex){
                            //Если изображение не удалилось значит его не было
                        }

                        if (response instanceof UserGoodMessage) {                           
                            ProductsTableModel model = (ProductsTableModel) resultTable.getModel();
                            model.deleteProduct(selectedProduct);
                            return (UserGoodMessage) response;
                        } else {
                            throw new ServiceException((UserBadMessage) response);
                        }
                    }

                    @Override
                    protected void doneQuery() {
                        UserGoodMessage message = getResponse();
                        JOptionPane.showMessageDialog(getParentComponent(),
                                message.getMessage(), "Подтверждение",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }.execute();
            }
        };
    }
}
