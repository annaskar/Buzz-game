import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ΠΡΟΟΡΙΖΕΤΑΙ ΜΟΝΟ ΓΙΑ ΔΥΟ ΠΑΙΧΤΕΣ
 *  Η ΚΛΑΣΗ BetGameForTwoPlayersGui ΔΗΜΙΟΥΡΓΕΙ ΤΙΣ ΚΑΤΑΛΛΗΛΕΣ ΔΙΕΠΑΦΕΣ ΟΥΤΩΣ ΩΣΤΕ ΝΑ ΜΠΟΡΕΙ Ο ΠΑΙΧΤΗΣ
 *  ΝΑ ΑΠΑΝΤΑΕΙ ΣΕ ΜΗ ΠΕΡΙΟΡΙΣΜΕΝΟ ΧΡΟΝΟ ΣΤΗΝ ΕΡΩΤΗΣΗ ΠΟΥ ΤΟΥ ΓΙΝΕΤΑΙ. Η ΑΠΑΝΤΗΣΗ ΔΙΝΕΤΑΙ ΜΕΣΩ ΠΛΗΚΤΡΟΛΟΓΙΟΥ.
 *  ΚΑΛΕΙΤΕ ΑΠΟ ΤΗΝ GuiBuzz() ΓΙΑ ΝΑ ΕΚΤΕΛΕΣΤΟΥΝ ΟΙ ΚΑΤΑΛΛΗΛΕΣ ΔΙΕΡΓΑΣΙΕΣ ΓΙΑ ΤΟΝ ΤΥΠΟ ΓΥΡΟΥ ΠΟΝΤΑΡΙΣΜΑ
 */
public class FastAnswerGui {

    private JFrame questionFrame;

    private JLabel pointsOfPlayer1;
    private JLabel pointsOfPlayer2;
    private JLabel answerOfPlayer1;
    private JLabel answerOfPlayer2;

    private final ArrayList<QuestionAndAnswer> questions;

    private boolean nextQuestion=true;


    private boolean firstPlayerAnswered=false;
    private boolean secondPlayerAnswered=false;
    private boolean firstPlayerAnsweredfirst=false;
    private boolean secondPlayerAnsweredfirst=false;


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
    public FastAnswerGui(Player p1 , Player p2 , JLabel l1 , JLabel l2 , ArrayList<QuestionAndAnswer> questions){
        pointsOfPlayer1=l1;
        pointsOfPlayer2=l2;
        this.questions = questions;

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
    public void questionsMenuForFastAnswerGame() throws IOException {
        nextQuestion=false;
        JButton questionButton = new JButton();
        questionButton.setOpaque(false);
        questionButton.setContentAreaFilled(false);
        questionButton.setBorderPainted(false);
        questionButton.setFont(new Font("" ,Font.PLAIN , 25));
        questionButton.setForeground(Color.WHITE);

        questionFrame = newFrame("Fast Answer");
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel , BoxLayout.Y_AXIS));

        JPanel questionPanelP2 = new JPanel();
        questionPanelP2.setLayout(new BoxLayout(questionPanelP2 , BoxLayout.Y_AXIS));

        JPanel pointsPanel = new JPanel();
        pointsPanel.setLayout(new BoxLayout(pointsPanel , BoxLayout.Y_AXIS));

        BorderLayout aLayout=new BorderLayout();
        questionFrame.setLayout(aLayout);

        BufferedImage img;
        String fontoName="src/images/1.jpeg";
        if (questions.get(0).getCategory().equals("Εικονα")){
            fontoName=questions.get(0).getIconPath();
        }
        img = ImageIO.read(new File(fontoName));
        ImageIcon icon = new ImageIcon(img);

        JButton photo = new JButton();
        photo.setIcon(icon);
        photo.setLayout(new BorderLayout());

        questions.get(0).printAnswers();
        questionButton.setText(questions.get(0).getQuestion());



        JLabel answer1=new JLabel(questions.get(0).getAnswers().get(0) + " (press q)");
        JLabel answer2=new JLabel(questions.get(0).getAnswers().get(1) + " (press w)");
        JLabel answer3=new JLabel(questions.get(0).getAnswers().get(2) + " (press e)");
        JLabel answer4=new JLabel(questions.get(0).getAnswers().get(3) + " (press r)");

