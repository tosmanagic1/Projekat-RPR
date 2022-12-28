package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Users;
import ba.unsa.etf.rpr.exceptions.FootballPitchException;

import java.sql.*;
import java.util.*;

public class UsersDaoSQLImpl extends AbstractDao<Users> implements UsersDao {

    private Connection connection;

    public UsersDaoSQLImpl (){
        super("Users");
    }


    @Override
    public Users row2object(ResultSet rs) throws FootballPitchException {
        try {
            Users user = new Users();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setNumber(rs.getInt("number"));
            user.setEmail(rs.getString("email"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        } catch (SQLException e) {
            throw new FootballPitchException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Users object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("name", object.getName());
        row.put("number", object.getNumber());
        row.put("email", object.getEmail());
        row.put("username", object.getUsername());
        row.put("password", object.getPassword());
        return row;
    }
}
