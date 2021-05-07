package ru.costonied.examples.enums;

/**
 * Show how to use enum variables
 */
public class EnumWithVariablesExample {
    public static void main(String[] args) {

        System.out.println(Singleton.INSTANCE.value);
        Singleton.INSTANCE.updateValue();
        System.out.println(Singleton.INSTANCE.value);

    }
}

enum Singleton {
    INSTANCE;
    int value = 0;
    private void incrementValue() {
        value = value + 1;
    }
    public synchronized int updateValue() {
        incrementValue();
        return value;
    }
}
