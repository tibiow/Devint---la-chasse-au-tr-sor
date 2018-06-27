package dvt.chasseautresor.partie.statut;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Antoine on 23/03/2016.
 */
public class StatutSpecialView extends StatutView{
    private int nbSpe;
    //private JLabel imgSpe1,imgSpe2,imgSpe3,imgSpe4;

    private ArrayList<JLabel> arrStatut; //utile pour remove

    public StatutSpecialView(){
        super();
        nbSpe=0;
        arrStatut=new ArrayList<>();
    }
    /*
    @Override
    protected void addStatut(Statut statut) {
        if (nbSpe==0){this.addFirstStatut(statut);}

        if (nbSpe==1){this.addSecondStatut(statut);}

        if (nbSpe==2){this.addThirdStatut(statut);}

        if(nbSpe==3){this.addFourthStatut(statut);}

        this.add(this.view);
        nbSpe++;
    }

    private void addFirstStatut(Statut statut){
        this.view.setLayout(new GridLayout(1,0));
        imgSpe1=new JLabel(new ImageIcon(statut.getImageURL()));
        this.view.add(imgSpe1);

        arrStatut.add(0,imgSpe1);
    }
    private void  addSecondStatut(Statut statut){
        this.view.setLayout(new GridLayout(2,0));
        this.view.add(imgSpe1); //imgSpé1 est déjà enregistré
        imgSpe2=new JLabel(new ImageIcon(statut.getImageURL()));
        this.view.add(imgSpe2);
    }
    private void addThirdStatut(Statut statut){

       this.setLayout(new GridLayout(2,2));
        this.view.add(imgSpe1);
        this.view.add(imgSpe2);
        imgSpe3=new JLabel(new ImageIcon(statut.getImageURL()));
        this.view.add(imgSpe3);
    }

        private void addFourthStatut(Statut statut){
        this.view.setLayout(new GridLayout(2,2));
        this.view.add(imgSpe1);
        this.view.add(imgSpe2);
        this.view.add(imgSpe3);
        imgSpe4=new JLabel(new ImageIcon(statut.getImageURL()));
        this.view.add(imgSpe4);
    }

    */
    @Override
    protected void addStatut(Statut statut){
       /*je modifie le GridLayout, ajoute ceux déjà enregistrés, créé le nouveau Jlbael et l'ajoute*/
        if (nbSpe==0){
            this.setLayout(new GridLayout(1,0));
            Image newimg = new ImageIcon(statut.getImageURL()).getImage().getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);
            JLabel imgSpe=new JLabel(new ImageIcon(newimg));
            this.add(imgSpe);

            /*l'enregistre*/
            arrStatut.add(0,imgSpe);
        }

        if (nbSpe==1){
            this.setLayout(new GridLayout(2,0));
            addSavedStatut();
            Image newimg = new ImageIcon(statut.getImageURL()).getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
            JLabel imgSpe=new JLabel(new ImageIcon(newimg));
            this.add(imgSpe);

            /*l'enregistre*/
            arrStatut.add(1,imgSpe);
        }

        if (nbSpe==2){
            this.setLayout(new GridLayout(2,2));
            addSavedStatut();
            Image newimg = new ImageIcon(statut.getImageURL()).getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
            JLabel imgSpe=new JLabel(new ImageIcon(newimg));
            this.add(imgSpe);

            /*l'enregistre*/
            arrStatut.add(2,imgSpe);
        }

        if (nbSpe==3){
            this.setLayout(new GridLayout(2,2));
            addSavedStatut();
            Image newimg = new ImageIcon(statut.getImageURL()).getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
            JLabel imgSpe=new JLabel(new ImageIcon(newimg));
            this.add(imgSpe);

            /*l'enregistre*/
            arrStatut.add(3,imgSpe);
        }

    }

    private void addSavedStatut(){
        /*J'ajoute tous les JLabels déjà enregistré*/
        for (JLabel st:arrStatut){
            this.add(st);
        }
    }

    @Override
    protected void removeStatut(Statut statut) {
        if (nbSpe==1){
            /*Je rends le Jlabel invisible*/
            this.view.setIcon(null);
            this.view.revalidate();
            //this.view.setVisible(false);
        }else {

            if (nbSpe == 2) {
            /*Je change de GridLayout*/
                this.view.setLayout(new GridLayout(1,0));
            }

            if (nbSpe == 3) {
            /*Je change de GridLayout*/
                this.view.setLayout(new GridLayout(2, 0));
            }
            /*if (nbSpe == 4) J'avais 4 statut j'en en aurais plus 3 mais je reste dans le même GridLayout*/

            /*Je trouve le statut à remove et j'ajoute ceux déjà saved*/
            arrStatut.remove(findStatutEquals(statut));
            addSavedStatut();
        }
        nbSpe--;
    }

    private JLabel findStatutEquals(Statut st){
            for (JLabel j:arrStatut){
                if (j.getLabelFor().equals(st.getImageURL())){ //test si les images sont égales.
                    return j;
                }
                continue;
            }

        return null;
    }

}
//PENSER A getText (retourne texte du Jlabel) ou getLabelFor retourne le component du JLABEL et removeComponent
    /* ?
    tous les this.view simplement des this. ?
     */