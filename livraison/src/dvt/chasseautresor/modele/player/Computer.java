package dvt.chasseautresor.modele.player;

import dvt.chasseautresor.modele.Arbitre;
import dvt.chasseautresor.modele.cards.Card;
import dvt.chasseautresor.partie.statut.Statut;
import dvt.chasseautresor.modele.cards.CardVitesse;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe hérite de Player. Elle représente l'ordinateur qui affronte le joueur
 * Project: Devint
 * Created by Les Montagnards on 23/02/2016.
 */


public class Computer extends Player {

    public Computer(Arbitre arbitre, List<Card> cardList) {
        super(arbitre, cardList);
        this.name = "Computer";
    }

    public Card playCard() {

        List<Statut> listStatutPlayer = new ArrayList<>();
        for (Card card1 : this.cardList) {
            listStatutPlayer.add(card1.getStatut());
        }

        List<Statut> commonElement = new ArrayList<>(arbitre.whatCanIPlay(this));
        commonElement.retainAll(listStatutPlayer);

        System.out.print("Jeu du joueur 2 :");
        for (Card card : this.cardList)
            System.out.print("["+card.getStatut()+"]"+ " ");
        System.out.print("\nLe joueur 2 peut jouer ça :");
        System.out.println(commonElement);

        int minCard = 200;
        int index = 0;
        for (Card card : this.getHand()) {
            if (arbitre.whatCanIPlay(this).contains(card.getStatut())) {
                if(card.getStatut().equals(Statut.VITESSE)){
                    CardVitesse vitesse = (CardVitesse) card;
                    if(minCard> vitesse.getValue()){
                        minCard = vitesse.getValue();
                        index = getHand().indexOf(card);
                    }

                }
                else{
                    if(Math.random()*2 == 0) {
                        arbitre.playCard(this, card);
                        System.out.println(card.getRole());
                        System.out.println("Joueur 2 : " + this.compteurVitesse + "\n\n");
                        return card;
                    }
                }


                /*
                arbitre.playCard(this, card);
                System.out.println(card.getRole());
                System.out.println("Joueur 2 : " + this.compteurVitesse + "\n\n");
                return card;
                */

            }

        }







        Card card = this.getHand().get(index);
        arbitre.playCard(this, card);
        System.out.println(card.getRole());
        System.out.println("Joueur 2 : " + this.compteurVitesse + "\n\n");
        return card;

        /*
        System.out.println("L'ordinateur a jeté une carte" + "\n\n");
        this.aJeter=true;
        return this.getHand().get(0);
        */

    }
}
