package com.lunatech.imdbq.model;

import java.util.List;

public class SharedTitleResponse {
    private List<TitleBasicRec> titleBasicRecs;

    public SharedTitleResponse(List<TitleBasicRec> titleBasicRecs) {
        this.titleBasicRecs = titleBasicRecs;
    }

    public SharedTitleResponse() {
    }

    public List<TitleBasicRec> getTitleBasicRecs() {
        return titleBasicRecs;
    }

    public void setTitleBasicRecs(List<TitleBasicRec> titleBasicRecs) {
        this.titleBasicRecs = titleBasicRecs;
    }
}
