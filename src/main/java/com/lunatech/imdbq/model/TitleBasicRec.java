package com.lunatech.imdbq.model;

public class TitleBasicRec {
    private String tconst;
    private String primaryTitle;
    private String genres;

    public TitleBasicRec(String tconst, String primaryTitle, String genres) {
        this.tconst = tconst;
        this.primaryTitle = primaryTitle;
        this.genres = genres;
    }

    public TitleBasicRec() {
    }

    public String getTconst() {
        return tconst;
    }

    public void setTconst(String tconst) {
        this.tconst = tconst;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public void setPrimaryTitle(String primaryTitle) {
        this.primaryTitle = primaryTitle;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }
}
