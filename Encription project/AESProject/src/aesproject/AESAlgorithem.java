/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aesproject;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESAlgorithem {
    public static String algo = "AES";
    public byte[] keyValue;
    
    //here we make constructor take key as string and store it in bytes in public keyValue variable
    public AESAlgorithem(byte key[]){
        keyValue = key;
    }
    
    // to generate key from algorithm using SecretKeySpec in java
    public Key generateKey() throws Exception{
        Key key = new SecretKeySpec(keyValue, algo);
        return key;
    }
    
    //method take string and return encrypted message
    public String encrypt(String msg) throws Exception{
        //get the key
        Key key = generateKey();
        
        //use algorithm using Cipher class 
        Cipher c = Cipher.getInstance(algo);
        
        // initialize cipher to encryption mode.
        c.init(Cipher.ENCRYPT_MODE, key);
        //return buffer result of encryption
        byte[] encVal = c.doFinal(msg.getBytes());
        
        // encodes the message without any line separation.
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }
    
    //method take encrypted message and return  decrypted message
    public String decrypt(String msg) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(algo);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(msg);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
}
