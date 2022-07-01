public class Transposition
{
    public Transposition() {
        String x = encrypt("DATENMANAGEMENT", 5);
        System.out.println(x);
        System.out.println(decrypt(x, 5));
    }

    public String encrypt(String message, int key) {
        String[] transposition = new String[key--];
        
        for (int i = 0; i < transposition.length; i++)
            transposition[i] = "";
        
        int row = 0;
        boolean up = true;
        for (int j = 0; j < message.length(); j++) {
            transposition[row] += message.charAt(j);
             
            if (up && row < key)
                row++;
            else if (!up && row > 0)
                row--;
                
            if (row == 0 || row == key)
                up = !up;
        }
        
        String chiffre = "";
        for (int k = 0; k < transposition.length; k++) {
            chiffre += transposition[k];
        }
        
        return chiffre;
    }
    
    public String decrypt(String message, int key) {
        int[] zeichen = new int[key];
        String[] transposition = new String[key--];
        
        for (int i = 0; i < transposition.length; i++)
            transposition[i] = "";
        
        int row = 0;
        boolean up = true;
        for (int j = 0; j < message.length(); j++) {
            zeichen[row] += 1;
             
            if (up && row < key)
                row++;
            else if (!up && row > 0)
                row--;
                
            if (row == 0 || row == key)
                up = !up;
        }
        
        row = 0;
        for (int k = 0; k < message.length(); k++) {
            transposition[row] += message.charAt(k);

            zeichen[row] -= 1;
            if (zeichen[row] == 0)
                row++;
        }
        
        String klartext = "";
        row = 0;
        up = true;
        for (int j = 0; j < message.length(); j++) {
            klartext += transposition[row].charAt(0);
            transposition[row] = transposition[row].replaceFirst(klartext.substring(0,1), "");
             
            if (up && row < key)
                row++;
            else if (!up && row > 0)
                row--;
                
            if (row == 0 || row == key)
                up = !up;
        }
        
        return klartext;
    }
}
