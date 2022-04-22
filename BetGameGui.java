import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * ΠΡΟΟΡΙΖΕΤΑΙ ΓΙΑ ΕΝΑΝ ΠΑΙΧΤΗ
 *  Η ΚΛΑΣΗ BetGameGui ΔΗΜΙΟΥΡΓΕΙ ΤΙΣ ΚΑΤΑΛΛΗΛΕΣ ΔΙΕΠΑΦΕΣ ΟΥΤΩΣ ΩΣΤΕ ΝΑ ΜΠΟΡΕΙ Ο ΠΑΙΧΤΗΣ
 *  ΝΑ ΑΠΑΝΤΑΕΙ ΣΕ ΜΗ ΠΕΡΙΟΡΙΣΜΕΝΟ ΧΡΟΝΟ ΣΤΗΝ ΕΡΩΤΗΣΗ ΠΟΥ ΤΟΥ ΓΙΝΕΤΑΙ. Η ΑΠΑΝΤΗΣΗ ΔΙΝΕΤΑΙ ΜΕΣΩ ΠΛΗΚΤΡΟΛΟΓΙΟΥ.
 *  ΚΑΛΕΙΤΕ ΑΠΟ ΤΗΝ GuiBuzz() ΓΙΑ ΝΑ ΕΚΤΕΛΕΣΤΟΥΝ ΟΙ ΚΑΤΑΛΛΗΛΕΣ ΔΙΕΡΓΑΣΙΕΣ ΓΙΑ ΤΟΝ ΤΥΠΟ ΓΥΡΟΥ ΠΟΝΤΑΡΙΣΜΑ
 */
public class BetGameGui {

    private JFrame questionFrame;

    private JLabel pointsOfPlayer1;

    private  ArrayList<QuestionAndAnswer> questions;

    private boolean nextQuestion=true;
    private int playersNumber=0;
    private int betPoints1=0;

    private Player p1;

