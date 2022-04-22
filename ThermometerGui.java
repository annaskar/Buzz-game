import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * ΠΡΟΟΡΙΖΕΤΑΙ ΜΟΝΟ ΓΙΑ ΔΥΟ ΠΑΙΧΤΕΣ
 *  Η ΚΛΑΣΗ BetGameForTwoPlayersGui ΔΗΜΙΟΥΡΓΕΙ ΤΙΣ ΚΑΤΑΛΛΗΛΕΣ ΔΙΕΠΑΦΕΣ ΟΥΤΩΣ ΩΣΤΕ ΝΑ ΜΠΟΡΕΙ Ο ΠΑΙΧΤΗΣ
 *  ΝΑ ΑΠΑΝΤΑΕΙ ΣΕ ΜΗ ΠΕΡΙΟΡΙΣΜΕΝΟ ΧΡΟΝΟ ΣΤΗΝ ΕΡΩΤΗΣΗ ΠΟΥ ΤΟΥ ΓΙΝΕΤΑΙ. Η ΑΠΑΝΤΗΣΗ ΔΙΝΕΤΑΙ ΜΕΣΩ ΠΛΗΚΤΡΟΛΟΓΙΟΥ.
 *  ΚΑΛΕΙΤΕ ΑΠΟ ΤΗΝ GuiBuzz() ΓΙΑ ΝΑ ΕΚΤΕΛΕΣΤΟΥΝ ΟΙ ΚΑΤΑΛΛΗΛΕΣ ΔΙΕΡΓΑΣΙΕΣ ΓΙΑ ΤΟΝ ΤΥΠΟ ΓΥΡΟΥ ΠΟΝΤΑΡΙΣΜΑ
 */
public class ThermometerGui {



    private JFrame questionFrame;


    private JLabel pointsOfPlayer1;
    private JLabel pointsOfPlayer2;
    private JLabel answerOfPlayer1;
    private JLabel answerOfPlayer2;

    private final QuestionAndAnswer q;

    private boolean nextQuestion=true;
    private boolean player1AnsweredForThermometer=false;


    private final Player p1;
    private final Player p2;

    /**
     * ΚΑΤΑΣΚΕΥΑΣΤΗΣ ΤΗΣ ΚΛΑΣΗΣ ΣΤΟΝ ΟΠΟΙΟ ΑΡΧΙΚΟΠΟΙΟΥΝΤΑΙ Ο ΠΑΙΧΤΗΣ 1 ΚΑΙ Ο ΠΑΙΧΤΗΣ 2 ΑΝ ΥΠΑΡΧΕΙ.
     * ΑΡΧΙΚΟΠΟΙΟΥΝΤΑΙ ΕΠΙΣΗΣ ΚΑΠΟΙΑ ΣΤΟΙΧΕΙΑ ΣΗΜΑΝΤΙΚΑ ΓΙΑ ΤΗΝ ΓΡΑΦΙΚΗ ΔΙΕΠΑΦΗ.
     * @param p1 ΠΑΡΑΜΕΤΡΟΣ ΠΟΥ ΣΥΜΒΟΛΙΖΕΙ ΤΟΝ ΠΡΩΤΟ ΠΑΙΧΤΗ
     * @param p2 ΠΑΡΑΜΕΤΡΟΣ ΠΟΥ ΣΥΜΒΟΛΙΖΕΙ ΤΟΝ ΔΕΥΤΕΡΟ ΠΑΙΧΤΗ
     * @param l1 ΓΡΑΦΙΚΟ ΣΤΟΙΧΕΙΟ ΠΟΥ ΠΕΡΙΕΧΕΙ ΤΟΥΣ ΠΟΝΤΟΥΣ ΤΟΥ ΠΡΩΤΟΥ ΠΑΙΧΤΗ
     * @param l2 ΓΡΑΦΙΚΟ ΣΤΟΙΧΕΙΟ ΠΟΥ ΠΕΡΙΕΧΕΙ ΤΟΥΣ ΠΟΝΤΟΥΣ ΤΟΥ ΔΕΥΤΕΡΟΥ ΠΑΙΧΤΗ
     * @param questions ArrayList<QuestionAndAnswer> ΤΟ ΟΠΟΙΟ ΧΡΗΣΙΜΟΠΟΙΕΙΤΕ ΓΙΑ ΤΗΝ ΕΡΩΤΗΣΗ ΠΟΥ ΠΡΕΙ ΝΑ ΓΙΝΕΙ
     *                  ΣΤΟΝ ΠΑΙΧΤΗ.
     */
    public ThermometerGui(Player p1 , Player p2 , JLabel l1 , JLabel l2 , QuestionAndAnswer questions ){

        pointsOfPlayer1=l1;
        pointsOfPlayer2=l2;
        q = questions;
        this.p1=p1;
        this.p2=p2;
    }

