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

        List<Statut> listStatutPlayer = new ArrayList<>(arbitre.whatCanIPlay(this));

        System.out.print("Jeu du joueur 2 :");
        for (Card card : this.cardList)
            System.out.print("["+card.getStatut()+"]"+ " ");
        System.out.print("\nLe joueur 2 peut jouer ça :");
        System.out.println(listStatutPlayer);

        if(listStatutPlayer.isEmpty()) {
            this.aJeter = true;
            System.out.println("Le joueur va jeter une carte");
            return this.cardList.get(0);
        }else
            this.aJeter=false;

        int minCard = 250;
        int index = 0;
        for (Card card : this.getHand()) {
            Statut stat = card.getStatut();
            if (arbitre.whatCanIPlay(this).contains(stat)) {
                if(stat.equals(Statut.VITESSE)){
                    CardVitesse vitesse = (CardVitesse) card;
                    if(minCard> vitesse.getValue()){
                        minCard = vitesse.getValue();
                        index = getHand().indexOf(card);
                    }

                }

                else{
                    if(!(arbitre.whatCanIPlay(this).contains(Statut.VITESSE))){
                        arbitre.playCard(this, card);
                        System.out.println(card.getRole());
                        return card;
                    }
                    if(Math.random()*2 == 0) {
                        arbitre.playCard(this, card);
                        System.out.println(card.getRole());
                        return card;
                    }
                }

            }

        }


        Card card = this.getHand().get(index);
        arbitre.playCard(this, card);
        System.out.println(card.getRole());
        System.out.println("Joueur 2 : " + this.compteurVitesse + "\n\n");
        return card;

        /*
        System.out.println("L'ordinateur a jeté une carte" + "\n\n");
        return this.getHand().get(0);
        */

    }
}
