/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

/**
 *
 * @author Madness
 */
public class CategoryPanel extends AbstractPanel{

    private JTree tree;
    private JButton buttonDel;
    private JButton buttonAdd;
    private JButton buttonEdit;
    JPanel panelButtons;
    JPanel panelTree;
    JScrollPane scrollPane;
    
    public CategoryPanel() {
        initComponents();
        configComponents();
        addComponents();
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        JFrame test = new JFrame();
        test.setLayout(new BorderLayout());
        test.add(new CategoryPanel());
        test.setVisible(true);
    }

    @Override
    protected void initComponents() {
        tree = new JTree();
        buttonAdd = new JButton("Добавить");
        buttonDel = new JButton("Удалить");
        buttonEdit = new JButton("Редактировать");
        panelButtons = new JPanel();
        panelTree = new JPanel();
    }

    @Override
    protected void addComponents() {
        panelButtons.add(buttonDel);
        panelButtons.add(buttonEdit);
        panelButtons.add(buttonAdd);
        panelTree.add(scrollPane);
        add(panelTree);
        add(panelButtons, BorderLayout.SOUTH);
    }

    @Override
    protected void configComponents() {
        //panelButtons.setLayout( new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelButtons.setLayout( new GridLayout(1, 3, 5, 5));
        setBorder(BorderFactory.createTitledBorder("Категории"));
        panelTree.setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        scrollPane = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    public JTree getTree() {
        return tree;
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
    
}
