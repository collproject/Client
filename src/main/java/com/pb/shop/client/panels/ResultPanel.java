/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
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

        tablePanel = new JScrollPane(resultTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    @Override
    protected void addComponents() {
        buttonsPanel.add(dellButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(addButton);

        add(tablePanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
//        add(dellButton, BorderLayout.SOUTH);
//        add(editButton, BorderLayout.SOUTH);
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
    
}
