package org.jabref.logic.util.strings;

public class StringChangeNextWord {
    private enum LetterCase {
        UPPER,
        LOWER,
        CAPITALIZED
    }

    private enum Direction {
        NEXT, PREVIOUS
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

    private static String setNextWordsCase(String text, int pos, LetterCase targetCase) {
        StringBuilder res = new StringBuilder();

        int firstWordChar = pos;
        boolean firstLetter = true;
        int i = pos;
        boolean firstLoop = true;
        StringBuilder newWordBuilder = new StringBuilder();
        for (; i < text.length(); i++) {
            // Swallow whitespace
            while (firstLoop && i < text.length() && !String.valueOf(text.charAt(i)).matches("\\w")) {
                i++;
            }
            if (firstLoop) {
                firstWordChar = i;
                firstLoop = false;
            }
            char currentChar = text.charAt(i);
            if (String.valueOf(currentChar).matches("\\w")) {
                switch (targetCase) {
                    case UPPER:
                        newWordBuilder.append(Character.toUpperCase(currentChar));
                        break;
                    case LOWER:
                        newWordBuilder.append(Character.toLowerCase(currentChar));
                        break;
                    case CAPITALIZED:
                        if (firstLetter) {
                            newWordBuilder.append(Character.toUpperCase(currentChar));
                            firstLetter = false;
                        } else {
                            newWordBuilder.append(Character.toLowerCase(currentChar));
                        }
                        break;
                }
            } else {
                // We have reached the word boundary.
                break;
            }
        }
        res.append(text, 0, firstWordChar);
        res.append(newWordBuilder);
        res.append(text, i, text.length());
        return res.toString();
    }

    /**
     * Delete all characters in a string from the given position to the next word boundary.
     *
     * @param pos The index to start from.
     * @param text The text to manipulate.
     * @param dir The direction to search.
     * @return The resulting text.
     */
    public static String deleteUntilWordBoundary(int pos, String text, Direction dir) {
        StringBuilder res = new StringBuilder();
        int offset;
        int wordBreak;
        switch (dir) {
            case NEXT:
                res.append(text, 0, pos);
                offset = 1;
                wordBreak = text.length();
                break;
            case PREVIOUS:
                res.append(text, pos, text.length());
                offset = -1;
                wordBreak = 0;
                break;
            default:
                throw new AssertionError("Missing case in switch deleteUntilWordBoundary");
        }

        for (int i = pos; i < text.length() && i >= 0; i += offset) {
            if (i == pos) {
                // Swallow whitespace until we hit a word character or newline.
                while (i < text.length()
                        && i >= 0
                        && !String.valueOf(text.charAt(i)).matches("\\w|[\\r\\n]")) {
                    i += offset;
                }
            }
            if (!String.valueOf(text.charAt(i)).matches("\\w")) {
                wordBreak = i;
            }
        }

        if (dir == Direction.NEXT) {
            res.append(text, wordBreak, text.length());
        } else {
            res.append(text, 0, wordBreak);
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
    @Deprecated
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
    @Deprecated
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
        return setNextWordsCase(text, pos, LetterCase.CAPITALIZED);
    }

    /**
     * Make all characters in the next word uppercase.
     *
     * @param pos the position of the cursor
     * @param text String to analyze
     * @return String the result text
     */
    public static String editNextWordUpperCase(int pos, String text) {
        return setNextWordsCase(text, pos, LetterCase.UPPER);
    }

    /**
     * Make all characters in the next word lowercase.
     *
     * @param pos the position of the cursor
     * @param text String to analyze
     * @return String the result text
     */
    public static String editNextWordLowerCase(int pos, String text) {
        return setNextWordsCase(text, pos, LetterCase.LOWER);
    }

    /**
     * Remove the next word on the right side of the cursor.
     *
     * @param pos the position of the cursor
     * @param text String to analyze
     * @return String the result text
     */
    public static String editNextWordToEmpty(int pos, String text) {
        return deleteUntilWordBoundary(pos, text, Direction.NEXT);
    }

    /**
     * Remove the previous word on the left side of the cursor.
     *
     * @param pos the position of the cursor
     * @param text String to analyze
     * @return String the result text
     */
    public static String editPreviousWordToEmpty(int pos, String text) {
        return deleteUntilWordBoundary(pos, text, Direction.PREVIOUS);
    }
}
