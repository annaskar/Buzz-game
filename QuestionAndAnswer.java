import java.util.ArrayList;
import java.util.Collections;

/**
 * Κλαση QuestionAndAnswer . Στην κλαση υπαρχουν τα πεδια  question, answers, trueAnswer, category,chekAnswer.
 * το πεδιο question αποθηκευει την ερωτηση
 * το πεδιο answers αποθηκευει τις πιθανες απαντησεις της ερωτησης
 * το πεδιο trueAnswer αποθηκευει την μοναδικη σωστη απαντηση της ερωτησης
 * το πεδιο category το οποιο αποθηκευει την κατηγορια της ερωτησης
 * το πεδιο chekAnswer αποθηκευει το πληκτρο που αντιστοιχει στην σωστη απαντηση(καθε ερωτηση εχει 4 πιθανες απντησεις
 * ,καθε απαντηση αντιστοιχει σε ενα πληκτρο a,b,c,d -->Αυτο γινεται ουτως ωστε ο παικτης να μπορει να απαντησει οσο
 * το δυνατον πιο γρηγορα με την πληκτρολογηση ενος μονο πληκτρου)
 */
public class QuestionAndAnswer {

    private final String question;
    private final ArrayList<String> answers;
    private String trueAnswer;
    private final String category;
    private String chekAnswer;
    private String iconPath;


    public String getIconPath(){
        return iconPath;
    }
    public ArrayList<String> getAnswers() {
        return answers;
    }

    /**
     *Κατασκευαστης της κλασης QuestionAndAnswer
     * @param question ορισμα τυπου String το οποιο περιεχει την ερωτηση
     * @param answers ορισμα τυπου ArrayList<String> το οποιο περιεχει τις απαντησεις της ερωτησης
     * @param trueAnswer ορισμα τυπου String το οποιο περιεχει την σωστη απαντηση της αντιστοιχης ερωτησης
     * @param category ορισμα τυπου String το οποιο περιεχει την κατηγορια της ερωτησης
     */
    public QuestionAndAnswer(String question , ArrayList<String> answers , String trueAnswer , String category) {
        this.question = question;
        this.answers = new ArrayList<>();
        this.answers.addAll(answers);
        this.trueAnswer=trueAnswer;
        this.category = category;
        if (category.equals("Εικονα")){
            this.trueAnswer=answers.get(0);
            iconPath = trueAnswer;
        }
    }

    /**
     * συναρτηση getQuestion()
     * @return επιστρεφει την ερωτηση του αντικειμενου της συγκεκριμενης κλασης
     */
    public String getQuestion() {
        return question;
    }

    /**
     * συναρτηση getTrueAnswer()
     * @return επιστεφει την σωστη απαντηση της ερωτησης του αντικειμενου QuestionAndAnswer
     */
    public String getTrueAnswer(){
        return trueAnswer;
    }

    /**
     * συναρτηση getCategory()
     * @return επιστρεφει την κατηγορια της ερωτησης του αντικειμενου QuestionAndAnswer
     */
    public String getCategory(){
        return category;
    }

    /**
     * συναρτηση η οποια αρχικα προκαλει μια αναδιαταξη των στοιχειων του πεδιου του αντικειμενου που περιεχει
     * τις απντησεις της ερωτησης
     * εμφανιση ενος μενου για την πληκτρολογηση καταλληλου πληκτρου για την επιλογη απαντησης του παικτη για την
     * ερωτηση
     * διεκρινιζει ποιο πληκτρο απο αυτα που μπορει να πληκτρολογησει ο παικτης περιεχει την σωστη απαντηση-->το απο-
     * θηκευει στο πεδιο checkAnswer
     */
    public void printAnswers(){
        Collections.shuffle(answers);
        System.out.printf("press a for %s\n",answers.get(0));
        System.out.printf("press b for %s\n",answers.get(1));
        System.out.printf("press c for %s\n",answers.get(2));
        System.out.printf("press d for %s\n",answers.get(3));
        if(trueAnswer.equals(answers.get(0))){
            chekAnswer = "a";
        }
        if(trueAnswer.equals(answers.get(1))){
            chekAnswer = "b";
        }
        if(trueAnswer.equals(answers.get(2))){
            chekAnswer = "c";
        }
        if(trueAnswer.equals(answers.get(3))){
            chekAnswer = "d" ;
        }
    }

    /**
     * Συναρτηση trueAnswer(String answerOfPlayer) η οποια επιστρεφει true αν η απαντηση του παικτη αντιστιχει στην
     * σωστη απαντηση της ερωτησης. Διαφορετικα επιστρεφει false.
     * @param answerOfPlayer παραμετρος τυπου String η οποια περιεχει την απαντηση του παικτη(ουσιαστικα το πληκτρο)
     * @return επιστρεφει αν η απαντηση του παικτη ειναι η αντιστοιχη με την σωστη απαντηση της ερωτησης
     */
    public boolean trueAnswer(String answerOfPlayer) {
        System.out.printf("The coorect answer is %s\n",trueAnswer);
        return answerOfPlayer.equals(chekAnswer);
    }

}