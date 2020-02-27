package org.jabref.logic.util.strings;

import org.jabref.logic.util.strings.StringChangeNextWord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringChangeNextWordTest {

    @Test
    public void editNextWordCapitalizePreservesNewlines() {
        int pos = 3;
        String textInput = "hello\nhello";
        String whatTextShouldBe = "hello\nHello";
        String textOutput = new StringChangeNextWord().editNextWordCapitalize(pos, textInput);
        assertEquals(textOutput, whatTextShouldBe);
    }

    @Test
    public void editNextWordUpperCaseEditsTheNextWord() {
        int pos = 3;
        String textInput = "hello hello";
        String whatTextShouldBe = "hello HELLO";
        String textOutput = new StringChangeNextWord().editNextWordUpperCase(pos, textInput);
        assertEquals(textOutput, whatTextShouldBe);
    }

    @Test
    public void editNextWordLowerCaseEditsTheNextWord() {
        int pos = 3;
        String textInput = "hello HELLO";
        String whatTextShouldBe = "hello hello";
        String textOutput = new StringChangeNextWord().editNextWordLowerCase(pos, textInput);
        assertEquals(textOutput, whatTextShouldBe);
    }

    @Test
    public void editNextWordToEmptyRemovesFromPostionUpToNextWord() {
        int pos = 3;
        String textInput = "hello hello";
        String whatTextShouldBe = "hel hello";
        String textOutput = new StringChangeNextWord().editNextWordToEmpty(pos, textInput);
        assertEquals(textOutput, whatTextShouldBe);
    }

    @Test
    public void editNextWordToEmptyRemovesNextWordIfPositionIsInSpace() {
        int pos = 5;
        String textInput = "hello person";
        String whatTextShouldBe = "person";
        String textOutput = new StringChangeNextWord().editNextWordToEmpty(pos, textInput);
        assertEquals(textOutput, whatTextShouldBe);
    }

    @Test
    public void getNumOfSpaceTwoWordOneSpace() {
        int pos = 8;
        String textInput = "hello person";
        int whatNumOfSpaceShouldBe = 1;
        int numOfSpace = new StringChangeNextWord().getNumOfSpace(pos, textInput);
        assertEquals(numOfSpace, whatNumOfSpaceShouldBe);
    }

}
