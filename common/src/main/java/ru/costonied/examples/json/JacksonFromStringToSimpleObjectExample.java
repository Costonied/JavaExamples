package ru.costonied.examples.json;


import com.fasterxml.jackson.databind.ObjectMapper;
import ru.costonied.examples.json.domain.DictionaryItem;

public class JacksonFromStringToSimpleObjectExample {
    public static void main(String[] args) {

        String jsonDictItem = "{\"value\":\"value1\",\"title\":\"title1\"}";

        ObjectMapper mapper = new ObjectMapper();

        try {
            DictionaryItem dictionaryItem1 = mapper.readValue(jsonDictItem, DictionaryItem.class);
            System.out.printf("{%n\tvalue : %s,%n\ttitle : %s%n}", dictionaryItem1.getValue(), dictionaryItem1.getTitle());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}