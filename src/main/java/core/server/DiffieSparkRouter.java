package core.server;

import com.google.gson.Gson;
import spark.Route;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.ConcurrentHashMap;

import static spark.Spark.post;

public class DiffieSparkRouter {

    private SecureRandom secureRandom;

    private ConcurrentHashMap<String, InitValues> hashMap;

    private KeyPairGenerator kpg;

    private ConcurrentHashMap<String, KeyPair> keyPairConcurrentHashMap;

    public DiffieSparkRouter() {

        secureRandom = new SecureRandom();
        hashMap = new ConcurrentHashMap<>();
        keyPairConcurrentHashMap = new ConcurrentHashMap<>();

        try {
            kpg = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        post("/secure/init", secureInit);
    }

    final Route secureInit = ((request, response) -> {

        int base = secureRandom.nextInt(100000);

        int prime = secureRandom.nextInt(100000);

        while (!isPrime(prime)) {
            prime = secureRandom.nextInt(100000);
        }

        KeyPair keyPair = kpg.generateKeyPair();

        InitValues initValues = new InitValues(base, prime, keyPair.getPublic());

        hashMap.put(request.ip(), initValues);

        keyPairConcurrentHashMap.put(request.ip(), keyPair);

        return new Gson().toJson(initValues, InitValues.class);

    });

    final private static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
