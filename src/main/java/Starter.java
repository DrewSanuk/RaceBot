import java.util.concurrent.TimeUnit;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Starter implements MessageCreateListener{

	
	public void onMessageCreate(MessageCreateEvent event) {
		Message m = event.getMessage();
		DiscordApi bot = event.getApi();

		HasRole hr = new HasRole();

		Server s = event.getServer().get();
		String server = event.getServer().get().getIdAsString();
		User user = m.getUserAuthor().get();

		if(m.getChannel().toString().contains("game-chat"))
		{
			//starts the game
			if(m.getContent().equalsIgnoreCase("!start") && hr.isOP(user, server))
			{
				event.getApi().updateActivity(ActivityType.WATCHING, "Kill Races!");

				//countdown
				event.getChannel().sendMessage("Game Starting...");

				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

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

				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				event.getChannel().sendMessage("Game Started! Moving Players to their respective voice channels...");

				//gets all the users with the roll "Team 1"
				for(User u : event.getApi().getRoleById("501973192605433877").get().getUsers())
				{
					for(Role r : u.getRoles(s))
					{
						//gets all voice Channels of the server
						for(ServerVoiceChannel vc : bot.getServerVoiceChannels())
						{
							//if the Voice channel name = team name
							if((vc.toString().contains(r.getName())))
							{
								//player is moved to correct team
								u.move(vc); 
							}
						}	
					}
				}

			//slight delay and then displays timer rules and good luck message with timer emoji
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			event.getChannel().sendMessage("Good luck!");

			event.getChannel().sendMessage("(Start Timer)");
		}

		if((m.getContent().equals("(Start Timer)")) && (m.getUserAuthor().get().isBot() == true))
		{
			event.addReactionsToMessage("â�±");
		}

	}
}
}
