package dvt.chasseautresor.modele;

import dvt.chasseautresor.modele.cards.*;
import dvt.chasseautresor.modele.player.*;
import dvt.chasseautresor.partie.statut.Statut;

import java.util.ArrayList;
import java.util.List;

/**
 * Project : modele-de-jeu
 * Created by Thibaut on 13/04/2016
 */
public class Arbitre {
  private Deck deck;
  private User player1;
  private Computer player2;


  public Arbitre() {
    deck = new Deck();
    player1 = new User(this, deck.getHand());
    player2 = new Computer(this, deck.getHand());
  }

  /**
   * Methode qui permet de savoir si on peut jouer une carte ou pas
   *
   * @param player
   * @param cardStatut
   * @return true si on peut false sinon
   */
  public boolean isItPossibleToPlay(Player player, Statut cardStatut) {
    switch (cardStatut.getCardType()) {
      case CARDDEFENSIVE:
        return isItPossibleToPlayDefensive(player, cardStatut);
      case CARDOFFENSIVE:
        return isItPossibleToPlayOffensive(player, cardStatut);
      case CARDVITESSE:
        return isItPossibleToPlayVitesse(player, cardStatut);
      case CARDSPECIAL:
        return isItPossibleToPlaySpecial(player, cardStatut);
    }


    return true;
  }


  /**
   * Methode permettant de savoir si on peut jouer une carte defensive.
   *
   * @param player
   * @param cardStatut
   * @return
   */
  private boolean isItPossibleToPlayDefensive(Player player, Statut cardStatut) {


    if (cardStatut.equals(Statut.VENTF)) {
      return !player.getStatutList().contains(Statut.CANON);
    }


    for (Statut statut : player.getStatutList()) {
      for (Statut statutOp : cardStatut.getOppositeCard()) {
        if (statut.equals(statutOp))
          return true;
      }
    }

    return false;
  }

  /**
   * @param player
   * @param cardStatut
   * @return
   */
  private boolean isItPossibleToPlayOffensive(Player player, Statut cardStatut) {


    Player opposite = player.getName() == "Computer" ? player1 : player2;

    for (Statut statutAtttack : Statut.getAttackStatut()) {
      if (opposite.getStatutList().contains(statutAtttack))
        return false;
    }

    for (Statut statutOp : opposite.getStatutList()) {
      for (Statut statutPl : cardStatut.getOppositeCard()) {
        if (statutOp.equals(statutPl) && statutOp.isSpecial()) {
          return false;
        }
      }
    }


    return true;
  }

  /**
   * @param player
   * @param cardStatut
   * @return
   */
  private boolean isItPossibleToPlayVitesse(Player player, Statut cardStatut) {
    return !player.getStatutList().contains(Statut.CANON);
  }

  /**
   * @param player
   * @param cardStatut
   * @return
   */
  private boolean isItPossibleToPlaySpecial(Player player, Statut cardStatut) {
    return true;
  }


  /**
   * @param player
   * @return
   */
  public List<Statut> whatCanIPlay(Player player) {
    List<Statut> listStatutPlayer = new ArrayList<>();
    for (Card card1 : player.getHand()) {
      listStatutPlayer.add(card1.getStatut());
    }
    List<Statut> statutCanIPlay = new ArrayList<>();
    for (Statut statut : Statut.values()) {
      if (isItPossibleToPlay(player, statut))
        statutCanIPlay.add(statut);
    }
    List<Statut> commonElement = new ArrayList<>(statutCanIPlay);
    commonElement.retainAll(listStatutPlayer);
    return commonElement;
  }


  public void playCard(Player player, Card card) {

    cleanFinishStatut(player);

    switch (card.getCardType()) {
      case CARDDEFENSIVE:
        playDefensive(player, card);
        break;
      case CARDOFFENSIVE:
        playOffensive(player, card);
        break;
      case CARDVITESSE:
        playVitesse(player, card);
        break;
      case CARDSPECIAL:
        playSpecial(player, card);
        break;
    }

    System.out.println("Statut joueur1" + player1.getStatutList());
    System.out.println("Statut joueur2" + player2.getStatutList());


  }

  private void cleanFinishStatut(Player player) {

    player.getStatutList().remove(Statut.VENTF);
    player.getStatutList().remove(Statut.TEMPETE);

  }


  private void playSpecial(Player player, Card card) {
    CardSpecial cardSpecial = (CardSpecial) card;
    player.getStatutList().add(cardSpecial.getStatut());
  }

  private void playVitesse(Player player, Card card) {
    CardVitesse cardVitesse = (CardVitesse) card;

    if (player.getStatutList().contains(Statut.VENTF)) {
      player.setCompteurVitesse(cardVitesse.getValue() + 20);
      player.getStatutList().remove(Statut.VENTF);
    } else if (player.getStatutList().contains(Statut.RECIF))
      player.setCompteurVitesse(cardVitesse.getValue() / 2);
    else if (player.getStatutList().contains(Statut.VOILEE))
      player.setCompteurVitesse(cardVitesse.getValue() - 20);
    else
      player.setCompteurVitesse(cardVitesse.getValue());

  }

  private void playOffensive(Player player, Card card) {
    CardOffensive cardOffensive = (CardOffensive) card;
    Player opposite = player.getName() == "Computer" ? player1 : player2;

    opposite.getStatutList().add(cardOffensive.getStatut());

    if (card.getStatut().equals(Statut.TEMPETE)) {
      opposite.setCompteurVitesseD(20);
    }
  }

  private void playDefensive(Player player, Card card) {
    CardDefensive cardDefensive = (CardDefensive) card;

    if (!cardDefensive.getStatut().equals(Statut.VENTF))
      player.getStatutList().remove(cardDefensive.getStatut().getOppositeCard().get(0));
    else
      player.getStatutList().add(cardDefensive.getStatut());

  }


  public Deck getDeck() {
    return deck;
  }

  public User getPlayer1() {
    return player1;
  }

  public Computer getPlayer2() {
    return player2;
  }
}