package dvt.chasseautresor.partie;

import dvt.chasseautresor.modele.cards.*;
import dvt.chasseautresor.modele.player.Player;
import dvt.chasseautresor.partie.action.*;
import dvt.chasseautresor.partie.statut.Statut;
import dvt.chasseautresor.partie.statut.StatutScreenView;
import dvt.devint.F4Action;
import dvt.devint.Jeu;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

import static dvt.devint.ConstantesDevint.FONT_TYPE_DEFAULT;
import static dvt.devint.ConstantesDevint.WIN_SON;

/**
 * Project : modele-de-jeu
 * Created by Montagnards on 29/02/2016
 */
public class ChasseAuTresor extends Jeu {

    private JPanel world;
    private BateauView bateauView;
    private StatutScreenView statutView;
    private CardView cardView;
    private int sonNum;
    private boolean show;
    private JLabel cardJlabel;
    private PausablePlayer player;


    @Override
    public void init() {
        this.setTitle("Jeu");


        //On ajoute le bouton au content pane de la JFrame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //On récupére la taille de l'écran
        Dimension screenSizePer4 = new Dimension((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4); //On la divise par 4


        //Jpanel Global
        world = new JPanel(new BorderLayout());
        world.setBackground(getBackground());

        bateauView = new BateauView();
        bateauView.setPreferredSize(screenSizePer4);
        world.add(bateauView, BorderLayout.NORTH);

        statutView = new StatutScreenView();
        statutView.setPreferredSize(screenSizePer4);
        world.add(statutView, BorderLayout.EAST);


        cardView = new CardView();
        world.add(cardView, BorderLayout.CENTER);
        this.add(world);



        /*ecouteur flèche droite*/
        addControlDown(KeyEvent.VK_RIGHT, new RightArrowAction(cardView));

        /*ecouteur flèche gauche*/
        addControlDown(KeyEvent.VK_LEFT, new LeftArrowAction(cardView));

        /*ecouteur touche entrer*/
        addControlDown(KeyEvent.VK_ENTER, new EnterAction(this));

        /*ecouteur touche espace*/
        addControlDown(KeyEvent.VK_SPACE, new SpaceAction(this));

        /*ecouteur touche F2*/
        addControlDown(KeyEvent.VK_F2, new F2Action(this));

        /*ecouteur touche F3*/
        addControlDown(KeyEvent.VK_F3, new F3Action(this));

        /*ecouteur touche F4*/
        addControlDown(KeyEvent.VK_F4, new F4Action(this));

        /*ecouteur touche Echap*/
        addControlDown(KeyEvent.VK_ESCAPE, new EchapAction(this));

        show = false;
        sonNum = 0;

        try {
            FileInputStream input = new FileInputStream("../ressources/sons/backgorundMusique.mp3");
            player = new PausablePlayer(input);

            player.play();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update() {
        updateBateauView();
    }

    @Override
    public void render() {
        world.setBackground(getBackground());

        cardView.setBackground(getBackground());


        renderBateauView();

        renderStatutView();
    }


    @Override
    public void reset() {
    }


    /**
     * Changer la couleur de vue des bateau
     */
    public void changeBateauColor() {
        bateauView.changeColor();
    }

    /**
     * Changer la couleur du border de la carte selectionnée
     */
    public void changeBorderColor() {
        cardView.changeBorderColor();
    }


    /**
     * Joue la carte selectionné
     * Selon le type de carte on ne reagit pas de la même façon
     */
    public void enterAction() {

        Card currentCard = cardView.getCurrentCard();
        Card card = cardView.enterAction();
        if (card != null) {

            updateStatut(cardView.getArbitre().getPlayer1(), currentCard);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateStatut(cardView.getArbitre().getPlayer2(), card);

        }

    }


    public void updateStatut(Player player, Card card) {

        if (card.getCardType().equals(CardType.CARDVITESSE)) {
            CardVitesse vitesse = (CardVitesse) card;

            if (!player.getName().equals("Computer"))
                bateauView.move(player.getCompteurVitesse());
            else
                bateauView.moveEnnemy(player.getCompteurVitesse());

            this.getSIVOX().playWav("../ressources/sons/wave1.wav");

        } else if (card.getCardType().equals(CardType.CARDSPECIAL) && !player.getName().equals("Computer")) {
            CardSpecial cardSpecial = (CardSpecial) card;
            statutView.addStatut(cardSpecial.getStatut());
        }
        //Carte autres
        else if (card.getCardType().equals(CardType.CARDOFFENSIVE) && player.getName().equals("Computer")) {

            CardOffensive cardSong = (CardOffensive) card;

            if (cardSong.getStatut().equals(Statut.RECIF)) {
                this.getSIVOX().playWav("../ressources/sons/recif.wav");
            } else if (cardSong.getStatut().equals(Statut.CANON)) {
                this.getSIVOX().playWav("../ressources/sons/canon.wav");
            } else if (cardSong.getStatut().equals(Statut.TEMPETE)) {
                this.getSIVOX().playWav("../ressources/sons/tempete.wav");
            }

            statutView.addStatut(cardSong.getStatut());
        } else if (card.getCardType().equals(CardType.CARDDEFENSIVE) && !player.getName().equals("Computer")) {
            if (!card.getStatut().equals(Statut.VENTF))
                statutView.removeStatut(card.getStatut());
        }

    }


    /**
     * Zoom la carte selectionné
     */
    public void spaceAction() {
        //Si pas zoom
        if (!show) {
            cardJlabel = new JLabel(cardView.getCurrentCard().getImageFullScreen());
            world.setVisible(false);
            this.add(cardJlabel);
            show = true;
        }
        //Si zoom
        else {
            this.remove(cardJlabel);
            world.setVisible(true);
            show = false;
        }
    }

    /**
     * Met a jour la partie modele des curseur bateau
     */
    private void updateBateauView() {
        int fini = bateauView.finish();
        //Si joueur a gagné
        if (fini == 1 && sonNum == 0) {
            player.stop();
            this.getSIVOX().playWav(WIN_SON);
            //this.getSIVOX().playText("Vous avez gagné");
            //Son num permet de jouer le son qu'une fois
            sonNum++;
        }
        //Si ordinateur a gagné
        else if (fini == -1 && sonNum == 0) {
            this.getSIVOX().playText("Vous avez perdu");
            sonNum++;
        }
        //Si fini==0 la partie pas finis
    }

    /**
     * Met a jour la vue des curseurs bateau
     */
    private void renderBateauView() {
        //Change le fond
        bateauView.setBackground(getBackground());
        for (JSlider jSlider : bateauView.getjSliderArrayList()) {
            jSlider.setBackground(getBackground());
        }
    }


    /**
     * Met a jour la vue des statuts
     */
    private void renderStatutView() {

        //Change le fond
        statutView.setBackground(getBackground());
        statutView.getAttackView().setBackground(getBackground());
        statutView.getSpecialView().setBackground(getBackground());
    }


    public void playRules() {
        player.pause();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (show) {
            System.out.println("je suis dans le role");
            this.getSIVOX().playText(cardView.getCurrentCard().getRole());
        } else {
            System.out.println("Je suis dans le plateau");
            this.getSIVOX().playText("Tu dois jouer une carte qui te permette d'avancer ou de contrer une attaque");
        }

        try {
            Thread.sleep(5500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        player.resume();

    }

    public void previous() {
        if (show) {
            this.remove(cardJlabel);
            world.setVisible(true);
            show = false;
        } else {
            player.stop();
            this.dispose();
        }
    }


}
