import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.util.logging.ExceptionLogger;

public class Main {
    public static void main(String[] args) {
        DiscordApi api = new DiscordApiBuilder().setToken(args[0]).
                login().exceptionally(ExceptionLogger.get()).join();

        JavacordCommandListener registry = new JavacordCommandListener("!");
        registry.registerCommand("ping", new Ping());
        api.addMessageCreateListener(registry);
    }
}
