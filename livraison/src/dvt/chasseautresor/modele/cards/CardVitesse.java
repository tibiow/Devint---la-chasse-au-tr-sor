package dvt.chasseautresor.modele.cards;

import dvt.chasseautresor.partie.statut.Statut;

/**
 * Project : modele-de-jeu
 * Created by Thibaut on 08/03/2016
 */
public class CardVitesse extends Card {


    private int value;

    public CardVitesse(CardType cardType,String image, String imageStatut, Statut statut,String role,int value) {
        super(image,cardType,imageStatut,statut,role);
        this.value=value;
    }



    public int getValue() {
        return value;
    }

    @Override
    public String getRole() {
        return "Cette carte te permet d'avancer de "+ value;
    }

}
