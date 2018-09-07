package core.parsers;

public interface IXMLParser<T> {

    void loadResource(String path);
    boolean parse();
    T getValue();

}
