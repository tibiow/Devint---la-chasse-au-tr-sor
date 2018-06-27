package dvt.chasseautresor.partie.action;

import dvt.chasseautresor.partie.ChasseAuTresor;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Project : modele-de-jeu
 * Created by Thibaut on 19/05/2016
 */
public class MAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient ChasseAuTresor chasseAuTresor;

    public MAction(ChasseAuTresor chasseAuTresor) {
        this.chasseAuTresor = chasseAuTresor;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        //chasseAuTresor.playMusic();
    }

}