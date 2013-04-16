/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.panels;

import com.pb.shop.data.models.CategoriesTreeModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.Enumeration;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeModelEvent;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Madness
 */
public class CategoryPanel extends AbstractPanel {

    private JTree tree;
    private JButton buttonDel;
    private JButton buttonAdd;
    private JButton buttonEdit;
    JPanel panelButtons;
    JPanel panelTree;
    JScrollPane scrollPane;

    public CategoryPanel() {
    }

    public CategoryPanel(Component c) {
        setParentComponent(c);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
        panelButtons.setLayout(new GridLayout(1, 3, 5, 5));
        setBorder(BorderFactory.createTitledBorder("Категории"));
        panelTree.setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        scrollPane = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setMinimumSize(new Dimension(50, 50));
        tree.setRootVisible(false);
        
        buttonAdd.addActionListener(addNewCategory());
        buttonDel.addActionListener(delCategory());
        buttonEdit.addActionListener(editCategory());
    }

    /*
     * Разворачивает все узлы дерева
     */
    public void expandAll(JTree tree) {
        TreeNode root = (TreeNode) tree.getModel().getRoot();
        expandAll(tree, new TreePath(root));
    }

    private void expandAll(JTree tree, TreePath parent) {
        TreeNode node = (TreeNode) parent.getLastPathComponent();
        if (node.getChildCount() >= 0) {
            for (Enumeration e = node.children(); e.hasMoreElements();) {
                TreeNode n = (TreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandAll(tree, path);
            }
        }
        tree.expandPath(parent);
        // tree.collapsePath(parent); для закрытия узла
    }
    
    public JTree getTree() {
        return tree;
    }
    
    public void setTreeModel(CategoriesTreeModel model){
        tree.setModel(model);
        expandAll(tree);
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

    private ActionListener addNewCategory() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
//                        Category category = new CategoryConfigDialog(getParentComponent()).getMaker();
//                        if (category != null) {
//                            ((MakersJListModel) makerList.getModel()).getList().add(maker);
//                            ((MakersJListModel) makerList.getModel()).update();
//                        }
                    }
                });
            }
        };
    }

    private ActionListener delCategory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private ActionListener editCategory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
