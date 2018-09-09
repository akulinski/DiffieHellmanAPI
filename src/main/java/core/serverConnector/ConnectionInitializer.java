package core.serverConnector;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import core.parsers.IXMLParser;

import java.util.HashMap;
import java.util.LinkedList;

public class ConnectionInitializer {

    private IXMLParser ixmlParser;
    private String resourcePath;
    private LinkedList<String> addressesList;
    private HashMap<String, JsonNode> jsonNodeHashMap;

    public IXMLParser getIxmlParser() {
        return ixmlParser;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public LinkedList<String> getAddressesList() {
        return addressesList;
    }

    public HashMap<String, JsonNode> getJsonNodeHashMap() {
        return jsonNodeHashMap;
    }

    public ConnectionInitializer(IXMLParser ixmlParser, String resourcePath) {
        this.ixmlParser = ixmlParser;
        this.resourcePath = resourcePath;
        this.jsonNodeHashMap = new HashMap<>();
        initParser();
    }

    private void initParser() {
        ixmlParser.loadResource(this.resourcePath);
        ixmlParser.parse();
        this.addressesList = ixmlParser.getValue();
    }

    public void checkOnlineServers() {

        for (String entry :
                this.addressesList) {

            try {

                HttpResponse<JsonNode> response = Unirest.get(entry).asJson();
                this.jsonNodeHashMap.put(entry, response.getBody());

            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }

    }
}
