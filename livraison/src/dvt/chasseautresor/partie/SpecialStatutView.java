package dvt.chasseautresor.partie;

import dvt.chasseautresor.modele.cards.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Project : modele-de-jeu
 * Created by Montagnards on 05/04/2016
 */
public class SpecialStatutView extends JPanel {
    private List<JLabel> jlabelList;
    private JLabel statutSpe1;
    private JLabel statutSpe2;
    private JLabel statutSpe3;
    private JLabel statutSpe4;

    public SpecialStatutView() {
        this.setLayout(new GridLayout(2,2));

        statutSpe1=new JLabel();
        statutSpe2=new JLabel();
        statutSpe3=new JLabel();
        statutSpe4=new JLabel();

        this.add(statutSpe1);
        this.add(statutSpe2);
        this.add(statutSpe3);
        this.add(statutSpe4);


        jlabelList=new ArrayList<>();
        jlabelList.add(statutSpe1);
        jlabelList.add(statutSpe2);
        jlabelList.add(statutSpe3);
        jlabelList.add(statutSpe4);
    }


    /**
     * Ajoute l'icone specialCard à la vue statut
     * @param currentCard
     */
    public void enterAction(Card currentCard) {
        for(JLabel statutSpec : jlabelList) {
            if (statutSpec.getIcon() == null){
                Image newimg = currentCard.getImageIcon().getImage().getScaledInstance(75, 75,  java.awt.Image.SCALE_SMOOTH);
                statutSpec.setIcon(new ImageIcon(newimg));
                break;
            }
        }
    }

}
