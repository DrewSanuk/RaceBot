import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.user.UserStatus;

public class App{

	
	public static void main(String[] args) throws InterruptedException
	{
		
		int run = 0;
		Timer t = new Timer();
		
		String tok = System.getenv("token");
		//creates a bot that logs into Token
		DiscordApi bot = new DiscordApiBuilder().setToken(tok).login().join();
		
		System.out.println("Success!");
		
		
		//listeners
		bot.addListener(new PingPong());
		bot.addListener(new TeamAssign());
		bot.addListener(new Starter());
		bot.addListener(new Help());
		bot.addListener(new Timer());
		bot.addListener(new Scores());
		bot.addListener(new TimerStop());
		bot.addListener(new Countdown());
		bot.addListener(new TeamCreate());
		
		Scores.pointsList.add(0);
		
		//System.out.println(bot.createBotInvite());

		bot.updateActivity(ActivityType.WATCHING, "Kill Races!");
		bot.updateStatus(UserStatus.DO_NOT_DISTURB);
		
		//tests for timer running
		while(run == 0)
		{
			if(bot.getActivity().get().getName().equals("5:00"))
			{
				t.update(bot);
			}
		}
		
	}
}