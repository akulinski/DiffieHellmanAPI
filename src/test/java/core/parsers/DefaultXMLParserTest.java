package core.parsers;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DefaultXMLParserTest {

    DefaultXMLParser defaultXMLParser;
    private static final String PATH = "resources/addresses.xml";


    @BeforeAll
    void init(){
        defaultXMLParser = new DefaultXMLParser();
        defaultXMLParser.loadResource(DefaultXMLParserTest.PATH);
    }

    @org.junit.jupiter.api.Test
    void loadResource() {

        assertEquals(defaultXMLParser.getPath(),DefaultXMLParserTest.PATH);
        assertNotEquals(defaultXMLParser.getfXmlFile(),null);
    }

    @org.junit.jupiter.api.Test
    void parse() {
    }

    @org.junit.jupiter.api.Test
    void getValue() {
        defaultXMLParser.parse();
        assertEquals(defaultXMLParser.getValue().get(0),"localhost:8080/initDiffie");
    }
}