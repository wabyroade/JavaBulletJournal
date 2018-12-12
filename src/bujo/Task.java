package bujo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Date;

import java.sql.*;
import java.text.SimpleDateFormat;

public class Task {
  private int taskID;


  private String taskName;
  private String taskDetail;
  private java.sql.Date taskCreated;
  private java.sql.Date taskDeadline;
  private int taskStatus;
  private int taskPriority;

  // New Task Constructor
  public Task(String taskName, String taskDetail, int taskStatus, int taskPriority, Date taskDeadline) {
    this.taskName = taskName;
    this.taskDetail = taskDetail;
    this.taskStatus = taskStatus;
    this.taskPriority = taskPriority;
    this.taskDeadline = taskDeadline;
    long millis=System.currentTimeMillis();
    this.taskCreated = new java.sql.Date(millis);
  }

  // Task from Database Constructor
  public Task(int taskID, String taskName, String taskDetail, int taskStatus, int taskPriority, Date taskCreated,
              Date taskDeadline) {
    this.taskID = taskID;
    this.taskName = taskName;
    this.taskDetail = taskDetail;
    this.taskStatus = taskStatus;
    this.taskPriority = taskPriority;
    this.taskCreated = taskCreated;
    this.taskDeadline = taskDeadline;
  }

  public static ObservableList<Task> getTaskList() {
    ObservableList<Task> taskList = FXCollections.observableArrayList();
    final String DATABASE_URL = "jdbc:derby:ToDoDB";
    final String SELECT_QUERY = "SELECT * FROM ToDoTasks";

    // use try-with-resources to connect to and query the database
    try (
            Connection connection = DriverManager.getConnection(
                    DATABASE_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY))
    {
      ResultSetMetaData metaData = resultSet.getMetaData();
      int numberOfColumns = metaData.getColumnCount();
      while (resultSet.next()) {
        // read the result set and instantiate an object for each user
        Task tempTask;
        tempTask = new Task(
                resultSet.getInt("TASKID"),
                resultSet.getString("TASKNAME"),
                resultSet.getString("TASKDETAIL"),
                resultSet.getInt("TASKSTATUS"),
                resultSet.getInt("TASKPRIORITY"),
                resultSet.getDate("TASKCREATED"),
                resultSet.getDate("TASKDEADLINE")
        );

        taskList.add(tempTask);
      }
    } // AutoCloseable objects' close methods are called now
    catch (SQLException sqlException)
    {
      sqlException.printStackTrace();
    }
    return taskList;
  }

  public boolean insertIntoDB() {
    String taskCreatedFormatted = new SimpleDateFormat("dd/MM/yyyy").format( taskCreated );
    String taskDeadlineFormatted = new SimpleDateFormat("dd/MM/yyyy").format( taskDeadline );
    final String DATABASE_URL = "jdbc:derby:ToDoDB";
    final String INSERT_QUERY = "insert into ToDoTasks (TASKNAME, TASKDETAIL, TASKSTATUS, "
          + "TASKPRIORITY) VALUES('" + taskName + "', '" + taskDetail + "', "
            + taskStatus + ", " + taskStatus + ")";
    System.out.println(INSERT_QUERY);

    // use try-with-resources to connect to and query the database
    try {
      Connection connection = DriverManager.getConnection(
              DATABASE_URL);
      Statement statement = connection.createStatement();
      int resultSet = statement.executeUpdate(INSERT_QUERY);
      return true;
    }
    catch  (SQLException sqlException) {
      sqlException.printStackTrace();
    }
    return false;
  }

  public void deleteTask() {
    final String DATABASE_URL = "jdbc:derby:ToDoDB";
    final String DELETE_QUERY = "delete from ToDoTasks where TASKID = " + this.taskID;
    System.out.println(DELETE_QUERY);

    // use try-with-resources to connect to and query the database
    try {
      Connection connection = DriverManager.getConnection(
              DATABASE_URL);
      Statement statement = connection.createStatement();
      int resultSet = statement.executeUpdate(DELETE_QUERY);
    }
    catch  (SQLException sqlException) {
      sqlException.printStackTrace();
    }
  }

  public int getTaskID() {
    return taskID;
  }

  public void setTaskID(int taskID) {
    this.taskID = taskID;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public String getTaskDetail() {
    return taskDetail;
  }

  public void setTaskDetail(String taskDetail) {
    this.taskDetail = taskDetail;
  }

  public Date getTaskCreated() {
    return taskCreated;
  }

  public void setTaskCreated(Date taskCreated) {
    this.taskCreated = taskCreated;
  }

  public Date getTaskDeadline() {
    return taskDeadline;
  }

  public void setTaskDeadline(Date taskDeadline) {
    this.taskDeadline = taskDeadline;
  }

  public int getTaskStatus() {
    return taskStatus;
  }

  public void setTaskStatus(int taskStatus) {
    this.taskStatus = taskStatus;
  }

  public int getTaskPriority() {
    return taskPriority;
  }

  public void setTaskPriority(int taskPriority) {
    this.taskPriority = taskPriority;
  }


}

