import org.javacord.api.event.message.MessageCreateEvent;

public interface JavacordCommand {
    /**
     * Runs the command.
     *
     * @param event The message create event that prompted the command.
     */
    void runCommand(MessageCreateEvent event);
}
