package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.*;
import ba.unsa.etf.rpr.domain.Admins;
import ba.unsa.etf.rpr.domain.Appointments;
import ba.unsa.etf.rpr.domain.FootballPitches;
import ba.unsa.etf.rpr.domain.Users;
import com.google.protobuf.DescriptorProtos;

import java.sql.Date;
import java.util.ArrayList;
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

        admins = dao1.getAll();

        Admins a3 = dao1.getById(2);
        //System.out.println(admins);
        //System.out.println(a3);

        //dao1.delete(2);

        List<Admins> admins1 = dao1.getAll();
        //System.out.println(admins1);

        Admins a4 = new Admins();
        a4.setId(1);
        a4.setName("admin1");
        a4.setNumber(798008);
        a4.setFootballPitch(f1);
        a4.setUsername("solzencki");
        a4.setPassword("SILOM");

        Admins a5 = dao1.update(a4);

        List admins2 = dao1.getAll();
        //System.out.println(admins2);

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

        Users u3 = dao2.getById(2);
        //System.out.println(users);
        //System.out.println(u3);

        //dao2.delete(2);

        List<Users> users1 = dao2.getAll();
        //System.out.println(users1);

        Users u4 = new Users();
        u4.setId(1);
        u4.setName("user1");
        u4.setNumber(798008);
        u4.setEmail("radosnakloaka@gmail.com");
        u4.setUsername("solzencki");
        u4.setPassword("SILOM");

        Users u5 = dao2.update(u4);

        List users2 = dao2.getAll();
        //System.out.println(users2);

        //Appointments test

        AppointmentsDao dao3 = new AppointmentsDaoSQLImpl();
        List<Appointments> appointments;

        Appointments ap1 = new Appointments();
        Appointments ap2 = new Appointments();
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

        appointments = dao3.getAll();

        Appointments ap3 = dao3.getById(2);
        System.out.println(appointments);
        System.out.println(ap3);

        dao3.delete(2);

        List<Appointments> appointments1 = dao3.getAll();
        System.out.println(appointments1);

        Appointments ap4 = new Appointments();
        ap4.setId(1);
        ap4.setAdmin(a2);
        ap4.setUser(u2);
        ap4.setCreated(date);
        ap4.setTypeOfPitch("Natural grass");
        dao3.add(ap4);

        Appointments ap5 = dao3.update(ap4);

        List appointments2 = dao3.getAll();
        System.out.println(appointments2);
    }
}
