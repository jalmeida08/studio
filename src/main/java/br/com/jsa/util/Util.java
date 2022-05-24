package br.com.jsa.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;

public class Util {

    public static String criptografarString(String dado){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(dado);
    }

    public static String gerarChaveAleatoria(){
        return KeyGenerators.string().generateKey();
    }

}
