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

    private static String text = "123|FirstName=First|SecondName=Second|567";

    /**
     * We need to make the quantifier non-greedy .*?
     * Because if use just .* then character | (pipe) will be inside regexp .* and we don't match what needed
     * @param text text for parsing
     * @return matches value
     */
    static String findPatternWithPipeCharNonGreedy(String text) {
        Pattern pattern = Pattern.compile("FirstName=(.*?)\\|");
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group(1) : null;
    }

    /**
     * Using lazy search.
     * The example no so clear for me than non-greedy way but it's more faster
     * @param text
     * @return
     */
    static String findPatternWithPipeCharLazy(String text) {
        Pattern pattern = Pattern.compile("FirstName=([^|]++)");
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group(1) : null;
    }

    public static void main(String[] args) {
        System.out.println(findPatternWithPipeCharNonGreedy(text));
        System.out.println(findPatternWithPipeCharLazy(text));
    }
}
