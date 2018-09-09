package core.serverConnector;

import com.mashape.unirest.http.JsonNode;
import core.parsers.DefaultXMLParser;
import core.parsers.IXMLParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConnectionInitializerTest {

    private ConnectionInitializer connectionInitializer;
    private IXMLParser<String> parser;
    private static final String PATH = "resources/addresses.xml";

    @BeforeAll
    public void init(){

        parser = new DefaultXMLParser();
        connectionInitializer = new ConnectionInitializer(parser,PATH);

    }

    @Test
    void checkOnlineServers() {

        connectionInitializer.checkOnlineServers();

        for (Map.Entry<String, JsonNode> entry:
             connectionInitializer.getJsonNodeHashMap().entrySet()) {
            System.out.println(entry.getKey()+" "+entry.getValue());
        }

        assertNotEquals(connectionInitializer.getJsonNodeHashMap(),null);
    }
}