/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.border.Border;

/**
 *
 * @author Madness
 */
public class MakerPanel extends AbstractPanel{
    
    private JPanel panelList;
    private JPanel panelButtons;
    private JButton buttonDel;
    private JButton buttonAdd;
    private JButton buttonEdit;
    private JScrollPane scrollPane;
    private JList makerList;

    @Override
    protected void initComponents() {
        panelButtons = new JPanel();
        panelList = new JPanel();
        buttonAdd = new JButton("Добавить");
        buttonDel = new JButton("Удалить");
        buttonEdit = new JButton("Редактировать");
        scrollPane = new JScrollPane();
        makerList = new JList(new String[]{"Nokia","LG","Fly","Samsung","Asus"});
        //makerList = new JList();
    }

    @Override
    protected void configComponents() {
        setLayout(new BorderLayout());
        panelButtons.setLayout( new GridLayout(1, 3, 5, 5));
        setBorder(BorderFactory.createTitledBorder("Производители"));
        panelList.setLayout(new BorderLayout());
        scrollPane = new JScrollPane(makerList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    @Override
    protected void addComponents() {
        panelButtons.add(buttonDel);
        panelButtons.add(buttonEdit);
        panelButtons.add(buttonAdd);
        panelList.add(scrollPane);
        add(panelList, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
    }
    public static void main(String[] args) {
        JFrame test = new JFrame();
        test.setLayout(new BorderLayout());
        test.add(new MakerPanel());
        test.setVisible(true);
    }

    public JButton getButtonDel() {
        return buttonDel;
    }

    public JButton getButtonAdd() {
        return buttonAdd;
    }

    public JButton getButtonEdit() {
        return buttonEdit;
    }

    public JList getMakerList() {
        return makerList;
    }
    
}
