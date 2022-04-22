import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.CharBuffer;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

/**
 * Η ΚΛΑΣΗ GuiBuzz ΕΙΝΑΙ Η ΚΛΑΣΗ <<ΕΓΚΕΦΑΛΟΣ>> ΣΕ ΟΤΙ ΣΧΕΤΙΖΕΤΑΙ ΜΕ ΤΗ ΓΡΑΦΙΚΗ ΔΙΕΠΑΦΗ ΤΟΥ ΠΑΙΧΝΙΔΙΟΥ Buzz
 * ΜΕΣΩ ΤΗΣ ΣΥΝΑΡΤΗΣΗΣ playGui() ΕΚΤΕΛΟΥΝΤΑΙ ΟΙ ΚΑΤΑΛΛΗΛΕΣ ΔΙΕΡΓΑΣΙΕΣ ΟΥΤΩΣ ΩΣΤΕ ΝΑ ΞΕΚΙΝΑ Η ΕΚΤΕΛΕΣΗ ΤΟΥ
 * ΠΡΟΓΡΑΜΜΑΤΟΣ ΚΑΙ ΝΑ ΕΜΦΑΝΙΖΟΝΤΑΙ ΤΑ ΚΑΤΑΛΛΗΛΑ ΠΑΡΑΘΥΡΑ ΑΝΑΛΟΓΑ ΜΕ ΤΟΝ ΑΡΙΘΜΟ ΤΩΝ ΠΑΙΚΤΩΝ ΠΟΥ ΕΠΙΛΕΧΘΗΚΕ
 * ΑΠΟ ΤΟΝ ΧΡΗΣΤΗ ΝΑ ΠΑΙΞΟΥΝ , ΚΑΙ ΤΟΝ ΤΥΧΑΙΑ ΕΠΙΛΕΓΟΜΕΝΩΝ ΤΥΠΩΝ ΓΥΡΟΥ ΑΠΟ ΤΟ ΠΡΟΓΡΑΜΜΑ
 */
public class GuiBuzz {

    private JFrame playersDataFrame;
    private JFrame firstFrame;
    private JLabel pointsOfPlayer1;
    private JLabel pointsOfPlayer2;

    private final ArrayList<QuestionAndAnswer> questions;
    private final HashSet<QuestionAndAnswer> victoriesForP1;
    private final HashSet<QuestionAndAnswer> victoriesForP2;

    private final JCheckBox pl1;
    private final JCheckBox pl2;


    private int playersNumber=0;

    private final Questions w;
    private Player firstPlayer;
    private Player secondPlayer;
    private final Game logic;

    /**
     * Ο ΚΑΤΑΣΚΕΥΑΣΤΗΣ ΤΗΣ ΚΛΑΣΗΣ GuiBuzz Ο ΟΠΟΙΟΣ ΑΡΧΙΚΑ ΔΙΑΒΑΖΕΙ ΚΑΙ ΑΠΟΘΗΚΕΥΕΙ ΤΙΣ ΕΡΩΤΗΣΕΙΣ ΤΟΥ
     * ΑΡΧΕΙΟΥ questions.txt . ΑΡΧΙΚΟΠΟΙΕΙ ΕΠΙΣΗΣ ΚΑΠΟΙΑ ΣΤΟΙΧΕΙΑ ΠΟΥ ΕΙΝΑΙ ΧΡΗΣΙΜΑ ΓΙΑ ΤΙς ΓΡΑΦΙΚΕΣ ΔΙΕΠΑΦΕΣ
     * ΚΑΙ ΘΑ ΣΥΜΒΑΛΛΟΥΝ ΣΤΗΝ ΕΚΚΙΝΗΣΗ ΤΟΥ ΠΑΙΧΝΙΔΙΟΥ
     * @throws IOException
     */
    public GuiBuzz() throws IOException {

        w= new Questions();
        w.reader();
        victoriesForP1=new HashSet<>();
        victoriesForP2=new HashSet<>();

        pl1 = new JCheckBox("");
        pl2 = new JCheckBox("");
        pointsOfPlayer1=new JLabel();
        pointsOfPlayer2=new JLabel();
        logic = new Game();
        //questions = logic.getW().getQuestions();
        questions = w.getQuestions();

    }

