package com.esr.MadeHotel.util;

import com.esr.MadeHotel.exception.MadeHotelRuntimeException;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SenhasUsuariosUtil {

    public static String criptografarSenha(String senha){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(senha.getBytes("UTF-8"));
            byte[] digest = messageDigest.digest(senha.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for(byte b : digest){
                hexString.append(String.format("%02X", 0xFF & b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex){
            throw new MadeHotelRuntimeException(ex);
        }
    }
}
