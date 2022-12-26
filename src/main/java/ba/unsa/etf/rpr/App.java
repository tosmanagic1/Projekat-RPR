package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.*;
import ba.unsa.etf.rpr.domain.Admins;
import ba.unsa.etf.rpr.domain.Appointments;
import ba.unsa.etf.rpr.domain.FootballPitches;
import ba.unsa.etf.rpr.domain.Users;


import java.sql.Date;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        //Football pitches test
        FootballPitchesDao dao = new FootballPitchesDaoSQLImpl();
        List<FootballPitches> pitches;

        FootballPitches f1 = new FootballPitches();
        f1.setId(1);
        f1.setAddress("Avdage Šahinagića");
        f1.setName("Bentbaša");
        FootballPitches f2 = new FootballPitches();
        f2.setId(2);
        f2.setAddress("Bulevar Meše Selimovića");
        f2.setName("Vistafon");
        dao.add(f1);
        dao.add(f2);

        //Admins test

        AdminsDao dao1 = new AdminsDaoSQLImpl();
        List<Admins> admins;

        Admins a1 = new Admins();
        Admins a2 = new Admins();
        Admins a3 = new Admins();

        a1.setId(1);
        a1.setName("admin");
        a1.setNumber(225883);
        a1.setFootballPitch(f1);
        a1.setUsername("kabas");
        a1.setPassword("SLOM");
        dao1.add(a1);
        a2.setId(2);
        a2.setName("adminko");
        a2.setNumber(445667);
        a2.setFootballPitch(f2);
        a2.setUsername("kabas123");
        a2.setPassword("SLOMIGA");
        dao1.add(a2);
        a3.setId(3);
        a3.setName("adminkec");
        a3.setNumber(222333);
        a3.setFootballPitch(f1);
        a3.setUsername("kabas123456");
        a3.setPassword("SLOMIGAKONIKOG");
        dao1.add(a3);

        //Users test

        UsersDao dao2 = new UsersDaoSQLImpl();
        List<Users> users;

        Users u1 = new Users();
        Users u2 = new Users();

        u1.setId(1);
        u1.setName("user");
        u1.setNumber(225883);
        u1.setEmail("tosmanagic1@etf.unsa.ba");
        u1.setUsername("kabas");
        u1.setPassword("SLOM");
        dao2.add(u1);
        u2.setId(2);
        u2.setName("userko");
        u2.setNumber(445667);
        u2.setEmail("tarik.osmanagic@gmail.com");
        u2.setUsername("kabas123");
        u2.setPassword("SLOMIGA");
        dao2.add(u2);

        users = dao2.getAll();


        //Appointments test

        AppointmentsDao dao3 = new AppointmentsDaoSQLImpl();
        List<Appointments> appointments, appointments1;

        Appointments ap1 = new Appointments();
        Appointments ap2 = new Appointments();
        Appointments ap3 = new Appointments();
        Date date = new Date(System.currentTimeMillis());

        ap1.setId(1);
        ap1.setAdmin(a1);
        ap1.setUser(u1);
        ap1.setCreated(date);
        ap1.setTypeOfPitch("Natural grass");
        dao3.add(ap1);

        ap2.setId(2);
        ap2.setAdmin(a2);
        ap2.setUser(u2);
        ap2.setCreated(date);
        ap2.setTypeOfPitch("Plastic grass");
        dao3.add(ap2);

        ap3.setId(3);
        ap3.setAdmin(a2);
        ap3.setUser(u2);
        ap3.setCreated(date);
        ap3.setTypeOfPitch("Natural grass");
        dao3.add(ap3);

        appointments = dao3.searchByUser(u2);
        appointments1 = dao3.searchByAdmin(a2);
        System.out.println(appointments);
        System.out.println(appointments1);



    }
}