    /**
     * Η ΣΥΝΑΡΤΗΣΗ playGui() ΔΗΜΗΟΥΡΓΕΙ ΤΟ ΠΡΩΤΟ ΠΑΡΑΘΥΡΟ ΜΕ ΤΟ ΟΠΟΙΟ Ο ΧΡΗΣΤΗΣ ΕΡΧΕΤΑΙ ΣΕ ΟΠΤΙΚΗ ΕΠΑΦΗ
     * ΜΕΣΩ ΤΗΣ ΜΕΘΟΔΟΥ startMenu. ΤΟ ΠΑΡΑΘΥΡΟ ΘΑ ΠΑΡΑΜΕΙΝΕΙ ΑΝΟΙΧΤΟ ΚΑΙ ΤΟ ΠΡΟΓΡΑΜΜΑ ΣΤΑΣΙΜΟ ΜΕΧΡΙ ΝΑ ΕΠΙΛΕΧΘΕΙ
     * ΑΠΟ ΤΟΝ ΧΡΗΣΤΗ ΠΟΙΟΣ ΘΑ ΕΙΝΑΙ Ο ΑΡΙΘΜΟΣ ΤΩΝ ΠΑΙΧΤΩΝ ΠΟΥ ΘΑ ΑΓΩΝΙΣΤΟΥΝ. ΓΙΝΕΤΑΙ ΝΑ ΑΓΩΝΙΣΤΟΥΝ ΕΝΑΣ
     * ΜΕΧΡΙ ΚΑΙ ΔΥΟ ΤΟ ΠΟΛΥ ΠΑΙΧΤΕΣ. ΜΟΛΙΣ ΕΠΙΛΕΧΘΕΙ Ο ΑΡΙΘΜΟΣ ΤΟ ΠΡΟΓΡΑΜΜΑ ΣΥΝΕΧΙΖΕΙ ΤΗ ΡΟΗ ΤΟΥ ΚΑΙ
     * ΘΑ ΑΥΤΟΜΑΤΑ ΘΑ ΔΩΣΕΙ ΤΗΝ ΔΥΝΑΤΟΤΗΤΑ ΝΑ ΠΑΙΖΟΝΤΑΙ ΠΕΝΤΕ ΓΥΡΟΙ ΤΩΝ ΠΕΝΤΕ ΕΡΩΤΗΣΣΕΩΝ
     * @throws IOException
     * @throws InterruptedException
     */
    public void playGui() throws IOException, InterruptedException {
        firstFrame = newFrame("Buzz Game");
        startMenu("src/images/1.jpeg");

        while(playersNumber==0) {
            TimeUnit.SECONDS.sleep(1);
        }

        firstFrame.dispose();


        for (int i=0; i<5; i++) {
            playOneRound();
            playersDataFrame = frameForPlayersData();
            playersDataFrame.setVisible(true);
            TimeUnit.SECONDS.sleep(10);
            playersDataFrame.dispose();
        }
        makeTheArchive();
    }


