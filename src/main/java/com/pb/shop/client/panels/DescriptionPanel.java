/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

/**
 *
 * @author Дмитрий
 */
public class DescriptionPanel extends AbstractPanel {

    private JLabel imgLabel;
    private JTextArea descText;
    private JSplitPane jsp;
    private ImageIcon imageIcon;
    JPanel panelLeft;

    @Override
    protected void initComponents() {
        imgLabel = new JLabel();
        descText = new JTextArea();
        jsp = new JSplitPane();
        imageIcon = new ImageIcon("D:\\test.png");
//        try {
//            imageIcon = new ImageIcon(new URL("http://localhost:7375/shop-app-server/admin/photo2"));
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(DescriptionPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
        panelLeft = new JPanel();
    }

    @Override
    protected void configComponents() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Описание товара"));
        panelLeft.setLayout(new BorderLayout());
        imgLabel.setIcon(imageIcon);
        jsp.setDividerSize(3);
        imgLabel.setMinimumSize( new Dimension(100, 100));
        descText.setText("Description");
        //imgLabel.setSize(new Dimension(200, 100));
        
        
        //Подгон размера изображения под размер JLabel
        imgLabel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e); 
                
                JLabel label = (JLabel) e.getSource();
                int w, h;
                w = label.getWidth();
                h = label.getHeight();
                
                if(w <= 0 || h <= 0) return;
                
                Image srcImg = imageIcon.getImage();
                BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2 = resizedImg.createGraphics();
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2.drawImage(srcImg, 0, 0, w, h, null);
                g2.dispose();
      
                label.setIcon(new ImageIcon(resizedImg));
            }
        });
    }

    @Override
    protected void addComponents() {
        panelLeft.add(imgLabel);
        jsp.setLeftComponent(panelLeft);
        jsp.setRightComponent(descText);
        add(jsp, BorderLayout.CENTER);
    }

    public JLabel getImgLabel() {
        return imgLabel;
    }

    public JTextArea getDescText() {
        return descText;
    }
    public static void main(String[] args) {
        JFrame test = new JFrame();
        test.setLayout(new BorderLayout());
        test.add(new DescriptionPanel());
        test.setVisible(true);
    }
}
