import java.util.concurrent.TimeUnit;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Countdown implements MessageCreateListener{

	public void onMessageCreate(MessageCreateEvent event) {

		if(event.getMessage().getContent().equals("!countdown"))
		{
			event.getChannel().sendMessage("3...");

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			event.getChannel().sendMessage("2...");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			event.getChannel().sendMessage("1...");

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			event.getChannel().sendMessage("Go!");
		}

	}

}
