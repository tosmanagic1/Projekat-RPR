package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Admins;
import ba.unsa.etf.rpr.domain.FootballPitches;
import ba.unsa.etf.rpr.exceptions.FootballPitchException;

import java.util.List;

public interface AdminsDao extends Dao<Admins> {

    List<Admins> searchByFootballPitch (FootballPitches fp) throws FootballPitchException;
}
