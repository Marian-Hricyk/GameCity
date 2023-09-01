package org.example;

import org.example.base_of_cities.CitiesGameWithComputer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGame {
    private static JFrame gameFrame;
    private static JTextField inputField;
    private static JButton goButton;
    private static String computerCourse;
    private static String userCourse;
    private static JLabel computerLabel;
    private static JLabel goCourse;
    private static CitiesGameWithComputer citiesGame;

    public static void welcomeMethods() {
        final JFrame welcomeFrame = new JFrame("Вітаємо");
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        welcomeFrame.setSize(400, 100);
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel labelWelcome = new JLabel("Вітаємо вас у грі дитинства і всіх розумників!");
        labelWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        JButton startButton = new JButton("ОК!");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                welcomeFrame.setVisible(false);
                gameFrame.setVisible(true);
            }
        });
        jPanel.add(labelWelcome);
        jPanel.add(startButton);
        welcomeFrame.add(jPanel);
        welcomeFrame.setLocationRelativeTo(null);
        welcomeFrame.setVisible(true);
    }

    public static void createGameFrame() {
        gameFrame = new JFrame("Cities Game");
        JPanel gameUp = new JPanel();
        JPanel gameDown = new JPanel();
        gameUp.setLayout(new FlowLayout());
        gameDown.setLayout(new FlowLayout());
        gameFrame.setSize(400, 200);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLayout(new BorderLayout());
        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(100, inputField.getPreferredSize().height));
        inputField.setHorizontalAlignment(SwingConstants.CENTER);
        goCourse = new JLabel("Введіть назву міста");
        goButton = new JButton("Зробити хід!");
        goButton.setHorizontalAlignment(SwingConstants.CENTER);
        computerLabel = new JLabel();
        citiesGame = new CitiesGameWithComputer();

        goButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userCourse = inputField.getText();
                citiesGame.setUserCourse(userCourse);
                inputField.setText("");
                computerCourse = citiesGame.getComputerCourse();
                computerLabel.setText("Комп'ютер: " + computerCourse);
            }
        });

        gameUp.add(inputField);
        gameUp.add(goCourse);
        gameDown.add(goButton);
        gameDown.add(computerLabel);

        gameFrame.add(gameUp, BorderLayout.NORTH);
        gameFrame.add(gameDown, BorderLayout.SOUTH);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(false);
    }
}
