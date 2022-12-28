package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.FootballPitches;
import ba.unsa.etf.rpr.exceptions.FootballPitchException;

import java.sql.*;
import java.util.*;

public class FootballPitchesDaoSQLImpl extends AbstractDao<FootballPitches> implements FootballPitchesDao {

    private Connection connection;

    public FootballPitchesDaoSQLImpl() {
        super("FootballPitches");
    }

    @Override
    public FootballPitches row2object(ResultSet rs) throws FootballPitchException {
        try {
            FootballPitches footballPitch = new FootballPitches();
            footballPitch.setId(rs.getInt("id"));
            footballPitch.setName(rs.getString("name"));
            footballPitch.setAddress(rs.getString("address"));
            return footballPitch;

        } catch (SQLException e) {
            throw new FootballPitchException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(FootballPitches object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("name", object.getName());
        row.put("address", object.getAddress());
        return row;
    }
}
