import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A Javacord listener that will dispatch events to registered commands.
 */

public class JavacordCommandListener implements MessageCreateListener {
    /**
     * The map that will hold our commands.
     */
    private Map<String, JavacordCommand> commands = new ConcurrentHashMap<>();

    /**
     * The prefix. Messages that do not start with this string will be disregarded.
     */
    private String prefix;

    /**
     * Create a new command listener.
     *
     * @param prefix The prefix to use.
     */
    public JavacordCommandListener(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Gets the command prefix.
     *
     * @return The prefix.
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Sets the command prefix.
     *
     * @param prefix The new prefix.
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Register a command.
     *
     * @param name    The name for the command.
     * @param command The actual command.
     */
    public void registerCommand(String name, JavacordCommand command) {
        commands.put(name.toLowerCase(), command);
    }

    /**
     * This method gets called by Javacord if a new message is created and then tries to
     * dispatch this message to the appropriate command.
     *
     * @param event The javacord event.
     */
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        //Filtering out every message that's not from a user
        if (!event.getMessageAuthor().isRegularUser()) {
            return;
        }

        String message = event.getMessageContent();
        // Check if the message starts with your prefix.
        if (message.startsWith(prefix)) {
            // Split everything after the prefix at the first space. Left part may be a command.
            String[] splitCommandString = message.substring(prefix.length()).split(" ", 2);
            // Check if we know a command by the name specified
            JavacordCommand command = commands.get(splitCommandString[0].toLowerCase());
            if (command != null) {
                command.runCommand(event);
            }
        }
    }
}
