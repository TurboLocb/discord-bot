package app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.security.auth.login.LoginException;

public class Main {

    private static final String token = "NjI2MTcxMDU4NDU3OTM1ODc0.XYqVyg.MwMZTeS8uBBGBAMj35sXbgYky_0";
    private final static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        //create bot
        MessageListener bot = new MessageListener();
        try {
            bot.makeBot(token);
        } catch (LoginException e) {
            logger.error("Error of bot creation {}", e.toString());
        }
    }

}
