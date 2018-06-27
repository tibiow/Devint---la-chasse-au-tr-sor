package dvt.chasseautresor.regle;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Les Montagnards on 02/03/2016.
 *
 * Cette classe est l'affichage de nos différentes Page.
 * Il y a un titre en haut
 * et le contenu au centre
 *
 * @param currentPage represent the number of the current page
 * @param titleLabel represent the title of the current page
 * @param contenuLabel represent the contents of the current page
 */
public  class ScreenRegleView extends JPanel {
    private int currentPage;
    private JLabel titleLabel, contenuLabel;

    public ScreenRegleView(){
        super(new BorderLayout()); //new BorderLayout() pour pouvoir positionner


        currentPage=1;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //On récupére la taille de l'écran
        Dimension screenSizePer6= new Dimension((int)screenSize.getWidth(),(int)screenSize.getHeight()/6); //On la divise par 4

        titleLabel=new JLabel("");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setPreferredSize(screenSizePer6);
        contenuLabel=new JLabel("");
        contenuLabel.setHorizontalAlignment(JLabel.CENTER);
        contenuLabel.setVerticalAlignment(JLabel.CENTER);
        refresh();



        this.add(titleLabel,BorderLayout.NORTH); // zone du titre
        this.add(contenuLabel, BorderLayout.CENTER);




    }

    public void getPreviousPage(){
        if(currentPage>1) {
            currentPage--;
            refresh();
        }
    }

    public void getNextPage() {
        if(currentPage<9) {
            currentPage++;
            refresh();
        }
    }

    /**
     * actualize the title and the contents of the Page
     */
    private void refresh(){
        //set le contenu de la page
        titleLabel.setText(getTitle(currentPage)); //utiliser le current page pour accéder au texte de celui ci
        contenuLabel.setText(getText(currentPage));
    }

    private String getText(int currentPage) {
        for (Page page : Page.values()) {
            if (page.getNumPage() == currentPage)
                return page.getContenu();
        }
        return null;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public String getTitle(int numPage){
        for (Page page : Page.values()) {
            if (page.getNumPage() == currentPage)
                return page.getTitle();
        }
        return null;

    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JLabel getContenuLabel() {
        return contenuLabel;
    }
}
