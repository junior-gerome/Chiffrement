/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chiffrement;

/**
 *
 * @author GLOBAL STORE
 */
public class Vigenere extends Chiffrements {

    // Constructeur de la classe Vigenère
    public Vigenere() {
        typeChiffrement = TypeChiffrement.VIGENERE;        
        setTitle("Chiffrement Vigenere"); // Définir le titre lors de la création de l'objet Vigenère
    }

    // Méthode pour chiffrer le message
 @Override
public String chiffrer(String message, String cle) {
    // Créer un StringBuilder pour construire le message chiffré
    StringBuilder texteChiffre = new StringBuilder();

    // Parcourir chaque caractère du message
    for (int i = 0, j = 0; i < message.length(); i++) {
        // Obtenir le caractère actuel du message
        char character = message.charAt(i);
        // Obtenir le caractère correspondant de la clé
        char cleChar = cle.charAt(j % cle.length());

        // Vérifier si le caractère est une lettre
        if (Character.isLetter(character)) {
            // Déterminer l'offset en fonction de la casse du caractère
            int offset = Character.isUpperCase(character) ? 'A' : 'a';
            // Ajuster la casse du caractère de la clé pour correspondre à celle du caractère du message
            cleChar = Character.isUpperCase(character) ? Character.toUpperCase(cleChar) : Character.toLowerCase(cleChar);
            // Chiffrer le caractère
            char ch = (char) (((character + cleChar - 2 * offset) % 26) + offset);
            // Ajouter le caractère chiffré au texte chiffré
            texteChiffre.append(ch);
            // Passer au caractère suivant de la clé
            j++;
        } else {
            // Si le caractère n'est pas une lettre, l'ajouter tel quel au texte chiffré
            texteChiffre.append(character);
        }
    }
    // Retourner le texte chiffré sous forme de chaîne de caractères
    return texteChiffre.toString();
}

@Override
public String dechiffrer(String messageChiffre, String cle) {
    // Créer un StringBuilder pour construire le message déchiffré
    StringBuilder texteDechiffre = new StringBuilder();

    // Parcourir chaque caractère du message chiffré
    for (int i = 0, j = 0; i < messageChiffre.length(); i++) {
        // Obtenir le caractère actuel du message chiffré
        char character = messageChiffre.charAt(i);
        // Obtenir le caractère correspondant de la clé
        char cleChar = cle.charAt(j % cle.length());

        // Vérifier si le caractère est une lettre
        if (Character.isLetter(character)) {
            // Déterminer l'offset en fonction de la casse du caractère
            int offset = Character.isUpperCase(character) ? 'A' : 'a';
            // Ajuster la casse du caractère de la clé pour correspondre à celle du caractère du message chiffré
            cleChar = Character.isUpperCase(character) ? Character.toUpperCase(cleChar) : Character.toLowerCase(cleChar);
            // Déchiffrer le caractère
            char ch = (char) (((character - cleChar + 26) % 26) + offset);
            // Ajouter le caractère déchiffré au texte déchiffré
            texteDechiffre.append(ch);
            // Passer au caractère suivant de la clé
            j++;
        } else {
            // Si le caractère n'est pas une lettre, l'ajouter tel quel au texte déchiffré
            texteDechiffre.append(character);
        }
    }
    // Retourner le texte déchiffré sous forme de chaîne de caractères
    return texteDechiffre.toString();
}

    // Méthode pour initialiser l'interface graphique
    @Override
    public void initialiserInterfaceGraphique() {
        setVisible(true);
        setJLAcceuilText("Chiffrement Vigenère");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Méthode principale
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            Vigenere vigenere = new Vigenere();
            vigenere.setVisible(true);
        });
    }

}
