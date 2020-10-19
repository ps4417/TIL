package com.example.p534;

public class Movie {
    String rank;
    String name;
    String audi;
    String open;
    String audiAll;
    String scrn;

    public Movie() {
    }

    public Movie(String rank, String name, String audi) {
        this.rank = rank;
        this.name = name;
        this.audi = audi;
    }

    public Movie(String rank, String name, String audi, String open, String audiAll, String scrn) {
        this.rank = rank;
        this.name = name;
        this.audi = audi;
        this.open = open;
        this.audiAll = audiAll;
        this.scrn = scrn;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAudi() {
        return audi;
    }

    public void setAudi(String audi) {
        this.audi = audi;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getAudiAll() {
        return audiAll;
    }

    public void setAudiAll(String audiAll) {
        this.audiAll = audiAll;
    }

    public String getScrn() {
        return scrn;
    }

    public void setScrn(String scrn) {
        this.scrn = scrn;
    }
}
