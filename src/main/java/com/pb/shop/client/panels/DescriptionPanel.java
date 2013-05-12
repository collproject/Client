/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.panels;

import com.pb.shop.exception.GeneralException;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
    protected JPanel panelLeft;
    private String imgPath;
    private boolean imgIsUrl;

    public DescriptionPanel() {
    }

    public DescriptionPanel(Component c) {
        setParentComponent(c);
    }

    @Override
    protected void initComponents() {
        imgLabel = new JLabel();
        descText = new JTextArea();
        jsp = new JSplitPane();
        imageIcon = new ImageIcon();
//        imageIcon = new ImageIcon("D:\\test.png");
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
        imgLabel.setMinimumSize(new Dimension(100, 100));
        //imgLabel.setSize(new Dimension(200, 100));


        //Подгон размера изображения под размер JLabel
        imgLabel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                resizeImage();
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

    public void setImage(String path) {
        this.imgPath = path;
        this.imgIsUrl = false;
        imageIcon = new ImageIcon(path);
        resizeImage();
    }
    public void delImage(){
        this.imgPath = null;
        this.imgIsUrl = false;
        imageIcon = new ImageIcon();
        resizeImage();
    }
    public void setUrlImage(String url) throws GeneralException {
        this.imgPath = url;
        this.imgIsUrl = true;

            try {
                //imageIcon = new ImageIcon(new URL(url));
                imageIcon = new ImageIcon(ImageIO.read( new URL(url)));
            } catch (IOException ex) {
                Logger.getLogger(DescriptionPanel.class.getName()).log(Level.SEVERE, null, ex);
                throw new GeneralException(ex);
            }
            imgLabel.setIcon(imageIcon);
            
        resizeImage();
    }

    public String getImgPath() {
        return imgPath;
    }

    public boolean isImgIsUrl() {
        return imgIsUrl;
    }

    public JTextArea getDescText() {
        return descText;
    }

    public static void main(String[] args) {
            JFrame test = new JFrame();
            test.setLayout(new BorderLayout());
            DescriptionPanel dp = new DescriptionPanel();
            test.add(dp);
    }

    /**
     * Подгон размера изображения под размер JLabel
     */
    private void resizeImage() {

        JLabel label = imgLabel;
        int w, h;
        w = label.getWidth();
        h = label.getHeight();

        if (w <= 0 || h <= 0) {
            return;
        }

        Image srcImg = imageIcon.getImage();
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        label.setIcon(new ImageIcon(resizedImg));
    }
}
