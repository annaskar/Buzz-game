import java.util.Scanner;
/**
 * H Κλαση LogicThermometreGame υλοποιει το παιχνιδι θερμομετρο
 *The class LogicThermometreGame is the logic for the thermotre game
 *
 * */
public class LogicThermometerGame {


    /**
     * κατασκευαστης
     * constructor
     */
    public LogicThermometerGame() {
    }

    LogicFastAnser a = new LogicFastAnser();

    /**
     * H μεθοδος coints δεχεται
     *
     * @param question        η ερωτηση / question
     * @param player1         o παιχτης Α/ the player A
     * @param player2         Ο παιχτης Β/ the player B
     * @param answerOfPlayer1 Η απαντηση ενος παιχτη /Answer for one player
     * @param answerOfPlayer2 Η απαντηση ενος παιχτη /Answer for one player
     *                        και καλει την μεθοδο Menu2 της LogicFastAn και αναλογα δινει την σωστη απαντηση
     *                        Αν η πρωτη απαντηση ειναι το παιχτη Α και ειναι σωστη τοτε ο παιχτης Α περνει +1 στις σωστες απαντησεις του
     *                        Αν η πρωτη απαντηση ειναι το παιχτη Β και ειναι σωστη τοτε ο παιχτης Β περνει +1 στις σωστες απαντησεις του
     *                        Αν η πρωτη απαντηση ειναι το παιχτη Α και ειναι λαθος τοτε ο παιχτης Α αλλα ο Β παιχτης εχει απαντησει σωστα περνει +1 στις σωστες ερωτησεις του
     *                        Αν η πρωτη απαντηση ειναι το παιχτη Β και ειναι λαθος τοτε ο παιχτης Β αλλα ο Α παιχτης εχει απαντησει σωστα περνει +1 στις σωστες ερωτησεις του
     *                        <p>
     *                        Call the method Menu2 from LogicFastAn and give which player is anwer correct
     *                        if the first answer is the player A and is correct then first player take +1 in the corect answers
     *                        if the first answer is the player B and is correct then first player take +1 in the corect answers
     *                        if the first answer is the player A and is NOT correct then first player But the player B is anwer correct then player B take +1 in the correct anwers
     *                        if the first answer is the player B and is NOT correct then first player But the player A is anwer correct then player A take +1 in the correct anwers
     */
    private void coints(QuestionAndAnswer question, Player player1, Player player2, String answerOfPlayer1, String answerOfPlayer2) {
        int Anser1 = a.menu2(question, answerOfPlayer1);
        int Anser2 = a.menu2(question, answerOfPlayer2);

        if (Anser1 == 1) {                             //AN O 1os ΠΑΙΧΤΗΣ ΕΙΝΑΙ Ο A B C D  ΚΑΙ ΑΠΑΝΤΗΣΗ ΣΩΣΤΑ +1 ΣΩΣΤΗ ΑΠΑΝΤΗΣΗ
            player1.correctAnserws();

        }
        if (Anser1 == 2) {                           //ΑΝ Ο ΠΡΩΤΟΣ ΠΑΙΧΤΗΣ ΕΙΝΑΙ Ο Q W E R ΚΑΙ ΑΠΑΝΤΗΣΗ ΠΡΩΤΟΣ ΣΩΣΤΑ +1 ΣΩΣΤΗ ΑΠΑΝΤΗΣΗ
            player2.correctAnserws();
        }

        if (Anser1 == -1) {                             //AN O 1os ΠΑΙΧΤΗΣ ΕΙΝΑΙ Ο A B C D  ΚΑΙ ΑΠΑΝΤΗΣΗ ΛΑΘΟΣ
            if (Anser2 == 2)                           //ΑΝ Ο 2ος ΠΑΙΧΤΗΣ ΕΙΝΑΙ Ο Q W E R ΚΑΙ ΑΠΑΝΤΗΣΗ 2ος ΣΩΣΤΑ +1 ΣΩΣΤΗ ΑΠΑΝΤΗΣΗ
                player2.correctAnserws();
        }
        if (Anser1 == -2) {                           //ΑΝ Ο ΔΕΥΤΕΡΠΣ ΠΑΙΧΤΗΣ ΕΙΝΑΙ Ο Q W E R ΚΑΙ ΑΠΑΝΤΗΣΗ ΛΑΘΟΣ
            if (Anser2 == 1)                           // ΑΝ Ο 1ος ΠΑΙΧΤΗΣ ΑΠΑΝΤΗΣΗ ΣΩΣΤΑ ΜΕΤΑ ΕΙΝΑΙ +1 ΣΤΙΣ ΣΩΣΤΕΣ ΑΠΑΝΤΗΣΕΙΣ ΤΟΥ
                player1.correctAnserws();
        }


    }

    /**
     * Η συναρτηση GameTe δεχεται
     *
     * @param player1  αναπαριστατον παιχτη Α/is the player A
     * @param player2  αναπαριστατον παιχτη B/is the player B
     * @param question αναπαριστατα τις ερωτησεις / Is questions
     * @param round    αναπαριστατα τους γυρους/ is the rounds
     *                 καλει το menu της LogicFastAn μετα δειχνει τις ερωτησεις και δεχεται 2 απαντησεις μια για τον παιχτη Α  και μια για τον παιχτη Β καλει την μεθοδο coints  μετα εμφανειζει ποσες σωστες απαντησεις εχει ο καθε παιχτης καθως και τους ποντους του
     *                 στην συνεχεια ελενχει ποσες ερωτησεις εχουν απομεινει και αν απαντησεις 5 σωστες δημιουργητε ενας νεος γυρος
     *                 <p>
     *                 The method GameTe call the menu from LogicFastAn After shows a question and take the answers from players one for each player .After call the method coints and after print How correct anwers have each player and how many points have
     *                 after chek how questions have to finsh the rounds if one of them have 5 correct answrer go to the next round
     */


    public void GameTe(Player player1, Player player2, QuestionAndAnswer question, Round round) {

        System.out.println("Temperatyre GAME");
        a.menu();


        System.out.println(question.getQuestion());

        question.printAnswers();

        Scanner sc = new Scanner(System.in); //System.in is a standard input stream.
        String answerOfPlayer1 = sc.nextLine(); //reads string.

        Scanner sc1 = new Scanner(System.in); //System.in is a standard input stream.
        String answerOfPlayer2 = sc1.nextLine(); //reads string.


        coints(question, player1, player2, answerOfPlayer1, answerOfPlayer2);

        System.out.print("player 1: " + player1.getCorectA() + "\n");
        System.out.print("player 2: " + player2.getCorectA() + "\n");


        System.out.println(player1.getPoints());

        System.out.println(player2.getPoints());

        round.NumberQuestions();
        if (round.getNumrQuestions() > 0) {
            System.out.println("next question");
        } else if (player1.getCorectA() >= 5 || player2.getCorectA() >= 5) {
            round.NumberQuestions();
        }
    }

}


