package dvt.chasseautresor.partie.action;

import dvt.chasseautresor.partie.ChasseAuTresor;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Project : modele-de-jeu
 * Created by Thibaut on 06/04/2016
 */
public class F1Action extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient ChasseAuTresor chasseAuTresor;

    public F1Action(ChasseAuTresor chasseAuTresor) {
        this.chasseAuTresor = chasseAuTresor;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        //chasseAuTresor.playPossibility();
    }

}