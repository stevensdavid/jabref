package org.jabref.logic.util.strings;

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
                        res.append(Character.toUpperCase(splitText[i].charAt(0)));
                        break;
                    case LOWER:
                        res.append(Character.toLowerCase(splitText[i].charAt(0)));
                        break;
                    case CAPITALIZED:
                        res.append(Character.toUpperCase(splitText[i].charAt(0)));
                        res.append(splitText[i].substring(1));
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
     * @param numOfSpace the number of spaces from the beginning to the cursor
     * @param text string to analyze
     * @return String the result text
     */
    public static String getNextWordEmpty(int pos, int numOfSpace, String text) {
        String[] splitText = text.split("\\s+");
        String res = "";
        if (text.charAt(pos) == ' ' || (pos > 0 && text.charAt(pos - 1) == ' ')) {
            for (int i = 0; i < splitText.length; ++i) {
                if (i != numOfSpace + 1) {
                    res += splitText[i];
                }
                if (i < splitText.length - 1) {
                    res += " ";
                }
            }
        } else {
            int indexSpace = 0;
            for (int i = 0; i < text.length(); ++i) {
                if (text.charAt(i) == ' ') {
                    indexSpace++;
                }
                if (i < pos) {
                    res += text.charAt(i);
                } else {
                    if (indexSpace > numOfSpace) {
                        res += text.charAt(i);
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
    public static String editNextWordCapitalize(int pos, String text) {
        int numOfSpace = getNumOfSpace(pos, text);
        String[] splitText = text.split("\\s+");
        String res = setNextWordsCase(numOfSpace, splitText, LetterCase.CAPITALIZED);
        return res;
    }

    /**
     * Make all characters in the next word uppercase.
     *
     * @param pos the position of the cursor
     * @param text String to analyze
     * @return String the result text
     */
    public static String editNextWordUpperCase(int pos, String text) {
        int numOfSpace = getNumOfSpace(pos, text);
        String[] splitText = text.split("\\s+");
        String res = setNextWordsCase(numOfSpace, splitText, LetterCase.UPPER);
        return res;
    }

    /**
     * Make all characters in the next word lowercase.
     *
     * @param pos the position of the cursor
     * @param text String to analyze
     * @return String the result text
     */
    public static String editNextWordLowerCase(int pos, String text) {
        int numOfSpace = getNumOfSpace(pos, text);
        String[] splitText = text.split("\\s+");
        String res = setNextWordsCase(numOfSpace, splitText, LetterCase.LOWER);
        return res;
    }

    /**
     * Remove the next word on the right side of the cursor.
     *
     * @param pos the position of the cursor
     * @param text String to analyze
     * @return String the result text
     */
    public static String editNextWordToEmpty(int pos, String text) {
        int numOfSpace = getNumOfSpace(pos, text);
        String[] splitText = text.split("\\s+");
        String res = getNextWordEmpty(pos, numOfSpace, text);
        return res;
    }

    /**
     * Remove the previous word on the left side of the cursor.
     *
     * @param pos the position of the cursor
     * @param text String to analyze
     * @return String the result text
     */
    public static String editPreviousWordToEmpty(int pos, String text) {
        int numOfSpace = getNumOfSpace(pos, text);
        String[] splitText = text.split("\\s+");
        String res = getPreviousWordEmpty(numOfSpace, splitText);
        return res;
    }
}
