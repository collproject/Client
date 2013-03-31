/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client.panels;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Madness
 */
public class MainMenu extends JMenuBar{

    private JMenu bar;
    private JMenuItem itemConnect;
    private JMenuItem itemExit;
    private JMenuItem itemConfig;
    
    public MainMenu() {
        initComponents();
        configComponents();
        addComponents();
    }

    private void initComponents() {
        bar = new JMenu("Главное меню");
        itemConfig = new JMenuItem("Настройки соединения");
        itemExit = new JMenuItem("Выход");
        itemConnect = new JMenuItem("Подключиться");
    }

    private void configComponents() {
    }

    private void addComponents() {
        bar.add(itemConnect);
        bar.add(itemConfig);
        bar.add(itemExit);
        add(bar);
    }
    public static void main(String[] args) {
        JFrame test = new JFrame();
        test.setLayout(new BorderLayout());
        test.add(new ProgressPanel());
        test.setJMenuBar(new MainMenu());
        test.setVisible(true);
    }

    public JMenuItem getItemConnect() {
        return itemConnect;
    }

    public JMenuItem getItemExit() {
        return itemExit;
    }

    public JMenuItem getItemConfig() {
        return itemConfig;
    }
}
