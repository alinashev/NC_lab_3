package lab_3.models;

import lab_3.models.pojo.Project;
import lab_3.models.pojo.Ticket;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private final String url = "jdbc:postgresql://localhost/bug_tracker";
    private final String user = "postgres";
    private final String password = "password1";
    private static Database database;
    private Connection conn;

    private Database() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean selectAllUsers(String username,String password) throws SQLException{
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT password FROM \"LAB3_ASH_User\" WHERE username=?");
        preparedStatement.setString(1,username);
        ResultSet set = preparedStatement.executeQuery();
        String pass = null;
        while (set.next()){
            pass = set.getString(1);
        }
        return pass.equals(password);
    }

    public void registration(String username, String email, String password) throws SQLException{

        PreparedStatement validationUser = conn.prepareStatement("SELECT COUNT (id) FROM \"LAB3_ASH_User\" WHERE \"username\" = ? OR \"email\" = ?");
        validationUser.setString(1,username);
        validationUser.setString(2,email);
        ResultSet resultSet = validationUser.executeQuery();
        while (resultSet.next()){
            if(resultSet.getInt(1) == 0){
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO \"LAB3_ASH_User\" VALUES ((SELECT max(\"id\") FROM \"LAB3_ASH_User\" )+1,?, ?, ?)");
                preparedStatement.setString(1,username);
                preparedStatement.setString(2,email);
                preparedStatement.setString(3,password);
                preparedStatement.executeUpdate();
            }else {
                System.out.println("ERROR");
            }
        }
    }

    public static Database database(){
        if(database == null){
            database = new Database();
        }
        return database;
    }

    public void createNewTicket(String bugStatus, String severity, String priority,
                                String name, String summary, String createdDate, String expected, String actual,
                                String author, int projectId) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement
                ("INSERT INTO \"LAB3_ASH_Ticket\" VALUES ((SELECT max(\"ticket_id\") FROM \"LAB3_ASH_Ticket\")+1,?,?,?,?,?,?,?,?,?,?)");
        preparedStatement.setString(1,bugStatus);
        preparedStatement.setString(2,severity);
        preparedStatement.setString(3,priority);
        preparedStatement.setString(4,name);
        preparedStatement.setString(5,summary);
        preparedStatement.setString(6,createdDate);
        preparedStatement.setString(7,expected);
        preparedStatement.setString(8,actual);
        preparedStatement.setString(9,author);
        preparedStatement.setInt(10,projectId);
        preparedStatement.executeUpdate();
    }

    public ArrayList<Ticket> getAllTickets() throws SQLException {
        ArrayList<Ticket> list = new ArrayList<>();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM \"LAB3_ASH_Ticket\"");
        ResultSet set = preparedStatement.executeQuery();
        while (set.next()){
            list.add(new Ticket(
                    set.getInt(1),set.getString(2),set.getString(3),
                    set.getString(4),set.getString(5),set.getString(6),
                    set.getString(7),set.getString(8),set.getString(9),
                    set.getString(10), set.getInt(11)));
        }
        return list;
    }

    public Ticket getCurrentTicket(String ticketId) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM \"LAB3_ASH_Ticket\" WHERE  \"ticket_id\" = ?");
        preparedStatement.setInt(1,Integer.valueOf(ticketId));
        ResultSet set = preparedStatement.executeQuery();
        while (set.next()) {
            return new Ticket(
                    set.getInt(1), set.getString(2), set.getString(3),
                    set.getString(4), set.getString(5), set.getString(6),
                    set.getString(7), set.getString(8), set.getString(9),
                    set.getString(10), set.getInt(11));
        }
        return null;
    }

    public void deleteTicket(String ticketId) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM \"LAB3_ASH_Ticket\" WHERE \"ticket_id\" = ?");
        preparedStatement.setInt(1,Integer.valueOf(ticketId));
        preparedStatement.executeUpdate();
    }

    public ArrayList<Ticket> searchTickets(String value) throws SQLException {
        ArrayList<Ticket> list = new ArrayList<>();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM \"LAB3_ASH_Ticket\" WHERE \"name\" ILIKE ?");
        preparedStatement.setString(1,"%"+value+"%");
        ResultSet set = preparedStatement.executeQuery();
        while (set.next()){
            list.add(new Ticket(
                    set.getInt(1),set.getString(2),set.getString(3),
                    set.getString(4),set.getString(5),set.getString(6),
                    set.getString(7),set.getString(8),set.getString(9),
                    set.getString(10), set.getInt(11)));
        }
        System.out.println(preparedStatement.toString());
        System.out.println(list);
        return list;
    }

    public Ticket searchTicket(String value) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM \"LAB3_ASH_Ticket\" WHERE \"name\" ILIKE ?");
        preparedStatement.setString(1,"%"+value+"%");
        ResultSet set = preparedStatement.executeQuery();
        while (set.next()){
            return new Ticket(
                    set.getInt(1),set.getString(2),set.getString(3),
                    set.getString(4),set.getString(5),set.getString(6),
                    set.getString(7),set.getString(8),set.getString(9),
                    set.getString(10), set.getInt(11));
        }
        return null;
    }

    public void createNewProject(String projectName) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement
                ("INSERT INTO \"LAB3_ASH_Project\" VALUES ((SELECT max(\"project_id\") FROM FROM \"LAB3_ASH_Project\" )+1,?)");
        preparedStatement.setString(1,projectName);
        preparedStatement.executeUpdate();
    }

    public ArrayList<Project> getAllProject() throws SQLException {
        ArrayList<Project> list = new ArrayList<>();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM \"LAB3_ASH_Project\"");
        ResultSet set = preparedStatement.executeQuery();
        while (set.next()){
            list.add(new Project(
                    set.getInt(1),set.getString(2)));
        }
        return list;
    }

    public Project getCurrentProject(String projectId) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM \"LAB3_ASH_Project\" WHERE  \"project_id\" = ?");
        preparedStatement.setInt(1,Integer.valueOf(projectId));
        ResultSet set = preparedStatement.executeQuery();
        while (set.next()) {
            return new Project(
                    set.getInt(1), set.getString(2));
        }
        return null;
    }

    public void deleteProject(String projectId) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM \"LAB3_ASH_Project\" WHERE \"project_id\" = ?");
        preparedStatement.setInt(1,Integer.valueOf(projectId));
        preparedStatement.executeUpdate();
    }

    public ArrayList<Project> searchProject(String value) throws SQLException {
        ArrayList<Project> list = new ArrayList<>();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM \"LAB3_ASH_Project\" WHERE \"project_name\" ILIKE ?");
        preparedStatement.setString(1,"%"+value+"%");
        ResultSet set = preparedStatement.executeQuery();
        while (set.next()){
            list.add(new Project(
                    set.getInt(1),set.getString(2)));
        }
        System.out.println(preparedStatement.toString());
        System.out.println(list);
        return list;
    }
}
