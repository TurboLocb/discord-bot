package app;

import app.objects.Command;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class MessageListener extends ListenerAdapter {

    private final String DARIAN_NAME = "Darian";
    private final String VYCHIK_NAME = "Arthes***";

    private final static Logger logger = LogManager.getLogger(MessageListener.class.getName());

    public void makeBot(String token) throws LoginException {
        JDA jda = JDABuilder.createDefault(token)
                .setActivity(Activity.playing("твоем очке"))
                .build();
        jda.addEventListener(new MessageListener());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        logger.info("Message from: " + event.getAuthor().getName()
                + ": "
                + event.getMessage().getContentDisplay());

        //return list of commands
        if (event.getMessage().getContentRaw().equals("!commands")) {
            //createMessageWithCommandsList(event);
            test(event);
        }

        //return ip
        else if (event.getMessage().getContentRaw().equals("!ip")) {
            createMessageWithIp(event);
        }

    }

    @Override
    public void onGuildVoiceJoin(@Nonnull GuildVoiceJoinEvent event) {
        if (event.getMember().getUser().getName().equals(VYCHIK_NAME) || event.getMember().getUser().getName().equals(DARIAN_NAME)) {
            Objects.requireNonNull(event.getGuild().getDefaultChannel())
                    .sendMessage("Черт " + event.getMember().getUser().getAsMention() + " нарисовался").queue();
        }
    }

    private void createMessageWithCommandsList(MessageReceivedEvent event) {
        Commands[] cmds = Commands.values();
        StringBuilder sb = new StringBuilder();

        sb.append("bot commands:");

        for (Commands c : cmds) {
            sb.append("!").append(c).append("\n");
        }

        event.getChannel().sendMessage(sb.toString()).queue();
    }

    private void test(MessageReceivedEvent event) {
        File file = new File("src/main/resources/commands.json");

        ObjectMapper mapper = new ObjectMapper();

        //don't do exception when find unexpected field
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            //get array of json
            List<Command> commands = mapper.readValue(file, new TypeReference<List<Command>>() {
            });

            //build in one string
            StringBuilder sb = new StringBuilder();

            sb.append("```");
            sb.append("yaml");
            sb.append("\n");

            for (Command c : commands
            ) {
                sb.append(c.getCommand()).append(" - ").append(c.getDescription()).append("\n");
            }

            sb.append("```");

            //System.out.println(sb.toString());

            event.getChannel().sendMessage(sb.toString()).queue();

        } catch (IOException e) {
            logger.error("Error '!commands! execution: {}", e.toString());
        }

    }

    private void createMessageWithIp(MessageReceivedEvent event) {
        try {
            //take ip of this pc by amazon service
            String ip = Utils.getIp();

            int random_int = new Random().nextInt(3);

            switch (random_int) {
                case 0:
                    event.getChannel().sendMessage(
                            "Мадиииина, ip хочешь, да? Ну ладно, держи, солнышко: "
                                    + ip).queue();
                    break;
                case 1:
                    event.getChannel().sendMessage(
                            "Ляяя, ip нада да? дал дал: "
                                    + ip).queue();
                    break;
                case 2:
                    event.getChannel().sendMessage(
                            "Доставляю с хрустом: "
                                    + ip).queue();
                    break;
            }
        } catch (IOException e) {
            logger.error("Ip-address getting exception {}", e.toString());
            event.getChannel().sendMessage("Can't got ip address").queue();
        }
    }

    private enum Commands {
        Ip,
        Commands,
    }

}
