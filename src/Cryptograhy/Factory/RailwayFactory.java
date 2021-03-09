package Cryptograhy.Factory;

import Cryptograhy.Methods.CryptographyMethod;
import Cryptograhy.Methods.Railway;

public class RailwayFactory implements CryptographyFactory {
    @Override
    public CryptographyMethod create() {
        return new Railway();
    }
}
