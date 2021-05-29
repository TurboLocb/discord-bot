package app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.security.auth.login.LoginException;

public class Main {

    private static final String token = Utils.readBotToken("bot.token");
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
