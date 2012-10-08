package com.ds.security.api;

/**
 * @author adlakha.vaibhav
 */
public interface PasswordEncryptionAPI {

  public String decrypt(String encryptedPassword);

    public String encrypt(String password);
}