    /**
     * ΚΑΤΑΣΚΕΥΑΣΤΗΣ ΤΗΣ ΚΛΑΣΗΣ ΣΤΟΝ ΟΠΟΙΟ ΑΡΧΙΚΟΠΟΙΟΥΝΤΑΙ Ο ΠΑΙΧΤΗΣ.
     * ΑΡΧΙΚΟΠΟΙΟΥΝΤΑΙ ΕΠΙΣΗΣ ΚΑΠΟΙΑ ΣΤΟΙΧΕΙΑ ΣΗΜΑΝΤΙΚΑ ΓΙΑ ΤΗΝ ΓΡΑΦΙΚΗ ΔΙΕΠΑΦΗ.
     * @param p1 ΠΑΡΑΜΕΤΡΟΣ ΠΟΥ ΣΥΜΒΟΛΙΖΕΙ ΤΟΝ ΠΡΩΤΟ ΠΑΙΧΤΗ
     * @param questions ArrayList<QuestionAndAnswer> ΤΟ ΟΠΟΙΟ ΧΡΗΣΙΜΟΠΟΙΕΙΤΕ ΓΙΑ ΤΗΝ ΕΡΩΤΗΣΗ ΠΟΥ ΠΡΕΙ ΝΑ ΓΙΝΕΙ
     *                  ΣΤΟΝ ΠΑΙΧΤΗ.
     */
    public BetGameGui(Player p1 , JLabel l1 , ArrayList<QuestionAndAnswer> questions){
        pointsOfPlayer1=l1;
        this.questions = questions;
        this.p1=p1;
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
     * ΕΠΙΣΤΡΕΦΕΙ ΤΟ ΠΟΝΤΑΡΙΣΜΑ ΠΟΥ ΕΠΙΘΥΜΕΙ Ο ΠΡΩΤΟΣ ΠΑΙΧΤΗΣ ΝΑ ΠΟΝΤΑΡΕΙ
     * @return ΠΟΝΤΟΙ ΠΟΥ ΠΟΝΤΑΡΕΙ Ο ΠΡΩΤΟΣ ΠΑΙΧΤΗΣ
     */
    public int getBetPoints1() {
        return betPoints1;
    }

    /**
     * ΔΗΜΗΟΥΡΓΕΙ ΤΗ ΓΡΑΦΙΚΗ ΔΙΕΠΑΦΗ ΓΙΑ ΝΑ ΠΡΟΒΑΛΛΕΙ ΣΤΟΝ ΠΑΙΧΤΗ ΠΟΙΕΣ ΕΙΝΑΙ ΟΙ ΔΥΝΑΤΕΣ ΑΠΑΝΤΗΣΕΙΣ
     * ΣΤΗΝ ΕΡΩΤΗΣΗ ΚΑΙ ΠΟΙΑ ΠΛΗΚΤΡΑ ΝΑ ΠΑΤΗΣΕΙ ΟΥΤΩΣ ΩΣΤΕ ΝΑ ΔΙΑΛΕΞΕΙ ΤΗΝ ΑΠΑΝΤΗΣΗ ΠΟΥ ΘΕΛΕΙ.
     * @throws IOException
     */
    public void questionsMenuForBetGame() throws IOException, InterruptedException {
        BetFrameForBetGameGui betFrameForBetGameGui = new BetFrameForBetGameGui(p1,null,1,pointsOfPlayer1,null,questions);
        betFrameForBetGameGui.betFrame();

        while (betPoints1== 0 ) {
            TimeUnit.SECONDS.sleep(1);
            betPoints1=betFrameForBetGameGui.getBetPoints1();

        }


        questions.get(0).printAnswers();

        nextQuestion=false;
        JButton questionButton = new JButton();
        questionButton.setOpaque(false);
        questionButton.setContentAreaFilled(false);
        questionButton.setBorderPainted(false);
        questionButton.setFont(new Font("" ,Font.PLAIN , 25));
        questionButton.setForeground(Color.WHITE);

        questionFrame = newFrame("Bet Game");
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

        questionButton.setText(questions.get(0).getQuestion());

        JLabel answer1=new JLabel(questions.get(0).getAnswers().get(0) + " (press q)");
        JLabel answer2=new JLabel(questions.get(0).getAnswers().get(1) + " (press w)");
        JLabel answer3=new JLabel(questions.get(0).getAnswers().get(2) + " (press e)");
        JLabel answer4=new JLabel(questions.get(0).getAnswers().get(3) + " (press r)");

        answer1.setForeground(Color.WHITE);
        answer1.setFont(new Font("" ,Font.PLAIN , 20));
        answer2.setForeground(Color.WHITE);
        answer2.setFont(new Font("" ,Font.PLAIN , 20));
        answer3.setForeground(Color.WHITE);
        answer3.setFont(new Font("" ,Font.PLAIN , 20));
        answer4.setForeground(Color.WHITE);
        answer4.setFont(new Font("" ,Font.PLAIN , 20));

        addKeyListener('q' , answer1);
        addKeyListener('w' , answer2);
        addKeyListener('e' , answer3);
        addKeyListener('r' , answer4);

        JLabel player1 =new JLabel(p1.getPlayer() + " QUESTION MENU");
        player1.setForeground(Color.orange);
        player1.setFont(new Font("" ,Font.PLAIN , 20));

        questionPanel.setOpaque(false);
        questionPanel.add(player1);
        questionPanel.add(answer1);
        questionPanel.add(answer2);
        questionPanel.add(answer3);
        questionPanel.add(answer4);

        photo.add(questionButton,BorderLayout.PAGE_START);
        pointsOfPlayer1.setForeground(Color.RED);
        pointsOfPlayer1.setFont(new Font("",Font.PLAIN , 20));
        pointsPanel.add(pointsOfPlayer1);
        pointsPanel.setOpaque(false);

        photo.add(pointsPanel,BorderLayout.PAGE_END);
        photo.add(questionPanel, BorderLayout.WEST);

        questionFrame.add(photo);
        questionFrame.setVisible(true);

    }

    /**
     * ΣΥΝΑΡΤΗΣΗ ΠΟΥ ΥΛΟΠΟΙΕΙ ΕΝΑΝ KeyListener ΠΟΥ ΥΠΟΣΤΗΡΙΖΕΙ ΤΟ ΠΑΙΧΝΙΔΙ ΜΕ 1 ΠΑΙΧΤΗ
     * ΜΟΝΟ ΟΤΑΝ ΑΠΑΝΤΗΣΕΙ ΠΕΖΕΤΑΙ ΜΙΑ ΕΡΩΤΗΣΗ
     * @param plhktro ΠΛΗΚΤΡΟ ΠΟΥ ΠΑΤΗΘΗΚΕ
     * @param ans Η ΑΠΑΝΤΗΣΗ ΠΟΥ ΑΝΤΙΣΤΟΙΧΕΙ ΣΤΟ ΠΛΗΚΤΟ ΠΟΥ ΠΑΤΗΘΗΚΕ (ΜΙΑ ΑΠΟ ΤΙΣ 4 ΠΙΘΑΝΕΣ). ΑΝ ΤΟ ΠΛΗΚΤΡΟ
     *            ΠΟΥ ΠΑΤΗΘΗΚΕ ΔΕΝ ΑΝΤΙΣΤΟΙΧΕΙ ΣΕ ΚΑΠΟΙΑ ΑΠΟ ΑΥΤΑ ΠΟΥ ΑΝΑΔΥΚΝΕΙΟΝΤΑΙ ΤΟΤΕ Η ΡΟΗ ΤΟΥ ΠΡΟΓΡΑΜ-
     *            ΜΑΤΟΣ ΠΑΡΑΜΕΝΕΙ ΣΤΑΘΕΡΗ ΜΕΧΡΙ ΝΑ ΠΑΤΗΘΟΥΝ ΣΩΣΤΑ ΠΛΗΚΤΡΑ
     */
    public void addKeyListener(char plhktro , JLabel ans  ){
        questionFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == plhktro) {

                    playOneQuestion(ans);
                    nextQuestion = true;
                    questionFrame.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    /**
     * ΣΥΝΑΡΤΗΣΗ ΠΟΥ ΑΠΟΔΙΔΕΙ ΚΑΤΑΛΛΗΛΑ ΤΟΥΣ ΠΟΝΤΟΥΣ ΣΥΜΦΩΝΑ ΜΕ ΤΟΥΣ ΚΑΝΟΝΕΣ ΤΟΥ ΓΥΡΟΥ ΣΩΣΤΗ ΑΠΑΝΤΗΣΗ.
     * ΕΛΕΓΧΕΙ ΤΗΝ ΑΠΑΝΤΗΣΗ ΠΟΥ ΔΟΘΗΚΕ ΑΠΟ ΚΑΘΕ ΠΑΙΧΤΗ (ΜΕ ΤΑ ΠΛΗΚΤΑ ΠΟΥ ΠΑΤΗΣΕ) ΜΕ ΤΗΝ ΟΝΤΩΣ ΣΩΣΤΗ ΑΠΑΝΤΗΣΗ
     * ΤΗΣ ΕΡΩΤΗΣΗΣ.
     * @param ansP1 ΑΠΑΝΤΗΣΗ ΤΟΥ ΠΡΩΤΟΥΧΤΗ ΠΑΙΧΤΗ ΣΤΗΝ ΕΡΩΤΗΣΗ ΠΟΥ ΤΟΥ ΑΝΑΤΕΘΗΚΕ
     */
    public void playOneQuestion(JLabel ansP1) {
        boolean temp1;
        boolean temp2;
        boolean temp3;
        boolean temp4;

        String temp =questions.get(0).getTrueAnswer();

        temp1=ansP1.getText().equals(temp + " (press q)");
        temp2=ansP1.getText().equals(temp + " (press w)");
        temp3=ansP1.getText().equals(temp + " (press e)");
        temp4=ansP1.getText().equals(temp + " (press r)");

        if (temp1 || temp2 || temp3 || temp4) {
            p1.updatePointsOfPlayer(betPoints1);
            p1.correctAnserws();
        } else {
            p1.updatePointsOfPlayer(-betPoints1);
        }

        pointsOfPlayer1 = new JLabel(p1.getPlayer() + " have " + p1.getPoints() + " points");
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
