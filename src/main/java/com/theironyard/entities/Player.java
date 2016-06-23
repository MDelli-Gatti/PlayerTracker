package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by michaeldelli-gatti on 6/23/16.
 */
@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String team;

    @Column(nullable = false)
    String position;

    @Column(nullable = false)
    int number;

    @Column
    String comments;

    @ManyToOne
    User user;

    public Player() {
    }

    public Player(int id, String name, String team, String position, int number, String comments, User user) {
        this.id = id;
        this.name = name;
        this.team = team;
        this.position = position;
        this.number = number;
        this.comments = comments;
        this.user = user;
    }

    public Player(String name, String team, String position, int number, String comments, User user) {
        this.name = name;
        this.team = team;
        this.position = position;
        this.number = number;
        this.comments = comments;
        this.user = user;
    }

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

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
