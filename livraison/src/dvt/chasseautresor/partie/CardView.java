package dvt.chasseautresor.partie;

import dvt.chasseautresor.modele.Arbitre;
import dvt.chasseautresor.modele.cards.*;
import dvt.chasseautresor.partie.statut.Statut;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Project : modele-de-jeu
 * Created by Montagnards on 01/03/2016
 */
public class CardView extends JPanel {

    private JLabel card1;
    private JLabel card2;
    private JLabel card3;
    private JLabel card4;
    private JLabel card5;
    private JLabel fleche1;
    private JLabel fleche2;
    private JLabel fleche3;
    private JLabel fleche4;
    private JLabel fleche5;
    private ArrayList<JLabel> cardList;
    private ArrayList<JLabel> flecheList;
    private int currentCard;
    private Deck deck;
    private List<Card> hand;

    private Color pathColor1;
    private int colorChoice;
    private static final Color[] BORDER_COLOR = { new Color(243,157,1), new Color(88,199,224),new Color(240,116,132),  new Color(47,179,142),new Color(174,2,0)};
    private Arbitre arbitre;

    public CardView() {
        colorChoice=0;
        pathColor1 = BORDER_COLOR [colorChoice];

        this.setLayout(new GridLayout(2,5));

        arbitre=new Arbitre();

        deck=arbitre.getDeck();

        hand=arbitre.getPlayer1().getHand();

        currentCard=0;


        card1=new JLabel(hand.get(0).getImage());
        card1.setBorder(new MatteBorder(5,5,5,5,pathColor1));

        card2=new JLabel(hand.get(1).getImage());

        card3=new JLabel(hand.get(2).getImage());

        card4=new JLabel(hand.get(3).getImage());

        card5=new JLabel(hand.get(4).getImage());

        fleche1 = new JLabel(new ImageIcon("../ressources/images/fleche0.png"));
        fleche2 = new JLabel(new ImageIcon("../ressources/images/fleche0.png"));
        fleche3 = new JLabel(new ImageIcon("../ressources/images/fleche0.png"));
        fleche4 = new JLabel(new ImageIcon("../ressources/images/fleche0.png"));
        fleche5 = new JLabel(new ImageIcon("../ressources/images/fleche0.png"));
        fleche2.setVisible(false);
        fleche3.setVisible(false);
        fleche4.setVisible(false);
        fleche5.setVisible(false);


        this.add(card1);
        this.add(card2);
        this.add(card3);
        this.add(card4);
        this.add(card5);
        this.add(fleche1);
        this.add(fleche2);
        this.add(fleche3);
        this.add(fleche4);
        this.add(fleche5);

        cardList=new ArrayList<>();
        cardList.add(card1);
        cardList.add(card2);
        cardList.add(card3);
        cardList.add(card4);
        cardList.add(card5);

        flecheList = new ArrayList<>();
        flecheList.add(fleche1);
        flecheList.add(fleche2);
        flecheList.add(fleche3);
        flecheList.add(fleche4);
        flecheList.add(fleche5);




    }


    /**
     * Change le carré de selection de place
     * a gauche
     */
    public void leftAction(){
        if(currentCard>0 && currentCard<5){
            cardList.get(currentCard).setBorder(null);
            flecheList.get(currentCard).setVisible(false);
            //System.out.println("Index left arrow"+currentCard);
            cardList.get(--currentCard).setBorder(new MatteBorder(5,5,5,5,BORDER_COLOR [colorChoice % BORDER_COLOR.length]));
            flecheList.get(currentCard).setVisible(true);

        }
    }

    /**
     * Change le carré de selection de place
     * a droite
     */
    public void rightAction() {
        if(currentCard>=0 && currentCard<4){
            cardList.get(currentCard).setBorder(null);
            flecheList.get(currentCard).setVisible(false);
            //System.out.println("Index right arrow"+currentCard);
            cardList.get(++currentCard).setBorder(new MatteBorder(5,5,5,5,BORDER_COLOR [colorChoice % BORDER_COLOR.length]));
            flecheList.get(currentCard).setVisible(true);
        }
    }


    public Card getCurrentCard() {
        return hand.get(currentCard);
    }

    /**
     * Action touche entrer
     * Joue la carte
     */
    public Card enterAction(){

        if(arbitre.getPlayer1().playCard(hand.get(currentCard))) {

            deck.addNewCard(hand.get(currentCard));
            hand.set(currentCard, deck.getNewCard());

            verifMinCardVitesse();

            updateHand();

            Card cardAdversaire= arbitre.getPlayer2().playCard();
            int position = arbitre.getPlayer2().getHand().indexOf(cardAdversaire);
            deck.addNewCard(cardAdversaire);
            arbitre.getPlayer2().getHand().set(position, deck.getNewCard());

            return cardAdversaire;

        }

        return null;
    }


    private void updateHand(){
        //Actualiste image de la main
        int i = 0;
        for (JLabel card : cardList) {
            card.setIcon(hand.get(i).getImage());
            i++;
        }
    }
    /**
     * Methode qui permet de vérifier qu'il y est toujours une carte vitesse
     * en main
     */
    private void verifMinCardVitesse() {
        boolean carteVitesse=false;

        while(!carteVitesse){
            for(Card card : hand){
                if(card.getCardType().equals(CardType.CARDVITESSE)) {
                    carteVitesse = true;
                    break;
                }
            }

            if(!carteVitesse){
                deck.addNewCard(hand.get(currentCard));
                hand.set(currentCard,deck.getNewCard());
            }
        }
    }


    public void changeBorderColor(){
        pathColor1 = BORDER_COLOR[++colorChoice % BORDER_COLOR.length];
        cardList.get(currentCard).setBorder(new MatteBorder(5,5,5,5,pathColor1));
        ImageIcon img = new ImageIcon("../ressources/images/fleche"+colorChoice % BORDER_COLOR.length+".png");
        for(int i=0;i<5;i++){
          flecheList.get(i).setIcon(img);
        }
    }


    public Arbitre getArbitre() {
        return arbitre;
    }
}
