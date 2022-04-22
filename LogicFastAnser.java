import java.util.Scanner;
/**
 * The class FastAnser is the game fast answer "γρηγορη απαντηση"
 * Η κλαση FastAnser αναπαριστα το παιχνιδι γρηγορη απαντηση
 * */
public class LogicFastAnser {

    /**
     * κατακσευαστης για FastAnser
     * constractur for FastAnser
     * */
    public LogicFastAnser(){}

    /**
     * Η μεθοδος menu εμφανιζει ενα μενου για τα πληκτρα των 2 παιχτων
     * the method menu print a menu which is say the buttons for 2 players
     * */
    public void menu()
    {
        System.out.print("Player a have the bottons a b c d \n");
        System.out.print("Player b have the bottons q w e r \n");

    }

    /**
     * Η μεθοδος menu2 δεχεται
     * @param questions -ενα ορισμα τις QuestionAndAnswer-/-a argument QuestionAndAnswer-
     * @param a -ενα string οπου ειναι η απαντηση -/ -a string which is a answer -
     * και επιστρεφει
     * @return 1 -Aν ο πρωτος παιχτης απαντησε σωστα- /-If first player answer correct-
     * @return -1 -Αν ο πρωτος παιχτης απαντησε λαθος-/ -If first player answer wrong-
     * @return 2 -Αν ο δευτερος παιχτης απαντησε σωστα-/ -If second player answer correct-
     * @return -2 -Αν ο δευτερος παιχτης απαντησε λαθος-/-If second player answer wrong-
     * @return 0 -Αν η απαντηση δεν ειναι ουτε του Α ουτε του Β -/ -if the string is not a answer from player A or player B-
     *ελενχει αν η απαντηση που πληκτρολογισε ο χρηστης ανηκει στον Α παιχτη Ή στον Β παιχτη
     * αν η απαντηση ανηκει στον Α παιχτη ελενχει αν ειναι σωστη,αν ειναι σωστη επιστρεφει 1,αν ειναι λαθος επιστρεφει -1
     * αν η απαντηση ανηκει στον Β παιχτη μετατρεπει την απαντηση του σε  a b c d αντισοτοιχα και ελενχει αν ειναι σωστη αν ειναι σωστη επιστρεφει 2 ,αν ειναι λαθος -2
     * αν η απαντηση δεν ανηκει ουτε στον Α παιχτη ουτε στοον Β τοτε επιστρεφει 0
     *
     *the method menu2 check if the answer is from player A or player B and also check if the answer is correct and return 1,-1,2,-2,0 in any case.
     * */
    public int  menu2(QuestionAndAnswer questions,String a)
    {
        if ( a.equals("a")|| a.equals("b")|| a.equals("c")||a.equals("d"))
        { if(questions.trueAnswer(a))
        { return 1;}
        else
        {return -1;}}
        else if(a.equals("q")||a.equals("w")||a.equals("e")||a.equals("r"))
        { {
            switch (a) {
                case "q":
                    a = "a";
                    break;
                case "w":
                    a = "b";
                    break;
                case "e":
                    a = "c";
                    break;
                case "r":
                    a = "d";
                    break;
            }
        }

            if(questions.trueAnswer(a))
            {return 2;}
            else
            {return -2;}}
        else
            return 0;
    }





