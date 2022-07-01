public class Caesar
{
    public Caesar() {
        String x = cipher("abc xyz", 3);
        System.out.println(x);
        System.out.println(decipher(x, 3));
    }
    
    public String cipher(String message, int key) {
        String encrypt = "";
        for (int i = 0; i < message.length(); i++) {
            encrypt += cipher(message.charAt(i), key);
        }
        return encrypt;
    }
    
    public String decipher(String message, int key) {
        String decrypt = "";
        for (int i = 0; i < message.length(); i++) {
           decrypt += decipher(message.charAt(i), key);
        }
        return decrypt;
    }
    
    public char cipher(char message, int key) {
        if (!Character.isAlphabetic(message)) return message;
        
        int value = Character.toLowerCase(message) - 'a';
        int cipher = 'a' + ((value + key) % 26);
        
        if (Character.isUpperCase(message))
            cipher -= 32;
        
        return (char) cipher;
    }
    
    public char decipher(char message, int key) {
        if (!Character.isAlphabetic(message)) return message;
        
        int value = 25 - (Character.toLowerCase(message) - 'a');
        int decipher = 'z' - ((value + key) % 26);
        
        if (Character.isUpperCase(message))
            decipher -= 32;
        
        return (char) decipher;
    }
}
