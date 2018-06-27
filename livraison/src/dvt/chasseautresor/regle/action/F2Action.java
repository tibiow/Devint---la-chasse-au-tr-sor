package dvt.chasseautresor.regle.action;


import dvt.chasseautresor.regle.RegleView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Project : modele-de-jeu
 * Created by Thibaut on 06/04/2016
 */
public class F2Action extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private transient RegleView regleView;

    public F2Action(RegleView regleView) {
        this.regleView = regleView;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        regleView.oralInstructions();
    }

}

