package dvt.chasseautresor.partie.action;

/**
 * Project : modele-de-jeu
 * Created by Les Montagnards on 05/04/2016
 */

import dvt.chasseautresor.partie.ChasseAuTresor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class F3Action extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient ChasseAuTresor chasseAuTresor;

    public F3Action(ChasseAuTresor chasseAuTresor) {
        this.chasseAuTresor = chasseAuTresor;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        chasseAuTresor.changeColor();
        chasseAuTresor.changeBateauColor();
        chasseAuTresor.changeBorderColor();
    }

}
