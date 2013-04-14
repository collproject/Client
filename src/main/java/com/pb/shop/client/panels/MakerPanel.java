/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.panels;

import com.pb.shop.client.action.ClientSwingWorker;
import com.pb.shop.client.dialogs.MakerConfigDialog;
import com.pb.shop.client.frames.MainFrame;
import com.pb.shop.exception.GeneralException;
import com.pb.shop.exception.ServiceException;
import com.pb.shop.model.Maker;
import com.pb.shop.model.UserBadMessage;
import com.pb.shop.model.UserGoodMessage;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
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
    private JList<Maker> makerList;

    public MakerPanel() {
    }

    public MakerPanel(Component parent) {
        setParentComponent(parent);
    }
    

    @Override
    protected void initComponents() {
        panelButtons = new JPanel();
        panelList = new JPanel();
        buttonAdd = new JButton("Добавить");
        buttonDel = new JButton("Удалить");
        buttonEdit = new JButton("Редактировать");
        scrollPane = new JScrollPane();
        makerList = new JList<Maker>();
    }

    @Override
    protected void configComponents() {
        setLayout(new BorderLayout());
        panelButtons.setLayout( new GridLayout(1, 3, 5, 5));
        setBorder(BorderFactory.createTitledBorder("Производители"));
        panelList.setLayout(new BorderLayout());
        scrollPane = new JScrollPane(makerList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setMinimumSize(new Dimension(40, 40));
        buttonAdd.addActionListener(addNewMaker());
        buttonDel.addActionListener(delMaker());
        buttonEdit.addActionListener(editMaker());
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

    private ActionListener addNewMaker() {
        return new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                         new MakerConfigDialog(getParentComponent());
                    }
                });
            }
        };
    }

    private ActionListener delMaker() {
        return new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new ClientSwingWorker<UserGoodMessage, Void>(getParentComponent()){

                    @Override
                    protected UserGoodMessage doClientQuery() throws Exception {
                        Object response;
                        
                        Maker selectedMaker = makerList.getSelectedValue();
                        if(selectedMaker == null)
                            throw new GeneralException("Производитель не выбран!");
                        
                        response = getClient().deleteMakerById(selectedMaker.getMakID().toString());
                        
                        if(response instanceof UserGoodMessage)
                            return (UserGoodMessage) response;
                        else
                            throw new ServiceException((UserBadMessage) response);
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

    private ActionListener editMaker() {
        return new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                         new MakerConfigDialog(getParentComponent(),
                                 makerList.getSelectedValue());
                    }
                });
            }
        };
    }
    
}
