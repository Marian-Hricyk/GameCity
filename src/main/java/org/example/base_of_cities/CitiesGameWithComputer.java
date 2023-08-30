package org.example.base_of_cities;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;



public class CitiesGameWithComputer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<String> usedCities = new HashSet<>();
        Set<String> citiesList = createCitiesList();

        System.out.println("Давайте грати у гру Міста!");
        System.out.println("Введіть перше місто:");

        String lastCity = scanner.nextLine().toLowerCase();
        usedCities.add(lastCity);

        while (true) {
            String computerCity = getComputerCity(lastCity, usedCities, citiesList);
            if (computerCity.isEmpty()) {
                System.out.println("Комп'ютер не може знайти підходяще місто. Ви перемогли!");
                break;
            }
            System.out.println("Комп'ютер вибрав: " + computerCity);

            lastCity = computerCity;
            usedCities.add(computerCity);

            System.out.println("Введіть ваше місто:");
            String userCity = scanner.nextLine().toLowerCase();

            if (!citiesList.contains(userCity)) {
                System.out.println("Такого міста немає у списку або некоректне найменування. Ви програли!");
                break;
            }

            if (usedCities.contains(userCity)) {
                System.out.println("Це місто вже було назване. Ви програли!");
                break;
            }

            if (userCity.charAt(0) != lastCity.charAt(lastCity.length() - 1)) {
                System.out.println("Місто повинно починатися на останню букву попереднього міста. Ви програли!");
                break;
            }

            usedCities.add(userCity);
            lastCity = userCity;
        }
    }

    private static Set<String> createCitiesList() {
        Set<String> citiesList = new HashSet<>();
        citiesList.add("київ");
        citiesList.add("львів");
        citiesList.add("одеса");
        citiesList.add("харків");
        citiesList.add("дніпро");
        citiesList.add("луцьк");
        citiesList.add("запоріжжя");
        citiesList.add("вінниця");
        citiesList.add("рівне");
        citiesList.add("полтава");
        citiesList.add("чернівці");
        citiesList.add("житомир");
        citiesList.add("трускавець");
        citiesList.add("кам'янець-подільський");
        citiesList.add("мукачево");
        citiesList.add("ужгород");
        citiesList.add("миколаїв");
        citiesList.add("суми");
        citiesList.add("черкаси");
        citiesList.add("кропивницький");
        citiesList.add("маріуполь");
        citiesList.add("севастополь");
        citiesList.add("сімферополь");
        citiesList.add("мелітополь");
        citiesList.add("донецьк");
        citiesList.add("славутич");
        citiesList.add("чернігів");
        citiesList.add("кіровоград");
        citiesList.add("кременчук");
        citiesList.add("червоноград");
        citiesList.add("херсон");
        citiesList.add("кривий ріг");
        citiesList.add("бердянськ");
        citiesList.add("павлоград");
        citiesList.add("марганець");
        citiesList.add("долина");
        citiesList.add("кам'янка-бузька");
        citiesList.add("борислав");
        citiesList.add("хуст");
        citiesList.add("радомишль");
        citiesList.add("самбір");
        citiesList.add("судова вишня");

        return citiesList;
    }

    private static String getComputerCity(String lastCity, Set<String> usedCities, Set<String> citiesList) {
        String lastLetter = lastCity.substring(lastCity.length() - 1);
        String prevLastLetter = lastCity.length() > 1 ? lastCity.substring(lastCity.length() - 2, lastCity.length() - 1) : null;

        for (String city : citiesList) {
            if (!usedCities.contains(city) && city.startsWith(lastLetter)) {
                return city;
            }
        }

        if (prevLastLetter != null) {
            for (String city : citiesList) {
                if (!usedCities.contains(city) && city.startsWith(prevLastLetter)) {
                    return city;
                }
            }
        }

        for (String city : citiesList) {
            if (!usedCities.contains(city)) {
                return city; // Обираємо будь-яке не використане місто в якості останнього варіанту
            }
        }

        return ""; // Якщо комп'ютер не може знайти підходяще місто, він проіграв.
    }
}
