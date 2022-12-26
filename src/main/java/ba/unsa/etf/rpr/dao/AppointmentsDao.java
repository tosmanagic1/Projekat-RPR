package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Admins;
import ba.unsa.etf.rpr.domain.Appointments;
import ba.unsa.etf.rpr.domain.Users;

import java.util.List;

public interface AppointmentsDao extends Dao<Appointments> {

    List<Appointments> searchByAdmin (Admins a);
    List<Appointments> searchByUser (Users u);
}