        JLabel directory1=new JLabel(questions.get(0).getAnswers().get(0) + " (press v)");
        JLabel directory2=new JLabel(questions.get(0).getAnswers().get(1) + " (press b)");
        JLabel directory3=new JLabel(questions.get(0).getAnswers().get(2) + " (press n)");
        JLabel directory4=new JLabel(questions.get(0).getAnswers().get(3) + " (press m)");

        addKeyListenerForFastAnserGame('v', directory1);
        addKeyListenerForFastAnserGame('b', directory2);
        addKeyListenerForFastAnserGame('n', directory3);
        addKeyListenerForFastAnserGame('m', directory4);

        addKeyListenerForFastAnserGame('q', answer1);
        addKeyListenerForFastAnserGame('w', answer2);
        addKeyListenerForFastAnserGame('e', answer3);
        addKeyListenerForFastAnserGame('r', answer4);


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
        pointsOfPlayer1.setForeground(Color.RED);
        pointsOfPlayer1.setFont(new Font("",Font.PLAIN , 20));
        pointsPanel.add(pointsOfPlayer1);

        pointsOfPlayer2.setForeground(Color.RED);
        pointsOfPlayer2.setFont(new Font("",Font.PLAIN , 20));
        pointsPanel.add(pointsOfPlayer2);

        pointsPanel.setOpaque(false);

        photo.add(pointsPanel,BorderLayout.PAGE_END);
        photo.add(questionPanel, BorderLayout.WEST);
        photo.add(questionPanelP2, BorderLayout.EAST);

