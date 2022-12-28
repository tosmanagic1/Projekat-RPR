package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Users;

/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Dino Keco
 */
public class DaoFactory {

    private static final AdminsDao adminDao = new AdminsDaoSQLImpl();
    private static final AppointmentsDao appointmentDao = new AppointmentsDaoSQLImpl();
    private static final FootballPitchesDao footballPitchDao = new FootballPitchesDaoSQLImpl();
    private static final UsersDao userDao = new UsersDaoSQLImpl();

    private DaoFactory(){
    }

    public static AdminsDao adminDao(){
        return adminDao;
    }

    public static AppointmentsDao appointmentDao(){
        return appointmentDao;
    }

    public static FootballPitchesDao footballPitchDao(){
        return footballPitchDao;
    }

    public static UsersDao userDao(){
        return userDao;
    }

}
