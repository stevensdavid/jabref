package org.jabref.logic.util.strings;
import org.jabref.model.util.CaretPostionResultText;

public class StringChangeNextWord {
    private enum LetterCase {
        UPPER,
        LOWER,
        CAPITALIZED
    }

    /**
     * Get the number of spaces from beginning of line to cursor.
     *
     * @param pos the position of the cursor
     * @param text String to analyze
     * @return int number of spaces
     */
    public static int getNumOfSpace(int pos, String text) {
        int numOfSpace = 0;
        for (int i = 0; i < pos - 1; ++i) {
            if (text.charAt(i) == ' ') {
                numOfSpace++;
            }
        }
        if (pos == 0) {
            numOfSpace = -1;
        }
        return numOfSpace;
    }

    private static String setNextWordsCase(int numOfSpace, String[] splitText, LetterCase targetCase) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < splitText.length; ++i) {
            if (i == numOfSpace + 1) {
                switch (targetCase) {
                    case UPPER:
                        res.append(splitText[i].toUpperCase());
                        break;
                    case LOWER:
                        res.append(splitText[i].toLowerCase());
                        break;
                    case CAPITALIZED:
                        res.append(Character.toUpperCase(splitText[i].charAt(0)));
                        // Set remainder of string to lower case
                        res.append(splitText[i].substring(1).toLowerCase());
                        break;
                }
            } else {
                res.append(splitText[i]);
            }
            if (i < splitText.length - 1) {
                res.append(" ");
            }
        }
        return res.toString();
    }

    /**
     * Get the overall string for making the next word empty.
     *
     * @param pos the position of the cursor
     * @param text string to analyze
     * @return String the result text
     */
    public static String getNextWordEmpty(int pos, String text) {
        StringBuilder res = new StringBuilder();
        if (text.charAt(pos) == ' ') {
            boolean meetNextSpace = true;
            int meetNumWord = 0;
            for (int i = 0; i < text.length(); ++i) {
                if (i < pos) {
                    res.append(text.charAt(i));
                } 
                else {
                    if (meetNextSpace && text.charAt(i) != ' ') {
                        meetNumWord++;
                        meetNextSpace = false;
                    } else {
                        if (meetNumWord > 0 && text.charAt(i) == ' ') {
                            meetNextSpace = true;
                        }
                    }
                    if (meetNumWord >= 2) {
                        res.append(text.charAt(i));
                    }
                }
            }
        }
        else {
            boolean meetNextSpace = false;
            boolean meetNextWord = false;
            for (int i = 0; i < text.length(); ++i) {
                if (i < pos) {
                    res.append(text.charAt(i));
                } 
                else {
                    if (meetNextWord) {
                        res.append(text.charAt(i));
                    } 
                    else {
                        if (text.charAt(i) == ' ') {
                            meetNextSpace = true;
                            res.append(text.charAt(i));
                        }
                        else {
                            if (meetNextSpace) {
                                meetNextWord = true;
                                res.append(text.charAt(i));
                            }
                        }
                    }
                }
            }
        }
        return res.toString();
    }

    /**
     * Get the overall string for making the previous word empty.
     *
     * @param numOfSpace the number of spaces from the beginning to the cursor
     * @param splitText array of strings to analyze
     * @return String the result text
     */
    public static String getPreviousWordEmpty(int numOfSpace, String[] splitText) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < splitText.length; ++i) {
            if (i != numOfSpace) {
                res.append(splitText[i]);
            }
            if (i < splitText.length - 1) {
                res.append(" ");
            }
        }
        return res.toString();
    }

    /**
     * Capitalize the next word on the right side of the cursor.
     *
     * @param pos the position of the cursor
     * @param text String to analyze
     * @return String the result text
     */
    public static CaretPostionResultText editNextWordCapitalize(int pos, String text) {
        int numOfSpace = getNumOfSpace(pos, text);
        String[] splitText = text.split("\\s+");
        String res = setNextWordsCase(numOfSpace, splitText, LetterCase.CAPITALIZED);
        return new CaretPostionResultText(pos, res);
    }

    /**
     * Make all characters in the next word uppercase.
     *
     * @param pos the position of the cursor
     * @param text String to analyze
     * @return String the result text
     */
    public static CaretPostionResultText editNextWordUpperCase(int pos, String text) {
        int numOfSpace = getNumOfSpace(pos, text);
        String[] splitText = text.split("\\s+");
        String res = setNextWordsCase(numOfSpace, splitText, LetterCase.UPPER);
        return new CaretPostionResultText(pos, res);
    }

    /**
     * Make all characters in the next word lowercase.
     *
     * @param pos the position of the cursor
     * @param text String to analyze
     * @return String the result text
     */
    public static CaretPostionResultText editNextWordLowerCase(int pos, String text) {
        int numOfSpace = getNumOfSpace(pos, text);
        String[] splitText = text.split("\\s+");
        String res = setNextWordsCase(numOfSpace, splitText, LetterCase.LOWER);
        return new CaretPostionResultText(pos, res);
    }

    /**
     * Remove the next word on the right side of the cursor.
     *
     * @param pos the position of the cursor
     * @param text String to analyze
     * @return String the result text
     */
    public static CaretPostionResultText editNextWordToEmpty(int pos, String text) {
        String res = getNextWordEmpty(pos, text);
        return new CaretPostionResultText(pos, res);
    }

    /**
     * Remove the previous word on the left side of the cursor.
     *
     * @param pos the position of the cursor
     * @param text String to analyze
     * @return String the result text
     */
    public static CaretPostionResultText editPreviousWordToEmpty(int pos, String text) {
        StringBuilder reverseText = new StringBuilder(text);
        reverseText.reverse();
        String res = getNextWordEmpty(pos, reverseText.toString());
        StringBuilder resText = new StringBuilder(res);
        resText.reverse();
        return new CaretPostionResultText(pos, resText.toString());
    }
}
