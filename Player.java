/**
 * Κλαση Player . Στην κλαση υπαρχουν τα πεδια points, player,corectA .
 * το πεδιο points αποθηκευει τους ποντους του παικτη απο την αρχη μεχρι το τελος του παιχνιδιου.
 * το πεδιο player αποθηκευει το συνθηματικο του παικτη.
 * το πεδιο corectA αποθηκευει τις σωστες απαντησεις που εχει δωσει ο παιχτης
 */
public class Player {
    private int points;
    private String player;
    private int corectA;

    /**
     * κατασκευαστης Player ο οποιος δεχεται σαν παραμετρο το συνθηματικο του παικτη
     * Αρχικοποιει τους ποντους του παικτη σε μια αρχικη τιμη μηδεν (0)
     * @param player περιεχει το συνθηματικο του παικτη
     */
    public Player(String player)
    {
        corectA=0;
        points = 0;
        this.player= player;
    }

    /**
     *Συναρτηση getPoints η οποια επιστρεφει του ποντου του παικτη
     * @return επιστρεφει τους ποντους του παικτη
     */
    public int getPoints(){
        return points;
    }

    /**
     *Συναρτηση getPlayer η οποια επιστρεφει του συνθηματικο του παικτη
     * @return επιστρεφει του συνθηματικο του παικτη
     */
    public String getPlayer(){
        return player;
    }

    /**
     *Συναρτηση updatePointsOfPlayer με παραμετρο εναν ακεραιο αριθμο ο οποιος ενημερωνει του ποντους του παικτη
     * @param p ποντοι του παικτη που ειτε θα αφαιρεθουν ειτε θα προστεθουν στον παικτη αναλογα με την αποδοση του
     * στην απαντηση της ερωτησης
     */
    public void updatePointsOfPlayer(double p){
        points+=p;
    }

    /**
     * Συναρτηση correctAnserws οπου οταν καλειτε ενημερωνονται οι σωστες απαντησεις που εχει δωσει και αυξανονται κατα μια μοναδα
     *
     * */
    public void correctAnserws()
    {corectA=corectA+1;}


    /**
     *Συναρτηση getCorectA η οποια επιστρεφει την μεταβλητη corectA οπου εχει το συνολο των σωστων απαντησεω του παιχτη
     * -@return corectA -επιστρεφει τις σωστες απαντησεις του παιχτη/retun the correct anwsers
     *a getter for CorrectA
     */
    public int getCorectA(){return corectA;}

    public int refreshA(){return corectA=0;}

}
