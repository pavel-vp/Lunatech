package com.lunatech.imdbq.model;

public class NameBasicRec {
    private String nconst;
    private String primaryName;
    private String birthYear;
    private String deathYear;

    public NameBasicRec(String nconst, String primaryName, String birthYear, String deathYear) {
        this.nconst = nconst;
        this.primaryName = primaryName;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public NameBasicRec() {
    }

    public String getNconst() {
        return nconst;
    }

    public void setNconst(String nconst) {
        this.nconst = nconst;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public void setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(String deathYear) {
        this.deathYear = deathYear;
    }

    @Override
    public String toString() {
        return "NameBasicRec{" +
                "tconst='" + nconst + '\'' +
                ", primaryName='" + primaryName + '\'' +
                ", birthYear='" + birthYear + '\'' +
                ", deathYear='" + deathYear + '\'' +
                '}';
    }
}
