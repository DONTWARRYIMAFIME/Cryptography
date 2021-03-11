package Cryptography.Factory;

import Cryptography.Methods.CryptographyMethod;
import Cryptography.Methods.Vigenere;

public class VigenereFactory implements CryptographyFactory {
    @Override
    public CryptographyMethod create() {
        return new Vigenere();
    }
}
