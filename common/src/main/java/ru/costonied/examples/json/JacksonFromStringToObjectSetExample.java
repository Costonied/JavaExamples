package ru.costonied.examples.json;


import com.fasterxml.jackson.databind.ObjectMapper;
import ru.costonied.examples.json.domain.DictionarySet;

public class JacksonFromStringToObjectSetExample {
    public static void main(String[] args) {

        String jsonDictItemSet = "{\"items\":[{\"value\":\"value1\",\"title\":\"title1\"},{\"value\":\"value2\",\"title\":\"title2\"}]}";

        ObjectMapper mapper = new ObjectMapper();

        try {
            DictionarySet dictionarySet = mapper.readValue(jsonDictItemSet, DictionarySet.class);
            dictionarySet.getItems().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}