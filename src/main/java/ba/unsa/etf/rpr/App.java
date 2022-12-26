package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.AdminsDao;
import ba.unsa.etf.rpr.dao.AdminsDaoSQLImpl;
import ba.unsa.etf.rpr.dao.FootballPitchesDao;
import ba.unsa.etf.rpr.dao.FootballPitchesDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Admins;
import ba.unsa.etf.rpr.domain.FootballPitches;
import com.google.protobuf.DescriptorProtos;

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
        System.out.println(admins);
        System.out.println(a3);

        dao1.delete(2);

        List<Admins> admins1 = dao1.getAll();
        System.out.println(admins1);

        Admins a4 = new Admins();
        a4.setId(1);
        a4.setName("admin1");
        a4.setNumber(798008);
        a4.setFootballPitch(f1);
        a4.setUsername("solzencki");
        a4.setPassword("SILOM");

        Admins a5 = dao1.update(a4);

        List admins2 = dao1.getAll();
        System.out.println(admins2);



    }
}
