import java.util.Scanner; //this library is for the read the anser from the player
/**
 * Η κλαση TrueAn αναπαριστα το παιχνδι Σωστή απάντηση
 * This class is the game TrueAnswer
 * */
public class LogicTrueAn {

    /**
     * κατακσευαστης
     * constractur
     * */
    public void LogicTrueAnueAn() { }

    /**
     *η μεδοθος startGame ελενχει σε ποια ερωτηση ειναι ο παιχτης.Πρεπει να ολοκληρωσει 4 ερωτησεις του παιχνιδιου TrueAn
     * για να παει στον επομενο γυρο
     * @param player1 αναπαριστα τον παιχτη
     * @param question αναπαριστα μια ερωτηση
     * @param round αναπαριστα ενα ο ορισμα της round οπου περνουμε πληροφοριες σε ποια ερωτηση ειναι ο παιχτης
     *
     * The method startGame check the number of question is the player.Player must answer in 4 questions from the game TrueAn
     * to go in another round
     *  * @param player1 is the player
     *  * @param question ,is question
     *  * @param round ,is the round
     *
     * */
    public void startGame(Player player1, QuestionAndAnswer question , Round round)
    {
        LogicTrueAn trueAn= new LogicTrueAn();
        trueAn.gameTrue(player1,question);
        System.out.printf("%s have %d points%n",player1.getPlayer(),player1.getPoints());

        round.NumberQuestions();
        if (round.getNumrQuestions()>0){
            System.out.println("next question");
        }
        else{
            round.NumberQuestions();
        }
    }

    /**Η μεθοδος gameTrue οταν  καλλειται δειχνει στο παιχτη την ερωτηση και του εμφανιζει της 4 πιθανες απαντησεις οταν
     *ο παιχτης διαλεξει μια απο της απαντησεις ελενχεται η απαντηση του και αν ειναι σωστη
     *προσθετοναι στους ποντους του +1000 ποντοι.
     @param player1  αναπαριστα τον παιχτη
     @param question αναπαριστα την ερωτηση
      **this method when called shows the question and for answers when the player choose one of the recommended answers
      **the method call a method from question to check if the answer is correct
      **If the answer is correct then added 1000 points in player points.
     **/
    public void gameTrue(Player player1, QuestionAndAnswer question)
    {
        System.out.println("Category Of Question " +question.getCategory());
        System.out.println(question.getQuestion());
        question.printAnswers();

        Scanner sc= new Scanner(System.in); //System.in is a standard input stream.
        String answerOfPlayer= sc.nextLine(); //reads string.

        if(question.trueAnswer(answerOfPlayer)){
            player1.updatePointsOfPlayer(1000);
        }


    }

}
