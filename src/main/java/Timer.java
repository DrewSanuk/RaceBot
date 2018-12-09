import java.util.concurrent.TimeUnit;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.reaction.ReactionAddEvent;
import org.javacord.api.listener.message.reaction.ReactionAddListener;

public class Timer implements ReactionAddListener
{
	public static int x  = 0;

	public void update(DiscordApi bot) throws InterruptedException
	{
		//1 = on, 0 = off
		
		//counts 5 minutes, uses seconds to allow TimerStop
		while(x == 1)
		{		
			bot.updateActivity("5:00 Minutes Left!");

			for(int min = 60; min >= 1; min--)
			{
				if(x == 1)
				{
					TimeUnit.SECONDS.sleep(1);
				}
				else break;
			}

			if(x == 1)
			{
				bot.updateActivity("4:00 Minutes Left!");
			}
			else break;

			for(int min = 60; min >= 1; min--)
			{
				if(x == 1)
				{
					TimeUnit.SECONDS.sleep(1);
				}
				else break;
			}

			if(x == 1)
			{
				bot.updateActivity("3:00 Minutes Left!");
			}
			else break;

			for(int min = 60; min >= 1; min--)
			{
				if(x == 1)
				{
					TimeUnit.SECONDS.sleep(1);
				}
				else break;
			}

			if(x == 1)
			{
				bot.updateActivity("2:00 Minutes Left!");
			}
			else break;

			for(int min = 60; min >= 1; min--)
			{
				if(x == 1)
				{
					TimeUnit.SECONDS.sleep(1);
				}
				else break;
			}
			if(x == 1)
			{
				bot.updateActivity("1:00 Minute Left!");
			}

			for(int sec = 57; sec > 3; sec--)
			{
				if(x == 1)
				{
					TimeUnit.SECONDS.sleep(1);
				}
				else break;
			}

			for(int sec = 3; sec >= 1; sec--)
			{
				if(x == 1)
				{
					bot.updateActivity(sec + "!");
					TimeUnit.SECONDS.sleep(1);
				}
				else break;
			}
			if(x == 1)
			{
				//ending message
				bot.updateActivity("Time!");
				bot.getChannelById("501973192605433880").get()
				.asTextChannel().get().sendMessage("Time! Moving all players back to the Pregame Lobby...");

				//moves all to PreGame
				for(User u : bot.getRoleById("501973192605433877").get().getUsers())
				{
					if(u.isBot() == false)
					{
						u.move(bot.getServerVoiceChannelById("501973192605433883").get());
					}
				}
				x = 0;
			}
			else break;
		}
	}
	//changes messsage of timer and updates to skull emoji for dead team and starts timer
	
	public void onReactionAdd(ReactionAddEvent event) {

		if(event.getEmoji().equalsEmoji("â�±") && event.getUser().isBot() == false)
		{
			event.removeAllReactionsFromMessage();
			event.editMessage("(Team dead?)");
			event.addReactionsToMessage("ðŸ’€");
			
			event.getApi().updateActivity("5:00");
			x = 1;
		}
	}

}





