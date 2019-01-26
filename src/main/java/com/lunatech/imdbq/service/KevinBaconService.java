package com.lunatech.imdbq.service;

import com.lunatech.imdbq.utils.BinaryHeap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
public class KevinBaconService {

    private static final String kbConst = "nm0000102";

    DataLoaderService dataLoaderService;

    @Autowired
    public KevinBaconService(DataLoaderService dataLoaderService) {
        this.dataLoaderService = dataLoaderService;
    }

    Logger logger = LoggerFactory.getLogger(this.getClass());

    Map<String, Integer> distances = null;

    public void buildKevinBaconsUniverse() {
        logger.info("Starting build Kevin's Universe...");
        this.distances  = getDists(kbConst);
        logger.info("Universe has built, size " + distances.size());
    }

    public Integer getDegree(String nconst) {
        return distances == null ? null : distances.get(nconst);
    }


    public Map<String, Integer> getDists(String start) {

        Map<String, Integer> distAll = new HashMap<>();
        BinaryHeap bh = new BinaryHeap(dataLoaderService.getTitleArtists().keySet().size() + 10);
        Set<String> used = new HashSet<>();

        for (String k : dataLoaderService.getArtistTitles().keySet()) {
            distAll.put(k, Integer.MAX_VALUE);
        }
        distAll.put(start, 0);
        // first - titles with the starter
        for (String tconst : dataLoaderService.getArtistTitles().get(start)) {
            BinaryHeap.Node node = new BinaryHeap.Node(tconst, 0);
            bh.add(node);
            used.add(node.tconst);
        }
        used.add(start);

        while (true) {
            BinaryHeap.Node minNode = bh.removeMin();
            if (minNode == null) break;

            Set<String> artists= dataLoaderService.getTitleArtists().get(minNode.tconst);
            if (artists == null) continue;
            for (String nconst : artists) {

                if (!used.contains(nconst) ) {
                    int newDist = Math.min(distAll.get(nconst), minNode.dist + 1);
                    distAll.put(nconst, newDist);
                    used.add(nconst);

                    Set<String> titles = dataLoaderService.getArtistTitles().get(nconst);
                    if (titles == null) continue;
                    for (String tconst : titles) {
                        if (!used.contains(tconst) ) {
                            used.add(tconst);
                            bh.add(new BinaryHeap.Node(tconst, newDist));
                        }
                    }
                }
            }
        }
        return distAll;
    }


}
