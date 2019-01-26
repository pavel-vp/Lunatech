package com.lunatech.imdbq.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Repository
public class LoadingDao {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void readTsvFile(String fileName, IRowProceeder rowProceeder) {
        File file = new File(fileName);

        int cntAll = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            while (true) {
                String line = br.readLine();
                if (line == null) break;
                String[] nextLine = line.split("\t");
                rowProceeder.proceed(nextLine);
                if (cntAll > 0 && cntAll % 1000000 == 0) {
                    logger.info(cntAll+ " readed");
                    // break;
                }
                cntAll++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
