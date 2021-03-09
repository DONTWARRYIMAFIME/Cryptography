package Cryptograhy.Methods;

public interface CryptographyMethod {
    String encrypt(String sourceStr, String key);
    String decrypt(String sourceStr, String key);

}
