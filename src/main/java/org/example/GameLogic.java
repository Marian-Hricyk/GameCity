package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class GameLogic {

    String cyrillicPattern = ".*[а-яА-Я].*"; // регулярний вираз для перевірки кирилиці
    Pattern pattern = Pattern.compile(cyrillicPattern);

    private Set<String> computersUsedCities = new HashSet<>(); // сюди мають записуватися обрані комп'ютером міста
    private Set<String> gamersUsedList = new HashSet<>(); // сюди мають записуватися обрані гравцем міста
    private Set<String> usedCities = new HashSet<>();
    private Set<String> availableCities = createCitiesListFromFile("cities_list.txt");
    private String info = "";
    private String lastCity = "";

    public int counterG = 0;
    public int counterC = 0;

    public void setUserCourse(String userCity) {
        Matcher matcher = pattern.matcher(userCity);
        if (matcher.matches()) {
            //Виконується, коли текст містить кирилицю
            userCity = userCity.toLowerCase();

            if (!availableCities.contains(userCity)) {
                if (usedCities.contains(userCity)) {
                    info = "Це місто вже було назване. Ви програли!";
                } else {
                    info = "Такого міста немає у списку. Ви програли!";
                }
            } else {
                if (!lastCity.isEmpty()) {
                    char lastLetter = lastCity.charAt(lastCity.length() - 1); // остання літера минулого слова
                    char firstLetter = userCity.charAt(0); // перша літера нового слова користувача
                    if (firstLetter != lastLetter) {
                        if (lastLetter == 'ь') {
                            //якщо останнє слово закінчується на букву з якої не починається жодне місто,
                            // то поченаємо превіряти другу з кінця літеру
                            char beforeTheLastLetter = lastCity.charAt(lastCity.length() - 2);

                            if (firstLetter != beforeTheLastLetter) {
                                info = "Місто має починатися на букву '" + lastLetter + "'. Ви програли!";
                            }
                        }
                    }
                }
            }
        } else {
            //Виконується, коли текст не містить кирилицю
            info = "Слід використовувати тільки кирилицю.";
        }

        gamersUsedList.add(userCity);
        counterG++;
        usedCities.add(userCity);
        lastCity = userCity;
        availableCities.remove(userCity);
    }

    public String getComputerCourse() {
        if (info.equals("")) {
            char lastLetter = lastCity.charAt(lastCity.length() - 1); // остання літера останнього слова, введеного користувачем
            String computerCity = "";
            if (lastLetter != 'ь') {
                for (String city : availableCities) {
                    if (!usedCities.contains(city) && city.charAt(0) == lastLetter) {
                        computerCity = city;
                        break;
                    }
                }
            } else {
                char prevLastLetter = lastCity.charAt(lastCity.length() - 2);
                for (String city : availableCities) {
                    if (!usedCities.contains(city) && city.charAt(0) == prevLastLetter) {
                        computerCity = city;
                        break;
                    }
                }
            }
            computersUsedCities.add(computerCity);
            counterC++;
            return computerCity;

        } else {
            return info;
        }

    }

    private Set<String> createCitiesListFromFile(String filename) {
        Set<String> citiesList = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                citiesList.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return citiesList;
    }


}
