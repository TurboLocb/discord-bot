package app.objects;

import app.Utils;

import java.util.HashMap;
import java.util.Map;

public class UkrAlphabet {

    //created for site "http://www.lexicons.ru/"
    private final static Map<Integer, String> ukrAlphabet = new HashMap<Integer, String>() {
        {
            put(1, "A");
            put(2, "Б");
            put(3, "В");
            put(4, "Г");
            put(5, "Ґ"); //Ґ = Г`
            put(6, "Д");
            put(7, "Ж");
            put(8, "З");
            put(9, "І");
            put(10, "Ї"); //Ї = I
            put(11, "К");
            put(12, "Л");
            put(13, "М");
            put(14, "Н");
            put(15, "О");
            put(16, "П");
            put(17, "Р");
            put(18, "С");
            put(19, "Т");
            put(20, "У");
            put(21, "Ф");
            put(22, "Х");
            put(23, "Ц");
            put(24, "Ч");
            put(25, "Ш");
            put(26, "Щ");
            put(27, "Ю");
            put(28, "Я");
            put(29, "Месяц");
            put(30, "Счет");

        }
    };

    public UkrAlphabet() {
    }

    public static String getByNumber(Integer i) {
        return ukrAlphabet.get(i);
    }

    //get random letter from alphabet
    public static String getByRandomNumber() {
        return ukrAlphabet.get(Utils.randomIntInRange(1, 30));
    }
}
