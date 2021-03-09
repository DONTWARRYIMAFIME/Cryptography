package Cryptography.Factory;

import Cryptography.Methods.CryptographyMethod;

public interface CryptographyFactory {
    CryptographyMethod create();
}
