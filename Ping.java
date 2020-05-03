import org.javacord.api.event.message.MessageCreateEvent;

public class Ping implements JavacordCommand {
    @Override
    public void runCommand(MessageCreateEvent event) {
        event.getServerTextChannel().ifPresent(serverTextChannel -> serverTextChannel.sendMessage("Pong"));
    }
}
