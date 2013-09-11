/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.twidgetpackage;

import java.io.*;
import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author Lee
 */
class DataFinder {

    public DataFinder(){
        try {
        FileInputStream fstream = new FileInputStream(NAME_SAVE_FILE);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        while((strLine = br.readLine()) != null){
             if (strLine.charAt(0)=='!'){ //looking for names, I guess this isn't really necessary
                 String name = strLine.substring(1);
                 String[] tokens = new String[2];
                 tokens[0] = br.readLine();
                 tokens[1] = br.readLine();
                 namesAndTokens.put(name,tokens);
             }
        }
        } catch (IOException e){
        }
    }
    
    boolean findName(String text) {
        return namesAndTokens.containsKey(text);
        
    }

    String[] getCredentials(String text) {
        return namesAndTokens.get(text);
    }

    void storeaccessTokens(String text, String token, String tokenSecret) {
        /*Format is this
         * Name
         * AccessToken
         * AccessTokenSecret
         */
        try{
            FileWriter fw = new FileWriter(NAME_SAVE_FILE,true);
            PrintWriter pw = new PrintWriter(fw);
            pw.printf("%s" + "%n", '!' + text);
            pw.printf("%s" + "%n", token);
            pw.printf("%s" + "%n", tokenSecret);
            
            pw.close();
            //fw.write("\n");//get it to a new line
            //fw.write('!' + text + '\n');
            //fw.write(token + '\n');
            //fw.write(tokenSecret + '\n');
        } catch (IOException e){
            System.err.println("IOException" + e.getMessage());
        }

    }
    private Map<String,String[]> namesAndTokens = new HashMap<String,String[]>();
    private static final String NAME_SAVE_FILE = "namelist.txt";


}
