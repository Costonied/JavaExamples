package ru.savini.examples.interview.yandex.json_transfer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonTransfer {
    private static int countOfShopOffersStrings;
    private static List<JSONObject> shopOffersBulks;
    private static JSONArray shopOffers;
    private static JSONParser jsonParser;

    public static void main(String[] args) throws IOException, ParseException {
        jsonParser = new JSONParser();
        readOffersFromFile();
        putAllShopOffersToOneList();
        sortOffers();
        writeOffersToFile();
    }

    private static void readOffersFromFile() throws IOException, ParseException {
        try (FileReader fileReader = new FileReader("input.txt");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            countOfShopOffersStrings = Integer.parseInt(bufferedReader.readLine());
            shopOffersBulks = new ArrayList<>();
            for (int i = 0; i < countOfShopOffersStrings; i++) {
                String offers = bufferedReader.readLine();
                shopOffersBulks.add((JSONObject)jsonParser.parse(offers));
            }
        }
    }

    private static void writeOffersToFile() throws IOException, ParseException {
        try (FileWriter fileWriter = new FileWriter("output.txt");
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("offers", shopOffers);
            bufferedWriter.write(jsonObject.toJSONString());
        }
    }

    private static void putAllShopOffersToOneList() {
        shopOffers = new JSONArray();
        for (JSONObject shopOffersBulk : shopOffersBulks) {
            JSONArray offers = (JSONArray) shopOffersBulk.get("offers");
            for (Object offer : offers) {
                shopOffers.add((JSONObject) offer);
            }
        }
    }

    private static void sortOffers() {
        Collections.sort(shopOffers, Comparator.comparingLong((JSONObject offerA) -> (Long) offerA.get("price")).thenComparing(offerA -> (String) offerA.get("offer_id")));
    }
}
