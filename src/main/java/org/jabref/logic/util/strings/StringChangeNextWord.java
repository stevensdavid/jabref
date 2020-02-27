package org.jabref.logic.util.strings;

public class StringChangeNextWord {
    /**
     * Capitalize the next word on the right side of the cursor. 
     *
     * @param pos the position of the cursor
     * @param text String to analyze
     * @return String the result text
     */
    public String editNextWordCapitalize (int pos, String text) {
        String[] splitText = text.split("\\s+");
        int numOfSpace = 0;
        for (int i = 0; i < pos - 1; ++i) {
            if (text.charAt(i) == ' ') {
                numOfSpace++;
            }
        }
        if (pos == 0) {
            numOfSpace = -1;
        }
        String res = "";
        for (int i = 0; i < splitText.length; ++i) {
            if (i == numOfSpace + 1) {
                res += Character.toUpperCase(splitText[i].charAt(0));
                res += splitText[i].substring(1);
            } else {
                res += splitText[i];
            }
            res += " ";
        }
        return res;
    }

    /**
     * Make all characters in the next word uppercase. 
     *
     * @param pos the position of the cursor
     * @param text String to analyze
     * @return String the result text
     */
    public String editNextWordUpperCase (int pos, String text) {
        String[] splitText = text.split("\\s+");
        int numOfSpace = 0;
        for (int i = 0; i < pos - 1; ++i) {
            if (text.charAt(i) == ' ') {
                numOfSpace++;
            }
        }
        if (pos == 0) {
            numOfSpace = -1;
        }
        String res = "";
        for (int i = 0; i < splitText.length; ++i) {
            if (i == numOfSpace + 1) {
                res += splitText[i].toUpperCase();
            } else {
                res += splitText[i];
            }
            res += " ";
        }
        return res;
    }
    /**
     * Make all characters in the next word lowercase. 
     *
     * @param pos the position of the cursor
     * @param text String to analyze
     * @return String the result text
     */
    public String editNextWordLowerCase (int pos, String text) {
        String[] splitText = text.split("\\s+");
        int numOfSpace = 0;
        for (int i = 0; i < pos - 1; ++i) {
            if (text.charAt(i) == ' ') {
                numOfSpace++;
            }
        }
        if (pos == 0) {
            numOfSpace = -1;
        }
        String res = "";
        for (int i = 0; i < splitText.length; ++i) {
            if (i == numOfSpace + 1) {
                res += splitText[i].toLowerCase();
            } else {
                res += splitText[i];
            }
            res += " ";
        }
        return res;
    }
}