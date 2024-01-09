package blackjack;

public class Card {

    private int number;
    private String suite;

    private String name;

    public Card(){

    }

    public Card(int number, String suite)
    {
        this.number = number;
        this.suite = suite;
        this.name = String.valueOf(number);
    }
    public Card(int number, String suite, String name)
    {
        this.number = number;
        this.suite = suite;
        this.name = name;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public void setSuite(String suite)
    {
        this.suite = suite;
    }

    public void setName(String name){this.name = name;}

    public int getNumber()
    {
        return number;
    }

    public String getSuite()
    {
        return suite;
    }

    public String getName(){return name;}

    public String toString()
    {
        return getNumber() + " of " + getSuite() +" FaceValue: "+ getName();
    }
}
