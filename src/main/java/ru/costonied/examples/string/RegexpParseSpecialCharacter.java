package ru.costonied.examples.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Example how to parse string using Matching and Pattern classes.
 * Also shows how to processed cases when special characters in the end of regexp string
 *
 * Answer was received from:
 * https://stackoverflow.com/questions/58796288/how-to-escape-pipe-character-in-pattern-compile/58796403#58796403
 */
public class RegexpParseSpecialCharacter {

    static String findPatternWithPipeChar() {

        String text = "123|FirstName=First|SecondName=Second|567";
        // We need to make the quantifier non-greedy .*?
        // Because if use just .* then character | (pipe) will be inside regexp .* and we don't match what needed
        Pattern pattern = Pattern.compile("FirstName=(.*?)\\|");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            System.out.println(matcher.group(1));
        }
        return null;
    }

    public static void main(String[] args) {
        RegexpParseSpecialCharacter.findPatternWithPipeChar();
    }
}
