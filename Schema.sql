CREATE TABLE Users (
	User_Id INT(6) AUTO_INCREMENT,
	Email VARCHAR(50) NOT NULL UNIQUE,
	Password VARCHAR(50) NOT NULL,
	Role VARCHAR(10) ,
	Signup_Date TIMESTAMP ,

	KEY (User_Id),
	CONSTRAINT PRIMARY KEY(User_Id)
);


CREATE TABLE posts (
	Post_Id INT(6) AUTO_INCREMENT,
	Post_Title VARCHAR(20) ,
	Text VARCHAR(500) ,
	User_Id INT(6) ,
	Post_Date TIMESTAMP ,

	KEY (Post_Id),
	CONSTRAINT PRIMARY KEY(Post_Id),
	CONSTRAINT FOREIGN KEY (User_Id) REFERENCES Users(User_Id)
);


CREATE TABLE Comments (
	Comment_Id INT(6) AUTO_INCREMENT PRIMARY KEY,
	Description VARCHAR(500) ,
	Comment_Date TIMESTAMP ,
	User_Id INT(6) ,
	Post_Id INT(6) ,

	KEY (Comment_Id),
	FOREIGN KEY (Post_Id) REFERENCES Posts(Post_Id) ,
	FOREIGN KEY (User_Id) REFERENCES Users(User_Id)
);


CREATE TABLE Sessions (
	Session_Id INT(6)  AUTO_INCREMENT PRIMARY KEY,
	Email VARCHAR(50) ,
	Session_ts TIMESTAMP,

	KEY (Session_Id),
	FOREIGN KEY (Email) REFERENCES Users(Email)
);
