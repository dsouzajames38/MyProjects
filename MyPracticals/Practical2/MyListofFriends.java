package MyPracticals.Practical2;

import java.util.List;
public class MyListofFriends {
	private List<Friend> friends;
	private String friendlistname;
		
	public MyListofFriends(){}
	public MyListofFriends(String friendlistname, List<Friend> friends) {
		
		setName(friendlistname);
		setMyFriends(friends);
	}
	
	public void setName(String friendlistname){this.friendlistname = friendlistname;};
	public String getName() {return friendlistname;}
	public void setMyFriends(List<Friend> friends){ this.friends = friends;}
	public List<Friend> getMyFriends(){ return friends ;}
	public void setRosterCount(int n){}
	public int getRosterCount() {return (friends == null)?0:friends.size();}
	
}