package org.example;

import org.example.base_of_cities.LoadingBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static JFrame gameFrame;
    private static JTextField inputField;
    private static JButton goButon;
    private static String computerСourse;
    private static String userCourse;
    private static JLabel coputerLable;
    private static JLabel goCourse;
    private static JPanel gameUp;
    private static JPanel gameDown;

    public static void main(String[] args) {
        LoadingBase loadingBase = new LoadingBase();
        loadingBase.LoadList();
        welcomMetods();
        createGameFrame();
    }
    public static void welcomMetods() {
        final JFrame welcomeFrame = new JFrame("Вітаємо");
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        welcomeFrame.setSize(400, 100);
        welcomeFrame.setDefaultCloseOperation(3);
        JLabel labelWelcom = new JLabel("Вітаємо вас у грі дитинства і всіх розумників!");
        labelWelcom.setHorizontalAlignment(2);
        JButton startButton = new JButton("ОК!");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                welcomeFrame.setVisible(false);
                Main.gameFrame.setVisible(true);
            }
        });
        jPanel.add(labelWelcom);
        jPanel.add(startButton);
        welcomeFrame.add(jPanel);
        welcomeFrame.setLocationRelativeTo((Component)null);
        welcomeFrame.setVisible(true);
    }
    private static void createGameFrame() {
        gameFrame = new JFrame("Cities Game");
        gameUp = new JPanel();
        gameDown = new JPanel();
        gameUp.setLayout(new FlowLayout());
        gameDown.setLayout(new FlowLayout());
        gameFrame.setSize(400, 200);
        gameFrame.setDefaultCloseOperation(3);
        gameFrame.setLayout(new BorderLayout());
        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(100, inputField.getPreferredSize().height));
        inputField.setHorizontalAlignment(2);
        goCourse = new JLabel("Введіть назву міста");
        goButon = new JButton("Зробити хід!");
        goButon.setHorizontalAlignment(2);
        coputerLable = new JLabel();
        goButon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.userCourse = Main.inputField.getText();
                Main.inputField.setText("");
                Main.computerСourse = "Місто від комп'ютера";
                Main.coputerLable.setText("Комп'ютер: " + Main.computerСourse);
            }
        });
        gameUp.add(inputField);
        gameUp.add(goCourse);
        gameDown.add(goButon);
        gameDown.add(coputerLable);
        gameFrame.add(gameUp, "North");
        gameFrame.add(gameDown, "South");
        gameFrame.setLocationRelativeTo((Component)null);
        gameFrame.setVisible(false);
    }
}