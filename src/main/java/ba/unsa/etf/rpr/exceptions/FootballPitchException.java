package ba.unsa.etf.rpr.exceptions;

public class FootballPitchException extends Exception {
    public FootballPitchException (String msg, Exception reason) {
        super(msg, reason);
    }

    public FootballPitchException (String msg){
        super(msg);
    }
}
