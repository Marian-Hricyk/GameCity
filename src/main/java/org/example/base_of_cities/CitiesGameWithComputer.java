package org.example.base_of_cities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CitiesGameWithComputer {
    private Set<String> usedCities;
    private Set<String> availableCities;
    private String lastCity;

    public CitiesGameWithComputer() {
        usedCities = new HashSet<>();
        availableCities = createCitiesListFromFile("city_list.txt");
        lastCity = "";
    }

    public void startGame() {
        System.out.println("Давайте грати в гру Міста!");
        System.out.println("Введіть перше місто:");
    }

    public void setUserCourse(String userCity) {
        userCity = userCity.toLowerCase();

        if (!availableCities.contains(userCity)) {
            System.out.println("Такого міста немає у списку або некоректне найменування. Ви програли!");
            return;
        }

        if (usedCities.contains(userCity)) {
            System.out.println("Це місто вже було назване. Ви програли!");
            return;
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
                System.out.println("Місто, яке починається на останню букву або передостанню букву попереднього міста, не знайдено. Ви програли!");
                return;
            }
        }

        usedCities.add(userCity);
        lastCity = userCity;
        availableCities.remove(userCity);
    }

    public String getComputerCourse() {
        char lastLetter = lastCity.charAt(lastCity.length() - 1);
        char prevLastLetter = lastCity.length() > 1 ? lastCity.charAt(lastCity.length() - 2) : '\0';

        for (String city : availableCities) {
            if (!usedCities.contains(city) && city.charAt(0) == lastLetter) {
                return city;
            }
        }

        if (prevLastLetter != '\0') {
            for (String city : availableCities) {
                if (!usedCities.contains(city) && city.charAt(0) == prevLastLetter) {
                    return city;
                }
            }
        }

        return "";
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
