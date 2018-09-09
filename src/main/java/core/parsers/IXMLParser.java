package core.parsers;

import java.util.LinkedList;

public interface IXMLParser<T> {

    void loadResource(String path);

    boolean parse();

    LinkedList<T> getValue();

}
