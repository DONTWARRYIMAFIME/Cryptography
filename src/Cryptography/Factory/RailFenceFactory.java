package Cryptography.Factory;

import Cryptography.Methods.CryptographyMethod;
import Cryptography.Methods.RailFence;

public class RailFenceFactory implements CryptographyFactory {
    @Override
    public CryptographyMethod create() {
        return new RailFence();
    }
}
