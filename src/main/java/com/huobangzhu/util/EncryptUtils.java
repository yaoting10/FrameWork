package com.huobangzhu.util;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Sha2Crypt;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @author: Tim.Yao
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EncryptUtils {

    public static final String password(String raw, String salt){
        return Sha2Crypt.sha512Crypt(raw.getBytes(Charsets.UTF_8), salt);
    }

    public static final String getSha512Salt(){
        // TODO Commons.Codec requires sha512 salt started with "$6$", and this
        // should be fixed in future.
        return "$6$" + UUID.randomUUID().toString();
    }

    public static final String md5(String raw){
        return DigestUtils.md5Hex(raw);
    }

    public static final String md5(String raw, String encode){
        try{
            byte[] digest = DigestUtils.md5(raw.getBytes(encode));
            StringBuffer hexString = new StringBuffer();
            String strTemp;
            for(int i = 0; i < digest.length; i++){
                strTemp = Integer.toHexString(
                        (digest[i] & 0x000000FF) | 0xFFFFFF00).substring(6);
                hexString.append(strTemp);
            }
            return hexString.toString();
        }catch(UnsupportedEncodingException e){
            return "";
        }
    }

    public static final String baseDecoder(String dest) throws UnsupportedEncodingException{
        Base64 decoder = new Base64();
        byte[] buffer = decoder.decodeBase64(dest.getBytes("UTF-8"));
        return new String(buffer,"UTF-8");
    }

    public static final String baseEncoder(String dest)throws UnsupportedEncodingException{
        Base64 encoder = new Base64();
        byte[] buffer = encoder.encodeBase64Chunked(dest.getBytes("UTF-8"));
        return new String(buffer,"UTF-8");
    }

    public static final String base32Decoder(String dest) throws UnsupportedEncodingException{
        Base32 decoder = new Base32();
        byte[] buffer = decoder.decode(dest.getBytes("UTF-8"));
        return new String(buffer,"UTF-8");
    }

    public static final String base32Encoder(String dest) throws UnsupportedEncodingException{
        Base32 encoder = new Base32();
        byte[] buffer = encoder.encode(dest.getBytes("UTF-8"));
        return new String(buffer,"UTF-8");
    }

    public static String xorAndBaseEncodeMessage(String message, String key) throws UnsupportedEncodingException {
        String xored = xorMessage(message, key);
        return base32Encoder(xored);
    }

    public static String baseDecodeAndXorMessage(String message, String key) throws UnsupportedEncodingException {
        String b32Decoded = base32Decoder(message);
        return xorMessage(b32Decoded, key);
    }

    public static String xorMessage(String message, String key) {
        try {
            if (message == null || key == null) return null;

            char[] keys = key.toCharArray();
            char[] mesg = message.toCharArray();

            int ml = mesg.length;
            int kl = keys.length;
            char[] newmsg = new char[ml];

            for (int i = 0; i < ml; i++) {
                newmsg[i] = (char) (mesg[i] ^ keys[i % kl]);
            }//for i
            mesg = null;
            keys = null;
            return new String(newmsg);
        } catch (Exception e) {
            return null;
        }
    }
}
