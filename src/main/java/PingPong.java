import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class PingPong implements MessageCreateListener {

	public void onMessageCreate(MessageCreateEvent event) {

		Message m = event.getMessage();

		if (m.getContent().equalsIgnoreCase("!ping")) {
			event.getChannel().sendMessage("Pong!");

		}
	}
}
