package com.ds.security.api;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
/**
 * @author adlakha.vaibhav
 */
@Service
public class Base64PasswordEncryptionAPI implements PasswordEncryptionAPI {

    public String decrypt(String encryptedPassword) {
        return StringUtils.isEmpty(encryptedPassword) ? encryptedPassword : new String(Base64.decode(encryptedPassword));

    }

    public String encrypt(String password) {
        return StringUtils.isEmpty(password) ? password : new String(Base64.encode(password.getBytes()));
    }

    public static void main(String[] args) {
        Base64PasswordEncryptionAPI passwordEncryptionAPI = new Base64PasswordEncryptionAPI();
        System.out.println(passwordEncryptionAPI.encrypt("pavitar"));
        System.out.println(passwordEncryptionAPI.decrypt(passwordEncryptionAPI.encrypt("pavitar")));

    }

}