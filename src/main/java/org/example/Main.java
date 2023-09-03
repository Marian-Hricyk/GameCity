package org.example;

import java.io.File;

public class Main {


    public static void main(String[] args) {
        //перевірка на наявність бази(вона повина завантажитись лише раз)
        String fileName = "city_list.txt";
        File rootFolder = new File(System.getProperty("user.dir"));
        File fileToCheck = new File(rootFolder, fileName);
        if (fileToCheck.exists()) {
            System.out.println("Файл city_list.txt вже існує в кореневій папці.");
        } else {
            LoadingBase.LoadList();
        }
        //завантажуємо ігровий інтерфейс
        InterfaceGame.welcomeMethods();

    }

}
