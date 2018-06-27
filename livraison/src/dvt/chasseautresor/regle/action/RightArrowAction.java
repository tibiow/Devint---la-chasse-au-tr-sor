package dvt.chasseautresor.regle.action;

import dvt.chasseautresor.regle.RegleView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Project : modele-de-jeu
 * Created by Thibaut on 07/03/2016
 */
public class RightArrowAction extends AbstractAction {

    RegleView regleView;

    public RightArrowAction(RegleView regleView) {
        this.regleView=regleView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        regleView.rightArrow();
    }
}
