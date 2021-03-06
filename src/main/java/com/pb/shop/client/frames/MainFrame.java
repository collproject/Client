/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.frames;


import com.pb.shop.client.action.AddProductController;
import com.pb.shop.client.action.ClientSwingWorker;
import com.pb.shop.client.action.EditProductController;
import com.pb.shop.client.action.MenuConfigController;
import com.pb.shop.client.action.MenuConnectController;
import com.pb.shop.client.action.SearchController;
import com.pb.shop.client.panels.CategoryPanel;
import com.pb.shop.client.panels.DescriptionPanel;
import com.pb.shop.client.panels.MainMenu;
import com.pb.shop.client.panels.MakerPanel;
import com.pb.shop.client.panels.ProgressPanel;
import com.pb.shop.client.panels.ResultPanel;
import com.pb.shop.client.panels.SearchPanel;
import com.pb.shop.data.models.ProductsTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Madness
 */
public class MainFrame extends JFrame {

    
    private JPanel panelLeft;
    private JPanel panelRight;
    private JPanel panelUp;
    private JPanel panel;
    
    private CategoryPanel categoryPanel;
    private DescriptionPanel descriptionPanel;
    private MakerPanel makerPanel;
    private ProgressPanel progressPanel;
    private ResultPanel resultPanel;
    private SearchPanel searchPanel;

    public DescriptionPanel getDescriptionPanel() {
        return descriptionPanel;
    }

    public ProgressPanel getProgressPanel() {
        return progressPanel;
    }

    public SearchPanel getSearchPanel() {
        return searchPanel;
    }
    
    private MainMenu mainMenu;
    
    private SearchController searchController;
    private AddProductController addProductController;
    private EditProductController editProductController;
    private MenuConnectController menuConnectController;
    
    private JSplitPane jspLeft;
    private JSplitPane jspRight;
    private JSplitPane jsp;
    
    public MainFrame() {
        super("Управление товарами");
        //setMinimumSize(new Dimension(800, 600));
        setSize(new Dimension(900,500));
        initComponents();
        configComponents();
        addComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);        
    }

    private void initComponents() {
        panelLeft = new JPanel(new BorderLayout());
        panelRight = new JPanel(new BorderLayout());
        
        categoryPanel = new CategoryPanel(this);
        descriptionPanel = new DescriptionPanel();
        makerPanel = new MakerPanel(this);
        progressPanel = new ProgressPanel();
        resultPanel = new ResultPanel();
        searchPanel = new SearchPanel();
        panel = new JPanel(new BorderLayout());
        panelUp = new JPanel(new BorderLayout());
        mainMenu = new MainMenu();
        
        searchController = new SearchController(this);
        addProductController = new AddProductController(this);
        editProductController = new EditProductController(this);
        menuConnectController = new MenuConnectController(this);
        
        jspLeft = new JSplitPane();
        jspRight = new JSplitPane();
        jsp = new JSplitPane();
    }

    private void configComponents() {
        searchPanel.getSearchButton().addActionListener(searchController);
        resultPanel.getAddButton().addActionListener(addProductController);
        resultPanel.getEditButton().addActionListener(new EditProductController(this));
        mainMenu.getItemConnect().addActionListener(menuConnectController);
        mainMenu.getItemConfig().addActionListener(new MenuConfigController(this));
        mainMenu.getItemExit().addActionListener(actionExit());
        
        categoryPanel.setMinimumSize(new Dimension(100, 100));
        makerPanel.setMinimumSize(new Dimension(100, 100));
        panelRight.setMinimumSize(new Dimension(100, 100));
        
        jspLeft.setDividerSize(3);
        jspRight.setDividerSize(3);
        jsp.setDividerSize(3);
        
        jspLeft.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jspRight.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jsp.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        
        jspLeft.setResizeWeight(0.5);
        jsp.setResizeWeight(0.2);
        
        resultPanel.getResultTable().getSelectionModel().addListSelectionListener(selectTableRow());
        
    }

    private void addComponents() {
        jspLeft.setLeftComponent(categoryPanel);
        jspLeft.setRightComponent(makerPanel);
        
        panelLeft.add(jspLeft);
        
        panelUp.add(searchPanel, BorderLayout.NORTH);
        panelUp.add(descriptionPanel, BorderLayout.CENTER);
        
        jspRight.setLeftComponent(panelUp);
        jspRight.setRightComponent(resultPanel);
        
        panelRight.add(jspRight);
        
        jsp.setLeftComponent(panelLeft);
        jsp.setRightComponent(panelRight);
               
        panel.add(jsp, BorderLayout.CENTER);
        panel.add(progressPanel, BorderLayout.SOUTH);
        
        setContentPane(panel);
        setJMenuBar(mainMenu);
    }

    public ResultPanel getResultPanel() {
        return resultPanel;
    }
    
    public MakerPanel getMakerPanel(){
        return makerPanel;
    }
    public CategoryPanel getCategoryPanel() {
        return categoryPanel;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MainFrame();
            }
        });
    }

    private ListSelectionListener selectTableRow() {
        return new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                final int selectedRow = resultPanel.getResultTable().getSelectedRow();
                if(selectedRow == -1) return;
                        
                final ProductsTableModel model = 
                        (ProductsTableModel) resultPanel.getResultTable().getModel();
                
                new ClientSwingWorker<String, Void>(MainFrame.this){

                    @Override
                    protected String doClientQuery() throws Exception {
                        String url = getClient()
                                .getUrlProductImage(model.getProduct(selectedRow).getProdID());
                        descriptionPanel.setUrlImage(url);
                        return url;
                        
                    }

                    @Override
                    protected void doneQuery() {
                        getResponse();
                        descriptionPanel.getDescText()
                                .setText(model.getProduct(selectedRow)
                                .getProdDescription());
                    }
                    
                }.execute();
            }
        };
    }

    private ActionListener actionExit() {
        return new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                MainFrame.this.dispose();
            }
        };
    }
}
