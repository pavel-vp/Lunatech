package com.lunatech.imdbq.dao;

import com.lunatech.imdbq.model.NameBasicRec;
import com.lunatech.imdbq.model.TitleBasicRec;

import java.util.List;

public class QueryMemDao implements IQueryApi {


    @Override
    public List<NameBasicRec> queryForName(String name) {
        return null;
    }

    @Override
    public List<String> getGenres(String nconst) {
        return null;
    }

    @Override
    public List<TitleBasicRec> getSharedTitle(String nconst1, String nconst2) {
        return null;
    }
}
