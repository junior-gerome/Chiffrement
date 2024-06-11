package chiffrement; // Définit le nom du package

public class Cesar extends Chiffrements { // Déclare une nouvelle classe publique Cesar qui étend la classe Chiffrements
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Déclare une constante pour l'alphabet utilisé pour le chiffrement

    public Cesar() { // Constructeur de la classe
        typeChiffrement = TypeChiffrement.CESAR; // Définit le type de chiffrement
        setTitle("Chiffrement César"); // Définit le titre de la fenêtre
    }

   @Override
public String chiffrer(String message, String cle) {
    int decalage;
    if (cle.matches("\\d+")) { // Vérifie si la clé est un nombre
        decalage = Integer.parseInt(cle); // Utilise la clé comme décalage
    } else {
        decalage = cle.chars().reduce(0, (a, b) -> a + b - 'a'); // Calcule le décalage à partir de la clé
    }
    decalage = (decalage % ALPHABET.length() + ALPHABET.length()) % ALPHABET.length(); // S'assure que le décalage est positif
    StringBuilder texteChiffre = new StringBuilder(); // Crée un StringBuilder pour construire le texte chiffré

    for (char character : message.toCharArray()) { // Parcourt chaque caractère du message
        int index = ALPHABET.indexOf(character); // Trouve l'index du caractère dans l'alphabet

        if (index != -1) { // Si le caractère est dans l'alphabet
            char ch = ALPHABET.charAt((index + decalage) % ALPHABET.length()); // Calcule le nouveau caractère
            texteChiffre.append(ch); // Ajoute le nouveau caractère au texte chiffré
        } else { // Si le caractère n'est pas dans l'alphabet
            texteChiffre.append(character); // Ajoute le caractère tel quel au texte chiffré
        }
    }

    return texteChiffre.toString(); // Renvoie le texte chiffré
}


    @Override
    public String dechiffrer(String messageChiffre, String cle) { // Méthode pour déchiffrer un message avec une clé
          int decalage;
    if (cle.matches("\\d+")) { // Vérifie si la clé est un nombre
        decalage = Integer.parseInt(cle); // Utilise la clé comme décalage
    } else {
        decalage = cle.chars().reduce(0, (a, b) -> a + b - 'a'); // Calcule le décalage à partir de la clé
    }
        decalage = (decalage % ALPHABET.length() + ALPHABET.length()) % ALPHABET.length(); // S'assure que le décalage est positif
        StringBuilder texteDechiffre = new StringBuilder(); // Crée un StringBuilder pour construire le texte déchiffré

        for (char character : messageChiffre.toCharArray()) { // Parcourt chaque caractère du message chiffré
            int index = ALPHABET.indexOf(character); // Trouve l'index du caractère dans l'alphabet

            if (index != -1) { // Si le caractère est dans l'alphabet
                char ch = ALPHABET.charAt((index - decalage + ALPHABET.length()) % ALPHABET.length()); // Calcule le caractère original
                texteDechiffre.append(ch); // Ajoute le caractère original au texte déchiffré
            } else { // Si le caractère n'est pas dans l'alphabet
                texteDechiffre.append(character); // Ajoute le caractère tel quel au texte déchiffré
            }
        }

        return texteDechiffre.toString(); // Renvoie le texte déchiffré
    }

    public static void main(String[] args) { // Méthode principale
        java.awt.EventQueue.invokeLater(() -> { // Crée un nouvel événement pour la file d'attente des événements
            Cesar cesar = new Cesar(); // Crée une nouvelle instance de la classe Cesar
            cesar.setVisible(true); // Rend l'instance visible
        });
    }

    @Override
    public void initialiserInterfaceGraphique() { // Méthode pour initialiser l'interface graphique
        setVisible(true); // Rend l'interface graphique visible
        setJLAcceuilText("Chiffrement César"); // Définit le texte d'accueil
    }
}