    /**
     * Η μεθοδος pointsOfGame ειναι μια μεθοδος που μοιραζει τους ποντους σε καθε παιχτη αναλογα με το αν η απαντη του ειναι σωστη
     * Δεχετε σαν ορισματα
     * @param question ενα ορισμα της QuestionAndAnswer /a paramet from QuestionAndAnswer
     * @param player1 ειναι ο παιχτης Α / is the player A
     * @param player2 ειναι ο παιχτης Β /Is the player B
     * @param answerOfPlayer1 ειναι η απαντηση ενος παιχτη /Is a anwer for a player
     * @param answerOfPlayer2 ειναι η απαντηση ενος παιχτη /Is a anwer for a player
     * Αρχηκα δεχεται τιμες απο την menu2 και ελενχει
     * 1.Αν η πρωτη απαντηση ειναι το πρωτου A και σωστη, βαζει στον A παιχτη +1000ποντους εννοω αν και ο B παιχτης εχει απαντησει σωστα του δινει +500ποντους
     * 2.Αν η πρωτη απαντηση ειναι του B παιχτη και σωστη ,δινει στον B παιχτη +1000 ποντους και αν ο Α παιχτης απαντησει σωστα του δινει +500 ποντους
     * 3.Αν η πρωτη απαντηση ειναι του A παιχτη αλλα ειναι λαθος,δινει στον Α του δινει +0 ποντους και αν ο Β παιχτης απαντησει σωστα περνει +1000ποντους
     * 4.Αν η πρωτη απαντηση ειναι του Β παιχτη αλλα ειναι λαθος,δινει στον Β του δινει +0 ποντους και αν ο Α παιχτης απαντησει σωστα περνει +1000ποντους
     *
     * Check if
     *1 the first answer is correct and from player A, put in player A  +1000 points and also if the anwser from player B is correct take  +500points
     *2 the first answer is correct and from player B, put in player B  +1000 points and also if the anwser from player A is correct take  +500points
     *3.If the first answer is from player A and is wrong,player Α take+0 points but if the anwer from player Β and is correct take +1000ποντους
     *4.If the first answer is from player B and is wrong,player B take+0 points but if the anwer from player A and is correct take +1000ποντους
     *  *
     * */
    public void pointsOfGame(QuestionAndAnswer question,Player player1,Player player2,String answerOfPlayer1,String answerOfPlayer2)
    {
        int Anser1=menu2(question,answerOfPlayer1);
        int Anser2=menu2(question,answerOfPlayer2);

        if(Anser1==1) {                             //AN O 1os ΠΑΙΧΤΗΣ ΕΙΝΑΙ Ο A B C D  ΚΑΙ ΑΠΑΝΤΗΣΗ ΣΩΣΤΑ +1000 ΠΟΝΤΟΥΣ
            player1.updatePointsOfPlayer(1000);
            if(Anser2==2)                           //ΑΝ Ο 2ος ΠΑΙΧΤΗΣ ΕΙΝΑΙ Ο Q W E R ΚΑΙ ΑΠΑΝΤΗΣΗ 2ος ΣΩΣΤΑ +500 ΠΟΝΤΟΥΣ
                player2.updatePointsOfPlayer(500);
        }
        if(Anser1==2) {                           //ΑΝ Ο ΠΡΩΤΟΣ ΠΑΙΧΤΗΣ ΕΙΝΑΙ Ο Q W E R ΚΑΙ ΑΠΑΝΤΗΣΗ ΠΡΩΤΟΣ ΣΩΣΤΑ +1000ΠΟΝΤΟΥΣ
            player2.updatePointsOfPlayer(1000);
            if(Anser2==1)                           // ΑΝ Ο 2ος ΠΑΙΧΤΗΣ ΑΠΑΝΤΗΣΗ ΣΩΣΤΑ ΜΕΤΑ ΕΙΝΑΙ +500
                player1.updatePointsOfPlayer(500);
        }

        if(Anser1==-1) {                             //AN O 1os ΠΑΙΧΤΗΣ ΕΙΝΑΙ Ο A B C D  ΚΑΙ ΑΠΑΝΤΗΣΗ ΛΑΘΟΣ +0 ΠΟΝΤΟΥΣ
            player1.updatePointsOfPlayer(0);
            if(Anser2==2)                           //ΑΝ Ο 2ος ΠΑΙΧΤΗΣ ΕΙΝΑΙ Ο Q W E R ΚΑΙ ΑΠΑΝΤΗΣΗ 2ος ΣΩΣΤΑ +1000 ΠΟΝΤΟΥΣ
                player2.updatePointsOfPlayer(1000);
        }
        if(Anser1==-2) {                           //ΑΝ Ο ΠΡΩΤΟΣ ΠΑΙΧΤΗΣ ΕΙΝΑΙ Ο Q W E R ΚΑΙ ΑΠΑΝΤΗΣΗ ΠΡΩΤΟΣ ΣΩΣΤΑ +0 ΠΟΝΤΟΥΣ
            player2.updatePointsOfPlayer(0);
            if(Anser2==1)                           // ΑΝ Ο 2ος ΠΑΙΧΤΗΣ ΑΠΑΝΤΗΣΗ ΣΩΣΤΑ ΜΕΤΑ ΕΙΝΑΙ +1000
                player1.updatePointsOfPlayer(1000);
        }



    }
    /**
     *H μεθοδος GameF δεχεται σαν ορισματα
     *@param player1 αναπαριστα τον παιχτη A/ Is a player A
     *@param player2 αναπαριστα τον παιχτη B/Is player B
     *@param question αναπαριστα τις ερωτησης/Are question
     *@param round αναπαριστα τους γυρους/is round
     * καλει την μενου και εμφανιζει τα πιθανα πληκτρα
     * διχνει μια ερωτησει και δεχεται 2 απαντησεις καλει την PointsOfGame εμφανιζει τους πονοτυς του PlayerA και του player B Ελενχει ποσες ερωτησεις εχουν μεινει ακομα ,
     * οταν τελειωσουν οι ερωτησεις αλλαζει γυρο.
     *
     *the method GameF call the function menu which is print the possibol bottons for the players shows a question and take 2 answers one for a players
     * call the method PointsOfGame,print the points ,check how many questions have and when the questions are finsh change the round

     **/

    public void GameF(Player player1,Player player2,QuestionAndAnswer question,Round round) {

        menu();

        System.out.println(question.getQuestion());

        question.printAnswers();

        Scanner sc = new Scanner(System.in); //System.in is a standard input stream.
        String answerOfPlayer1 = sc.nextLine(); //reads string.

        Scanner sc1 = new Scanner(System.in); //System.in is a standard input stream.
        String answerOfPlayer2= sc1.nextLine(); //reads string.


        pointsOfGame(question,player1,player2,answerOfPlayer1,answerOfPlayer2);


        System.out.println(player1.getPoints());

        System.out.println(player2.getPoints());

        round.NumberQuestions();
        if (round.getNumrQuestions()>0){
            System.out.println("next question");
        }
        else{
            round.NumberQuestions();
        }
    }

}
