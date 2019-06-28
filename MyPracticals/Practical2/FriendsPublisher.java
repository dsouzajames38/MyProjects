package MyPracticals.Practical2;
import javax.xml.ws.Endpoint;
import java.util.List;

/* The program will attempt to custom bind the wsdl into unwrapped version 
using custom.xml by using wsimport command */
class FriendsPublisher {
	public static void main(String[] args){
		try
		{
		int port = 8888;
		String url = "http://localhost:" + port + "/friends";
		System.out.println("Publishing All List of Friends on url " + url );
		
		FriendsService friendsservice = new FriendsService();
		
		System.out.println("Friends Service object created");
		
		Endpoint.publish(url, friendsservice);
				
		//testFriendsService();
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	
	}
	
	public static void testFriendsService()
	{
		FriendsService friendsservice = new FriendsService();
		List<MyListofFriends> AllListofFriends = friendsservice.getAllListofFriends();
		
		for (MyListofFriends friendlist : AllListofFriends) 
		{            
			System.out.println("Friend List name: " + friendlist.getName() + " (roster count: " + friendlist.getRosterCount() + ")");            
			for (Friend myfriend : friendlist.getMyFriends())                
				System.out.println("  Friend: " + myfriend.getNickname());        
		}
	}
}