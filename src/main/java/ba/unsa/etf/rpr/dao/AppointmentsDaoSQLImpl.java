package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Admins;
import ba.unsa.etf.rpr.domain.Appointments;
import ba.unsa.etf.rpr.domain.Users;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AppointmentsDaoSQLImpl implements AppointmentsDao{

    private Connection connection;

    public AppointmentsDaoSQLImpl (){
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
    public Appointments getById(int id) {
        String query = "SELECT * FROM Appointments WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Appointments appointment = new Appointments();
                appointment.setId(rs.getInt("id"));
                appointment.setAdmin(new AdminsDaoSQLImpl().getById(rs.getInt("id")));
                appointment.setUser(new UsersDaoSQLImpl().getById(rs.getInt("id")));
                appointment.setCreated(rs.getDate("date"));
                appointment.setTypeOfPitch(rs.getString("typeOfPitch"));
                rs.close();
                return appointment;
            } else {
                return null; // if there is no elements in the result set return null
            }
        } catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }

    private int getMaxId(){
        int id=1;
        try {
            PreparedStatement statement = this.connection.prepareStatement("SELECT MAX(id) FROM Appointments");
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
    public Appointments add(Appointments item) {
        String insert = "INSERT INTO Appointments (id, idAdmin, idUser, date, typeOfPitch)" + " VALUES (?, ?, ?, ?, ?)";
        int id = getMaxId();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert);

            item.setId(id);
            stmt.setInt(1, item.getId());
            stmt.setInt(2, item.getAdmin().getId());
            stmt.setInt(3, item.getUser().getId());
            stmt.setDate(4, item.getCreated());
            stmt.setString(5,item.getTypeOfPitch());
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Appointments update(Appointments item) {
        String insert = "UPDATE Appointments SET idAdmin = ?, idUser = ?, date = ?, typeOfPitch = ? WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getAdmin().getId());
            stmt.setObject(2, item.getUser().getId());
            stmt.setObject(3, item.getCreated());
            stmt.setObject(4, item.getTypeOfPitch());
            stmt.setObject(5, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM Appointments WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Appointments> getAll() {
        String query = "SELECT * FROM Appointments";
        List<Appointments> appointments = new ArrayList<>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Appointments appointment = new Appointments();
                appointment.setId(rs.getInt("id"));
                appointment.setAdmin(new AdminsDaoSQLImpl().getById(rs.getInt("idAdmin")));
                appointment.setUser(new UsersDaoSQLImpl().getById(rs.getInt("idUser")));
                appointment.setCreated(rs.getDate("date"));
                appointment.setTypeOfPitch(rs.getString("typeOfPitch"));
                appointments.add(appointment);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return appointments;
    }

    @Override
    public List<Appointments> searchByAdmin (Admins a) {
        String query = "SELECT * FROM Appointments WHERE idAdmin = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, a.getId());
            ResultSet rs = stmt.executeQuery();
            ArrayList<Appointments> appointmentsList = new ArrayList<>();
            while(rs.next()) {
                Appointments ap = new Appointments();
                ap.setId(rs.getInt("id"));
                ap.setCreated(rs.getDate("date"));
                ap.setTypeOfPitch(rs.getString("typeOfPitch"));
                ap.setAdmin(new AdminsDaoSQLImpl().getById(rs.getInt("idAdmin")));
                ap.setUser(new UsersDaoSQLImpl().getById(rs.getInt("idUser")));
                appointmentsList.add(ap);
            }
            return appointmentsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Appointments> searchByUser (Users u) {
        String query = "SELECT * FROM Appointments WHERE idUser = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, u.getId());
            ResultSet rs = stmt.executeQuery();
            ArrayList<Appointments> appointmentsList = new ArrayList<>();
            while(rs.next()) {
                Appointments a = new Appointments();
                a.setId(rs.getInt("id"));
                a.setCreated(rs.getDate("date"));
                a.setTypeOfPitch(rs.getString("typeOfPitch"));
                a.setAdmin(new AdminsDaoSQLImpl().getById(rs.getInt("idAdmin")));
                a.setUser(new UsersDaoSQLImpl().getById(rs.getInt("idUser")));
                appointmentsList.add(a);
            }
            return appointmentsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
