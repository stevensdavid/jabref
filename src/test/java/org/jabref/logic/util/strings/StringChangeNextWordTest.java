package org.jabref.logic.util.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringChangeNextWordTest {

    @Test
    public void editNextWordCapitalizePreservesNewlines() {
        int pos = 3; //Postion of the caret, between the two "ll in the first hello"
        String textInput = "hello\nhello";
        String whatTextShouldBe = "hello\nHello";
        String textOutput = StringChangeNextWord.editNextWordCapitalize(pos, textInput);
        assertEquals(whatTextShouldBe, textOutput);
    }

    @Test
    public void editNextWordUpperCaseEditsTheNextWord() {
        int pos = 3; //Postion of the caret, between the two ll in the first hello
        String textInput = "hello hello";
        String whatTextShouldBe = "hello HELLO";
        String textOutput = StringChangeNextWord.editNextWordUpperCase(pos, textInput);
        assertEquals(whatTextShouldBe, textOutput);
    }

    @Test
    public void editNextWordLowerCaseEditsTheNextWord() {
        int pos = 3; //Postion of the caret, between the two ll in the first hello
        String textInput = "hello HELLO";
        String whatTextShouldBe = "hello hello";
        String textOutput = StringChangeNextWord.editNextWordLowerCase(pos, textInput);
        assertEquals(whatTextShouldBe, textOutput);
    }

    @Test
    public void editNextWordToEmptyRemovesFromPostionUpToNextWord() {
        int pos = 3; //Postion of the caret, between the two "ll in the first hello"
        String textInput = "hello hello";
        String whatTextShouldBe = "hel hello";
        String textOutput = StringChangeNextWord.editNextWordToEmpty(pos, textInput);
        assertEquals(whatTextShouldBe, textOutput);
    }

    @Test
    public void editNextWordToEmptyRemovesNextWordIfPositionIsInSpace() {
        int pos = 5; //Postion of the caret, atfer the first hello"
        String textInput = "hello person";
        String whatTextShouldBe = "hello";
        String textOutput = StringChangeNextWord.editNextWordToEmpty(pos, textInput);
        assertEquals(whatTextShouldBe, textOutput);
    }

}
