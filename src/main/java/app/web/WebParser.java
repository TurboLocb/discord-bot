package app.web;

public interface WebParser<T> {

    void connect();

    T parse();

    void closeConnection();
}
