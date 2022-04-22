import java.util.Scanner;
/**
 * Η κλαση BetGame αναπαριστα το παιχνιδι Ποντάρισμα
 * This class is the game BetGame (player bet some points before answer and if he answer right take the points) -Ποντάρισμα-*/
public class LogicBetGame {
    private QuestionAndAnswer question;
    private Player player1;

    public LogicBetGame(){}

    /**
     *Κατασκευαστης της BetGame κατασκευαζει τα πεδια question και player
     *Constractur for BetGame
     * */
    public LogicBetGame(QuestionAndAnswer question , Player player1){
        this.question = question;
        this.player1 = player1;
    }
    /**
     *η μεδοθος startBetGame ελενχει σε ποια ερωτηση ειναι ο παιχτης.Πρεπει να ολοκληρωσει 4 ερωτησεις του παιχνιδιου
     * για να παει στον επομενο γυρο
     * @param player1 αναπαριστα τον παιχτη
     * @param question αναπαριστα μια ερωτηση
     * @param round αναπαριστα ενα ο ορισμα της round οπου περνουμε πληροφοριες σε ποια ερωτηση ειναι ο παιχτης
     *
     * The method startBetGame check the number of question is the player.Player must answer in 4 questions from the game
     * to go in another round
     *  * @param player1 is the player
     *  * @param question ,is question
     *  * @param round ,is the round
     *
     * */
    public void startBetGame(Player player1,QuestionAndAnswer question,Round round)
    {
        LogicBetGame Bet= new LogicBetGame(question,player1);
        Bet.game();
        System.out.printf("%s have %d points%n",player1.getPlayer(),player1.getPoints());

        round.NumberQuestions();
        if (round.getNumrQuestions()>0){
            System.out.println("next question");
        }
        else{
            round.NumberQuestions();
        }
    }

    /**
     *Η μεθοδος menu εκπτυπωνει ενα μενου στον παιχτη για να διαλεξει ποσους ποντους θελει να πονταρει
     * The method menu print a menu to player ,to choose how points want to bet */

    private void menu() {
        System.out.print("You must to bet a points\n");
        System.out.print("If you want to bet 250 please tap a \n");
        System.out.print("If you want to bet 500 please tap b\n");
        System.out.print("If you want to bet 750 please tap c\n");
        System.out.print("If you want to bet 1000 please tap d \n");
    }
    /**Η μεθοδος menu2 διαβαζει απο τον χρηστη την απαντηση του πονταρισματος που εδωσε και επιστρεφει το αντιστοιχο πονταριμσα
     * @return 250 επιστρεφει 250 ποντους οτι πονταρε
     * @return 500 επιστρεφει 500 ποντους οτι πονταρε
     * @return 750 επιστρεφει 750 ποντους οτι πονταρε
     * @return 1000 επιστρεφει 1000 ποντους οτι πονταρε
     * The method menu2 read from the player how points want to bet and return the points
     *@return 250 returns 250 points
     *@return 500 returns 500 points
     *@return 750 returns 750 points
     *@return 1000 returns 1000 points
     * */
    private int menu2() {
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();//System.in is a standard input stream.
        while (!a.equals(" "))
        {if (a.equals("a")) {
            return 250;
        } else if (a.equals("b")) {
            return 500;
        } else if (a.equals("c")) {
            return 750;
        } else if (a.equals("d")) {
            return 1000;
        } else
            System.out.println("Chose correct please :( \n");
            a = sc.nextLine();//System.in is a standard input stream.
        }
        return 0;
    }
    /**
     * Η μεθοδος game οταν καλειτε ζητα απο τον παιχτη να πονταρει και εμφανιζει την ερωτηση και τις πιθανες απαντησεις μολις ο παιχτης
     * απαντησει αν η απαντηση του ειναι σωστη προσθηθονται οι ποντοι που πονταρε στους π οντους του αλλιως αφαιρουνται.
     *
     * The method game when called .ask from the player to bet and print the question and the answers .If player answer corect the bet
     * points are add in total points ,if the player does not answer corect the bet points are subtractive from the total points
     * */


    public void game() {
        int point;
        menu();
        point =menu2();
        System.out.println(question.getQuestion());

        question.printAnswers();

        Scanner sc = new Scanner(System.in); //System.in is a standard input stream.
        String answerOfPlayer = sc.nextLine(); //reads string.

        if (!question.trueAnswer(answerOfPlayer)) {
            point = point * (-1);
        }
        player1.updatePointsOfPlayer(point);
    }
}