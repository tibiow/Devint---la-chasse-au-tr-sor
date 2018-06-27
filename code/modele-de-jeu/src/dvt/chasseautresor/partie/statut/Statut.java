package dvt.chasseautresor.partie.statut;


import dvt.chasseautresor.modele.cards.CardType;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Cette enum représente l'ensemble des statut dans lequel se trouve le joueur quand
 * il est attaqué ou lorsque il a posé une carte spéciale
 * Un statut est caractérisé par ces effets et par son Image
 *
 * Created by Antoine on 16/03/2016.
 */
public enum Statut {


    COUSTEAU("../ressources/images/StatutIcon/coustot.png",true,CardType.CARDSPECIAL, new ArrayList<Statut>() {{add(RECIF);}}),
    EVELYNE("../ressources/images/StatutIcon/meteoMan.png",true,CardType.CARDSPECIAL,new ArrayList<Statut>() {{add(TEMPETE);}}),
    BOB("../ressources/images/StatutIcon/carpenter.png",true,CardType.CARDSPECIAL,new ArrayList<Statut>() {{add(CANON);}}),
    NOEUD("../ressources/images/StatutIcon/rope.png",true,CardType.CARDSPECIAL,new ArrayList<Statut>() {{add(VOILEE);}}),

    TEMPETE("../ressources/images/StatutIcon/storm.png",false, CardType.CARDOFFENSIVE, new ArrayList<Statut>() {{add(ECLAIRCIE);add(EVELYNE);}}),
    RECIF("../ressources/images/StatutIcon/reef.png",false, CardType.CARDOFFENSIVE,new ArrayList<Statut>() {{add(COUSTEAU);add(ESQUIVE);}}),
    VOILEE("../ressources/images/StatutIcon/shipTorn.png", false,CardType.CARDOFFENSIVE, new ArrayList<Statut>() {{add(NOEUD);add(VOILED);}}),
    CANON("../ressources/images/StatutIcon/canon.png",false,CardType.CARDOFFENSIVE, new ArrayList<Statut>() {{add(BOB);add(REPARE);}}),

    VOILED("../ressources/images/StatutIcon/unknow.png",false, CardType.CARDDEFENSIVE, new ArrayList<Statut>(){{add(VOILEE);}}),
    ESQUIVE ("../ressources/images/StatutIcon/unknow.png",false, CardType.CARDDEFENSIVE, new ArrayList<Statut>(){{add(RECIF);}}),
    VENTF("../ressources/images/StatutIcon/unknow.png",false, CardType.CARDDEFENSIVE, null),
    ECLAIRCIE("../ressources/images/StatutIcon/unknow.png",false, CardType.CARDDEFENSIVE, new ArrayList<Statut>(){{add(TEMPETE);}}),
    REPARE("../ressources/images/StatutIcon/unknow.png",false, CardType.CARDDEFENSIVE, new ArrayList<Statut>(){{add(CANON);}}),

    VITESSE("../ressources/images/StatutIcon/unknow.png",false, CardType.CARDVITESSE, null);

    private String imageURL;
    private boolean isSpecial; //si true spécial si false attaque
    private CardType cardType;
    private List<Statut> oppositeCard;



    Statut(String s,boolean b, CardType cardType, List<Statut> oppositeCard) {
        this.imageURL=s;
        this.isSpecial=b;
        this.cardType=cardType;
        this.oppositeCard=oppositeCard;

    }

    public String getImageURL(){
        return imageURL;
    }

    public boolean isSpecial(){
        return isSpecial;
    }


    public List<Statut> getOppositeCard() {
        return oppositeCard;
    }

    public static List<Statut> getAttackStatut(){
        List<Statut> statutListAttack= new ArrayList<>();
        statutListAttack.add(CANON);
        statutListAttack.add(RECIF);
        statutListAttack.add(VOILEE);

        return statutListAttack;
    }

    public CardType getCardType() {
        return cardType;
    }
}
