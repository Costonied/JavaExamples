package ru.costonied.examples.string;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Example how to use Apache Common Lang library to make overriding of toString() method more easy.
 * Here we show how to print all class attributes while toString() was invoked.
 */
public class ToStringEasyWay {

    public String version = "1.0";
    public String toStringPrefixStyle = "short";
    private String privateAttribute = "Also seen!";

    @Override
    public String toString() {
        switch (toStringPrefixStyle) {
            case ("short"):
                return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
            case("full"):
            default:
                return ToStringBuilder.reflectionToString(this);
        }
    }

    public static void main(String[] args) {
        ToStringEasyWay toStringEasyWay = new ToStringEasyWay();
        System.out.println(toStringEasyWay);
    }

}
