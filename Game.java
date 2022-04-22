import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
/**H κλαση Game εχει ως μεταβλητες εναν παιχτη και τις ερωτησεις και καλει τυχαια τα παιχνιδια
 * The class GAME have a player and questions and call random the games to play with player.*/


public class Game {
    private final Player player1;
    private final Player player2;
    private final Questions w ;

    /**
     * Kατασκευαστης Game ο οποιος δημιουργει εναν παιχτη, ζητα ερωτησεις και διαβαζει ολες τις ερωτησεις.*/
    public Game()throws FileNotFoundException{
        player1= new Player("ANNA");
        player2=new Player("Bill");
        w= new Questions();
        w.reader();
    }
    public Questions getW(){
        return w;
    }

    /**Η μεθοδος playGame1 ξεκιναει καθε γυρο παιχνιδιου για εναν παιχτη
     * The method playGame1 start a game for one player */
    public void playGame1() {
        int pl1=1;
        Round round1 = new Round();
        Round round2 = new Round();
        Round round3 = new Round();
        Round round4 = new Round();
        Round round5 = new Round();
        System.out.print("First round you have 5 rounds");
        playRound(round1,pl1);
        System.out.print("you have 4 rounds");
        playRound(round2,pl1);
        System.out.print("you have 3 rounds");
        playRound(round3,pl1);
        System.out.print("you have 2 rounds");
        playRound(round4,pl1);
        System.out.print("you have 1 rounds");
        playRound(round5,pl1);

    }
    /**
     * Η μεθοδος playGame2 ξεκιναει καθε γυρο παιχνιδιου για δυο παιχτες
     * The method playGame2 starts game for two players
     * */

    public void playGame2() {
        int pl2=2;
        Round round1 = new Round();
        Round round2 = new Round();
        Round round3 = new Round();
        Round round4 = new Round();
        Round round5 = new Round();
        System.out.print("First round you have 5 rounds");
        playRound(round1,pl2);
        System.out.print("you have 4 rounds");
        playRound(round2,pl2);
        System.out.print("you have 3 rounds");
        playRound(round3,pl2);
        System.out.print("you have 2 rounds");
        playRound(round4,pl2);
        System.out.print("you have 1 rounds");
        playRound(round5,pl2);

    }

    public String giveTypeOfRound(){
        ArrayList<String> c=new ArrayList<>();
        //c.add("TrueAn");
        //c.add("BetGame");
        //c.add("FastAnser");
        c.add("Chronometer");
        //c.add("Thermometer");
        Collections.shuffle(c);
        return c.get(0);
    }


    /**
     * Συναρτηση giveTypeOfRound1 η οποια επιστρεφει ενα String το οποιο υποδηλωνει τον τυπο του γυρου και επιλεγεται
     * τυχαια για παιχνιδια με εναν παιχτη
     * @return επιστρεφει ενα String το οποιο υποδηλωνει τον τυπο του γυρου
     */
    public String giveTypeOfRound1(){
        ArrayList<String> c=new ArrayList<>();
        c.add("TrueAn");
        c.add("BetGame");
        Collections.shuffle(c);
        return c.get(0);
    }
    /**
     * Συναρτηση giveTypeOfRound2 η οποια επιστρεφει ενα String το οποιο υποδηλωνει τον τυπο του γυρου και επιλεγεται
     * τυχαια για παιχνιδια με δυο παιχτων
     * @return επιστρεφει ενα String το οποιο υποδηλωνει τον τυπο του γυρου
     */
    public String giveTypeOfRound2(){
        ArrayList<String> c=new ArrayList<>();
        c.add("FastAnser");
        c.add("ThermometerGame");
        Collections.shuffle(c);
        return c.get(0);
    }
    /**
     * Συναρτηση playRound η οποια δεχεται μια παραμετρο τυπου Round. Καλωντας την συναρτηση giveTypeOfRound και την
     * συναρτηση giveQuestionAndAnswer ξεκιναει να παιζει τον γυρο αναλογα σε ποια κατηγορια επιλεχθηκε (τυχαια) απο την
     * giveTypeOfRound.
     * @param round περιεχει ολες τις ιδιοτητες για εναν γυρο
     */
    public void playRound(Round round,int pl){
        String typeOfRound="";
        if(pl==1)
        { typeOfRound = giveTypeOfRound1();}
        else
        { typeOfRound = giveTypeOfRound2();}
        while (round.getNumrQuestions()>0){
            QuestionAndAnswer n=w.giveQuestionAndAnswer();
            startRound(typeOfRound , n , round);
        }
    }

    /**
     * η συναρτηση δεχεται τον τυπο του παιχνιδιου ,την δομη της ερωτησης, τις ιδιοτητες του γυρου
     * και αναλογως καλει τις συναρτησεις για να ξεκινησει το παιχνιδι με τον καταλληλο τυπο
     * @param typeOfRound περιεχει τον τυπο του γυρου
     * @param n περιεχει την δομη της ερωτησης
     * @param round περιεχει τις ιδιοτητες του γυρου
     */
    public void startRound(String typeOfRound , QuestionAndAnswer n , Round round){
        switch (typeOfRound) {
            case "TrueAn": {
                LogicTrueAn a = new LogicTrueAn();
                a.startGame(player1, n, round);
                break;
            }
            case "BetGame":
                LogicBetGame e = new LogicBetGame();
                e.startBetGame(player1, n, round);
                break;
            case "FastAnser":
                LogicFastAnser q = new LogicFastAnser();
                q.GameF(player1, player2, n, round);

                break;
            case "ThermometerGame": {
                LogicThermometerGame a = new LogicThermometerGame();
                a.GameTe(player1, player2, n, round);
                break;
            }
        }

    }


}