package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GameLogic {
    private Set<String> computersUsedCities = new HashSet<>(); // сюди мають записуватися обрані комп'ютером міста
    private Set<String> gamersUsedList = new HashSet<>(); // сюди мають записуватися обрані гравцем міста
    private Set<String> usedCities = new HashSet<>();
    private Set<String> availableCities = createCitiesListFromFile("cities_list.txt");
    private String info = "";
    private String lastCity = "";


    public void setUserCourse(String userCity) {
        userCity = userCity.toLowerCase();


        if (!availableCities.contains(userCity)) {
            info = ("Такого міста немає у списку або некоректне найменування. Ви програли!");

        }

        if (usedCities.contains(userCity)) {
            info = ("Це місто вже було назване. Ви програли!");

        }

        if (!lastCity.isEmpty() && userCity.charAt(0) != lastCity.charAt(lastCity.length() - 1)) {
            boolean validMove = false;
            for (String city : availableCities) {
                if (!usedCities.contains(city) && city.startsWith(String.valueOf(lastCity.charAt(lastCity.length() - 1)))) {
                    validMove = true;
                    break;
                }
            }

            if (!validMove) {
                for (String city : availableCities) {
                    if (!usedCities.contains(city) && city.startsWith(String.valueOf(lastCity.charAt(lastCity.length() - 2)))) {
                        validMove = true;
                        break;
                    }
                }
            }

            if (!validMove) {
                info = ("Місто, яке починається на останню букву або передостанню букву попереднього міста, не знайдено. Ви програли!");

            }
        }

        usedCities.add(userCity);
        lastCity = userCity;
        availableCities.remove(userCity);
    }

    public String getComputerCourse() {
        char lastLetter = lastCity.charAt(lastCity.length() - 1);
        char prevLastLetter = lastCity.length() > 1 ? lastCity.charAt(lastCity.length() - 2) : '\0';
        String computerCity = null;
        for (String city : availableCities) {
            if (!usedCities.contains(city) && city.charAt(0) == lastLetter) {

                computerCity = city;
                break;
            }
        }

        if (prevLastLetter != '\0') {
            for (String city : availableCities) {
                if (!usedCities.contains(city) && city.charAt(0) == prevLastLetter) {
                    computerCity = city;
                    break;
                }
            }
        }

        if (info.equals("")) {
            usedCities.add(computerCity); // Додаємо місто, яке вибрав комп'ютер, до usedCities
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