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
    public void editNextWordUpperCasePreservesSpace() {
        int pos = 3; //Postion of the caret, between the two ll in the first hello
        String textInput = "hello hello";
        String whatTextShouldBe = "hello HELLO";
        String textOutput = StringChangeNextWord.editNextWordUpperCase(pos, textInput);
        assertEquals(whatTextShouldBe, textOutput);
    }

    @Test
    public void editNextWordUpperCasePreservesNewlines() {
        int pos = 3; //Postion of the caret, between the two ll in the first hello
        String textInput = "hello\nhello";
        String whatTextShouldBe = "hello\nHELLO";
        String textOutput = StringChangeNextWord.editNextWordUpperCase(pos, textInput);
        assertEquals(whatTextShouldBe, textOutput);
    }

    @Test
    public void editNextWordUpperCasePreservesTab() {
        int pos = 3; //Postion of the caret, between the two ll in the first hello
        String textInput = "hello\thello";
        String whatTextShouldBe = "hello\tHELLO";
        String textOutput = StringChangeNextWord.editNextWordUpperCase(pos, textInput);
        assertEquals(whatTextShouldBe, textOutput);
    }

    @Test
    public void editNextWordUpperCasePreservesDoubleSpace() {
        int pos = 3; //Postion of the caret, between the two ll in the first hello
        String textInput = "hello hello";
        String whatTextShouldBe = "hello HELLO";
        String textOutput = StringChangeNextWord.editNextWordUpperCase(pos, textInput);
        assertEquals(whatTextShouldBe, textOutput);
    }

    @Test
    public void editNextWordUpperCasePreservesMixedSpaceNewLineTab() {
        int pos = 3; //Postion of the caret, between the two ll in the first hello
        String textInput = "hello \n\thello";
        String whatTextShouldBe = "hello \n\tHELLO";
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
