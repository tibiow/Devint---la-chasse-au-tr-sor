package dvt.chasseautresor.partie.action;

import dvt.chasseautresor.partie.ChasseAuTresor;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Project : modele-de-jeu
 * Created by Thibaut on 16/03/2016
 */
public class SpaceAction extends AbstractAction {

    private ChasseAuTresor chasseAuTresor;

    public SpaceAction(ChasseAuTresor chasseAuTresor) {
        this.chasseAuTresor=chasseAuTresor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chasseAuTresor.spaceAction();
    }
}
