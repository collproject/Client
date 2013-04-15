/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.dialogs;

import com.pb.shop.client.action.ClientSwingWorker;
import com.pb.shop.client.frames.MainFrame;
import com.pb.shop.client.panels.MakerConfigPanel;
import com.pb.shop.exception.GeneralException;
import com.pb.shop.exception.ServiceException;
import com.pb.shop.model.Maker;
import com.pb.shop.model.UserBadMessage;
import com.pb.shop.model.UserGoodMessage;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author Madness
 */
public class MakerConfigDialog extends DefaultDialog {

    private MakerConfigPanel configPanel;
    private Maker maker;
    private boolean updateMaker = false;
    private boolean statusOk = false;

    public MakerConfigDialog(Component c) {
        super(c, new MakerConfigPanel(), new Dimension(300, 220));
        configPanel = (MakerConfigPanel) getContentPanel();
        configComponents();
        setVisible(true);
    }

    public MakerConfigDialog(Component c, Maker maker) {
        super(c, new MakerConfigPanel(), new Dimension(300, 220));
        configPanel = (MakerConfigPanel) getContentPanel();
        this.maker = maker;
        configComponents();
        setVisible(true);
    }

    public Maker getMaker() {
        if(statusOk)
            return maker;
        else
            return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Maker maker = new Maker(123, "Nokia");
                maker.setMakDescr("sdfsdfsefsxvsdfdsfsgse");
                new MakerConfigDialog(null, maker);
            }
        });
    }

    private void configComponents() {
        if (maker != null) {
            updateMaker = true;
            configPanel.getFieldId().setText(maker.getMakID().toString());
            configPanel.getFieldId().setEditable(false);
            configPanel.getFieldName().setText(maker.getMakName());
            if (maker.getMakDescr() != null) {
                configPanel.getTextAreaDescr().setText(maker.getMakDescr());
            }           
        }
        getButtonOk().addActionListener(sendNewData());
    }
    
    private void fillMaker() {
        maker = new Maker(); 
        maker.setMakID(Integer.parseInt(configPanel.getFieldId().getText()));
        maker.setMakName(configPanel.getFieldName().getText());
        maker.setMakDescr(configPanel.getTextAreaDescr().getText());
    }

    private ActionListener sendNewData() {
        return new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                fillMaker();
                new ClientSwingWorker<UserGoodMessage, Void>(mainFrame){

                    @Override
                    protected UserGoodMessage doClientQuery() throws Exception {
                        Object response;
                        if(updateMaker){
                            response = getClient().updateMaker(maker);
                        }else{
                            response = getClient().addMaker(maker);
                            
                        }
                        if(response instanceof UserGoodMessage)
                            return (UserGoodMessage) response;
                        else{
                            throw new ServiceException((UserBadMessage) response);
                        }
                            
                    }

                    @Override
                    protected void doneQuery() {
                            UserGoodMessage message = getResponse();
                            statusOk = true;
                            MakerConfigDialog.this.dispose();
                            JOptionPane.showMessageDialog(mainFrame, message.getMessage(), "Подтверждение",
                                    JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                }.execute();
            }
        };
    }
}
