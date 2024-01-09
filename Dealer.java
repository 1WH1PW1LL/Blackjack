package blackjack;

import java.util.Random;

public class Dealer {

    Random randomNumber = new Random();

    Card startingCard1;
    Card startingCard2;

    int cardNumber = randomNumber.nextInt(52);
    private int currentSum;

    public Dealer(Card startingCard1, Card startingCard2)
    {
        this.startingCard1 = startingCard1;
        this.startingCard2 = startingCard2;
    }

    public void setCurrentSum(int currentSum)
    {
        this.currentSum = currentSum;
    }

    public int getCurrentSum()
    {
        return currentSum;
    }

    public Card[] shuffle(Card[] cardDeck)
    {
        int numberOfShuffles = 1 ;//randomNumber.nextInt(1000);
        System.out.println("number of shuffles:" + numberOfShuffles);
        int countUp = 0;
        while(countUp < numberOfShuffles)
        {
            Card[] shuffledDeck = new Card[52];
            for(int count1 = 0,count2=0; count1 < 52;count2++)
            {
                shuffledDeck[count1++] = cardDeck[count2];
                shuffledDeck[count1++] = cardDeck[count2 +  26];
            }

            countUp++;
            cardDeck = shuffledDeck;
        }
        return cardDeck;
    }


}
