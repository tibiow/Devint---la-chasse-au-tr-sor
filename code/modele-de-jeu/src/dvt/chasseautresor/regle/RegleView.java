package dvt.chasseautresor.regle;

import dvt.chasseautresor.regle.action.*;
import dvt.devint.Jeu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

/**
 * Project : modele-de-jeu
 * Created by Les Montagnards on 01/03/2016
 *
 * @param fenetre represente l'ecran
 * @param arrowPrev représente la flèche pour revenir en arrière
 * @param arrowNext représente la flèche pour aller à la page suivante
 * @param ScreenRegleView represent the controlor of the changement of Page
 *
 */
public class RegleView extends Jeu {
    private JPanel fenetre;
    private JLabel arrowPrev,arrowNext;
    private ScreenRegleView screenRegleView;
    private int colorChoice;

    //arrow by default
    private ImageIcon imgArrowNext, imgArrowPrev;

    /*Other arrows*/
    private ImageIcon arrowWhiteNext,arrowWhitePrev,arrowBlueNext,arrowBluePrev,arrowOrangeNext,arrowOrangePrev,arrowBlackNext,arrowBlackPrev;



    @Override
    public void init() {
        fenetre= new JPanel(new BorderLayout()); //new BorderLayout() pour pouvoir positionner
        fenetre.setBackground(getBackground()); //permet le changement de couleur


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //On récupére la taille de l'écran
        Dimension screenSizePer6= new Dimension((int)screenSize.getWidth()/6,(int)screenSize.getHeight()/6); //On la divise par 4

        /*Ajout flèches*/
        colorChoice=0;
        imgArrowNext=new ImageIcon("../ressources/images/ReglesView/arrow"+colorChoice%5+".png");
        imgArrowPrev= new ImageIcon("../ressources/images/ReglesView/arrowPrev"+colorChoice%5+".png");
        arrowNext= new JLabel(imgArrowNext);
        arrowPrev= new JLabel(imgArrowPrev);

        arrowNext.setPreferredSize(screenSizePer6);
        arrowPrev.setPreferredSize(screenSizePer6);
        arrowPrev.setVisible(false);//la fléche prev n'est pas visible en première page
        arrowNext.setVisible(true);


        /*Positionnement arrows*/
        fenetre.add(arrowNext,BorderLayout.EAST);
        fenetre.add(arrowPrev, BorderLayout.WEST);

        /*ecouteur flèche droite*/
        addControlDown(KeyEvent.VK_RIGHT, new RightArrowAction(this));

        /*ecouteur flèche gauche*/
        addControlDown(KeyEvent.VK_LEFT, new LeftArrowAction(this));

        /*ecouteur F2*/
        addControlDown(KeyEvent.VK_F2,new F2Action(this));

        /*ecouteur F3*/
        addControlDown(KeyEvent.VK_F3,new F3Action(this));

        /*ecouteur F7*/
        addControlDown(KeyEvent.VK_F7,new RepeatSynthese(this));

        screenRegleView = new ScreenRegleView();
        screenRegleView.setBackground(getBackground());
        //info = new JLabel(new ScreenRegleView(), JLabel.CENTER);
        //info.setFont(getFont());
        fenetre.add(screenRegleView,BorderLayout.CENTER);

        this.add(fenetre);

        oralInstructions();
    }

    @Override
    public void update() {

        // TODO

        //mise à jour du modèle
    }

    @Override
    public void render() {
        //mise à jour graphique

        fenetre.setBackground(getBackground());
        screenRegleView.setBackground(getBackground());
        screenRegleView.getTitleLabel().setFont(getFont());
        screenRegleView.getTitleLabel().setForeground(getForeground());
        screenRegleView.getContenuLabel().setFont(getFont());
        screenRegleView.getContenuLabel().setForeground(getForeground());


    }

    @Override
    public void reset() {

    }

    /**
     * oralInstuction allows to get back the synthesis vocal of a Page
     */
    public void oralInstructions(){
        this.getSIVOX().playText(removeHtml(screenRegleView.getTitleLabel().getText()));
        this.getSIVOX().playText(removeHtml(screenRegleView.getContenuLabel().getText()));
    }

    /**
     * leftArrow represent all actions done when the user press the left Arrow on the Keyboard
     * on the "regle" section
     */
    public void leftArrow() {
        screenRegleView.getPreviousPage();
        this.setVisibleArrows();
        oralInstructions();
    }

    /**
     * rightArrow represent all actions done when the user press the right arrow on the Keyboard
     * on the "regle" section
     */
    public void rightArrow(){
        screenRegleView.getNextPage();
        this.setVisibleArrows();
        oralInstructions();
    }

    /**
     * allows the disparition of the left arrow if the user is on the first page
     * allows the disparition of the right arrow if the user is on the last page
     * In other cases the both arrows are visible
     */
    public void setVisibleArrows(){
        if (screenRegleView.getCurrentPage()==1){
            arrowPrev.setVisible(false);
        }else{
            arrowPrev.setVisible(true);
        }

        if (screenRegleView.getCurrentPage()==9){
            arrowNext.setVisible(false);
        }else{
            arrowNext.setVisible(true);
        }

    }

    /**
     * Change color of arrow
     */
    public void changeArrowsColor() {
        colorChoice++;
        imgArrowNext=new ImageIcon("../ressources/images/ReglesView/arrow"+colorChoice%5+".png");
        arrowNext.setIcon(imgArrowNext);

        imgArrowPrev=new ImageIcon("../ressources/images/ReglesView/arrowPrev"+colorChoice%5+".png");
        arrowPrev.setIcon(imgArrowPrev);
    }

    private String removeHtml(String text){
        text=text.replace("<html>","");
        text=text.replace("</html>","");
        text=text.replace("<br/>","");
        return text;
    }
}
