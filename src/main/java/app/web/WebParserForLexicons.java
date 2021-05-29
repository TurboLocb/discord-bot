package app.web;

import app.Utils;
import app.objects.UkrAlphabet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

public class WebParserForLexicons implements WebParser<String> {

    /**
     * TODO: create logger
     */

    private final String URL = "http://www.lexicons.ru/modern/u/ukrainian/ukr-rus-strange-words.html";

    private Document page = new Document(URL);

    @Override
    public void connect() {
        try {
            page = Jsoup.connect(URL).get();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    @Override
    public String parse() {
        //returned value
        String value;

        //random letter
        String letter = UkrAlphabet.getByRandomNumber();

        System.out.println("Generated letter is - " + letter);

        try {
            //get h2 with random letter
            System.out.println(page.select("h2.head2:contains(" + letter + ")").first());

            //get ul under h2
            Element e = page.select("h2.head2:contains(" + letter + ") ~ ul.preview0").first();

            //get all nested li
            List<Element> elements = e.getElementsByTag("li");

            //return random li from ul
            value = elements.get(Utils.randomIntInRange(0, elements.size() - 1)).text();
        } catch (NullPointerException npe) {
            value = "Didn't get phrase on site";

            npe.printStackTrace();
        }

        return value;
    }

    @Override
    public void closeConnection() {

    }
}
