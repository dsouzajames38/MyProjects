package MyPracticals.Practical2;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class FriendsUtility{
	private Map<String, MyListofFriends> mylistoffriends_map;
	
	public FriendsUtility(){
		mylistoffriends_map = new HashMap<String, MyListofFriends>();
	}
	
	public MyListofFriends getListofFriends(String listname){return mylistoffriends_map.get(listname);}
	
	public List<MyListofFriends> getAllListofFriends(){
		
		List<MyListofFriends> list = new ArrayList<MyListofFriends>();
		Set<String> keys = mylistoffriends_map.keySet();
		
		for(String key: keys)
			list.add(mylistoffriends_map.get(key));
		return list;
	}
	
	public void make_test_friends(){
		List<MyListofFriends> AllListofFriends = new ArrayList<MyListofFriends>();
		Friend chico = new Friend("Leonard Marx","Chico");
		Friend groucho = new Friend("Julius Marx","Groucho");
		Friend harpo = new Friend("Adolph Marx","Harpo");
		
		List<Friend> mb = new ArrayList<Friend>();
		mb.add(chico); mb.add(groucho); mb.add(harpo);
		
		MyListofFriends marx_brothers = new MyListofFriends("marx_brothers",mb);
		
		AllListofFriends.add(marx_brothers);
		
		store_allfriendlist(AllListofFriends);
	}
	
	private void store_allfriendlist(List<MyListofFriends> AllListofFriends){
		for(MyListofFriends friendlist: AllListofFriends)
			mylistoffriends_map.put(friendlist.getName(), friendlist);
	}
	
	
}