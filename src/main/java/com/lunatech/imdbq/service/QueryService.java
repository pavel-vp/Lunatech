package com.lunatech.imdbq.service;

import com.lunatech.imdbq.dao.QueryDBDao;
import com.lunatech.imdbq.model.NameBasicRec;
import com.lunatech.imdbq.model.TitleBasicRec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class QueryService {

    @Autowired
    QueryDBDao queryDao;

    public List<NameBasicRec> queryForName(String name) {
        return queryDao.queryForName(name);
    }

    public boolean isTypeCasted(String nconst) {
        return isTypecasted(queryDao.getGenres(nconst));
    }

    public List<TitleBasicRec> getSharedTitles(String nconst1, String nconst2) {
        return queryDao.getSharedTitle(nconst1, nconst2);
    }

    public boolean isTypecasted(List<String> genresList) {
        int max = 0;
        int titlesQty = 0;
        Map<String, Integer> genreQty =new TreeMap<>();
        for (String genres : genresList) {
            if (genres != null) {
                String[] genreArray = genres.split(",");
                for (String g : genreArray) {
                    genreQty.putIfAbsent(g, 0);
                    int newQty = genreQty.get(g) + 1;
                    genreQty.put(g, newQty);
                    max = Math.max(max, newQty);
                }
                titlesQty++;
            }
        }
        if (max != 0 && max > (titlesQty / 2)) {
            return true;
        } else {
            return false;
        }
    }

}
