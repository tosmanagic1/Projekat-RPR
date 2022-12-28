package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Admins;
import ba.unsa.etf.rpr.domain.FootballPitches;
import ba.unsa.etf.rpr.exceptions.FootballPitchException;

import java.sql.*;
import java.util.*;


/**
 * MySQL's implementation of the DAO
 * @author Tarik Osmanagic
 */
public class AdminsDaoSQLImpl extends AbstractDao<Admins> implements AdminsDao {

    private Connection connection;

    public AdminsDaoSQLImpl (){
        super("Admins");
    }

    @Override
    public Admins row2object(ResultSet rs) throws FootballPitchException {
        try {
            Admins admin = new Admins();
            admin.setId(rs.getInt("id"));
            admin.setName(rs.getString("name"));
            admin.setNumber(rs.getInt("number"));
            admin.setFootballPitch(DaoFactory.footballPitchDao().getById(rs.getInt("idFootballPitch")));
            admin.setUsername(rs.getString("username"));
            admin.setPassword(rs.getString("password"));
            return admin;
        } catch (SQLException e) {
            throw new FootballPitchException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Admins object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("name", object.getName());
        row.put("number", object.getNumber());
        row.put("idFootballPitch", object.getFootballPitch().getId());
        row.put("username", object.getUsername());
        row.put("password", object.getPassword());
        return row;
    }

    /**
     * @param fp football pitch search string for admins
     * @return list of admins
     */
    @Override
    public List<Admins> searchByFootballPitch(FootballPitches fp) throws FootballPitchException {
        String query = "SELECT * FROM Admins WHERE idFootballPitch = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, fp.getId());
            ResultSet rs = stmt.executeQuery();
            ArrayList<Admins> adminsList = new ArrayList<>();
            while (rs.next()) {
                adminsList.add(row2object(rs));
            }
            return adminsList;
        } catch (SQLException e) {
            throw new FootballPitchException(e.getMessage(), e);
        }
    }
}
