import java.awt.Color;

import org.javacord.api.entity.channel.ChannelCategory;
import org.javacord.api.entity.channel.ServerVoiceChannelBuilder;
import org.javacord.api.entity.channel.VoiceChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.permission.RoleBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class TeamCreate implements MessageCreateListener{

	
	public void onMessageCreate(MessageCreateEvent event) {

		Message m = event.getMessage();
		ChannelCategory category = event.getApi().getChannelCategoryById("501973192605433882").get();
		
		
		if(m.getContent().contains("!createTeam"))
		{
			String teamNum = m.getContent().substring(12);
			Color color = new Color((float)(Math.random()), (float)(Math.random()), (float)(Math.random()));
			//String color = m.getContent().substring(15, m.getContent().length());
			
			ServerVoiceChannelBuilder vcb = new ServerVoiceChannelBuilder(event.getServer().get());
			
			vcb.setName("Team " + teamNum);
			vcb.setCategory(category);
			
			vcb.create().join();
			
			RoleBuilder rb = new RoleBuilder(event.getServer().get());
			
			rb.setName("Team " + teamNum);
			rb.setColor(color);
			rb.setDisplaySeparately(true);
			rb.create().join();
			
			Scores.pointsList.add(0);;
			
			event.getChannel().sendMessage("Team " + teamNum + " created!");
		}
		
		else if(m.getContent().equalsIgnoreCase("!restart"))
		{
			event.getChannel().sendMessage("Restarting...");
			
			for(VoiceChannel vc : event.getApi().getVoiceChannels())
			{
				if((vc.getIdAsString().equals("501973192605433883") == false) && (vc.getIdAsString().equals("515670231260069888") == false))
				{
					vc.asServerVoiceChannel().get().delete();
				}
			}
			for(Role r : event.getApi().getRoles())
			{
				if((r.getName().equals("OP") == false) && (r.getName().equals("Bot") == false))
				{
					r.delete();
				}
				
			}
			
		}

	}

}
