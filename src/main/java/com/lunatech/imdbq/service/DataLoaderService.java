package com.lunatech.imdbq.service;

import com.lunatech.imdbq.dao.LoadingDao;
import com.lunatech.imdbq.model.NameBasicRec;
import com.lunatech.imdbq.model.TitleBasicRec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class DataLoaderService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LoadingDao loadingDao;

    public Map<String, TitleBasicRec> getTitles() {
        return titles;
    }

    public Map<String, NameBasicRec> getNames() {
        return names;
    }

    public Map<String, Set<String>> getTitleArtists() {
        return titleArtists;
    }

    public Map<String, Set<String>> getArtistTitles() {
        return artistTitles;
    }

    Map<String, TitleBasicRec> titles = new HashMap<>();
    Map<String, NameBasicRec> names = new HashMap<>();
    Map<String, Set<String>> titleArtists = new HashMap<>();
    Map<String, Set<String>> artistTitles = new HashMap<>();

    public void loadData() {
/*
        // Commented out because of huge memory consumption on my laptop
        // But this decision could be easier to do without database
        logger.info("Starting load data title.basics.tsv");
        loadingDao.readTsvFile("title.basics.tsv", row -> titles.put(row[0], new TitleBasicRec(row[0], row[2], row[8])));
        logger.info("Loaded "+titles.size());

        logger.info("Starting load data name.basics.tsv");
        loadingDao.readTsvFile("name.basics.tsv", row -> names.put(row[0], new NameBasicRec(row[0], row[1], row[2], row[3])));
        logger.info("Loaded "+names.size());
*/
        logger.info("Starting load data title.principals.tsv");
        loadingDao.readTsvFile("title.principals.tsv", row -> {
            String tconst = row[0];
            String nconst = row[2];
            String category = row[3];
            if ("actor".equals(category)) {
                Set<String> actors = titleArtists.computeIfAbsent(tconst, k -> new HashSet<>());
                actors.add(nconst);

                Set<String> titles = artistTitles.computeIfAbsent(nconst, k -> new HashSet<>());
                titles.add(tconst);
            }
        });
        logger.info("Loaded "+titleArtists.size());
    }


}
