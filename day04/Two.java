package day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

class Two {
    static List<Card> cards = new ArrayList<>();

    // To cache how much cards are won
    static int[] cache;

    // Number of cards a given card wins
    static int cardsWon(Card c) {
        int matches = c.matches();
        if (matches == 0)
            return 1;

        if (cache[c.cardIdx] != -1)
            return cache[c.cardIdx];

        int wonCards = 0;
        for (int i = 1; i <= matches; i++) {
            int wonCardIdx = c.cardIdx + i;
            if (wonCardIdx < cards.size()) {
                wonCards += cardsWon(cards.get(wonCardIdx));
            }
        }

        // Original card is always there
        wonCards++;
        cache[c.cardIdx] = wonCards;
        return wonCards;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Provide input file!");
            return;
        }

        try {
            File f = new File(args[0]);
            Scanner reader = new Scanner(f);

            while (reader.hasNextLine()) {
                Card card = Card.fromString(reader.nextLine());
                cards.add(card);
            }

            // Empty the cache
            cache = new int[cards.size()];
            for (int i = 0; i < cards.size(); i++)
                cache[i] = -1;

            int totalCards = 0;
            for (Card c : cards) {
                totalCards += cardsWon(c);
            }

            System.out.println(totalCards);
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}