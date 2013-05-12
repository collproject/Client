/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.panels;

import com.pb.shop.client.action.ClientSwingWorker;
import com.pb.shop.client.dialogs.CategoryConfigDialog;
import com.pb.shop.data.models.CategoriesTreeModel;
import com.pb.shop.exception.GeneralException;
import com.pb.shop.exception.ServiceException;
import com.pb.shop.model.Category;
import com.pb.shop.model.UserBadMessage;
import com.pb.shop.model.UserGoodMessage;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeModelEvent;
import javax.swing.tree.DefaultMutableTreeNode;
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

    public List<Category> getCategories() {
        return ((CategoriesTreeModel) tree.getModel()).getCategories();
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
        tree = new JTree(new CategoriesTreeModel(null));
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
        tree.addMouseListener(deselect());

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

    public Category getSelectedCategory() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (node == null) {
            return null;
        }
        Object nodeInfo = node.getUserObject();
//        if (node.isLeaf()) {
//            return (Category) nodeInfo;
//        } else {
//            return null;
//        }
        return (Category) nodeInfo;
    }

    public JTree getTree() {
        return tree;
    }

    public void setTreeModel(CategoriesTreeModel model) {
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
                        List<Category> categories = ((CategoriesTreeModel) tree.getModel()).getCategories();
                        Category category = new CategoryConfigDialog(getParentComponent(),
                                categories).getCategory();
                        if (category != null) {
                            CategoriesTreeModel model = (CategoriesTreeModel) tree.getModel();

                            if (categories == null) {
                                categories = new ArrayList<Category>();
                            }

                            categories.add(category);
                            model.setCategories(categories);
                            CategoryPanel.this.setTreeModel(model);
                        }
                    }
                });
            }
        };
    }

    private ActionListener delCategory() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ClientSwingWorker<UserGoodMessage, Void>(getParentComponent()) {
                    @Override
                    protected UserGoodMessage doClientQuery() throws Exception {
                        Object response;

                        Category selectedCategory = getSelectedCategory();
                        if (selectedCategory == null) {
                            throw new GeneralException("Категория не выбрана!");
                        }

                        response = getClient().deleteCategoryById(selectedCategory.getCatID().toString());

                        if (response instanceof UserGoodMessage) {
                            //Обновление отображения элементов в дереве
                            CategoriesTreeModel model = (CategoriesTreeModel) tree.getModel();
                            List<Category> list = model.getCategories();
                            list.remove(selectedCategory);
                            model.setCategories(list);
                            setTreeModel(model);
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

    private ActionListener editCategory() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        List<Category> parentCategories = ((CategoriesTreeModel) tree.getModel())
                                .getParentCategories(getSelectedCategory());
                        Category category = new CategoryConfigDialog(getParentComponent(),
                                parentCategories, getSelectedCategory()).getCategory();

                        if (category != null) {
                            CategoriesTreeModel model = (CategoriesTreeModel) tree.getModel();

                            List<Category> categories = ((CategoriesTreeModel) tree.getModel())
                                    .getCategories();

                            categories.remove(getSelectedCategory());
                            categories.add(category);

                            model.setCategories(categories);
                            CategoryPanel.this.setTreeModel(model);
                        }
                    }
                });
            }
        };
    }

    private MouseListener deselect() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    CategoryPanel.this.tree.clearSelection();
                }
            }
        };
    }
}
