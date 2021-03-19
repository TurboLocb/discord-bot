package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Utils {
    //Code author Will https://stackoverflow.com/questions/2939218/getting-the-external-ip-address-in-java
    public static String getIp() throws IOException {
        URL whatismyip = new URL("http://checkip.amazonaws.com");

        BufferedReader in = new BufferedReader(new InputStreamReader(
                whatismyip.openStream()));
        return in.readLine();
    }
}
