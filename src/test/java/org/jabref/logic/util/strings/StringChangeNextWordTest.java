package org.jabref.logic.util.strings;

import org.jabref.model.util.CaretPostionResultText;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringChangeNextWordTest {

    @Test
    public void editNextWordCapitalizePreservesNewlines() {
        int pos = 5; // Position of the caret, between the two ll in the first hellO"
        String textInput = "hello\n\nhELLO";
        String whatTextShouldBe = "hello\n\nHello";
        CaretPostionResultText textOutput = StringChangeNextWord.editNextWordCapitalize(pos, textInput);
        assertEquals(whatTextShouldBe, textOutput.resText);
    }

    @Test
    public void editNextWordUpperCasePreservesSpace() {
        int pos = 3; // Position of the caret, between the two ll in the first hello
        String textInput = "hello hello";
        String whatTextShouldBe = "helLO hello";
        CaretPostionResultText textOutput = StringChangeNextWord.editNextWordUpperCase(pos, textInput);
        assertEquals(whatTextShouldBe, textOutput.resText);
    }

    @Test
    public void editNextWordUpperCasePreservesNewlines() {
        int pos = 3; // Position of the caret, between the two ll in the first hello
        String textInput = "hello\nhello";
        String whatTextShouldBe = "helLO\nhello";
        CaretPostionResultText textOutput = StringChangeNextWord.editNextWordUpperCase(pos, textInput);
        assertEquals(whatTextShouldBe, textOutput.resText);
    }

    @Test
    public void editNextWordUpperCasePreservesTab() {
        int pos = 3; // Position of the caret, between the two ll in the first hello
        String textInput = "hello\thello";
        String whatTextShouldBe = "helLO\thello";
        CaretPostionResultText textOutput = StringChangeNextWord.editNextWordUpperCase(pos, textInput);
        assertEquals(whatTextShouldBe, textOutput.resText);
    }

    @Test
    public void editNextWordUpperCasePreservesDoubleSpace() {
        int pos = 5; // Position of the caret, at the first space
        String textInput = "hello  hello";
        String whatTextShouldBe = "hello  HELLO";
        CaretPostionResultText textOutput = StringChangeNextWord.editNextWordUpperCase(pos, textInput);
        assertEquals(whatTextShouldBe, textOutput.resText);
    }

    @Test
    public void editNextWordUpperCaseIgnoresTrailingWhitespace() {
        int pos = 5; // First space
        String textInput = "hello  ";
        String whatTextShouldBe = "hello  ";
        CaretPostionResultText textOutput = StringChangeNextWord.editNextWordUpperCase(pos, textInput);
        assertEquals(whatTextShouldBe, textOutput.resText);
        // Expected caret position is right after the last space, which is index 7
        assertEquals(7, textOutput.caretPos);
    }

    @Test
    public void editNextWordToEmptyTrimsTrailingWhitespace() {
        int pos = 5; // First space
        String textInput = "hello  ";
        String whatTextShouldBe = "hello";
        CaretPostionResultText textOutput = StringChangeNextWord.editNextWordToEmpty(pos, textInput);
        assertEquals(whatTextShouldBe, textOutput.resText);
        assertEquals(pos, textOutput.caretPos);
    }

    @Test
    public void editPreviousWordToEmptyTrimsPreceedingWhitespace() {
        int pos = 1; // Second space
        String textInput = "  hello";
        // One space should be preserved since we are deleting everything preceding the second space.
        String whatTextShouldBe = " hello";
        CaretPostionResultText textOutput = StringChangeNextWord.editPreviousWordToEmpty(pos, textInput);
        assertEquals(whatTextShouldBe, textOutput.resText);
        // The caret should have been moved to the start.
        assertEquals(0, textOutput.caretPos);
    }

    @Test
    public void editNextWordUpperCasePreservesMixedSpaceNewLineTab() {
        int pos = 5; // Position of the caret, after first hello
        String textInput = "hello \n\thello";
        String whatTextShouldBe = "hello \n\tHELLO";
        CaretPostionResultText textOutput = StringChangeNextWord.editNextWordUpperCase(pos, textInput);
        assertEquals(whatTextShouldBe, textOutput.resText);
    }

    @Test
    public void editNextWordLowerCaseEditsTheNextWord() {
        int pos = 5; // Position of the caret, right at the space
        String textInput = "hello HELLO";
        String whatTextShouldBe = "hello hello";
        CaretPostionResultText textOutput = StringChangeNextWord.editNextWordLowerCase(pos, textInput);
        assertEquals(whatTextShouldBe, textOutput.resText);
    }

    @Test
    public void editNextWordToEmptyRemovesFromPositionUpToNextWord() {
        int pos = 3; // Position of the caret, between the two "ll in the first hello"
        String textInput = "hello hello";
        String whatTextShouldBe = "hel hello";
        CaretPostionResultText textOutput = StringChangeNextWord.editNextWordToEmpty(pos, textInput);
        assertEquals(whatTextShouldBe, textOutput.resText);
    }

    @Test
    public void editNextWordToEmptyRemovesNextWordIfPositionIsInSpace() {
        int pos = 5; // Position of the caret, atfer the first hello"
        String textInput = "hello person";
        String whatTextShouldBe = "hello";
        CaretPostionResultText textOutput = StringChangeNextWord.editNextWordToEmpty(pos, textInput);
        assertEquals(whatTextShouldBe, textOutput.resText);
    }

}
