package dvt.chasseautresor.modele.cards;

import dvt.chasseautresor.partie.statut.Statut;

/**
 *
 * Cette classe représente les différents cartes défensives de notre jeu
 * Project: Devint
 * Created by Les Montagnards on 23/02/2016.
 */


public class CardDefensive extends Card {


    public CardDefensive(CardType cardType,String image, String imageStatut, Statut statut,String role) {
        super(image,cardType,imageStatut,statut,role);
    }

}
