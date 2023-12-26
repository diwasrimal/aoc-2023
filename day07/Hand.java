package day07;

class Hand implements Comparable<Hand> {

    // Hand types
    final static int fiveOfKind = 7;
    final static int fourOfKind = 6;
    final static int fullHouse = 5;
    final static int threeOfKind = 4;
    final static int twoPair = 3;
    final static int onePair = 2;
    final static int highCard = 1;

    final static char[] cardSymbols = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};

    String origCards;
    String bestCards;
    boolean considerJoker;
    int bid;
    int type;

    Hand(String cards, int bid, boolean considerJoker) {
        this.origCards = cards;
        this.bestCards = cards;
        this.bid = bid;
        this.type = handType(cards);
        this.considerJoker = considerJoker;

        // Find the best possible card set if joker is considered
        if (considerJoker && cards.contains("J")) {
            for (int i = 0; i < cardSymbols.length; i++) {
                char rep = cardSymbols[i];
                String newCards = cards.replace('J', rep);
                int newType = handType(newCards);
                if (newType > this.type) {
                    this.type = newType;
                    this.bestCards = newCards;
                }
            }
        }
    }

    public int compareTo(Hand h) {
        if (this.type > h.type) {
            return 1;
        } else if (this.type < h.type) {
            return -1;

        // Individual card comparision is done with original set of cards
        // This ensures we use value of J, not the value its pretending to be
        } else {
            for (int i = 0; i < this.origCards.length(); i++) {
                int our = cardIdx(this.origCards.charAt(i));
                int their = cardIdx(h.origCards.charAt(i));
                if (our > their) {
                    return 1;
                } else if (their > our) {
                    return -1;
                }
            }
        }
        return 0;
    }

    int handType(String cards) {
        int[] counts = new int[13];
        for (int i = 0; i < cards.length(); i++) {
            char c = cards.charAt(i);
            counts[cardIdx(c)]++;
        }

        int[] repeats = new int[6];
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                repeats[counts[i]]++;
            }
        }

        if (repeats[5] > 0)
            return fiveOfKind;
        if (repeats[4] > 0)
            return fourOfKind;
        if (repeats[3] > 0)
            return repeats[2] > 0 ? fullHouse : threeOfKind;
        if (repeats[2] == 2)
            return twoPair;
        if (repeats[2] == 1)
            return onePair;
        return highCard;
    }

    int cardIdx(char cardType) {
        int idx = 0;
        for (; idx < cardSymbols.length; idx++) {
            if (cardSymbols[idx] == cardType) {
                break;
            }
        }

        // If joker is considered, J will have least value i.e. 0
        // values from 2 .. T will have one more
        if (this.considerJoker) {
            if (cardType == 'J')
                return 0;
            if (idx < 10)
                return idx + 1;
        }

        return idx;
    }

}