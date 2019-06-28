	DROP DATABASE IF EXISTS MyTwitter;
	CREATE DATABASE IF NOT EXISTS MyTwitter;
	
	USE MyTwitter;
	
	CREATE TABLE MyTweets(  
       ID INT NOT NULL AUTO_INCREMENT,  
       nProfileID INT NOT NULL,  
       CreateDateTime DATETIME ,  
	   Tweet VARCHAR(140),
       PRIMARY KEY ( ID )  
    );  
	
	CREATE TABLE Profile(  
       ID INT NOT NULL AUTO_INCREMENT,  
       ProfileName VARCHAR(100),
       CreateDateTime DATETIME ,  
	   PRIMARY KEY ( ID  )  
    );  
	
	CREATE TABLE Followers(  
       ID INT NOT NULL AUTO_INCREMENT,  
       WhoIsFollowingID INT NOT NULL, 
       WhomYouFollowingID INT NOT NULL,  
	   CreateDateTime DATETIME ,  
	   PRIMARY KEY ( ID  )  
    );  
	
	CREATE TRIGGER setcurrentdatetime1 BEFORE INSERT ON  MyTweets
	FOR EACH ROW 
	SET NEW.CreateDateTime = NOW();
	
	CREATE TRIGGER setcurrentdatetime2 BEFORE INSERT ON  Profile
	FOR EACH ROW 
	SET NEW.CreateDateTime = NOW();
	
	CREATE TRIGGER setcurrentdatetime3 BEFORE INSERT ON  Followers
	FOR EACH ROW 
	SET NEW.CreateDateTime = NOW();
	
	
	INSERT INTO Profile (ID, ProfileName) VALUES (1, "JAMES DSOUZA");
	INSERT INTO Profile (ID, ProfileName) VALUES (2, "ANTONIN DSOUZA");
	INSERT INTO Profile (ID, ProfileName) VALUES (3, "NIGEL DSOUZA");
		
	INSERT INTO MyTweets (ID, nProfileID,Tweet) VALUES (1,1,"I love computer science");
	INSERT INTO MyTweets (ID, nProfileID,Tweet) VALUES (2,1,"Rest services are excellent");
	INSERT INTO MyTweets (ID, nProfileID,Tweet) VALUES (3,2,"It was a good day");
	INSERT INTO MyTweets (ID, nProfileID,Tweet) VALUES (4,2,"It was a great match");
	INSERT INTO MyTweets (ID, nProfileID,Tweet) VALUES (5,3,"Hopefully I will get admission to medicals");
	
	INSERT INTO Followers (ID, WhoIsFollowingID, WhomYouFollowingID) VALUES (1,1,2);
	INSERT INTO Followers (ID, WhoIsFollowingID, WhomYouFollowingID) VALUES (2,1,3);
	INSERT INTO Followers (ID, WhoIsFollowingID, WhomYouFollowingID) VALUES (3,2,1);
	INSERT INTO Followers (ID, WhoIsFollowingID, WhomYouFollowingID) VALUES (4,3,1);
	
	SELECT * FROM PROFILE;
	SELECT * FROM MYTWEETS;
	SELECT * FROM FOLLOWERS;
	