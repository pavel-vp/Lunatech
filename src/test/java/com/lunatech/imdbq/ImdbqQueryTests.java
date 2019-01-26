package com.lunatech.imdbq;

import com.lunatech.imdbq.service.DataLoaderService;
import com.lunatech.imdbq.service.KevinBaconService;
import com.lunatech.imdbq.service.QueryService;
import com.lunatech.imdbq.utils.BinaryHeap;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ImdbqQueryTests {

    @Test
    public void heapTest() {
        BinaryHeap bh = new BinaryHeap(10);
        for (int i = 0; i<10; i++) {
            BinaryHeap.Node node = new BinaryHeap.Node("v", (int) (Math.random() * 100));
            bh.add(node);
        }
        BinaryHeap.Node node = null;
        int prev = Integer.MIN_VALUE;
        while((node = bh.removeMin()) != null ) {
            assertTrue(prev <= node.dist );
        }
    }


    @Test
    public void testDist() {
        DataLoaderService dataLoaderService= new DataLoaderService();

        String start  = "kb";
        Map<String, Set<String>> titleArtists = dataLoaderService.getTitleArtists();
        Map<String, Set<String>> artistTitles = dataLoaderService.getArtistTitles();

        Set<String> a_t1 = new HashSet<>();
        a_t1.add("kb");
        a_t1.add("a1");
        a_t1.add("a2");
        titleArtists.put("t1", a_t1);

        Set<String> a_t2 = new HashSet<>();
        a_t2.add("a2");
        a_t2.add("a3");
        a_t2.add("a6");
        a_t2.add("a4");
        titleArtists.put("t2", a_t2);

        Set<String> a_t3 = new HashSet<>();
        a_t3.add("kb");
        a_t3.add("a4");
        titleArtists.put("t3", a_t3);

        Set<String> a_t4 = new HashSet<>();
        a_t4.add("a4");
        a_t4.add("a5");
        titleArtists.put("t4", a_t4);


//////////////
        Set<String> t_kb = new HashSet<>();
        t_kb.add("t1");
        t_kb.add("t3");
        artistTitles.put("kb", t_kb);

        Set<String> t_a1 = new HashSet<>();
        t_a1.add("t1");
        artistTitles.put("a1", t_a1);

        Set<String> t_a2 = new HashSet<>();
        t_a2.add("t1");
        t_a2.add("t2");
        artistTitles.put("a2", t_a2);

        Set<String> t_a3 = new HashSet<>();
        t_a3.add("t2");
        artistTitles.put("a3", t_a3);

        Set<String> t_a4 = new HashSet<>();
        t_a4.add("t3");
        t_a4.add("t4");
        t_a4.add("t2");
        artistTitles.put("a4", t_a4);

        Set<String> t_a5 = new HashSet<>();
        t_a5.add("t4");
        artistTitles.put("a5", t_a5);

        Set<String> t_a6 = new HashSet<>();
        t_a6.add("t2");
        artistTitles.put("a6", t_a6);


        KevinBaconService kevinBaconService = new KevinBaconService(dataLoaderService);

        Map<String, Integer> dist = kevinBaconService.getDists(start);
        assertEquals(dist.size(), 7);
        assertEquals(dist.get("kb").intValue(), 0);
        assertEquals(dist.get("a1").intValue(), 1);
        assertEquals(dist.get("a4").intValue(), 1);
    }

    @Test
    public void genreTest() {
        QueryService queryService = new QueryService();

        List<String> genresList = new ArrayList<>();
        genresList.add("Action, Fiction, Documental");
        genresList.add("Action");
        genresList.add("Horror");

        boolean result = queryService.isTypecasted(genresList);
        assertTrue(result);

        genresList.add("Music, Fiction");
        genresList.add("Show");
        result = queryService.isTypecasted(genresList);
        assertFalse(result);

        genresList.add("Series");
        result = queryService.isTypecasted(genresList);
        assertFalse(result);

    }

}
