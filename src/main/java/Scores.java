import java.awt.Color;
import java.util.ArrayList;

import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Scores implements MessageCreateListener{

	public static ArrayList<Integer> pointsList = new ArrayList<Integer>(1);

	
	public void onMessageCreate(MessageCreateEvent event) {

		Message m = event.getMessage();

		HasRole hr = new HasRole();

		String server = event.getServer().get().getIdAsString();
		User user = m.getUserAuthor().get();

		if(m.getChannel().toString().contains("game-chat"))
		{

			if(m.getContent().equalsIgnoreCase("!reset") && hr.isOP(user, server))
			{
				for(int x = 0; x < pointsList.size(); x++)
				{
					pointsList.set(x, 0);
				}
				m.getChannel().sendMessage("Scoreboard reset!");

			}

			else if(m.getContent().length() >= 3 && m.getContent().length() <=4)
			{
				if(m.getContent().substring(0, 2).equalsIgnoreCase("+ "))
				{
					String points = m.getContent().substring(2, m.getContent().length());
					int intPoints = Integer.parseInt(points);

					for(Role r : m.getUserAuthor().get().getRoles(event.getServer().get()))
					{
						if(r.getName().contains("Team"))
						{
							int teamNum = Integer.parseInt(r.getName().substring(5, r.getName().length()));

							pointsList.set(teamNum, pointsList.get(teamNum) + intPoints);

							m.getChannel().sendMessage("Added " + intPoints + " points to Team " + teamNum + ".");
						}
					}
				}
			}

			else if(m.getContent().equalsIgnoreCase("!scoreboard"))
			{
				EmbedBuilder eb = new EmbedBuilder();

				eb.setTitle("Scoreboard").setColor(Color.magenta);
				
				
				//int team = pointsList.indexOf(Collections.max(pointsList));
				
				
				int teamNum = 0;
				for(int x : pointsList)
				{
					if(teamNum > 0)
					{
						eb.addInlineField("Team " + teamNum + ":", x + "");
					}
					
					teamNum++;
				}

				new MessageBuilder().setEmbed(eb).send(event.getChannel());

			}

		}

	}

}
