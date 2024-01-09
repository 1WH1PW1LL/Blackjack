package blackjack;

public class Deck {

    private Card[] deckOfCards = new Card[52];
    private String[] faceValue = {"Jack","Queen","King","Ace"};

    public Deck() {
    }

    public Deck(Card[] deck)
    {
        deckOfCards = deck;
    }

    public void setDeckOfCards()
    {
        this.deckOfCards = deckOfCards;
        // Card card1 = new Card(1, "hearts");

        String currentSuite = "Hearts";

        for(int count1 = 1; count1 <= 4; count1++)
        {
            int royalty = 0;

            if(count1 == 1)
            {
                currentSuite = "Hearts";
            }
            if(count1 == 2)
            {
                currentSuite = "Diamonds";
            }
            if(count1 == 3)
            {
                currentSuite = "Clubs";
            }
            if(count1 == 4)
            {
                currentSuite = "Spades";
            }


            for(int count2 = 0; count2 <= 8; count2++)
            {
                deckOfCards[(count1-1)*13 + count2 + royalty] = new Card(count2+2, currentSuite);
            }
            for (String faceVal : faceValue) {
                if(faceVal.equals("Ace"))
                    deckOfCards[(count1-1)*13 + 9 + royalty++] = new Card(1, currentSuite, faceVal);
                else
                    deckOfCards[(count1-1)*13 + 9 + royalty++] = new Card(10, currentSuite, faceVal);
            }

        }
    }

    public Card[] getDeckOfCards()
    {
        return deckOfCards;
    }

}

