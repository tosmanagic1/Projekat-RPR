package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.FootballPitches;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FootballPitchesDaoSQLImpl implements FootballPitchesDao {

    private Connection connection;

    public FootballPitchesDaoSQLImpl() {
        try{
            Properties p = new Properties();
            InputStream is = new FileInputStream("conf/database.properties");
            p.load(is);

            this.connection = DriverManager.getConnection(p.getProperty("db.url"), p.getProperty("db.username"), p.getProperty("db.password"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public FootballPitches getById(int id) {
        String query = "SELECT * FROM FootballPitches WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                FootballPitches footballPitch = new FootballPitches();
                footballPitch.setId(rs.getInt("id"));
                footballPitch.setName(rs.getString("name"));
                footballPitch.setAddress(rs.getString("address"));
                rs.close();
                return footballPitch;
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
            PreparedStatement statement = this.connection.prepareStatement("SELECT MAX(id) FROM FootballPitches");
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
    public FootballPitches add (FootballPitches item) {
        String insert = "INSERT INTO FootballPitches (id, name, address)" + " VALUES (?, ?, ?)";
        int id = getMaxId();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert);

            item.setId(id);

            stmt.setInt(1,item.getId());

            stmt.setString(2, item.getName());

            stmt.setString(3, item.getAddress());
            stmt.executeUpdate();

            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public FootballPitches update(FootballPitches item) {
        String insert = "UPDATE FootballPitches SET name = ?, address = ? WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getName());
            stmt.setObject(2, item.getAddress());
            stmt.setObject(3, item.getId());

            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM FootballPitches WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<FootballPitches> getAll() {
        String query = "SELECT * FROM FootballPitches";
        List<FootballPitches> footballPitches = new ArrayList<FootballPitches>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                FootballPitches footballPitch = new FootballPitches();
                footballPitch.setId(rs.getInt("id"));
                footballPitch.setName(rs.getString("name"));
                footballPitch.setAddress(rs.getString("address"));
                footballPitches.add(footballPitch);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return footballPitches;
    }
}