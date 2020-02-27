package org.jabref.logic.util.strings;

public class StringChangeNextWord {

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

    /**
     * Get the overall string for capitalizing the next word.
     *
     * @param numOfSpace the number of spaces from the beginning to the cursor
     * @param splitText array of strings to analyze
     * @return String the result text
     */
    public static String getNextWordCapitalize(int numOfSpace, String[] splitText) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < splitText.length; ++i) {
            if (i == numOfSpace + 1) {
                res.append(Character.toUpperCase(splitText[i].charAt(0)));
                res.append(splitText[i].substring(1));
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
     * Get the overall string for making the next word uppercase.
     *
     * @param numOfSpace the number of spaces from the beginning to the cursor
     * @param splitText array of strings to analyze
     * @return String the result text
     */
    public static String getNextWordUpperCase(int numOfSpace, String[] splitText) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < splitText.length; ++i) {
            if (i == numOfSpace + 1) {
                res.append(splitText[i].toUpperCase());
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
     * Get the overall string for making the next word lowercase.
     *
     * @param numOfSpace the number of spaces from the beginning to the cursor
     * @param splitText array of strings to analyze
     * @return String the result text
     */
    public static String getNextWordLowerCase(int numOfSpace, String[] splitText) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < splitText.length; ++i) {
            if (i == numOfSpace + 1) {
                res.append(splitText[i].toLowerCase());
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
                    if (meetNumWord == 2) {
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
    public static String editNextWordCapitalize(int pos, String text) {
        int numOfSpace = getNumOfSpace(pos, text);
        String[] splitText = text.split("\\s+");
        String res = getNextWordCapitalize(numOfSpace, splitText);
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
        String res = getNextWordUpperCase(numOfSpace, splitText);
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
        String res = getNextWordLowerCase(numOfSpace, splitText);
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
