package data.structure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackExampleTest {

    @Test
    public void parse_shouldReturnCorrectAnswer() {
        assertEquals("Success", StackExample.BracketsParser.parse("([](){([])})"));
        assertEquals("Success", StackExample.BracketsParser.parse("{[]}()"));
        assertEquals("Success", StackExample.BracketsParser.parse("foo(bar);"));
        assertEquals("5", StackExample.BracketsParser.parse("()[]}"));
        assertEquals("7", StackExample.BracketsParser.parse("{{[()]]"));
        assertEquals("10", StackExample.BracketsParser.parse("foo(bar[i);"));
        assertEquals("1", StackExample.BracketsParser.parse("{"));
        assertEquals("3", StackExample.BracketsParser.parse("{[}"));
        assertEquals("1", StackExample.BracketsParser.parse("}"));
        assertEquals("1", StackExample.BracketsParser.parse("({["));
        assertEquals("3", StackExample.BracketsParser.parse("{}([]"));
        assertEquals("3", StackExample.BracketsParser.parse("{}([]{"));
    }

}