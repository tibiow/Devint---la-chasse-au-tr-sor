package dvt.chasseautresor.regle.action;

import dvt.chasseautresor.regle.RegleView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Project : modele-de-jeu
 * Created by Thibaut on 07/03/2016
 */
public class LeftArrowAction extends AbstractAction {

    RegleView regleView;

    public LeftArrowAction(RegleView regleView) {
        this.regleView=regleView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        regleView.leftArrow();
    }
}
