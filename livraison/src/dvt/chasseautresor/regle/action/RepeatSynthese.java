package dvt.chasseautresor.regle.action;


import dvt.chasseautresor.regle.RegleView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Les Montagnards on 16/03/2016.
 */
public class RepeatSynthese extends AbstractAction {
    private RegleView regleView;

    public RepeatSynthese(RegleView regleView){
        this.regleView=regleView;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        regleView.oralInstructions();
    }
}
