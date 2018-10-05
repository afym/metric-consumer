package com.afym.controller.api.v1;

import com.afym.entity.BuildFrequencyEntity;
import com.afym.repository.elasticsearch.write.BuildFrequencyWrite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildFrequencyController {
    @Autowired
    private BuildFrequencyWrite buildFrequencyWrite;

    @RequestMapping(value="/api/v1/build/frequency", method=RequestMethod.POST, produces="application/json")
    public String insert(@RequestBody BuildFrequencyEntity build) {
        int status = this.buildFrequencyWrite.insert(build);
        return "{\"status\" : " + new Integer(status).toString() +"}";
    }
}
