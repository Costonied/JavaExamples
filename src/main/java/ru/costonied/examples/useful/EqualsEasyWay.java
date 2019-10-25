package ru.costonied.examples.useful;

import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * The example how to override equals more easy used Apache Common Lang EqualsBuilder.
 * Int the example we compare all attributes of objects.
 *
 * If you don't want to compare all attributes of object then it will be more complex way.
 * Order to compare objects by several attributes rather than all of them -
 * create new object EqualsBuilder with appendSuper() and append() and isEquals() functions
 * (look at documentation)
 */
public class EqualsEasyWay {

    private int variable1;
    private String variable2;

    public EqualsEasyWay(int variable1, String variable2) {
        this.variable1 = variable1;
        this.variable2 = variable2;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public static void main(String[] args) {
        EqualsEasyWay equalsEasyWay_1 = new EqualsEasyWay(1, "1");
        EqualsEasyWay equalsEasyWay_2 = new EqualsEasyWay(1, "1");
        EqualsEasyWay equalsEasyWay_3 = new EqualsEasyWay(1, "2");
        EqualsEasyWay equalsEasyWay_4 = new EqualsEasyWay(2, "2");

        System.out.println(equalsEasyWay_1.equals(equalsEasyWay_2));
        System.out.println(equalsEasyWay_1.equals(equalsEasyWay_3));
        System.out.println(equalsEasyWay_1.equals(equalsEasyWay_4));
        System.out.println(equalsEasyWay_3.equals(equalsEasyWay_4));
    }
}
