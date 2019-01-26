package com.lunatech.imdbq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.io.*;

@Service
public class CsvImport {

    @Autowired
    JdbcTemplate jdbcTemplate;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @EventListener(ApplicationReadyEvent.class)
    public void readCsvBulk() {
        logger.info("Starting upload data into the database...");
        loadBulk(new File("title.basics.tsv").getAbsolutePath(),"titlebasics");
        loadBulk(new File("name.basics.tsv").getAbsolutePath(),"namebasics");
        loadBulk(new File("title.principals.tsv").getAbsolutePath(),"titleprincipals");

        logger.info("Starting create indicies...");
        jdbcTemplate. execute("CREATE INDEX i_namebasics_primaryName ON namebasics(primaryName)");
        jdbcTemplate. execute("CREATE INDEX i_namebasics_nconst ON namebasics(nconst)");

        jdbcTemplate. execute("CREATE INDEX i_titlebasics_tconst ON titlebasics(tconst)");

        jdbcTemplate. execute("CREATE INDEX i_titleprincipals_tconst ON titleprincipals(tconst)");
        jdbcTemplate. execute("CREATE INDEX i_titleprincipals_nconst ON titleprincipals(nconst)");

        logger.info("Indicies created");
    }


    private void loadBulk(String filePath, String tableName) {
        String q = "LOAD DATA LOCAL INFILE '"+filePath+"' INTO TABLE "+tableName+" FIELDS TERMINATED BY '\t' IGNORE 1 LINES";
        logger.info("File="+filePath+" to table="+tableName);
        jdbcTemplate.execute(q);
        logger.info("Loaded");
    }



}
