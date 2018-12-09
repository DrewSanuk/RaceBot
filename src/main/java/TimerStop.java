import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.event.message.reaction.ReactionAddEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.listener.message.reaction.ReactionAddListener;

public class TimerStop implements MessageCreateListener, ReactionAddListener{

	
	public void onMessageCreate(MessageCreateEvent event) {

		Message m = event.getMessage();
		HasRole hr = new HasRole();

		String server = event.getServer().get().getIdAsString();
		User user = m.getUserAuthor().get();

		if(m.getChannel().toString().contains("game-chat"))
		{

			if(m.getContent().equalsIgnoreCase("!stop") && hr.isOP(user, server))
			{
				//breaks timer update method
				Timer.x = 0;
				event.getChannel().sendMessage("Timer stopped!");

				event.getApi().updateActivity(ActivityType.WATCHING, "Kill Races!");
			}
		}
	}

	private int teamsDead = 1;

	public void onReactionAdd(ReactionAddEvent event)
	{
		int numTeams = 0;

		for(Role team : event.getApi().getRoles())
		{
			if(team.getName().contains("Team"))
			{
				numTeams++;
			}
		}
		if(event.getEmoji().equalsEmoji("💀") && event.getUser().isBot() == false)
		{
			teamsDead++;

			if(teamsDead == numTeams)
			{
				//breaks timer update method
				Timer.x = 0;
				event.getApi().updateActivity(ActivityType.WATCHING, "Kill Races!");

				event.removeAllReactionsFromMessage();
				event.deleteMessage();

				event.getChannel().sendMessage("All other teams died! Moving all players to the Pregame Lobby...");

				for(User u : event.getApi().getRoleById("501973192605433877").get().getUsers())
				{
					if(u.isBot() == false)
					{
						u.move(event.getApi().getServerVoiceChannelById("501973192605433883").get());
					}

				}

			}
		}
	}

}


