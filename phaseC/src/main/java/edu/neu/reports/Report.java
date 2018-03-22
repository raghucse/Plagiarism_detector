package edu.neu.reports;

import javax.persistence.*;

@Entity
@Table(name="report")
public class Report {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    private int owner;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    private byte[] report;

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getProfilePic() {
        return report;
    }

    public void setProfilePic(byte[] profilePic) {
        this.report = profilePic;
    }

}