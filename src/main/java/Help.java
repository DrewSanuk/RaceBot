import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageDecoration;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Help implements MessageCreateListener {

	MessageDecoration bold = MessageDecoration.BOLD;
	MessageDecoration italics = MessageDecoration.ITALICS;

	public void onMessageCreate(MessageCreateEvent event) {

		Message m = event.getMessage();

		
		if(m.getContent().equalsIgnoreCase("!help"))
		{
			new MessageBuilder()
			.append("+ [points]", bold).appendNewLine()
			.append("-Adds [points] to your team's score.", italics).appendNewLine()
			.append("!clear*", bold).appendNewLine()
			.append("-Removes all roles.", italics).appendNewLine()
			.append("!createTeam [number]", bold).appendNewLine()
			.append("-Creates a new team with the number given.", italics).appendNewLine()
			.append("!join [team number]", bold).appendNewLine()
			.append("-Adds you to the specified team.", italics).appendNewLine()
			.append("!reset*", bold).appendNewLine()
			.append("-Resets the scoreboard.", italics).appendNewLine()
			.append("!restart", bold).appendNewLine()
			.append("-Restarts the game.", italics).appendNewLine()
			.append("!scoreboard", bold).appendNewLine()
			.append("-Displays the scoreboard.", italics).appendNewLine()
			.append("!start*", bold).appendNewLine()
			.append("-Starts the game.", italics).appendNewLine()
			.append("!stop*", bold).appendNewLine()
			.append("-Stops the timer.", italics).appendNewLine().appendNewLine()
			.append("*Requires OP", MessageDecoration.UNDERLINE)
			.send(event.getChannel());

		}
	}

}
