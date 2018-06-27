package dvt.chasseautresor.modele.cards;

import dvt.chasseautresor.partie.statut.Statut;

import javax.swing.*;
import java.awt.*;

/**
 *
 * Cette classe représente les différents cartes de notre jeu
 * Project: Devint
 * Created by Les Montagnards on 23/02/2016.
 */


public abstract class Card {

    protected String image;
    protected CardType cardType;
    protected String imageState;
    protected Statut statut;
    protected String role;

    public Card(String image, CardType cardType, String imageState, Statut statut, String role) {
        this.image = image;
        this.cardType = cardType;
        this.imageState = imageState;
        this.statut = statut;
        this.role = role;
    }

    public ImageIcon getImage() {
        ImageIcon imageIcon = new ImageIcon(image);
        Image newimg = imageIcon.getImage().getScaledInstance(180, 269,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        return imageIcon;
    }

    public ImageIcon getImageFullScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        System.out.println(width+""+height);


        int scale=(int)(height*395)/571;

        ImageIcon imageIcon = new ImageIcon(image);
        Image newimg = imageIcon.getImage().getScaledInstance(scale-20,(int) height-20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        return imageIcon;
    }

    public ImageIcon getImageIcon() {
        ImageIcon imageIcon = new ImageIcon(imageState);
        Image newimg = imageIcon.getImage().getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        return imageIcon;
    }

    public CardType getCardType() {
        return cardType;
    }

    public String getRole() {
        return "Cette carte te permet "+role;
    }

    public Statut getStatut() {
        return statut;
    }
}
