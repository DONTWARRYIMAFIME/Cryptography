package Cryptography.Factory;

import Cryptography.Methods.Columnar;
import Cryptography.Methods.CryptographyMethod;

public class ColumnarFactory implements CryptographyFactory {

    @Override
    public CryptographyMethod create() {
        return new Columnar();
    }
}
