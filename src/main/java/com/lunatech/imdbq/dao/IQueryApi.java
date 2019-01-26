package com.lunatech.imdbq.dao;

import com.lunatech.imdbq.model.NameBasicRec;
import com.lunatech.imdbq.model.TitleBasicRec;

import java.util.List;

public interface IQueryApi {

    List<NameBasicRec> queryForName(String name);
    List<String> getGenres(String nconst);
    List<TitleBasicRec> getSharedTitle(String nconst1, String nconst2);

}
