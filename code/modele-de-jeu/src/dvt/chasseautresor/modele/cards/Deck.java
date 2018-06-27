package dvt.chasseautresor.modele.cards;

import dvt.chasseautresor.partie.statut.Statut;

import java.util.*;

import static dvt.chasseautresor.partie.statut.Statut.*;

/**
 * Project : modele-de-jeu
 * Created by Thibaut on 08/03/2016
 */
public class Deck {

    private List<Card> deck;

    public Deck() {
        this.deck = init();
        shuffle();
        shuffle();
    }


    public List<Card> shuffle(){
        Collections.shuffle(deck);
        return deck;
    }


    public ArrayList<Card> getHand(){
        ArrayList<Card> hand= new ArrayList<>();
        for(int i=0; i<5;i++){
            Random random = new Random();
            int index = random.nextInt(deck.size());
            hand.add(deck.remove(index));
        }

        if (checkHand(hand))
            return hand;

        deck.addAll(hand);
        return getHand();
    }

    private boolean checkHand(ArrayList<Card> hand) {
        int carteVitesse=0;
        for (Card card : hand){
            if(card.getCardType()==CardType.CARDVITESSE)
                carteVitesse++;
        }

        return carteVitesse != 0;
    }

    public Card getNewCard(){
        return deck.remove(0);
    }

    public void addNewCard(Card card){
        deck.add(deck.size(),card);
    }


    /**
     * Methode permettant d'initialiser les cartes du deck
     * @return deck complet
     */
    private List<Card> init() {
        ArrayList<Card> deck = new ArrayList<>();

        for(int i=0; i<=10;i++){
            deck.add(new CardVitesse(CardType.CARDVITESSE,"../ressources/images/Cartes/card25.png","",VITESSE,"",25));
            deck.add(new CardVitesse(CardType.CARDVITESSE,"../ressources/images/Cartes/card50.png","",VITESSE,"",50));
            deck.add(new CardVitesse(CardType.CARDVITESSE,"../ressources/images/Cartes/card75.png","",VITESSE,"",75));

        }
        for(int i=0;i<=12; i++) {
            deck.add(new CardVitesse(CardType.CARDVITESSE, "../ressources/images/Cartes/card100.png","",VITESSE,"",100));
        }
        for(int i=0;i<=4;i++) {
            deck.add(new CardVitesse(CardType.CARDVITESSE, "../ressources/images/Cartes/card200.png","",VITESSE,"",200));
        }

        for(int i=0;i<=3;i++){
            deck.add(new CardOffensive(CardType.CARDOFFENSIVE,"../ressources/images/Cartes/cardRecif.png","../ressources/images/StatutIcon/reef.png", RECIF,"de lancer une attaque recif contre l'adversaire"));
            deck.add(new CardOffensive(CardType.CARDOFFENSIVE,"../ressources/images/Cartes/cardVoileE.png","../ressources/images/StatutIcon/shipTorn.png",VOILEE,"d'emmeler les voiles de l'adversaire"));
            deck.add(new CardOffensive(CardType.CARDOFFENSIVE,"../ressources/images/Cartes/cardTempete.png","../ressources/images/StatutIcon/storm.png", TEMPETE,"de lancer une tempéte contre l'adversaire"));
            deck.add(new CardOffensive(CardType.CARDOFFENSIVE,"../ressources/images/Cartes/cardCanon.png","../ressources/images/StatutIcon/canon.png", CANON,"de tirer un coup de canon contre l'adversaire"));
        }

        for(int i=0; i<=6;i++){
            deck.add(new CardDefensive(CardType.CARDDEFENSIVE,"../ressources/images/Cartes/cardVoileD.png","../ressources/images/StatutIcon/unknow.png", VOILED,"de demeler tes voiles"));
            deck.add(new CardDefensive(CardType.CARDDEFENSIVE,"../ressources/images/Cartes/cardEsquive.png","../ressources/images/StatutIcon/unknow.png", ESQUIVE,"d'esquiver un recif"));
            deck.add(new CardDefensive(CardType.CARDDEFENSIVE,"../ressources/images/Cartes/cardVent.png","../ressources/images/StatutIcon/wind.png", VENTF,"d'avancer plus vite"));
        }

        for( int i= 0; i<=10; i++){
            deck.add(new CardDefensive(CardType.CARDDEFENSIVE,"../ressources/images/Cartes/cardRepare.png","../ressources/images/StatutIcon/unknow.png",REPARE,"de reparer ton beateau"));
        }

        deck.add(new CardSpecial(CardType.CARDSPECIAL,"../ressources/images/Cartes/cardCousteau.png","../ressources/images/StatutIcon/coustot.png", COUSTEAU,"de ne plus etre bloqué par un recif"));
        deck.add(new CardSpecial(CardType.CARDSPECIAL,"../ressources/images/Cartes/cardExpertMeteo.png","../ressources/images/StatutIcon/meteoMan.png",EVELYNE,"de ne plus etre bloqué par une tempéte"));
        deck.add(new CardSpecial(CardType.CARDSPECIAL,"../ressources/images/Cartes/cardExpertNoeud.png","../ressources/images/StatutIcon/rope.png",NOEUD,"de ne plus etre recevoir d'attaque voile emmelé"));
        deck.add(new CardSpecial(CardType.CARDSPECIAL,"../ressources/images/Cartes/cardBricoleur.png","../ressources/images/StatutIcon/carpenter.png",BOB,"de ne plus etre attaquer par un canon"));


        return deck;

    }
}
