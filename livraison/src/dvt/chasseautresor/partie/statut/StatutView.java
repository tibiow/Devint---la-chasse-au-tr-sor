package dvt.chasseautresor.partie.statut;

import javax.swing.*;

/**
 * classe mère de StatutAttackView et StatutSpecialView
 * Created by Antoine on 23/03/2016.
 */
public abstract class StatutView extends JPanel {
    protected JLabel view;

    protected StatutView(){
        super();
        view=new JLabel();
    }

    protected abstract void addStatut(Statut statut);

    protected abstract void removeStatut(Statut statut);
}
