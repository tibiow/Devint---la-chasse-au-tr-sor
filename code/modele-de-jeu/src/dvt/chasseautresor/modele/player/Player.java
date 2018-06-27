package dvt.chasseautresor.modele.player;

import dvt.chasseautresor.modele.Arbitre;
import dvt.chasseautresor.modele.cards.Card;
import dvt.chasseautresor.partie.statut.Statut;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Cette classe représente les différents joueurs de notre jeu
 * Project: Devint
 * Created by Les Montagnards on 23/02/2016.
 */

public abstract class Player {
    protected Arbitre arbitre;
    protected String name;
    protected List<Statut> statutList;
    protected List<Card> cardList;
    protected int compteurVitesse;
    protected  boolean aJeter;



    public Player(Arbitre arbitre, List<Card> cardList) {
        this.arbitre = arbitre;
        this.cardList = cardList;
        this.statutList = new ArrayList<>();
        this.compteurVitesse=0;
        this.aJeter=false;
    }

    public Arbitre getArbitre() {
        return arbitre;
    }

    public List<Statut> getStatutList() {
        return statutList;
    }

    public List<Card> getHand() {
        return cardList;
    }

    public String getName() {
        return name;
    }

    public void setCompteurVitesse(int compteurVitesse) {
        this.compteurVitesse += compteurVitesse;
    }

    public void setCompteurVitesseD(int compteurVitesse) {
        this.compteurVitesse -= compteurVitesse;
    }

    public int getCompteurVitesse() {
        return compteurVitesse;
    }

    public boolean isaJeter() {
        return aJeter;
    }

    public void setaJeter(boolean aJeter) {
        this.aJeter = aJeter;
    }
}