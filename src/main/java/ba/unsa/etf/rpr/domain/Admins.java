package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Admins implements Idable{

    private int id;
    private String name;
    private int number;

    private FootballPitches footballPitch;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public FootballPitches getFootballPitch() {
        return footballPitch;
    }

    public void setFootballPitch(FootballPitches footballPitch) {
        this.footballPitch = footballPitch;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admins{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", footballPitch=" + footballPitch +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admins admin = (Admins) o;
        return id == admin.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, number, footballPitch, username, password);
    }
}
