package MyPracticals.Practical2;

import FriendsCB.FriendsServiceService;
import FriendsCB.FriendsInterface;
import FriendsCB.MyListofFriends;
import FriendsCB.Friend;
//import friendsCB.GetFriendsResponse;
import java.util.List;

class FriendsClient {
	
	public static void main(String args[]) throws Exception {
		FriendsServiceService service = new FriendsServiceService();
		FriendsInterface port = service.getFriendsServicePort();
		
		/* wrapped document style - code begins */
		List<MyListofFriends> AllListofFriends = port.getAllListofFriends();
		/* wrapped document style - code ends */
		
		/* unwrapped document style - code begins*/
		//GetTeamsResponse response = port.getTeams(null); 
		//List<Team> teams = response.getReturn();
		/* *************** */
		
		for (MyListofFriends friendlist: AllListofFriends){
			System.out.println("Friends List name: " + 
			friendlist.getName() + " (roster count: " + 
			friendlist.getRosterCount() + ")");            
			
			for (Friend myfriend : friendlist.getMyFriends())                
				System.out.println("  Friend: " + myfriend.getNickname());        
		}
	}
}