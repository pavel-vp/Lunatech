package com.lunatech.imdbq.web;

import com.lunatech.imdbq.model.KevinBaconDegreeResponse;
import com.lunatech.imdbq.model.NameBasicRec;
import com.lunatech.imdbq.model.SharedTitleResponse;
import com.lunatech.imdbq.model.TypeCastResponse;
import com.lunatech.imdbq.service.KevinBaconService;
import com.lunatech.imdbq.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class QueryController {

    @Autowired
    QueryService queryService;
    @Autowired
    KevinBaconService kevinBaconService;

    @GetMapping("/query/suggest")
    public @ResponseBody List<NameBasicRec> suggestActors(@RequestParam String name) {
        return queryService.queryForName(name);
    }

    @GetMapping("/query/typecast")
    public @ResponseBody TypeCastResponse isTypecasted(@RequestParam String nconst) {
        return new TypeCastResponse(queryService.isTypeCasted(nconst));
    }

    @GetMapping("/query/sharedtitle")
    public @ResponseBody SharedTitleResponse getSharedTitles(@RequestParam String nconst1, @RequestParam String nconst2) {
        return new SharedTitleResponse(queryService.getSharedTitles(nconst1,nconst2));
    }

    @GetMapping("/query/kevinbacondegree")
    public @ResponseBody KevinBaconDegreeResponse getKBDegree(@RequestParam String nconst) {
        Integer degree = kevinBaconService.getDegree(nconst);
        return new KevinBaconDegreeResponse(degree == null || degree == Integer.MAX_VALUE ? null : degree);
    }


}
