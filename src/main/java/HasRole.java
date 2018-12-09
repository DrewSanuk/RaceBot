import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.user.User;

public class HasRole {

	public boolean isOP(User u, String id)
	{
		boolean lean = false;
		
		for(Role r : u.getRoles(u.getApi().getServerById(id).get()))
		{
			if(r.getName().equals("OP"))
				lean = true;
		}
		
		return lean;
		
	}
}
