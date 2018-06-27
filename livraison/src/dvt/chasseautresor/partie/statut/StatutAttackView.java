package dvt.chasseautresor.partie.statut;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Vue de la carte attaque
 * Created by Antoine on 23/03/2016.
 */
public class StatutAttackView extends StatutView {
    private JLabel imgAtt;

    public StatutAttackView() {
        super();
    }

    @Override
    public void addStatut(Statut statut) {
        Image newimg = new ImageIcon(statut.getImageURL()).getImage().getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
        imgAtt=new JLabel(new ImageIcon(newimg));
        this.add(imgAtt);
        this.setVisible(true);
        this.add(this.view);
    }

    @Override
    public void removeStatut(Statut statut) {
        imgAtt.setIcon(null);
        this.view.repaint();
        this.view.revalidate();
        this.view.setVisible(true);
    }
}
