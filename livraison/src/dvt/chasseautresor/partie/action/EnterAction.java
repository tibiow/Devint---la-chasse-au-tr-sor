package dvt.chasseautresor.partie.action;

import dvt.chasseautresor.partie.ChasseAuTresor;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Project : modele-de-jeu
 * Created by Thibaut on 15/03/2016
 */
public class EnterAction extends AbstractAction {
    private ChasseAuTresor chasseAuTresor;

    public EnterAction(ChasseAuTresor chasseAuTresor) {
        this.chasseAuTresor=chasseAuTresor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chasseAuTresor.enterAction();
    }
}
