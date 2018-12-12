CREATE TABLE ToDoTasks (
  TaskID int NOT NULL AUTO_INCREMENT,
  TaskName varchar(40),
  DateTimeCreated datetime DEFAULT NULL,
  TaskStatus int DEFAULT NULL,
  TaskPriority int DEFAULT NULL,
  DateTimeDeadline datetime DEFAULT NULL,
  TaskDetail text,
  TaskCategory text,
	PRIMARY KEY ( TaskID ) 
);