    /**
     * ΕΠΙΣΤΡΕΦΕΙ ΑΝ Η ΔΙΑΔΙΚΑΣΙΑ ΤΗΣ ΜΙΑ ΕΡΩΤΗΣΗΣ ΕΧΕΙ ΤΕΛΕΙΩΣΕΙ ΟΥΤΩΣ ΩΣΤΕ ΝΑ ΠΡΟΧΩΡΗΣΕΙ ΣΤΗΝ ΕΠΟΜΕΝΗ ΕΡΩΤΗΣΗ.
     * ΑΥΤΟ ΓΙΝΕΤΑΙ ΟΤΑΝ ΟΛΟΙ ΟΙ ΠΑΙΧΤΕΣ ΑΠΑΝΤΗΣΟΥΝ.
     * @return
     */
    public boolean getNextQuestion() {
        return nextQuestion;
    }

    /**
     * ΕΠΙΣΤΡΕΦΕΙ ΤΟ ΓΡΑΦΙΚΟ ΣΤΟΙΧΕΙΟ ΜΕ ΤΟΥΣ ΠΟΝΤΟΥΣ ΤΟΥ ΠΡΩΤΟΥ ΠΑΙΧΤΗ
     * @return ΠΟΝΤΟΙ ΤΟΥ ΠΡΩΤΟΥ ΠΑΙΧΤΗ ΣΕ ΓΡΑΦΙΚΟ ΠΕΔΙΟ
     */
    public JLabel getPointsOfPlayer1() {
        return pointsOfPlayer1;
    }

    /**
     * ΕΠΙΣΤΡΕΦΕΙ ΤΟ ΓΡΑΦΙΚΟ ΣΤΟΙΧΕΙΟ ΜΕ ΤΟΥΣ ΠΟΝΤΟΥΣ ΤΟΥ ΔΕΥΤΕΡΟΥ ΠΑΙΧΤΗ
     * @return ΠΟΝΤΟΙ ΤΟΥ ΔΕΥΤΕΡΟΥ ΠΑΙΧΤΗ ΣΕ ΓΡΑΦΙΚΟ ΠΕΔΙΟ
     */
    public JLabel getPointsOfPlayer2() {
        return pointsOfPlayer2;
    }


