import java.math.BigInteger;

public class BlockRSA
{
    int blockLength = 5;
    
    public BlockRSA(int bitLength) {
        if (bitLength < blockLength * 4) bitLength = blockLength * 4;
         
        BigInteger[][] keys = RSA.generateKeys(bitLength);
        System.out.printf("public Key: (%d, %d)\nprivate Key: (%d, %d)\n", keys[0][0], keys[0][1], keys[1][0], keys[1][1]);
        
        String message = "";
        while (true) {
            message = RSA.input();
            if (message == null) break;
            
            System.out.println("\nEingabe: " + message);
            
            String[] encrypted = encrypt(message, keys[0]);
            for (int i = 0; i < encrypted.length; i++)
                System.out.print(encrypted[i].toString() + " ");
                
            System.out.println("\nNachricht: " + decrypt(encrypted, keys[1]));
        }
        
        System.exit(1);
    }
    
    public String[] encrypt(String message, BigInteger[] key) {
        int sequences = message.length() / blockLength + (message.length() % blockLength == 0 ? 0 : 1);
        String ascii;
        
        String[] encrypted = new String[sequences];
        
        for (int i = 0; i < message.length(); i += blockLength) {
            ascii = "";
            for (int j = i; j < i+blockLength; j++) {
                if (j < message.length())   
                    ascii += Integer.toHexString(message.codePointAt(j));
                else
                    ascii += "00";
            }
            
            encrypted[i/blockLength] = new BigInteger(ascii, 16).modPow(key[1], key[0]).toString(16);
        }

        return encrypted;
    }
    
    public String decrypt(String[] chiffre, BigInteger[] key) {
        String message = "";
        String ascii;
        
        for (int i = 0; i < chiffre.length; i++) {
            ascii = new BigInteger(chiffre[i], 16).modPow(key[1], key[0]).toString(16);
            
            for (int j = 0; j < ascii.length(); j += 2) {
                if (ascii != "00")
                    message += (char) Integer.parseInt(ascii.substring(j, j+2), 16);
            }
        }
        
        return message;
    }
}
