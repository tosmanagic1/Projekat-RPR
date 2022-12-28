package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.*;
import ba.unsa.etf.rpr.domain.FootballPitches;
import ba.unsa.etf.rpr.domain.Users;
import ba.unsa.etf.rpr.exceptions.FootballPitchException;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws FootballPitchException {

        FootballPitches f1 = new FootballPitches();
        f1.setId(1);
        f1.setAddress("Avdage Šahinagića");
        f1.setName("Bentbaša");
        FootballPitches f2 = new FootballPitches();
        f2.setId(2);
        f2.setAddress("Bulevar Meše Selimovića");
        f2.setName("Vistafon");
        DaoFactory.footballPitchDao().add(f1);
        DaoFactory.footballPitchDao().add(f2);

        List<FootballPitches> footballPitches = DaoFactory.footballPitchDao().getAll();
        System.out.println(footballPitches);

        Users u1 = new Users();
        u1.setId(1);
        u1.setName("user");
        u1.setNumber(225883);
        u1.setEmail("tosmanagic1@etf.unsa.ba");
        u1.setUsername("kabas");
        u1.setPassword("SLOM");
        Users u2 = new Users();
        u2.setId(2);
        u2.setName("userko");
        u2.setNumber(445667);
        u2.setEmail("tarik.osmanagic@gmail.com");
        u2.setUsername("kabas123");
        u2.setPassword("SLOMIGA");
        DaoFactory.userDao().add(u1);
        DaoFactory.userDao().add(u2);

        List<Users> users = DaoFactory.userDao().getAll();
        System.out.println(users);

        Users u = DaoFactory.userDao().getById(1);
        System.out.println(u);

    }
}
