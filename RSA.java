import java.math.BigInteger;
import java.util.Random;

public class RSA
{
    public RSA(int bitLength) 
    {
        BigInteger[][] keys = generateKeys(bitLength);
        System.out.printf("public Key: (%d, %d)\nprivate Key: (%d, %d)\n", keys[0][0], keys[0][1], keys[1][0], keys[1][1]);
        
        String message = "";
        while (true) {
            message = input();
            if (message == null) break;
            
            System.out.println("\nEingabe: " + message);
            
            BigInteger[] encrypted = encrypt(message, keys[0]);
            for (int i = 0; i < encrypted.length; i++)
                System.out.print(encrypted[i].toString() + " ");
                
            System.out.println("\nNachricht: " + decrypt(encrypted, keys[1]));
        }
        
        System.exit(1);
    }
    
    
    public BigInteger[] encrypt(String message, BigInteger[] key) {
        BigInteger[] encrypted = new BigInteger[message.length()];
        BigInteger ascii;
        
        for (int i = 0; i < message.length(); i++) {
            ascii = new BigInteger((int) message.charAt(i) + "");
            encrypted[i] = ascii.modPow(key[1], key[0]);
        }
        
        return encrypted;
    }
    
    public String decrypt(BigInteger[] chiffre, BigInteger[] key) {
        String message = "";
        BigInteger ascii;
        
        for (int i = 0; i < chiffre.length; i++) {
            ascii = chiffre[i].modPow(key[1], key[0]);
            message += (char) ascii.intValue();
        }
        
        return message;
    }
    
    
    public static BigInteger[][] generateKeys(int bitLength) {
        BigInteger p = BigInteger.probablePrime(bitLength, new Random());
        BigInteger q = BigInteger.probablePrime(bitLength, new Random());
        
        BigInteger n = p.multiply(q);
        BigInteger r = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        
        BigInteger e = BigInteger.probablePrime(bitLength-1, new Random());
        BigInteger d = berlekamp(p, q, r, e);
        
        return new BigInteger[][] {{n, e}, {n, d}};
    }
    
    private static BigInteger berlekamp(BigInteger p, BigInteger q, BigInteger r, BigInteger c) {
        BigInteger[] a = new BigInteger[] { r, c };
        BigInteger[] u = new BigInteger[] { new BigInteger("0"), new BigInteger("1") };
        BigInteger[] v = new BigInteger[] { new BigInteger("1"), new BigInteger("0") };
        
        BigInteger qi = a[0].divide(a[1]);
        BigInteger tmp;
        
        while (a[1].compareTo(BigInteger.ONE) != 0) {
            tmp = a[1];
            a[1] = a[0].subtract(a[1].multiply(qi));
            a[0] = tmp;
            
            tmp = u[1];
            u[1] = u[0].subtract(u[1].multiply(qi));
            u[0] = tmp;
            
            tmp = v[1];
            v[1] = v[0].subtract(v[1].multiply(qi));
            v[0] = tmp;
            
            qi = a[0].divide(a[1]);
        }
        
        if (u[1].intValue() < 0) {
            tmp = u[1].multiply(new BigInteger("-1")).divide(r).add(BigInteger.ONE);
            u[1] = u[1].add(tmp.multiply(r));
        }
        
        return u[1];
    }
    
    public static String input() {
        return javax.swing.JOptionPane.showInputDialog("Message:");
    }
}
