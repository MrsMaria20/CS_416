import java.util.Arrays;

/**
 * Ponitfex class for encrypting/decrypting strings.
 * @author Kristina Logan and CS 416
 * @version 2
 */
public class Pontifex {

    private Integer[] deck;

    /**
     * Initializes the Pontifex object with the provided interger array
     * representing the deck of cards.
     * @param deck The deck of cards used for the Pontifex algorithm
     */
    public Pontifex(Integer[] deck) {
        this.deck = deck;
    }

    /**
     * Finds the index of a card in the deck.
     * @param card The card value to find
     * @return int The index of the card
     */
    private int findCard(int card) {
        for (int i = 0; i < deck.length; i++) {
            if (deck[i] == card) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Moves a card down in the deck by a given number of positions,
     * wrapping around to the beginning if it moves past the end.
     * @param card The card value to move
     * @param positions The number of positions to move down
     */
    private void moveDown(int card, int positions) {
        int pos = findCard(card);
        // Remove the card from the deck
        Integer[] temp = new Integer[deck.length - 1];
        System.arraycopy(deck, 0, temp, 0, pos);
        System.arraycopy(deck, pos + 1, temp, pos, deck.length - pos - 1);

        // Calculate new position
        int newPos = pos + positions;
        if (newPos > temp.length) {
            newPos = newPos - temp.length;
        }

        // Insert the card at the new position
        System.arraycopy(temp, 0, deck, 0, newPos);
        deck[newPos] = card;
        System.arraycopy(temp, newPos, deck, newPos + 1, temp.length - newPos);
    }

    /**
     * Performs the triple cut: swaps everything above the first joker
     * with everything below the second joker.
     */
    private void tripleCut() {
        int posA = findCard(27);
        int posB = findCard(28);
        int first = Math.min(posA, posB);
        int second = Math.max(posA, posB);

        Integer[] upper = Arrays.copyOfRange(deck, 0, first);
        Integer[] middle = Arrays.copyOfRange(deck, first, second + 1);
        Integer[] lower = Arrays.copyOfRange(deck, second + 1, deck.length);

        // Lower goes to top, middle stays, upper goes to bottom
        int idx = 0;
        for (Integer c : lower) {
            deck[idx++] = c;
        }
        for (Integer c : middle) {
            deck[idx++] = c;
        }
        for (Integer c : upper) {
            deck[idx++] = c;
        }
    }

    /**
     * Performs the count cut: looks at the bottom card's value,
     * moves that many cards from the top to just above the bottom card.
     */
    private void countCut() {
        int bottomVal = deck[deck.length - 1];
        if (bottomVal == 27 || bottomVal == 28) {
            bottomVal = 27;
        }

        Integer[] top = Arrays.copyOfRange(deck, 0, bottomVal);
        Integer[] rest = Arrays.copyOfRange(deck, bottomVal, deck.length - 1);
        Integer bottomCard = deck[deck.length - 1];

        int idx = 0;
        for (Integer c : rest) {
            deck[idx++] = c;
        }
        for (Integer c : top) {
            deck[idx++] = c;
        }
        deck[idx] = bottomCard;
    }

    /**
     * generates the keystream value from the deck configuration.
     * @return int The keystream value
     */
    public int keystream() {
        while (true) {
            // Step 1: Move Joker A (27) down one card
            moveDown(27, 1);
            // Step 2: Move Joker B (28) down two cards
            moveDown(28, 2);
            // Step 3: Triple cut
            tripleCut();
            // Step 4: Count cut
            countCut();
            // Step 5: Calculate keystream value
            int topVal = deck[0];
            if (topVal == 27 || topVal == 28) {
                topVal = 27;
            }
            int keystreamCard = deck[topVal];
            // If it's a joker, repeat the process
            if (keystreamCard != 27 && keystreamCard != 28) {
                return keystreamCard;
            }
        }
    }

    /**
     * encrypts the message using the initial deck.
     * @param message The message to encrypt
     * @return String The encrypted meaage
     */
    public String encrypt(String message) {
        StringBuilder result = new StringBuilder();
        String upper = message.toUpperCase();
        for (int i = 0; i < upper.length(); i++) {
            char c = upper.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                int charNum = c - 'A';
                int ks = keystream();
                int encrypted = (charNum + ks) % 26;
                result.append((char) ('A' + encrypted));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * decrypts the message using the deck.
     * @param message The message to decrypt
     * @return String the decrypted message
     */
    public String decrypt(String message) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                int charNum = c - 'A';
                int ks = keystream();
                int decrypted = Math.floorMod(charNum - ks, 26);
                result.append((char) ('A' + decrypted));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * @return String that represents the deck of cards.
     */
    public String toString() {
        return Arrays.toString(deck);
    }

    /**
     * main function used for testing purposes.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        Pontifex p = new Pontifex(new Integer[]{19, 18, 21, 25,
            6, 17, 15, 27, 14, 22, 28, 20, 5, 12, 2, 13, 9, 16, 3, 7, 10, 23, 8,
            24, 4, 11, 10, 26});
        System.out.println(p.encrypt("hello"));

        p = new Pontifex(new Integer[]{22, 8, 25,
            23, 15, 5, 1, 12, 13, 26, 9, 18, 14, 6, 28, 19, 21, 17, 20, 3, 10,
            16, 27, 24, 11, 7, 4, 2});
        System.out.println(p.decrypt("UVCCU MNUFA"));
    }
}