package dvt.chasseautresor.regle;

/**
 * Project: Devint
 * Created by Les Montagnards on 02/03/2016.
 *
 * Cette enum  représente représente l'ensemble des pages associée au règles. Chaque page est reconnaissable
 * par sa valeur et contient un titre et du texte
 */
public enum Page {
    HISTOIRE("<html>Histoire</html>","<html>Tu es un pirate.<br/> Tu vas à la recherche d'un trésor.<br/>Tu dois y arriver avant ton adversaire.</html>",1),
    DEROULEMENT("<html>Déroulement</html>","<html>A chaque tour tu poses une carte.<br/> Puis tu en pioches une.<br/> L'adversaire fait pareil.</html>",2),
    VITESSE("<html>Cartes Vitesses</html>","<html>Les cartes vitesse te permettent d'avancer.<br/> Plus le chiffre est élevé plus tu vas vite.</html>",3),
    ATTAQUE("<html>Cartes Attaque</html>s","<html>Si tu lances une carte d'attaque tu ralentis l'adversaire.<br/>Si l'adversaire lance une carte attaque tu ralentis.</html>",4),
    DEFENSE("<html>Cartes Défenses</html>","<html>Si l'adversaire te lance une carte attaque , la <br/>carte de défense te permet d'annuler son attaque.<br/>Si tu lances une carte attaque l'adversaire peut l'annuler avec une carte de défense.</html>",5),
    SPECIAL("<html>Cartes Spéciales</html>","<html>Ces cartes permettent d'annuler certaines attaques <br/>jusqu'à la fin de la partie.</html>",6),
    TOUCHES("<html>Les Touches</html>","<html>Utilise les fléches directionnelles pour choisir la carte <br/>et appuie sur entrer pour la valider</html>",7),
    TOUCHES2("<html>Les Touches</html>","<html>Tu peux utiliser la barre d'espace pour agrandir la carte<br/> selectioné La touche F2 te permet de savoir ce que fait la carte</html>",8),
    TOUCHES3("<html>Les Touches</html>","<html>La touche F3 te permet de changer la couleur des fenêtres</html>",9);

    private String title;
    private String contenu;
    private int numPage;

    Page(String title, String contenu, int numPage){
        this.title=title;
        this.contenu=contenu;
        this.numPage=numPage;
    }

    public int getNumPage(){
        return numPage;
    }

    public String getTitle(){
        return title;
    }

    public String getContenu() {
        return contenu;
    }
}
