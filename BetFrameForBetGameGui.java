import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BetFrameForBetGameGui {

    private JFrame betForOneQuestionFrame;
    private ArrayList<QuestionAndAnswer> questions;



    private JLabel pointsOfPlayer1;
    private JLabel pointsOfPlayer2;

    private int playersNumber=0;
    private int betPoints1=0;
    private int betPoints2=0;






    private Player p1;
    private Player p2;

    public int getBetPoints1() {
        return betPoints1;
    }
    public int getBetPoints2() {
        return betPoints2;
    }

    public BetFrameForBetGameGui(Player p1 , Player p2 , int playersNumber , JLabel l1 , JLabel l2 , ArrayList<QuestionAndAnswer> questions){
        pointsOfPlayer1=l1;
        pointsOfPlayer2=l2;
        this.questions = questions;
        this.playersNumber=playersNumber;
        this.p1=p1;
        this.p2=p2;
    }

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

    public void betFrame() throws IOException {

        betForOneQuestionFrame = newFrame("SET THE BET");

        BufferedImage img;
        String fontoName="src/images/1.jpeg";
        img = ImageIO.read(new File(fontoName));
        ImageIcon icon = new ImageIcon(img);

        JButton photo = new JButton();
        photo.setIcon(icon);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel , BoxLayout.Y_AXIS));

        JPanel pointsPanel = new JPanel();
        pointsPanel.setLayout(new BoxLayout(pointsPanel , BoxLayout.Y_AXIS));

        JLabel bet1;
        JLabel bet2;
        JLabel bet3;
        JLabel bet4;
        if (playersNumber==1) {
            bet1 =new JLabel(p1.getPlayer() + " press 1 to bet 250 points");
            bet2 =new JLabel(p1.getPlayer() + " press 2 to bet 500 points");
            bet3 =new JLabel(p1.getPlayer() + " press 3 to bet 750 points");
            bet4 =new JLabel(p1.getPlayer() + " press 4 to bet 1000 points");

            bet1.setForeground(Color.WHITE);
            bet1.setFont(new Font("" ,Font.PLAIN , 20));
            bet2.setForeground(Color.WHITE);
            bet2.setFont(new Font("" ,Font.PLAIN , 20));
            bet3.setForeground(Color.WHITE);
            bet3.setFont(new Font("" ,Font.PLAIN , 20));
            bet4.setForeground(Color.WHITE);
            bet4.setFont(new Font("" ,Font.PLAIN , 20));

            panel.setOpaque(false);
            panel.add(bet1);
            panel.add(bet2);
            panel.add(bet3);
            panel.add(bet4);

            addKeyListenerForBetFrameFor1Player('1' , 250);
            addKeyListenerForBetFrameFor1Player('2' , 500);
            addKeyListenerForBetFrameFor1Player('3' , 750);
            addKeyListenerForBetFrameFor1Player('4' , 1000);
            pointsOfPlayer1.setForeground(Color.RED);
            pointsOfPlayer1.setFont(new Font("",Font.PLAIN , 20));
            pointsPanel.setOpaque(false);
            pointsPanel.add(pointsOfPlayer1);
        }
        else if (playersNumber==2) {
            bet1 =new JLabel(p1.getPlayer() + " press 1 to bet 250 points");
            bet2 =new JLabel(p1.getPlayer() + " press 2 to bet 500 points");
            bet3 =new JLabel(p1.getPlayer() + " press 3 to bet 750 points");
            bet4 =new JLabel(p1.getPlayer() + " press 4 to bet 1000 points");

            bet1.setForeground(Color.RED);
            bet1.setFont(new Font("" ,Font.PLAIN , 20));
            bet2.setForeground(Color.RED);
            bet2.setFont(new Font("" ,Font.PLAIN , 20));
            bet3.setForeground(Color.RED);
            bet3.setFont(new Font("" ,Font.PLAIN , 20));
            bet4.setForeground(Color.RED);
            bet4.setFont(new Font("" ,Font.PLAIN , 20));

            JLabel bet5 = new JLabel(p2.getPlayer() + " press 6 to bet 250 points");
            JLabel bet6 = new JLabel(p2.getPlayer() + " press 7 to bet 500 points");
            JLabel bet7 = new JLabel(p2.getPlayer() + " press 8 to bet 750 points");
            JLabel bet8 = new JLabel(p2.getPlayer() + " press 9 to bet 1000 points");

            bet5.setForeground(Color.ORANGE);
            bet5.setFont(new Font("" ,Font.PLAIN , 20));
            bet6.setForeground(Color.ORANGE);
            bet6.setFont(new Font("" ,Font.PLAIN , 20));
            bet7.setForeground(Color.ORANGE);
            bet7.setFont(new Font("" ,Font.PLAIN , 20));
            bet8.setForeground(Color.ORANGE);
            bet8.setFont(new Font("" ,Font.PLAIN , 20));

            panel.setOpaque(false);
            panel.add(bet1);
            panel.add(bet2);
            panel.add(bet3);
            panel.add(bet4);

            panel.add(bet5);
            panel.add(bet6);
            panel.add(bet7);
            panel.add(bet8);

            addKeyListenerForBetFrameFor2Players('1' , 250 , 0);
            addKeyListenerForBetFrameFor2Players('2' , 500 , 0);
            addKeyListenerForBetFrameFor2Players('3' , 750 , 0);
            addKeyListenerForBetFrameFor2Players('4' , 1000 , 0);

            addKeyListenerForBetFrameFor2Players('6' , 0 , 250 );
            addKeyListenerForBetFrameFor2Players('7' , 0 , 500 );
            addKeyListenerForBetFrameFor2Players('8' , 0 , 750 );
            addKeyListenerForBetFrameFor2Players('9' , 0 , 1000 );

            pointsOfPlayer1.setForeground(Color.RED);
            pointsOfPlayer1.setFont(new Font("",Font.PLAIN , 20));
            pointsOfPlayer2.setForeground(Color.RED);
            pointsOfPlayer2.setFont(new Font("",Font.PLAIN , 20));
            pointsPanel.setOpaque(false);
            pointsPanel.add(pointsOfPlayer1);
            pointsPanel.add(pointsOfPlayer2);
        }

        String categoryOfQuestion = questions.get(0).getCategory();
        JLabel quest = new JLabel("Bet one of the following points for a question from category: " + categoryOfQuestion);
        quest.setForeground(Color.RED);
        quest.setFont(new Font("",Font.PLAIN , 20));

        photo.setLayout(new BorderLayout());
        photo.add(quest,BorderLayout.PAGE_START);
        photo.add(panel , BorderLayout.WEST);
        photo.add(pointsPanel , BorderLayout.PAGE_END);

        betForOneQuestionFrame.add(photo);
        betForOneQuestionFrame.setVisible(true);
    }

    public void addKeyListenerForBetFrameFor1Player(char plhktro , int bPoints){

        betForOneQuestionFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar()==plhktro) {
                    betPoints1 = bPoints;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
    public void addKeyListenerForBetFrameFor2Players(char plhktro , int bPoints1 , int bPoints2){

        betForOneQuestionFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar()==plhktro) {
                    if (plhktro == '1' || plhktro == '2' || plhktro == '3' || plhktro == '4') {
                        betPoints1 = bPoints1;
                    }
                    else if ((plhktro == '6' || plhktro == '7' || plhktro == '8' || plhktro == '9')) {
                        betPoints2 = bPoints2;
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
}

