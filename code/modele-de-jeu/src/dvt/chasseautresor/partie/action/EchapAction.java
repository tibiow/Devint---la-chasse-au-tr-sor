package dvt.chasseautresor.partie.action;

import dvt.chasseautresor.partie.ChasseAuTresor;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Antoine on 22/04/2016.
 */
public class EchapAction extends AbstractAction{
    private static final long serialVersionUID = 1L;
    private transient ChasseAuTresor chasseAuTresor;

    public EchapAction(ChasseAuTresor chasseAuTresor) {
        this.chasseAuTresor = chasseAuTresor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chasseAuTresor.previous();
    }
}