    /**
     * ΔΗΜΗΟΥΡΓΕΙ ΤΗ ΓΡΑΦΙΚΗ ΔΙΕΠΑΦΗ ΓΙΑ ΝΑ ΠΡΟΒΑΛΛΕΙ ΣΤΟΝ ΠΑΙΧΤΗ ΠΟΙΕΣ ΕΙΝΑΙ ΟΙ ΔΥΝΑΤΕΣ ΑΠΑΝΤΗΣΕΙΣ
     * ΣΤΗΝ ΕΡΩΤΗΣΗ ΚΑΙ ΠΟΙΑ ΠΛΗΚΤΡΑ ΝΑ ΠΑΤΗΣΕΙ ΟΥΤΩΣ ΩΣΤΕ ΝΑ ΔΙΑΛΕΞΕΙ ΤΗΝ ΑΠΑΝΤΗΣΗ ΠΟΥ ΘΕΛΕΙ.
     * @throws IOException
     */
    public void questionsMenuForThermometerGame() throws IOException {

        nextQuestion=false;
        JButton questionButton = new JButton();
        questionButton.setOpaque(false);
        questionButton.setContentAreaFilled(false);
        questionButton.setBorderPainted(false);
        questionButton.setFont(new Font("" ,Font.PLAIN , 25));
        questionButton.setForeground(Color.WHITE);

        questionFrame = newFrame("Thermometer");
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel , BoxLayout.Y_AXIS));

        JPanel questionPanelP2 = new JPanel();
        questionPanelP2.setLayout(new BoxLayout(questionPanelP2 , BoxLayout.Y_AXIS));

        JPanel pointsPanel = new JPanel();
        pointsPanel.setLayout(new BoxLayout(pointsPanel , BoxLayout.Y_AXIS));

        BorderLayout aLayout=new BorderLayout();
        questionFrame.setLayout(aLayout);

        BufferedImage img;
        String fontoName="src/images/3.jpeg";
        if (q.getCategory().equals("Εικονα")){
            fontoName=q.getIconPath();
        }
        img = ImageIO.read(new File(fontoName));
        ImageIcon icon = new ImageIcon(img);

        JButton photo = new JButton();
        photo.setIcon(icon);
        photo.setLayout(new BorderLayout());

        questionButton.setText(q.getQuestion());

        q.printAnswers();


        JLabel answer1=new JLabel(q.getAnswers().get(0) + " (press q)");
        JLabel answer2=new JLabel(q.getAnswers().get(1) + " (press w)");
        JLabel answer3=new JLabel(q.getAnswers().get(2) + " (press e)");
        JLabel answer4=new JLabel(q.getAnswers().get(3) + " (press r)");

        JLabel directory1=new JLabel(q.getAnswers().get(0) + " (press v)");
        JLabel directory2=new JLabel(q.getAnswers().get(1) + " (press b)");
        JLabel directory3=new JLabel(q.getAnswers().get(2) + " (press n)");
        JLabel directory4=new JLabel(q.getAnswers().get(3) + " (press m)");

        addKeyListenerForThermometerGame('v', directory1);
        addKeyListenerForThermometerGame('b', directory2);
        addKeyListenerForThermometerGame('n', directory3);
        addKeyListenerForThermometerGame('m', directory4);

        addKeyListenerForThermometerGame('q', answer1);
        addKeyListenerForThermometerGame('w', answer2);
        addKeyListenerForThermometerGame('e', answer3);
        addKeyListenerForThermometerGame('r', answer4);


        JLabel player1 =new JLabel(p1.getPlayer() + " QUESTION MENU");
        JLabel player2 =new JLabel(p2.getPlayer() + " QUESTION MENU");
        player1.setForeground(Color.orange);
        player1.setFont(new Font("" ,Font.PLAIN , 20));
        player2.setForeground(Color.orange);
        player2.setFont(new Font("" ,Font.PLAIN , 20));

        questionPanel.add(player1);
        questionPanel.add(answer1);
        questionPanel.add(answer2);
        questionPanel.add(answer3);
        questionPanel.add(answer4);

        questionPanelP2.add(player2);
        questionPanelP2.add(directory1);
        questionPanelP2.add(directory2);
        questionPanelP2.add(directory3);
        questionPanelP2.add(directory4);


        answer1.setForeground(Color.WHITE);
        answer1.setFont(new Font("" ,Font.PLAIN , 20));
        answer2.setForeground(Color.WHITE);
        answer2.setFont(new Font("" ,Font.PLAIN , 20));
        answer3.setForeground(Color.WHITE);
        answer3.setFont(new Font("" ,Font.PLAIN , 20));
        answer4.setForeground(Color.WHITE);
        answer4.setFont(new Font("" ,Font.PLAIN , 20));

        directory1.setForeground(Color.WHITE);
        directory1.setFont(new Font("" ,Font.PLAIN , 20));
        directory2.setForeground(Color.WHITE);
        directory2.setFont(new Font("" ,Font.PLAIN , 20));
        directory3.setForeground(Color.WHITE);
        directory3.setFont(new Font("" ,Font.PLAIN , 20));
        directory4.setForeground(Color.WHITE);
        directory4.setFont(new Font("" ,Font.PLAIN , 20));


        questionPanel.setOpaque(false);
        questionPanelP2.setOpaque(false);

        photo.add(questionButton,BorderLayout.PAGE_START);
        pointsPanel.add(pointsOfPlayer1);

        pointsPanel.add(pointsOfPlayer2);
        photo.add(questionPanel, BorderLayout.WEST);
        photo.add(questionPanelP2, BorderLayout.EAST);

        questionFrame.add(photo);
        questionFrame.setVisible(true);
    }

    /**
     * ΣΥΝΑΡΤΗΣΗ ΠΟΥ ΥΛΟΠΟΙΕΙ ΕΝΑΝ KeyListener ΠΟΥ ΥΠΟΣΤΗΡΙΖΕΙ ΤΟ ΠΑΙΧΝΙΔΙ ΜΕ 2 ΠΑΙΧΤΕΣ
     * ΜΟΝΟ ΟΤΑΝ ΚΑΙ ΟΙ 2 ΠΑΙΧΤΕΣ ΑΠΑΝΤΗΣΟΥΝ ΠΕΖΕΤΑΙ ΜΙΑ ΕΡΩΤΗΣΗ
     * @param plhktro ΠΛΗΚΤΡΟ ΠΟΥ ΠΑΤΗΘΗΚΕ
     * @param ans Η ΑΠΑΝΤΗΣΗ ΠΟΥ ΑΝΤΙΣΤΟΙΧΕΙ ΣΤΟ ΠΛΗΚΤΟ ΠΟΥ ΠΑΤΗΘΗΚΕ (ΜΙΑ ΑΠΟ ΤΙΣ 4 ΠΙΘΑΝΕΣ). ΑΝ ΤΟ ΠΛΗΚΤΡΟ
     *            ΠΟΥ ΠΑΤΗΘΗΚΕ ΔΕΝ ΑΝΤΙΣΤΟΙΧΕΙ ΣΕ ΚΑΠΟΙΑ ΑΠΟ ΑΥΤΑ ΠΟΥ ΑΝΑΔΥΚΝΕΙΟΝΤΑΙ ΤΟΤΕ Η ΡΟΗ ΤΟΥ ΠΡΟΓΡΑΜ-
     *            ΜΑΤΟΣ ΠΑΡΑΜΕΝΕΙ ΣΤΑΘΕΡΗ ΜΕΧΡΙ ΝΑ ΠΑΤΗΘΟΥΝ ΣΩΣΤΑ ΠΛΗΚΤΡΑ
     */
    public void addKeyListenerForThermometerGame(char plhktro , JLabel ans){
        questionFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == plhktro) {
                    if ( (plhktro=='q' || plhktro=='w' || plhktro=='e' || plhktro=='r') ){
                        answerOfPlayer1=new JLabel(ans.getText());
                        player1AnsweredForThermometer=true;
                    }
                    else if ((plhktro=='v' || plhktro=='b' || plhktro=='n' || plhktro=='m') ){
                        answerOfPlayer2=new JLabel(ans.getText());
                    }

                    if (player1AnsweredForThermometer) {
                        playOneQuestionForP1(answerOfPlayer1);
                        nextQuestion = true;
                        questionFrame.dispose();
                    }
                    else {
                        playOneQuestionForP2(answerOfPlayer2 );
                        nextQuestion = true;
                        questionFrame.dispose();
                    }

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }


    /**
     * ΣΥΝΑΡΤΗΣΗ ΠΟΥ ΑΠΟΔΙΔΕΙ ΚΑΤΑΛΛΗΛΑ ΤΟΥΣ ΠΟΝΤΟΥΣ ΣΥΜΦΩΝΑ ΜΕ ΤΟΥΣ ΚΑΝΟΝΕΣ ΤΟΥ ΓΥΡΟΥ ΘΕΡΜΟΜΕΤΡΟ.
     * ΕΛΕΓΧΕΙ ΤΗΝ ΑΠΑΝΤΗΣΗ ΠΟΥ ΔΟΘΗΚΕ ΑΠΟ ΤΟΝ ΠΡΩΤΟ ΠΑΙΧΤΗ (ΜΕ ΤΑ ΠΛΗΚΤΑ ΠΟΥ ΠΑΤΗΣΕ) ΜΕ ΤΗΝ ΟΝΤΩΣ ΣΩΣΤΗ ΑΠΑΝΤΗΣΗ
     * ΤΗΣ ΕΡΩΤΗΣΗΣ.
     * @param ansP1 ΑΠΑΝΤΗΣΗ ΤΟΥ ΠΡΩΤΟΥΧΤΗ ΠΑΙΧΤΗ ΣΤΗΝ ΕΡΩΤΗΣΗ ΠΟΥ ΤΟΥ ΑΝΑΤΕΘΗΚΕ
     */
    public void playOneQuestionForP1(JLabel ansP1 ) {

        boolean temp1;
        boolean temp2;
        boolean temp3;
        boolean temp4;

        String temp =q.getTrueAnswer();


        temp1 = ansP1.getText().equals(temp + " (press q)");
        temp2 = ansP1.getText().equals(temp + " (press w)");
        temp3 = ansP1.getText().equals(temp + " (press e)");
        temp4 = ansP1.getText().equals(temp + " (press r)");
        if (temp1 || temp2 || temp3 || temp4) {
            p1.correctAnserws();
        }

        if (p1.getCorectA()==5) {
            p1.updatePointsOfPlayer(5000);
        }

        pointsOfPlayer1 = new JLabel(p1.getPlayer() + " have " + p1.getPoints() + " points");
        pointsOfPlayer2 = new JLabel(p2.getPlayer() + " have " + p2.getPoints() + " points");
    }

    /**
     * ΣΥΝΑΡΤΗΣΗ ΠΟΥ ΑΠΟΔΙΔΕΙ ΚΑΤΑΛΛΗΛΑ ΤΟΥΣ ΠΟΝΤΟΥΣ ΣΥΜΦΩΝΑ ΜΕ ΤΟΥΣ ΚΑΝΟΝΕΣ ΤΟΥ ΓΥΡΟΥ ΘΕΡΜΟΜΕΤΡΟ.
     * ΕΛΕΓΧΕΙ ΤΗΝ ΑΠΑΝΤΗΣΗ ΠΟΥ ΔΟΘΗΚΕ ΑΠΟ ΚΑΘΕ ΠΑΙΧΤΗ (ΜΕ ΤΑ ΠΛΗΚΤΑ ΠΟΥ ΠΑΤΗΣΕ) ΜΕ ΤΗΝ ΟΝΤΩΣ ΣΩΣΤΗ ΑΠΑΝΤΗΣΗ
     * ΤΗΣ ΕΡΩΤΗΣΗΣ.
     * @param ansP2 ΑΠΑΝΤΗΣΗ ΤΟΥ ΔΕΥΤΕΡΟΥ ΠΑΙΧΤΗ ΣΤΗΝ ΕΡΩΤΗΣΗ ΠΟΥ ΤΟΥ ΑΝΑΤΕΘΗΚΕ
     */
    public void playOneQuestionForP2(JLabel ansP2 ) {

        boolean temp5;
        boolean temp6;
        boolean temp7;
        boolean temp8;
        String temp =q.getTrueAnswer();

        temp5 = ansP2.getText().equals(temp + " (press v)");
        temp6 = ansP2.getText().equals(temp + " (press b)");
        temp7 = ansP2.getText().equals(temp + " (press n)");
        temp8 = ansP2.getText().equals(temp + " (press m)");
        if (temp5 || temp6 || temp7 || temp8){
            p2.correctAnserws();
        }
        if (p2.getCorectA()==5){
            p2.updatePointsOfPlayer(5000);
        }
        pointsOfPlayer1 = new JLabel(p1.getPlayer() + " have " + p1.getPoints() + " points");
        pointsOfPlayer2 = new JLabel(p2.getPlayer() + " have " + p2.getPoints() + " points");
    }


    /**
     * ΣΥΝΑΡΤΗΣΗ Η ΟΠΟΙΑ ΥΛΟΠΟΙΕΙ ΤΗΝ ΔΗΜΙΟΥΡΓΙΑ ΕΝΟΣ ΠΑΡΑΘΥΡΟΥ ΜΕ ΒΑΣΙΚΑ ΣΤΟΧΕΙΑ. ΔΕΧΕΤΑΙ ΜΙΑ ΣΥΜΒΟΛΟΣΕΙΡΑ
     * Η ΟΠΟΙΑ ΠΡΟΟΡΙΖΕΤΑΙ ΓΙΑ ΠΙΟ ΘΑ ΕΙΝΑΙ ΤΟ ΟΝΟΜΑ ΤΟΥ ΠΑΡΑΘΥΡΟΥ ΠΟΥ ΘΑ ΔΗΜΙΟΥΡΓΗΘΕΙ
     * @param str ΟΝΟΜΑ ΤΟΥ ΠΑΡΑΘΥΡΟΥ ΠΟΥ ΘΑ ΔΗΜΙΟΥΡΓΗΘΕΙ
     * @return ΕΠΙΣΤΡΕΦΕΤΑΙ ΕΝΑ ΠΑΡΑΘΥΡΟ ΜΕ ΒΑΣΙΚΑ ΣΤΟΙΧΕΙΑ ΓΡΑΦΙΚΩΝ ΔΙΕΠΑΦΩΝ
     */
    public JFrame newFrame(String str) {
        JFrame f;
        f = new JFrame(str);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(true);
        f.setSize(1100,800);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        return f;
    }
}
