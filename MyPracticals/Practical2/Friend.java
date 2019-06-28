package MyPracticals.Practical2;

public class Friend {
	private String friendname;
	private String nickname;
	
	public Friend(){}
	public Friend(String friendname, String nickname) {
		setName(friendname);
		setNickname(nickname);
	}
	
	public void setName(String friendname){this.friendname = friendname;}
	public String getName(){return friendname;}
	public void setNickname(String nickname){this.nickname = nickname;}
	public String getNickname(){return nickname;}
}