    /**
     * Η ΣΥΝΑΡΤΗΣΗ ΑΝΑΛΑΜΒΑΝΕΙ ΝΑ ΕΠΙΛΕΓΕΙ ΤΥΧΑΙΑ ΤΟΝ ΤΥΠΟ ΓΥΡΟΥ ΚΑΙ ΣΥΜΦΩΝΑ ΜΕ ΤΟΝ ΤΥΠΟ ΚΑΙ ΜΕ
     * ΤΟΥΣ ΚΑΝΟΝΕΣ ΑΠΟΔΟΣΗΣ
     * ΠΟΝΤΩΝ  ΝΑ ΚΑΛΟΥΝΤΑΙ ΟΙ ΚΑΤΑΛΛΗΛΕΣ ΚΛΑΣΕΙΣ ΩΣΤΕ ΝΑ ΕΜΦΑΝΙΖΟΝΑΤΙ ΤΑ ΚΑΤΑΛΛΗΛΑ
     * ΠΑΡΑΘΥΡΑ ΣΤΟΝ ΧΤΗΣΤΗ . ΜΕ ΑΥΤΗΝ ΤΗΝ ΣΥΝΑΡΤΗΣΗ ΚΑΛΥΠΤΕΤΑΙ Η ΔΙΕΡΓΑΣΙΑ ΕΝΟΣ ΓΥΡΟΥ ΠΑΙΧΝΙΔΙΟΥ
     * ΟΣΟΝ ΔΗΠΟΤΕ ΠΑΙΧΤΩΝ ΕΜΦΑΝΙΖΟΝΤΑΣ ΤΟΥΣ ΚΑΘΕ ΦΟΡΑ 5 ΕΡΩΤΗΣΕΙΣ
     * @throws IOException
     * @throws InterruptedException
     */
    public void playOneRound() throws IOException, InterruptedException {

        firstPlayer.refreshA();
        if (playersNumber==2)
            secondPlayer.refreshA();
        String typeOfRound = logic.giveTypeOfRound();
        for (int i=0; i<5 ; i++) {

            if (playersNumber == 1) {

                int temp1 = firstPlayer.getCorectA();
                switch (typeOfRound) {
                    case "TrueAn":
                        TrueAnswerGui trueAnswerGui = new TrueAnswerGui(firstPlayer, secondPlayer, playersNumber, pointsOfPlayer1, pointsOfPlayer2, questions );
                        trueAnswerGui.questionsMenuForTrueAnswer();
                        while (!trueAnswerGui.getNextQuestion()) {
                            TimeUnit.SECONDS.sleep(1);
                        }
                        if (temp1!=firstPlayer.getCorectA()){
                            victoriesForP1.add(questions.get(0));
                        }
                        pointsOfPlayer1 = trueAnswerGui.getPointsOfPlayer1();
                        break;
                    case "BetGame":
                        BetGameGui betGameGui = new BetGameGui(firstPlayer, pointsOfPlayer1, questions );
                        betGameGui.questionsMenuForBetGame();
                        while (!betGameGui.getNextQuestion()) {
                            TimeUnit.SECONDS.sleep(1);
                        }
                        if (temp1!=firstPlayer.getCorectA()){
                            victoriesForP1.add(questions.get(0));
                        }
                        pointsOfPlayer1 = betGameGui.getPointsOfPlayer1();
                        break;
                    case "Chronometer":
                        ChronometerGui chronometerGui = new ChronometerGui(firstPlayer, secondPlayer, playersNumber, pointsOfPlayer1, pointsOfPlayer2, questions );
                        chronometerGui.questionsMenuForChronometerGame();
                        while (!chronometerGui.getNextQuestion()) {
                            TimeUnit.SECONDS.sleep(1);
                        }
                        if (temp1!=firstPlayer.getCorectA()){
                            victoriesForP1.add(questions.get(0));
                        }
                        pointsOfPlayer1 = chronometerGui.getPointsOfPlayer1();
                        break;
                }
            } else if (playersNumber == 2) {
                int temp1 = firstPlayer.getCorectA();
                int temp2= secondPlayer.getCorectA();
                switch (typeOfRound) {
                    case "TrueAn":

                        TrueAnswerGui trueAnswerGui = new TrueAnswerGui(firstPlayer, secondPlayer, playersNumber, pointsOfPlayer1, pointsOfPlayer2, questions );
                        trueAnswerGui.questionsMenuForTrueAnswer();
                        while (!trueAnswerGui.getNextQuestion()) {
                            TimeUnit.SECONDS.sleep(1);
                        }
                        if (temp1!=firstPlayer.getCorectA()){
                            victoriesForP1.add(questions.get(0));

                        }
                        if (temp2!=secondPlayer.getCorectA()){
                            victoriesForP2.add(questions.get(0));

                        }
                        pointsOfPlayer1 = trueAnswerGui.getPointsOfPlayer1();
                        pointsOfPlayer2 = trueAnswerGui.getPointsOfPlayer2();

                        break;
                    case "BetGame":
                        BetGameForTwoPlayersGui betGameForTwoPlayersGui = new BetGameForTwoPlayersGui(firstPlayer, secondPlayer, pointsOfPlayer1, pointsOfPlayer2, questions );
                        betGameForTwoPlayersGui.questionsMenuForBetGame2Players();
                        while (!betGameForTwoPlayersGui.getNextQuestion()) {
                            TimeUnit.SECONDS.sleep(1);
                        }
                        if (temp1!=firstPlayer.getCorectA()){
                            victoriesForP1.add(questions.get(0));
                            System.out.println("oeo");
                        }
                        if (temp2!=secondPlayer.getCorectA()){
                            victoriesForP2.add(questions.get(0));

                        }
                        pointsOfPlayer1 = betGameForTwoPlayersGui.getPointsOfPlayer1();
                        pointsOfPlayer2 = betGameForTwoPlayersGui.getPointsOfPlayer2();
                        break;
                    case "Chronometer":
                        ChronometerGui chronometerGui = new ChronometerGui(firstPlayer, secondPlayer, playersNumber, pointsOfPlayer1, pointsOfPlayer2, questions );
                        chronometerGui.questionsMenuForChronometerGame();
                        while (!chronometerGui.getNextQuestion()) {
                            TimeUnit.SECONDS.sleep(1);
                        }
                        if (temp1!=firstPlayer.getCorectA()){
                            victoriesForP1.add(questions.get(0));
                        }
                        if (temp2!=secondPlayer.getCorectA()){
                            victoriesForP2.add(questions.get(0));

                        }
                        pointsOfPlayer1 = chronometerGui.getPointsOfPlayer1();
                        pointsOfPlayer2 = chronometerGui.getPointsOfPlayer2();

                        break;
                    case "FastAnser":
                        FastAnswerGui fastAnswerGui = new FastAnswerGui(firstPlayer, secondPlayer, pointsOfPlayer1, pointsOfPlayer2, questions );
                        fastAnswerGui.questionsMenuForFastAnswerGame();
                        while (!fastAnswerGui.getNextQuestion()) {
                            TimeUnit.SECONDS.sleep(1);
                        }
                        if (temp1!=firstPlayer.getCorectA()){
                            victoriesForP1.add(questions.get(0));

                        }
                        if (temp2!=secondPlayer.getCorectA()){
                            victoriesForP2.add(questions.get(0));

                        }
                        pointsOfPlayer1 = fastAnswerGui.getPointsOfPlayer1();
                        pointsOfPlayer2 = fastAnswerGui.getPointsOfPlayer2();

                        break;
                    case "Thermometer":

                        i=1;
                        ThermometerGui thermometerGui = new ThermometerGui(firstPlayer, secondPlayer, pointsOfPlayer1, pointsOfPlayer2, questions.get(0));
                        thermometerGui.questionsMenuForThermometerGame();
                        while (!thermometerGui.getNextQuestion()) {
                            TimeUnit.SECONDS.sleep(1);
                        }

                        if (temp1!=firstPlayer.getCorectA()){
                            victoriesForP1.add(questions.get(0));

                        }
                        if (temp2!=secondPlayer.getCorectA()){
                            victoriesForP2.add(questions.get(0));

                        }
                        pointsOfPlayer1 = thermometerGui.getPointsOfPlayer1();
                        pointsOfPlayer2 = thermometerGui.getPointsOfPlayer2();
                        if (firstPlayer.getCorectA()==5 || secondPlayer.getCorectA() == 5) {
                            i = 10;
                        }

                        break;
                }

            }
            questions.remove(0);

        }
    }


