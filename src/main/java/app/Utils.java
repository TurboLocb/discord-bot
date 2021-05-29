package app;

import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.Random;

public class Utils {
    private static String configFile = "config.properties";

    private static final Random random = new Random();

    //Code author Will https://stackoverflow.com/questions/2939218/getting-the-external-ip-address-in-java
    public static String getIp() throws IOException {
        URL whatismyip = new URL("http://checkip.amazonaws.com");

        BufferedReader in = new BufferedReader(new InputStreamReader(
                whatismyip.openStream()));
        return in.readLine();
    }

    //return random in range
    public static int randomIntInRange(int min, int max) {
        return random.nextInt((max - min) + 1);
    }

    //read '.properties' files
    public static String readBotToken(String propertyName) {
        String value = "";

        //read config file
        try (InputStream resource = Utils.class.getClassLoader().getResourceAsStream(configFile)) {

            Properties properties = new Properties();
            properties.load(resource);

            //iterate of key in file
            for (String key : properties.stringPropertyNames()) {
                if (key.equals(propertyName)){
                    value = properties.getProperty(key);
                }
            }

        } catch (IOException | NullPointerException exception) {
            exception.printStackTrace();
        }

        System.out.println("Bot token is: " + value);

        return value;
    }
}
