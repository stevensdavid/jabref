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
}
