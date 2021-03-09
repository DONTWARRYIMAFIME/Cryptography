package Cryptograhy.Factory;

import Cryptograhy.Methods.CryptographyMethod;
import Cryptograhy.Methods.RailFence;

public class RailFenceFactory implements CryptographyFactory {
    @Override
    public CryptographyMethod create() {
        return new RailFence();
    }
}