    /**
     * Η ΣΥΝΑΡΤΗΣΗ ΔΗΜΙΟΥΡΓΕΙ ΕΝΑ ΠΑΡΑΘΥΡΟ ΤΟ ΟΠΟΙΟ ΠΕΡΙΕΧΕΙ ΤΟ ΣΚΟΡ ΚΑΙ ΤΙΣ ΝΙΚΕΣ ΤΟΥ/ΤΩΝ ΠΑΙΧΤΗ/ΠΑΙΧΤΩΝ
     * @return ΕΠΙΣΤΡΕΦΕΙ ΤΟ ΠΑΡΑΘΥΡΟ ΜΕ ΤΑ ΔΕΔΟΜΕΝΑ ΤΟΥ/ΤΩΝ ΠΑΙΧΤΗ/ΠΑΙΧΤΩΝ
     * @throws IOException
     */
    public JFrame frameForPlayersData() throws IOException {
        JFrame frame = newFrame("Players Data");
        BufferedImage img;
        String fontoName="src/images/1.jpeg";
        img = ImageIO.read(new File(fontoName));
        ImageIcon icon = new ImageIcon(img);

        JButton photo = new JButton();
        photo.setIcon(icon);
        photo.setLayout(new BorderLayout());


        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new BoxLayout(dataPanel , BoxLayout.Y_AXIS));
        dataPanel.add(pointsOfPlayer1);
        pointsOfPlayer1.setForeground(Color.RED);
        pointsOfPlayer1.setFont(new Font("",Font.PLAIN , 20));
        JLabel temp1 = new JLabel(firstPlayer.getPlayer() + " answered correct to the following questions");
        temp1.setForeground(Color.RED);
        temp1.setFont(new Font("",Font.PLAIN , 18));
        dataPanel.add(temp1);
        for (QuestionAndAnswer str: victoriesForP1) {
            JLabel er1 = new JLabel(str.getQuestion());
            er1.setForeground(Color.RED);
            er1.setFont(new Font("",Font.PLAIN , 15));
            dataPanel.add(er1);
        }
        if (playersNumber==2) {
            dataPanel.add(pointsOfPlayer2);
            pointsOfPlayer2.setForeground(Color.ORANGE);
            pointsOfPlayer2.setFont(new Font("",Font.PLAIN , 20));
            JLabel temp2 = new JLabel(secondPlayer.getPlayer() + " answered correct to the following questions");
            temp2.setForeground(Color.ORANGE);
            temp2.setFont(new Font("",Font.PLAIN , 18));
            dataPanel.add(temp2);
            for (QuestionAndAnswer str: victoriesForP2) {
                JLabel er1 = new JLabel(str.getQuestion());
                er1.setForeground(Color.ORANGE);
                er1.setFont(new Font("",Font.PLAIN , 15));
                dataPanel.add(er1);
            }
        }
        photo.add(dataPanel,BorderLayout.WEST);
        dataPanel.setOpaque(false);
        frame.add(photo);
        return frame;
    }


    /**
     * Η ΣΥΝΑΡΤΗΣΗ ΔΕΧΕΤΑΙ ΜΙΑ ΣΥΜΒΟΛΟΣΕΙΡΑ Η ΟΠΟΙΑ ΣΤΗΝ ΟΥΣΙΑ ΕΙΝΑΙ ΤΟ ΜΟΝΟΠΑΤΙ ΜΙΑΣ ΕΙΚΟΝΑΣ ΣΕ ΕΝΑΝ ΦΑΚΕΛΟ
     * ΔΗΜΙΟΥΡΓΕΙ ΤΟ ΑΡΧΙΚΟ ΠΑΡΑΘΥΡΟ ΤΟΥ ΠΑΙΧΝΙΔΙΟΥ ΤΟ ΟΠΟΙΟ ΠΑΡΑΘΥΡΟ ΕΧΕΙ ΦΟΝΤΟ ΤΗΝ ΕΙΚΟΝΑ ΣΤΗΝ ΟΠΙΑ ΤΟ ΜΟΝΟΠΑΤΙ
     * ΟΔΗΓΕΙ. ΔΙΝΕΙ ΕΠΙΣΗΣ ΤΗΝ ΔΥΝΑΤΟΤΗΤΑ ΣΤΟΝ ΠΑΙΧΤΗ ΝΑ ΕΠΙΛΕΞΕΙ Play , Exit Game ΤΑ ΟΠΟΙΑ ΟΔΗΓΟΥΝ ΣΤΗΝ ΕΚΙΝΝΗΣΗ ΚΑΙ
     * ΣΤΟΝ ΤΕΡΜΑΤΙΣΜΟ ΤΟΥ ΠΑΙΧΝΙΔΙΟΥ ΑΝΤΙΣΤΟΙΧΑ
     * @param fontoName ΣΥΜΒΟΛΟΣΕΙΡΑ Η ΟΠΟΙΑ ΣΤΗΝ ΟΥΣΙΑ ΕΙΝΑΙ ΤΟ ΜΟΝΟΠΑΤΙ ΜΙΑΣ ΕΙΚΟΝΑΣ ΣΕ ΕΝΑΝ ΦΑΚΕΛΟ.
     *                  ΧΡΗΣΙΜΟΠΟΙΕΙΤΕ ΓΙΑ ΝΑ ΕΙΝΑΙ ΦΟΝΤΟ ΤΟΥ ΑΡΧΙΚΟΥ ΠΑΡΑΘΥΡΟΥ.
     * @throws IOException
     */
    public void startMenu(String fontoName) throws IOException  {
        JPanel panel= new JPanel();
        panel.setLayout(new BorderLayout());

        BufferedImage img;
        img = ImageIO.read(new File(fontoName));
        ImageIcon icon = new ImageIcon(img);

        JButton photo = new JButton();
        photo.setIcon(icon);

        JButton bStartGame = new JButton("Play");
        bStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuForChoosingTheNumberOfPlayers();
            }
        });

        JButton bExitGame = new JButton("Exit Game");
        bExitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstFrame.dispose();
            }
        });

        BorderLayout aLayout = new BorderLayout();
        panel.setLayout(aLayout);

        panel.add(photo, BorderLayout.CENTER);
        firstFrame.add(panel);
        firstFrame.add(bStartGame,BorderLayout.PAGE_START);
        firstFrame.add(bExitGame,BorderLayout.PAGE_END);
        firstFrame.setIconImage(icon.getImage());
        firstFrame.setVisible(true);
    }

    /**
     * ΣΥΝΑΡΤΗΣΗ Η ΟΠΟΙΑ ΥΛΟΠΟΙΕΙ ΕΝΑ ΠΑΡΑΘΥΡΟ ΤΟ ΟΠΟΙΟ ΕΜΦΑΝΙΖΕΤΑΙ ΣΤΗΝ ΟΘΟΝΗ ΚΑΙ Ο ΧΡΗΣΤΗΣ
     * ΚΑΛΕΙΤΕ ΝΑ ΕΠΙΛΕΞΕΙ ΠΟΣΟΙ ΠΑΙΧΤΕΙΣ ΘΑ ΛΑΒΟΥΝ ΜΕΡΟΣ ΣΤΟ ΠΑΙΧΝΙΔΙ.
     */
    public void menuForChoosingTheNumberOfPlayers() {
        JFrame menuPlayers;
        menuPlayers = new JFrame("Number of players");
        menuPlayers.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuPlayers.setResizable(false);
        menuPlayers.setSize(320,100);
        menuPlayers.setLocationRelativeTo(null);
        menuPlayers.setLayout(null);

        pl1.setText("1 player");
        pl1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstPlayer=new Player("anna");
                playersNumber=1;
            }
        });
        pl1.setBounds(0,0,100,20);

        pl2.setText("2 players");
        pl2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstPlayer=new Player("anna");
                secondPlayer=new Player("bill");
                playersNumber=2;
            }
        });
        pl2.setBounds(0,30,100,20);

        menuPlayers.add(pl1);
        menuPlayers.add(pl2);
        menuPlayers.setVisible(true);
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
        f.setSize(1100,800);
        f.setLocationRelativeTo(null);
        return f;
    }

    /**
     * ΣΥΝΑΡΤΗΣΗ Η ΟΠΟΙΑ ΔΗΜΙΟΥΡΓΕΙ ΕΝΑ ΑΡΧΕΙΟ ΠΟΥ ΚΡΑΤΑ ΤΙς ΝΙΚΕΣ ΚΑΙ ΤΟ ΣΚΟΡ ΤΩΝ ΠΑΙΧΤΩΝ , ΑΦΟΥ
     * ΟΛΟΚΛΗΡΩΘΟΥΝ ΟΛΟΙ ΟΙ ΓΥΡΟΙ(5).
     * @throws IOException
     */
    public void makeTheArchive() throws IOException {
        FileReader out = new FileReader("o.txt");
        for (QuestionAndAnswer str: victoriesForP1) {
            out.read(CharBuffer.wrap("eeee"));
        }
    }
}