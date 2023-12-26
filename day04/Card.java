package day04;

import java.util.ArrayList;
import java.util.List;

class Card {
    List<Integer> winningNums;
    List<Integer> nums;
    int cardIdx;

    Card(int cardIdx, List<Integer> winningNums, List<Integer> nums) {
        this.cardIdx = cardIdx;
        this.winningNums = winningNums;
        this.nums = nums;
    }

    static Card fromString(String str) {
        String[] parts = str.split(": ");
        int cardIdx = Integer.parseInt(parts[0].split("Card ")[1].trim()) - 1;

        String numParts[] = parts[1].split(" [|] ");
        List<Integer> winningNums = new ArrayList<>();
        List<Integer> numsIHave = new ArrayList<>();

        for (String s : numParts[0].split(" ", 0)) {
            if (!s.equals("")) {
                winningNums.add(Integer.parseInt(s));
            }
        }
        for (String s : numParts[1].split(" ", 0)) {
            if (!s.equals("")) {
                numsIHave.add(Integer.parseInt(s));
            }
        }

        return new Card(cardIdx, winningNums, numsIHave);
    }

    int matches() {
        int match = 0;
        for (int winNum : this.winningNums) {
            for (int num : this.nums) {
                if (winNum == num)
                    match++;
            }
        }
        return match;
    }
}