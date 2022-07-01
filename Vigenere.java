public class Vigenere
{
    public Vigenere() {
        String x = encrypt("Hallo mein Name ist Niclas!", "kljdlkfjlkH");
        System.out.println(x);
        System.out.println(decrypt(x, "kljdlkfjlkH"));
    }

    public String encrypt(String message, String key) {
        String chiffre = "";
        
        for (int i = 0; i < message.length(); i++) {
            char current = message.charAt(i);
            char cKey = key.charAt(i % key.length());
            
            if (Character.isAlphabetic(current)) {
                int stelle = Character.toLowerCase(current) - 'a';
                int schluessel = Character.toLowerCase(cKey) - 'a';

                int verschluesselung = 'a' + ((stelle + schluessel) % 26);
                if (Character.isUpperCase(current))
                    verschluesselung -= 32;
                    
                chiffre += (char) verschluesselung;
            }
            else {
                chiffre += current;
            }
        }
        
        return chiffre;
    }
    
    public String decrypt(String message, String key) {
        String klartext = "";
        
        for (int i = 0; i < message.length(); i++) {
            char current = message.charAt(i);
            char cKey = key.charAt(i % key.length());
            
            if (Character.isAlphabetic(current)) {
                int stelle = 25 - (Character.toLowerCase(current) - 'a');
                int schluessel = Character.toLowerCase(cKey) - 'a';

                int verschluesselung = 'z' - ((stelle + schluessel) % 26);
                if (Character.isUpperCase(current))
                    verschluesselung -= 32;
                    
                klartext += (char) verschluesselung;
            }
            else {
                klartext += current;
            }
        }
        
        return klartext;
    }
}
