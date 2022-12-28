package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Admins;
import ba.unsa.etf.rpr.domain.Appointments;
import ba.unsa.etf.rpr.domain.Users;
import ba.unsa.etf.rpr.exceptions.FootballPitchException;

import java.sql.*;
import java.util.*;

/**
 * MySQL's implementation of the DAO
 * @author Tarik Osmanagic
 */
public class AppointmentsDaoSQLImpl extends AbstractDao<Appointments> implements AppointmentsDao{

    private Connection connection;

    public AppointmentsDaoSQLImpl (){
        super("Appointments");
    }

    @Override
    public Appointments row2object(ResultSet rs) throws FootballPitchException {
        try {
            Appointments appointment = new Appointments();
            appointment.setId(rs.getInt("id"));
            appointment.setAdmin(DaoFactory.adminDao().getById(rs.getInt("idAdmin")));
            appointment.setUser(DaoFactory.userDao().getById(rs.getInt("idUser")));
            appointment.setCreated(rs.getDate("date"));
            appointment.setTypeOfPitch(rs.getString("typeOfPitch"));
            return appointment;
        } catch (SQLException e) {
            throw new FootballPitchException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Appointments object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("idAdmin", object.getAdmin().getId());
        row.put("idUser", object.getUser().getId());
        row.put("date", object.getCreated());
        row.put("typeOfPitch", object.getTypeOfPitch());
        return row;
    }


    /**
     * @param a admin search string for appointments
     * @return list of appointments
     */
    @Override
    public List<Appointments> searchByAdmin (Admins a) throws FootballPitchException {
        String query = "SELECT * FROM Appointments WHERE idAdmin = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, a.getId());
            ResultSet rs = stmt.executeQuery();
            ArrayList<Appointments> appointmentsList = new ArrayList<>();
            while(rs.next()) {
                appointmentsList.add(row2object(rs));
            }
            return appointmentsList;
        } catch (SQLException e) {
            throw new FootballPitchException(e.getMessage(), e);
        }
    }

    /**
     * @param u user search string for appointments
     * @return list of appointments
     */
    @Override
    public List<Appointments> searchByUser (Users u) throws FootballPitchException {
        String query = "SELECT * FROM Appointments WHERE idUser = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, u.getId());
            ResultSet rs = stmt.executeQuery();
            ArrayList<Appointments> appointmentsList = new ArrayList<>();
            while(rs.next()) {
                appointmentsList.add(row2object(rs));
            }
            return appointmentsList;
        } catch (SQLException e) {
            throw new FootballPitchException(e.getMessage(), e);
        }
    }
}
