package ru.costonied.examples.json;


import com.fasterxml.jackson.databind.ObjectMapper;

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

class DictionaryItem {

    private String value;       // Ключ объекта словаря
    private String title;       // Значение объекта словаря

    public DictionaryItem() {
        // nothing
    }

    public String getValue() {
        return value;
    }

    public String getTitle() {
        return title;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}