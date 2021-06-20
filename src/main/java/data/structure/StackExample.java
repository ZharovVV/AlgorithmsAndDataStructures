package data.structure;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * https://stepik.org/lesson/41234/step/1?unit=19818
 * <p>
 * Расстановка скобок в коде
 * Sample Input 1:
 * ([](){([])})
 * Sample Output 1:
 * Success
 * <p>
 * Sample Input 2:
 * ()[]}
 * Sample Output 2:
 * 5
 * <p>
 * Sample Input 3:
 * {{[()]]
 * Sample Output 3:
 * 7
 */
public class StackExample {

    public static class BracketsParser {

        public static String parse(String input) {
            return parse(input.toCharArray());
        }

        public static String parse(char[] inputCharArray) {
            Deque<Symbol<Bracket>> stack = new ArrayDeque<>();
            for (int i = 1; i <= inputCharArray.length; ++i) {
                char inputChar = inputCharArray[i - 1];
                Bracket bracket = Bracket.getBracketByChar(inputChar);
                if (bracket == null) {
                    continue;
                }
                if (stack.isEmpty() && bracket.isClosing()) {
                    return String.valueOf(i);
                }
                if (bracket.isOpening()) {
                    Symbol<Bracket> bracketSymbol = new Symbol<>(i, bracket);
                    stack.push(bracketSymbol);
                }
                if (bracket.isClosing()) {
                    Bracket openingBracket = stack.pop().getSymbolType();
                    if (openingBracket.getPairCharacter() != bracket.getCharacter()) {
                        return String.valueOf(i);
                    }
                }
            }
            return stack.isEmpty() ? "Success" : String.valueOf(stack.peekLast().getIndex());
        }
    }

    public static class Symbol<SymbolType> {

        private final int index;
        private final SymbolType symbolType;

        public Symbol(int index, SymbolType symbolType) {
            this.index = index;
            this.symbolType = symbolType;
        }

        public int getIndex() {
            return index;
        }

        public SymbolType getSymbolType() {
            return symbolType;
        }
    }

    interface Bracket {
        boolean isOpening();

        boolean isClosing();

        char getCharacter();

        char getPairCharacter();

        static Bracket getBracketByChar(char character) {
            Bracket bracket;
            bracket = OpeningBracket.valueOf(character);
            if (bracket == null) {
                bracket = ClosingBracket.valueOf(character);
            }
            return bracket;
        }
    }

    public enum OpeningBracket implements Bracket {
        ROUND_OPENING_BRACKET('(', ')'),
        SQUARE_OPENING_BRACKET('[', ']'),
        OPENING_BRACE('{', '}');

        private final char character;
        private final char closingPairCharacter;

        OpeningBracket(char character, char closingPairCharacter) {
            this.character = character;
            this.closingPairCharacter = closingPairCharacter;
        }

        public static OpeningBracket valueOf(char character) {
            for (OpeningBracket openingBracket : OpeningBracket.values()) {
                if (openingBracket.character == character) {
                    return openingBracket;
                }
            }
            return null;
        }

        @Override
        public boolean isOpening() {
            return true;
        }

        @Override
        public boolean isClosing() {
            return false;
        }

        @Override
        public char getCharacter() {
            return character;
        }

        @Override
        public char getPairCharacter() {
            return closingPairCharacter;
        }
    }

    public enum ClosingBracket implements Bracket {
        ROUND_CLOSING_BRACKET(')', '('),
        SQUARE_CLOSING_BRACKET(']', '['),
        CLOSING_BRACE('}', '{');

        private final char character;
        private final char openingPairCharacter;

        ClosingBracket(char character, char openingPairCharacter) {
            this.character = character;
            this.openingPairCharacter = openingPairCharacter;
        }

        public static ClosingBracket valueOf(char character) {
            for (ClosingBracket closingBracket : ClosingBracket.values()) {
                if (closingBracket.character == character) {
                    return closingBracket;
                }
            }
            return null;
        }

        @Override
        public boolean isOpening() {
            return false;
        }

        @Override
        public boolean isClosing() {
            return true;
        }

        @Override
        public char getCharacter() {
            return character;
        }

        @Override
        public char getPairCharacter() {
            return openingPairCharacter;
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(BracketsParser.parse(scanner.nextLine()));
        }
    }
}