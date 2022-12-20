package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class FootballPitches {

    private int id;
    private String name;
    private String address;

    @Override
    public String toString() {
        return "FootballPitches{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballPitches footballPitch = (FootballPitches) o;
        return id == footballPitch.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }
}
