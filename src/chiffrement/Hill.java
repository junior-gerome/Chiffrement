package chiffrement; // Déclaration du package

// Déclaration de la classe Hill qui hérite de la classe Chiffrements
public class Hill extends Chiffrements {

    private static final int MATRIX_SIZE = 2; // Définition de la taille de la matrice

    // Constructeur de la classe Hill
    public Hill() {
        typeChiffrement = TypeChiffrement.HILL; // Définition du type de chiffrement
        setTitle("Chiffrement Hill"); // Définition du titre de la fenêtre
    }

    // Méthode pour chiffrer le message
    @Override
  public String chiffrer(String message, String cle) {
    // On s'assure que la longueur du message est un multiple de la taille de la matrice
    while (message.length() % MATRIX_SIZE != 0) {
        message += "x"; // On ajoute 'x' à la fin du message si nécessaire
    }

    // On définit la matrice de clé pour le chiffrement
    int[][] keyMatrix = {{4, -3}, {-1, 2}};

    // On crée un StringBuilder pour construire le texte chiffré
    StringBuilder encryptedText = new StringBuilder();

    // On boucle sur chaque segment du message
    for (int i = 0; i < message.length(); i += MATRIX_SIZE) {
        // On extrait le segment actuel du message
        String segment = message.substring(i, i + MATRIX_SIZE);

        // On convertit le segment en un vecteur de texte brut
        int[] plainTextVector = new int[MATRIX_SIZE];
        for (int j = 0; j < MATRIX_SIZE; j++) {
            plainTextVector[j] = segment.charAt(j) - 'a'; // On soustrait 'a' pour obtenir des valeurs de 0 à 25
        }

        // On calcule le vecteur de texte chiffré
        int[] cipherTextVector = new int[MATRIX_SIZE];
        for (int j = 0; j < MATRIX_SIZE; j++) {
            for (int k = 0; k < MATRIX_SIZE; k++) {
                cipherTextVector[j] += keyMatrix[j][k] * plainTextVector[k]; // On multiplie et on additionne
            }
            cipherTextVector[j] %= 26; // On fait le modulo 26 pour rester dans l'alphabet
        }

        // On convertit le vecteur de texte chiffré en texte et on l'ajoute au texte chiffré
        for (int j = 0; j < MATRIX_SIZE; j++) {
            encryptedText.append((char) (cipherTextVector[j] + 'a')); // On ajoute 'a' pour revenir aux lettres
        }
    }

    // On retourne le texte chiffré
    return encryptedText.toString();
}

    // Méthode pour déchiffrer le message
 @Override
public String dechiffrer(String messageChiffre, String cle) {
    // On définit la matrice de clé pour le déchiffrement
    int[][] keyMatrix = {{4, -3}, {-1, 2}};

    // On calcule le déterminant de la matrice de clé
    int determinant = (keyMatrix[0][0] * keyMatrix[1][1] - keyMatrix[0][1] * keyMatrix[1][0] + 26) % 26;

    // On calcule l'inverse de la matrice de clé
    int[][] keyMatrixInverse = {{keyMatrix[1][1], -keyMatrix[0][1]}, {-keyMatrix[1][0], keyMatrix[0][0]}};
    for (int i = 0; i < MATRIX_SIZE; i++) {
        for (int j = 0; j < MATRIX_SIZE; j++) {
            keyMatrixInverse[i][j] = (keyMatrixInverse[i][j] * modInverse(determinant, 26) + 26) % 26;
        }
    }

    // On crée un StringBuilder pour construire le texte déchiffré
    StringBuilder decryptedText = new StringBuilder();

    // On boucle sur chaque segment du message chiffré
    for (int i = 0; i < messageChiffre.length(); i += MATRIX_SIZE) {
        // On extrait le segment actuel du message chiffré
        String segment = messageChiffre.substring(i, i + MATRIX_SIZE);

        // On convertit le segment en un vecteur de texte chiffré
        int[] cipherTextVector = new int[MATRIX_SIZE];
        for (int j = 0; j < MATRIX_SIZE; j++) {
            cipherTextVector[j] = segment.charAt(j) - 'a'; // On soustrait 'a' pour obtenir des valeurs de 0 à 25
        }

        // On calcule le vecteur de texte brut
        int[] plainTextVector = new int[MATRIX_SIZE];
        for (int j = 0; j < MATRIX_SIZE; j++) {
            for (int k = 0; k < MATRIX_SIZE; k++) {
                plainTextVector[j] += keyMatrixInverse[j][k] * cipherTextVector[k]; // On multiplie et on additionne
            }
            plainTextVector[j] %= 26; // On fait le modulo 26 pour rester dans l'alphabet
            if (plainTextVector[j] < 0) {
                plainTextVector[j] += 26; // On s'assure que le résultat est positif
            }
        }

        // On convertit le vecteur de texte brut en texte et on l'ajoute au texte déchiffré
        for (int j = 0; j < MATRIX_SIZE; j++) {
            decryptedText.append((char) (plainTextVector[j] + 'a')); // On ajoute 'a' pour revenir aux lettres
        }
    }

    // On retourne le texte déchiffré
    return decryptedText.toString();
}

    // Méthode pour calculer l'inverse modulaire
    private int modInverse(int a, int m) {
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1;
    }

    // Méthode pour initialiser l'interface graphique
    @Override
    public void initialiserInterfaceGraphique() {
        setVisible(true); // Rendre l'interface graphique visible
        setJLAcceuilText("Chiffrement Hill"); // Définition du texte d'accueil
    }

    // Méthode principale
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            Hill hill = new Hill(); // Création d'un objet Hill
            hill.setVisible(true); // Affichage de l'objet Hill
        });
    }
}
