package Cryptograhy.Factory;

import Cryptograhy.Methods.Columnar;
import Cryptograhy.Methods.CryptographyMethod;

public class ColumnarFactory implements CryptographyFactory {

    @Override
    public CryptographyMethod create() {
        return new Columnar();
    }
}
