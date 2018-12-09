//import java.util.ArrayList;

import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class TeamAssign implements MessageCreateListener
{

	public void onMessageCreate(MessageCreateEvent event) {

		Message m = event.getMessage();

		HasRole hr = new HasRole();
		
		Server s = event.getServer().get();

		String server = event.getServer().get().getIdAsString();
		User user = m.getUserAuthor().get();


		String everybody = "501973192605433877";

		if(m.getChannel().toString().contains("game-chat"))
		{
			//clears roles of all users
			if(m.getContent().equalsIgnoreCase("!clear") && hr.isOP(user, server))
			{

				m.getChannel().sendMessage("Clearing Teams..");
				for(User u : event.getApi().getRoleById(everybody).get().getUsers())
				{
					if(u.isBot() == false)
					{
						for(Role r : u.getRoles(s))
						{
							if(r.getName().equals("OP") == false)
							{
								u.removeRole(r);
							}
						}
					}
				}
			}

			//adds player to team 1 
			else if(m.getContent().contains("!join ") && m.getAuthor().isUser() == true)
			{
				int exists = 0;

				String teamNum = m.getContent().substring(6, m.getContent().length());
				for(Role r : m.getUserAuthor().get().getRoles(event.getServer().get()))
				{
					if(r.getName().equals("OP") == false)
					{
						m.getUserAuthor().get().removeRole(r);
					}
				}

				for(Role r : event.getApi().getRoles())
				{

					if(r.getName().contains(teamNum))
					{
						m.getUserAuthor().get().addRole(r);

						m.getChannel().sendMessage("Added " + m.getUserAuthor().get()
								.getDisplayName(event.getServer().get()) + " to Team " + teamNum + "!");

						exists = 1;
					}

				}

				if(exists == 0)
				{
					m.getChannel().sendMessage("That team does not exist!");
				}

			}

		}

	}
}