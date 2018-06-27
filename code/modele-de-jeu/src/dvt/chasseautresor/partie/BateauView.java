package dvt.chasseautresor.partie;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static dvt.devint.ConstantesDevint.*;


/**
 * Project : modele-de-jeu
 * Created by Montagnards on 29/02/2016
 */
public class BateauView extends JPanel {

    private JSlider slider1;
    private JSlider slider2;
    private ArrayList<JSlider> jSliderArrayList;
    private Color pathColor1;
    private Color pathColor2;
    private int colorChoice;


    private static final Color[] PATHWAYCOLOR_DEFAULT = { new Color(243,157,1), new Color(88,199,224),new Color(240,116,132),  new Color(47,179,142),new Color(174,2,0)};
    private static final Color[] PATHWAY_PASTCOLOR = {new Color(243,233,169),   new Color(215,238,241),new Color(253,240,239), new Color(187,230,210), new Color(249,214,203)};
    //private boolean win;
    //private boolean lose;

    public BateauView() {
        //win = false;
        //lose = false;

        this.setLayout(new GridLayout(2, 0));

        jSliderArrayList=new ArrayList<>();

        slider1 = new JSlider(JSlider.HORIZONTAL, 0, SHIP_MAXDISTANCE, 0);
        slider1.setEnabled(false);
       // this.move(slider1, 600);
        slider1.setUI(new mySliderUI(slider1,0));

        pathColor1 = PATHWAYCOLOR_DEFAULT[0];
        pathColor2 = PATHWAY_PASTCOLOR[0];

        jSliderArrayList.add(slider1);

        this.add(slider1);


        slider2 = new JSlider(JSlider.HORIZONTAL, 0, SHIP_MAXDISTANCE, 0);
        slider2.setEnabled(false);
        //this.move(slider2, 50);
        slider2.setUI(new mySliderUI(slider2,1));

        jSliderArrayList.add(slider2);

        this.add(slider2);


    }

    /**
     * the slider move a certain distance
     *
     * @param jslider
     */
    private void move(JSlider jslider, int mile) {
        int p = jslider.getValue();
        jslider.setValue(p + mile);
    }

    public void move(int mile) {
        int p = slider1.getValue();
        slider1.setValue(mile);
    }


    public void moveEnnemy(int mile) {
        int p = slider2.getValue();
        slider2.setValue(mile);
    }

    public ArrayList<JSlider> getjSliderArrayList() {
        return jSliderArrayList;
    }

    private class mySliderUI extends BasicSliderUI {

        Image knobImage;

        public mySliderUI(JSlider aSlider, int test) {

            super(aSlider);


            try {
                if(test==1)
                    this.knobImage = ImageIO.read(new File("../ressources/images/ship"+colorChoice%5+".png"));
                else
                    this.knobImage = ImageIO.read(new File("../ressources/images/shipTest"+colorChoice%5+".png"));

            } catch (IOException e) {

                e.printStackTrace();
            }

        }

        @Override
        public void paintThumb(Graphics g) {
            g.drawImage(this.knobImage, thumbRect.x, thumbRect.y - 40, 100, 100, null);
        }

        @Override
        public void paintTrack(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            Rectangle t = trackRect;

            //g2.setPaint(new GradientPaint(0, 0, new Color(220, 220, 220), (int) t.getWidth(), 0, new Color(125, 125, 125), true));
            //g2.setColor(pathColor2);
            //g2.fill3DRect(t.x, t.y, (int)t.getWidth(), (int) t.getHeight(),true);

            //g2.setPaint(new GradientPaint(0, 0, new Color(90, 90, 255), (int) t.getWidth(), 0, new Color(255, 0, 0), true));
            g2.setColor(pathColor1);
            g2.fill3DRect(thumbRect.x + 50 , t.y, (int) t.getWidth() - thumbRect.x - 50 , (int) t.getHeight()+20,true);

            //g2.setPaint(new GradientPaint(0, 0, new Color(220, 220, 220), (int) t.getWidth(), 0, new Color(125, 125, 125), true));
            g2.setColor(pathColor2);
            g2.fill3DRect(t.x, t.y, thumbRect.x + 50, (int) t.getHeight()+20,true);

        }

    }

    public void changeColor(){
        pathColor1 = PATHWAYCOLOR_DEFAULT[++colorChoice % PATHWAYCOLOR_DEFAULT.length];
        pathColor2 = PATHWAY_PASTCOLOR[colorChoice % PATHWAY_PASTCOLOR.length];
        slider1.setUI(new mySliderUI(slider1,0));
        slider2.setUI(new mySliderUI(slider2,1));
    }


    public int finish(){
        if(slider1.getValue() >= SHIP_MAXDISTANCE){
            this.removeAll();
            setLayout(new GridLayout(0,5));
            this.revalidate();
            for(int i = 0; i < 5; i++) {
                this.add(new JLabel(new ImageIcon("../ressources/images/tresor.png")));
            }
            return 1;
        }
        else if(slider2.getValue() >= SHIP_MAXDISTANCE){
            this.removeAll();
            setLayout(new GridLayout(0,5));
            this.revalidate();
            for(int i = 0; i < 5; i++) {
                this.add(new JLabel(new ImageIcon("../ressources/images/lose.png")));
            }
            return -1;
        }
        return 0;
    }


}


