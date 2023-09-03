package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfaceGame {
    private static JFrame welcomeFrame;
    private static JFrame gameFrame;

    public static void welcomeMethods() {
        welcomeFrame = new JFrame("Вітаємо");
        welcomeFrame.setSize(400, 100);
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Вітаємо вас у грі дитинства і всіх розумників!");
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setHorizontalAlignment(JLabel.CENTER);
        welcomeFrame.add(label, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton button = new JButton("Ок, Старт!");
        button.setPreferredSize(new Dimension(100, 30));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomeFrame.dispose(); // Закриття першого вікна
                createGameFrame();
            }
        });
        buttonPanel.add(button);

        welcomeFrame.add(buttonPanel, BorderLayout.SOUTH);

        location(welcomeFrame); // розположення вікна на екрані
    }

    private static void createGameFrame() {
        gameFrame = new JFrame("Cities Game");
        gameFrame.setSize(400, 500);
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Верхня панель
        topPanel();
        // Ліва панель зі списком слів (поповнюється)
        leftPanel();
        // Права панель зі списком слів (такий самий, як в лівій панелі)
        rightPanel();
        // Нижня панель з полем для виводу інформації і кнопкою
        bottomPanel();

        location(gameFrame); // розположення вікна на екрані
    }

    private static void topPanel(){
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel goCourse = new JLabel("Введіть назву міста");
        goCourse.setFont(new Font("Arial", Font.PLAIN, 16));
        goCourse.setAlignmentX(Component.CENTER_ALIGNMENT); // Вирівнювання по центру
        topPanel.add(goCourse);

        JTextField inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(100, 30));
        inputField.setHorizontalAlignment(JTextField.CENTER);
        inputField.setAlignmentX(Component.CENTER_ALIGNMENT); // Вирівнювання по центру
        topPanel.add(inputField);

        JLabel score = new JLabel("Гравець: " + 10 + "/ Комп'ютер: " + 10);
        score.setFont(new Font("Arial", Font.PLAIN, 16));
        score.setAlignmentX(Component.CENTER_ALIGNMENT); // Вирівнювання по центру
        topPanel.add(score);

        gameFrame.add(topPanel, BorderLayout.NORTH);
    }

    private static void leftPanel(){
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JTextArea wordListLeft = new JTextArea("Слово A\nСлово B\nСлово C\nСлово A\nСлово B\nСлово "); // Тут можна поповнювати список слів
        wordListLeft.setFont(new Font("Arial", Font.PLAIN, 16));
        wordListLeft.setEditable(false); // Заборона редагування
        wordListLeft.setAlignmentX(Component.CENTER_ALIGNMENT); // Вирівнювання по центру
        leftPanel.add(wordListLeft);

        gameFrame.add(leftPanel, BorderLayout.WEST);
    }

    private static void rightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JTextArea wordListRight = new JTextArea("Слово A\nСлово B\nСлово C\nСлово A\nСлово B\nСлово C"); // Тут можна поповнювати список слів
        wordListRight.setFont(new Font("Arial", Font.PLAIN, 16));
        wordListRight.setEditable(false); // Заборона редагування
        wordListRight.setAlignmentX(Component.CENTER_ALIGNMENT); // Вирівнювання по центру
        rightPanel.add(wordListRight);

        gameFrame.add(rightPanel, BorderLayout.EAST);
    }

    private static void bottomPanel(){
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        JTextField infoField = new JTextField("Комп'ютер: "); // Тут можна виводити інформацію
        infoField.setFont(new Font("Arial", Font.PLAIN, 16));
        infoField.setEditable(false); // Заборона редагування
        infoField.setAlignmentX(Component.CENTER_ALIGNMENT); // Вирівнювання по центру
        bottomPanel.add(infoField);

        JButton button = new JButton("Зробити хід!");
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Вирівнювання по центру
        bottomPanel.add(button);

        gameFrame.add(bottomPanel, BorderLayout.SOUTH);
    }
    private static void location(JFrame frame){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);

        frame.setVisible(true);
    }

}
