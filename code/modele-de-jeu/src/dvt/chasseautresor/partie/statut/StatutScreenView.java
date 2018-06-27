package dvt.chasseautresor.partie.statut;

import javax.swing.*;
import java.awt.*;

/**
 *
 * Cette classe est l'ecran principal des statuts
 * C'est un GridLayout avec 2 cases , l'une contenant l'ecran de l'attaque subie
 * l'autre contenant l'écran de la/les cartes spéciales (c'est un GridLayout)
 * Created by Antoine on 18/03/2016.
 */
public class StatutScreenView extends JPanel {

    private int cptSpecialCard;
    private StatutAttackView attackView;
    private StatutSpecialView specialView;

    public StatutScreenView(){
        super();
        attackView=new StatutAttackView();
        specialView=new StatutSpecialView();
    }

    public void addStatut(Statut statut) {
        if (statut.isSpecial()){
            //add dans StatutSpecialView
            specialView.addStatut(statut);
            cptSpecialCard++;
        }else{
            //carte attaque
            attackView.removeAll();
            attackView.addStatut(statut);
        }
        refresh();
    }

    public void removeStatut(Statut statut){
        if(statut.isSpecial()){
            //remove dans StatutSpecialView
            specialView.removeStatut(statut);
            cptSpecialCard--;
        }else{
            attackView.removeStatut(statut);
        }
    }

    public void refresh(){
        //affiche
        if (cptSpecialCard==0){ //no special card
            this.setLayout(new GridLayout(1,0));
            this.add(attackView);
        }else{
            this.setLayout(new GridLayout(2,0));
            this.add(attackView);
            this.add(specialView);
        }
        specialView.revalidate();
        attackView.revalidate();
    }

    public StatutSpecialView getSpecialView() {
        return specialView;
    }

    public StatutAttackView getAttackView() {
        return attackView;
    }
}
