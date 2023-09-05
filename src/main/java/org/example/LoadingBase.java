package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadingBase {
    private static String url = "https://uk.wikipedia.org/wiki/%D0%9C%D1%96%D1%81%D1%82%D0%B0_%D0%A3%D0%BA%D1%80%D0%B0%D1%97%D0%BD%D0%B8_(%D0%B7%D0%B0_%D0%BD%D0%B0%D1%81%D0%B5%D0%BB%D0%B5%D0%BD%D0%BD%D1%8F%D0%BC)";
    private static String fileName = "cities_list.txt";


    public static void LoadList(){
        List<String> correctList = FilterOfList(CreateListOfCities(url));

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            for(String cityName: correctList){
                writer.write(cityName);
                writer.newLine();
            }
            System.out.println("City list written to " + fileName);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static List<String> FilterOfList(List<String> createdList){
        List<String> correctList = new ArrayList<>();
        for(String element: createdList){
            if(element.charAt(0) != '['){
                correctList.add(element);
            }
        }
        return correctList;
    }

    private static List<String> CreateListOfCities(String url){
        List<String> cities = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();

            Elements cityElements = doc.select(".wikitable.sortable tbody tr td:nth-child(2) a:first-child");

            for (Element cityElement : cityElements) {
                cities.add((String) cityElement.text());
            }
            cities.add("Київ"); //Так, це кастиль, але поки я не можу второпати, як його виправити

        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;
    }
}