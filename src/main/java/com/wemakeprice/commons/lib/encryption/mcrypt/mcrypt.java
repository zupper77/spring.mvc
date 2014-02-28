package com.wemakeprice.commons.lib.encryption.mcrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Repository;

@Repository("mcrypto")
public class mcrypt {

        static char[] HEX_CHARS = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

        private SecretKeySpec passkeyspec;
        private SecretKeySpec userkeyspec;
        private Cipher cipher;

        private String passwdSecretKey = "wlrmagodqhrgksrk";
        private String userinfoSecretKey = "roqkftlfghkdlxld";
        
        public mcrypt()
        {
        	 String PasspaddedKey = passwdSecretKey;
        	 String UserpaddedKey = userinfoSecretKey;
        	 passkeyspec = new SecretKeySpec(PasspaddedKey.getBytes(), "AES");
        	 userkeyspec = new SecretKeySpec(UserpaddedKey.getBytes(), "AES");
        }

        public String encryptPass(String source) throws Exception
        {
        	 Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
             cipher.init(Cipher.ENCRYPT_MODE, passkeyspec);
             String paddedData = padString2(source);
             return bytesToHex(cipher.doFinal(paddedData.getBytes()));
        }

        public String encryptUser(String source) throws Exception
        {
        	 Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
             cipher.init(Cipher.ENCRYPT_MODE, userkeyspec);
             String paddedData = padString2(source);
             return bytesToHex(cipher.doFinal(paddedData.getBytes()));
        }        
        
        public String decryptPass(String source) throws Exception
        {
                if(source == null || source.length() == 0)
                        throw new Exception("Empty string");
                Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
                cipher.init(Cipher.DECRYPT_MODE, passkeyspec);
                return new String(cipher.doFinal(hexToBytes(source)));
        }      

        public String decryptUser(String source) throws Exception
        {
                if(source == null || source.length() == 0)
                        throw new Exception("Empty string");
                Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
                cipher.init(Cipher.DECRYPT_MODE, userkeyspec);
                return new String(cipher.doFinal(hexToBytes(source)));
        }  
        
        public static String bytesToHex(byte[] buf)
        {
            char[] chars = new char[2 * buf.length];
            for (int i = 0; i < buf.length; ++i)
            {
                chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
                chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
            }
            return new String(chars);
        }

        
        public static byte[] hexToBytes(String str) {
                if (str==null) {
                        return null;
                } else if (str.length() < 2) {
                        return null;
                } else {
                        int len = str.length() / 2;
                        byte[] buffer = new byte[len];
                        for (int i=0; i<len; i++) {
                                buffer[i] = (byte) Integer.parseInt(str.substring(i*2,i*2+2),16);
                        }
                        return buffer;
                }
        }


        public static String padString2(String source) {
            char paddingChar = '\0';
            int size = 16;
            int padLength = size - source.length() % size;

            for (int i = 0; i < padLength; i++) {
                source += paddingChar;
            }

            return source;
        }
        

        private static String padString(String source)
        {
          char paddingChar = 0;
          int size = 16;
          int x = source.length() % size;
          int padLength = size - x;

          for (int i = 0; i < padLength; i++)
          {
                  source += paddingChar;
          }

          return source;
        }
}
