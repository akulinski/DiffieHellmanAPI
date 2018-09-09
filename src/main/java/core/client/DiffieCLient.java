package core.client;

import com.mashape.unirest.http.Unirest;
import core.parsers.DefaultXMLParser;
import core.serverConnector.ConnectionInitializer;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class DiffieCLient {

    private KeyPairGenerator keyPairGenerator;

    private KeyPair keyPair;

    private  DefaultXMLParser defaultXMLParser;

    private ConnectionInitializer connectionInitializer;

    DiffieCLient(){

        defaultXMLParser = new DefaultXMLParser();

        connectionInitializer = new ConnectionInitializer(defaultXMLParser,"resources/addresses.xml");

        try {

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPair = keyPairGenerator.generateKeyPair();
            sendPublicKey();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private void sendPublicKey(){

        connectionInitializer.getAddressesList().forEach(address->{
            Unirest.post(address).field("publicKey",keyPair.getPublic());
        });

    }
}
