package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.FootballPitches;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FootballPitchesDaoImpl implements FootballPitchesDao {

    private Connection connection;

    public  FootballPitchesDaoImpl () {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7583497", "sql7583497", "5YtG69q1pp");
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

    @Override
    public FootballPitches add (FootballPitches item) {
        String insert = "INSERT INTO FootballPitches VALUES(id, name, address)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setId(rs.getInt(1)); //set id to return it back

            stmt.setString(1, item.getName());

            stmt.setString(1, item.getAddress());
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
            stmt.setObject(1, item.getId());
            stmt.setObject(2, item.getName());
            stmt.setObject(3, item.getAddress());
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
