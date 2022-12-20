package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

public class Appointments {

    private int id;
    private Date created;
    private String typeOfPitch;

    private Admins admin;

    private Users user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getTypeOfPitch() {
        return typeOfPitch;
    }

    public void setTypeOfPitch(String typeOfPitch) {
        this.typeOfPitch = typeOfPitch;
    }

    public Admins getAdmin() {
        return admin;
    }

    public void setAdmin(Admins admin) {
        this.admin = admin;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Appointments{" +
                "id=" + id +
                ", created=" + created +
                ", typeOfPitch='" + typeOfPitch + '\'' +
                ", admin=" + admin +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointments appointment = (Appointments) o;
        return id == appointment.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, typeOfPitch, admin, user);
    }
}
