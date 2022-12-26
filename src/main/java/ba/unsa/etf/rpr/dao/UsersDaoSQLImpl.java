package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Admins;
import ba.unsa.etf.rpr.domain.Users;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UsersDaoSQLImpl implements UsersDao {

    private Connection connection;

    public UsersDaoSQLImpl (){
        try {
            Properties p = new Properties();
            InputStream is = new FileInputStream("conf/database.properties");
            p.load(is);

            this.connection = DriverManager.getConnection(p.getProperty("db.url"), p.getProperty("db.username"), p.getProperty("db.password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public Users getById(int id) {
        String query = "SELECT * FROM Users WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setNumber(rs.getInt("number"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                rs.close();
                return user;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }

    private int getMaxId(){
        int id=1;
        try {
            PreparedStatement statement = this.connection.prepareStatement("SELECT MAX(id) FROM Users");
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                id = rs.getInt(1) + 1;
                rs.close();
                return id;
            }
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public Users add(Users item) {
        String insert = "INSERT INTO Users (id, name, number, email, username, password)" + " VALUES (?, ?, ?, ?, ?, ?)";
        int id = getMaxId();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert);

            item.setId(id);
            stmt.setInt(1, item.getId());
            stmt.setString(2, item.getName());
            stmt.setInt(3, item.getNumber());
            stmt.setString(4,item.getEmail());
            stmt.setString(5,item.getUsername());
            stmt.setString(6, item.getPassword());
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Users update(Users item) {
        String insert = "UPDATE Users SET name = ?, number = ?, email = ?, username = ?, password = ? WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getName());
            stmt.setObject(2, item.getNumber());
            stmt.setObject(3, item.getEmail());
            stmt.setObject(4, item.getUsername());
            stmt.setObject(5, item.getPassword());
            stmt.setObject(6, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM Users WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Users> getAll() {
        String query = "SELECT * FROM Users";
        List<Users> users = new ArrayList<Users>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setNumber(rs.getInt("number"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return users;
    }
}