        questionFrame.add(photo);
        questionFrame.setVisible(true);
    }

    /**
     * ΣΥΝΑΡΤΗΣΗ ΠΟΥ ΑΠΟΔΙΔΕΙ ΚΑΤΑΛΛΗΛΑ ΤΟΥΣ ΠΟΝΤΟΥΣ ΣΥΜΦΩΝΑ ΜΕ ΤΟΥΣ ΚΑΝΟΝΕΣ ΤΟΥ ΓΥΡΟΥ ΣΩΣΤΗ ΑΠΑΝΤΗΣΗ.
     * ΕΛΕΓΧΕΙ ΤΗΝ ΑΠΑΝΤΗΣΗ ΠΟΥ ΔΟΘΗΚΕ ΑΠΟ ΚΑΘΕ ΠΑΙΧΤΗ (ΜΕ ΤΑ ΠΛΗΚΤΑ ΠΟΥ ΠΑΤΗΣΕ) ΜΕ ΤΗΝ ΟΝΤΩΣ ΣΩΣΤΗ ΑΠΑΝΤΗΣΗ
     * ΤΗΣ ΕΡΩΤΗΣΗΣ.
     * @param ansP1 ΑΠΑΝΤΗΣΗ ΤΟΥ ΠΡΩΤΟΥΧΤΗ ΠΑΙΧΤΗ ΣΤΗΝ ΕΡΩΤΗΣΗ ΠΟΥ ΤΟΥ ΑΝΑΤΕΘΗΚΕ
     * @param ansP2 ΑΠΑΝΤΗΣΗ ΤΟΥ ΔΕΥΤΕΡΟΥ ΠΑΙΧΤΗ ΣΤΗΝ ΕΡΩΤΗΣΗ ΠΟΥ ΤΟΥ ΑΝΑΤΕΘΗΚΕ
     */
    public void playOneQuestion(JLabel ansP1 , JLabel ansP2) {
        boolean temp1;
        boolean temp2;
        boolean temp3;
        boolean temp4;
        boolean temp5;
        boolean temp6;
        boolean temp7;
        boolean temp8;
        String temp =questions.get(0).getTrueAnswer();

        temp1 = ansP1.getText().equals(temp + " (press q)");
        temp2 = ansP1.getText().equals(temp + " (press w)");
        temp3 = ansP1.getText().equals(temp + " (press e)");
        temp4 = ansP1.getText().equals(temp + " (press r)");

        temp5 = ansP2.getText().equals(temp + " (press v)");
        temp6 = ansP2.getText().equals(temp + " (press b)");
        temp7 = ansP2.getText().equals(temp + " (press n)");
        temp8 = ansP2.getText().equals(temp + " (press m)");

        if (temp1 || temp2 || temp3 || temp4){ //ημαινει οτι ο πρωτος παιχτης απαντησε σωστα
            if (firstPlayerAnsweredfirst) {
                p1.updatePointsOfPlayer(1000);
                p1.correctAnserws();
            }
            else {
                p1.updatePointsOfPlayer(500);
                p1.correctAnserws();
            }
        }
        if (temp5 || temp6 || temp7 || temp8){ //ημαινει οτι ο πρωτος παιχτης απαντησε σωστα
            if (secondPlayerAnsweredfirst) {
                p2.updatePointsOfPlayer(1000);
                p2.correctAnserws();
            }
            else {
                p2.updatePointsOfPlayer(500);
                p2.correctAnserws();
            }
        }
        pointsOfPlayer2 = new JLabel(p2.getPlayer() + " have " + p2.getPoints() + " points");
        pointsOfPlayer1 = new JLabel(p1.getPlayer() + " have " + p1.getPoints() + " points");
    }

    /**
     * ΣΥΝΑΡΤΗΣΗ ΠΟΥ ΥΛΟΠΟΙΕΙ ΕΝΑΝ KeyListener ΠΟΥ ΥΠΟΣΤΗΡΙΖΕΙ ΤΟ ΠΑΙΧΝΙΔΙ ΜΕ 2 ΠΑΙΧΤΕΣ
     * ΜΟΝΟ ΟΤΑΝ ΚΑΙ ΟΙ 2 ΠΑΙΧΤΕΣ ΑΠΑΝΤΗΣΟΥΝ ΠΕΖΕΤΑΙ ΜΙΑ ΕΡΩΤΗΣΗ
     * @param plhktro ΠΛΗΚΤΡΟ ΠΟΥ ΠΑΤΗΘΗΚΕ
     * @param ans Η ΑΠΑΝΤΗΣΗ ΠΟΥ ΑΝΤΙΣΤΟΙΧΕΙ ΣΤΟ ΠΛΗΚΤΟ ΠΟΥ ΠΑΤΗΘΗΚΕ (ΜΙΑ ΑΠΟ ΤΙΣ 4 ΠΙΘΑΝΕΣ). ΑΝ ΤΟ ΠΛΗΚΤΡΟ
     *            ΠΟΥ ΠΑΤΗΘΗΚΕ ΔΕΝ ΑΝΤΙΣΤΟΙΧΕΙ ΣΕ ΚΑΠΟΙΑ ΑΠΟ ΑΥΤΑ ΠΟΥ ΑΝΑΔΥΚΝΕΙΟΝΤΑΙ ΤΟΤΕ Η ΡΟΗ ΤΟΥ ΠΡΟΓΡΑΜ-
     *            ΜΑΤΟΣ ΠΑΡΑΜΕΝΕΙ ΣΤΑΘΕΡΗ ΜΕΧΡΙ ΝΑ ΠΑΤΗΘΟΥΝ ΣΩΣΤΑ ΠΛΗΚΤΡΑ
     */
    public void addKeyListenerForFastAnserGame(char plhktro , JLabel ans ){
        questionFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == plhktro) {
                    if ( (plhktro=='q' || plhktro=='w' || plhktro=='e' || plhktro=='r') ){
                        answerOfPlayer1=new JLabel(ans.getText());
                        firstPlayerAnswered=true;
                        if (!secondPlayerAnsweredfirst)
                            firstPlayerAnsweredfirst=true;
                    }
                    else if ((plhktro=='v' || plhktro=='b' || plhktro=='n' || plhktro=='m') ){
                        answerOfPlayer2=new JLabel(ans.getText());
                        secondPlayerAnswered=true;
                        if (!firstPlayerAnsweredfirst)
                            secondPlayerAnsweredfirst=true;
                    }

                    if (firstPlayerAnswered && secondPlayerAnswered) {
                        playOneQuestion(answerOfPlayer1 , answerOfPlayer2 );
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

