package ru.savini.examples.interview.get_request_and_sort_numbers;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;


public class GetRequestAndSortNumbers {
    private static String url;
    private static String port;
    private static int a;
    private static int b;
    private static String serverResponse;
    private static JSONArray numbers;

    public static void main(String[] args) throws IOException, ParseException {
        readDataFromFile();
        sendGetRequest();
//        sendGetRequestMock();
        parseNumbersFromResponse();
        sortNumbers();
        writeNumbersToFile();
    }

    private static void readDataFromFile() throws IOException {
        try (FileReader fileReader = new FileReader("input.txt");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            url = bufferedReader.readLine();
            port = bufferedReader.readLine();
            a = Integer.parseInt(bufferedReader.readLine());
            b = Integer.parseInt(bufferedReader.readLine());
        }
    }

    private static void sendGetRequest() throws IOException {
        String finalUrl = String.format("%s:%s?a=%d&b=%d", url, port, a, b);
        HttpURLConnection httpClient = (HttpURLConnection) new URL(finalUrl).openConnection();
        httpClient.setRequestMethod("GET");
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            serverResponse = response.toString();
        }
    }

    private static void sendGetRequestMock() {
        serverResponse = "[  \n" +
                "  8,  \n" +
                "  6,  \n" +
                "  -2,  \n" +
                "  2,  \n" +
                "  4,  \n" +
                "  17,  \n" +
                "  256,  \n" +
                "  1024,  \n" +
                "  -17,  \n" +
                "  -19  \n" +
                "]";
    }

    private static void parseNumbersFromResponse() throws ParseException {
        JSONParser jsonParser = new JSONParser();
        numbers = (JSONArray) jsonParser.parse(serverResponse);
    }

    private static void sortNumbers() {
        Collections.sort(numbers, (Long numberA, Long numberB) -> numberA.compareTo(numberB));
    }

    private static void writeNumbersToFile() throws IOException {
        try (FileWriter fileWriter = new FileWriter("output.txt");
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Object number : numbers) {
                bufferedWriter.write(number.toString());
                bufferedWriter.write('\n');
                bufferedWriter.flush();
            }
        }
    }

}
