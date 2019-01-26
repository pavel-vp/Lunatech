package com.lunatech.imdbq.dao;

import com.lunatech.imdbq.model.NameBasicRec;
import com.lunatech.imdbq.model.TitleBasicRec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class QueryDBDao implements IQueryApi{

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<NameBasicRec> queryForName(String name) {
        String q = "select * from namebasics where primaryname like ? order by 1 limit 20";
        return jdbcTemplate.query(q, new String[]{name},  new RowMapper<NameBasicRec>() {
            @Override
            public NameBasicRec mapRow(ResultSet resultSet, int i) throws SQLException {
                return new NameBasicRec(resultSet.getString("nconst"),
                        resultSet.getString("primaryName"),
                        resultSet.getString("birthYear"),
                        resultSet.getString("deathYear")
                        );
            }
        });
    }

    public List<String> getGenres(String nconst) {
        String q = "select tb.genres from titleprincipals tp, titlebasics tb  where tp.nconst=? and tp.tconst=tb.tconst";
        List<String> genresList = jdbcTemplate.queryForList(q, new String[]{nconst}, String.class);
        return genresList;
    }


    public List<TitleBasicRec> getSharedTitle(String nconst1, String nconst2) {
        String q = "select tb.* from titleprincipals tp1, titleprincipals tp2, titlebasics tb  where tp1.nconst=? and tp2.nconst=? and tp1.tconst=tp2.tconst and tp1.tconst=tb.tconst";
        return jdbcTemplate.query(q, new String[]{nconst1, nconst2},
                (resultSet, i) -> new TitleBasicRec(resultSet.getString("tconst"), resultSet.getString("primaryTitle"), resultSet.getString("genres") ));
    }



}

