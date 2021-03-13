package Cryptography.Factory;

import Cryptography.Methods.CryptographyMethod;
import Cryptography.Methods.RotatingLattice;

public class RotatingLatticeFactory implements CryptographyFactory {
    @Override
    public CryptographyMethod create() {
        return new RotatingLattice();
    }
}
