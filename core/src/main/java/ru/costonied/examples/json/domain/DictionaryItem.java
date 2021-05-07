package ru.costonied.examples.json.domain;


public class DictionaryItem {

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
