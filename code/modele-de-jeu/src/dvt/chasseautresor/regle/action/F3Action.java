package dvt.chasseautresor.regle.action;

import dvt.chasseautresor.partie.ChasseAuTresor;
import dvt.chasseautresor.regle.RegleView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Project : modele-de-jeu
 * Created by Thibaut on 05/04/2016
 */

public class F3Action extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient RegleView regleView;

    public F3Action(RegleView regleView) {
        this.regleView = regleView;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        regleView.changeColor();
        regleView.changeArrowsColor();
    }

}
