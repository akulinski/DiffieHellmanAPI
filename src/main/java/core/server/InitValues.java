package core.server;

import java.security.Key;

public class InitValues {

    private int base;
    private int prime;

    public Key getPublicKey() {
        return publicKey;
    }

    private Key publicKey;

    public InitValues(int base, int prime,Key publicKey) {
        this.base = base;
        this.prime = prime;
        this.publicKey = publicKey;
    }

    public int getBase() {

        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getPrime() {
        return prime;
    }

    public void setPrime(int prime) {
        this.prime = prime;
    }
}

