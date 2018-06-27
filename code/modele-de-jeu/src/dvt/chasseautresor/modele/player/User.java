package dvt.chasseautresor.modele.player;

import dvt.chasseautresor.modele.Arbitre;
import dvt.chasseautresor.modele.cards.Card;
import dvt.chasseautresor.partie.statut.Statut;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe hérite de Player. Elle représente le joueur du mille borne
 * Project: Devint
 * Created by Les Montagnards on 23/02/2016.
 */
public class User extends Player {


    public User(Arbitre arbitre, List<Card> cardList) {
        super(arbitre, cardList);
        this.name = "User";
    }

    public boolean playCard(Card card) {

        List<Statut> listStatutPlayer = new ArrayList<>();
        for (Card card1 : this.cardList) {
            listStatutPlayer.add(card1.getStatut());
        }

        List<Statut> commonElement = new ArrayList<>(arbitre.whatCanIPlay(this));
        commonElement.retainAll(listStatutPlayer);


        System.out.print("Jeu du joueur 1 :");
        for (Card card3 : this.cardList)
            System.out.print("["+card3.getStatut()+"]"+ " ");

        System.out.print("\nLe joueur 1 peut jouer ça :");
        System.out.println(commonElement);

        if (arbitre.whatCanIPlay(this).contains(card.getStatut())) {
            arbitre.playCard(this, card);
            System.out.println(card.getRole());
            System.out.println("Joueur 1 : " + this.compteurVitesse+ "\n\n");
            return true;
        }

        if (commonElement.isEmpty()) {
            System.out.println("J'ai jeté une carte"+ "\n\n");
            return true;
        }

        return false;


    }
}
