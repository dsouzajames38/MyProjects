package MyPracticals.Practical2;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;

/* Both SIB and SEI is not implemented together */
@WebService(endpointInterface="MyPracticals.Practical2.FriendsInterface")
public class FriendsService implements FriendsInterface{
	private FriendsUtility utils;
	
	public FriendsService(){
		utils = new FriendsUtility();
		utils.make_test_friends();
	}
	
	public MyListofFriends getListofFriends(String listname) { return utils.getListofFriends(listname); } 
	public List<MyListofFriends> getAllListofFriends() {return utils.getAllListofFriends(); }

}