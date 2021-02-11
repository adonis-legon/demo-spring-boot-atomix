package com.example.demospringbootatomix.api;

import com.example.demospringbootatomix.business.SequenceService;
import com.example.demospringbootatomix.config.SequenceClusterConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sequence")
public class SequenceController {
    @Autowired
    SequenceService sequenceService;

    @Autowired
    SequenceClusterConfig sequenceClusterConfig;

    @GetMapping(value = "/current")
    public String getCurrent(){
        return String.format("Value: %d, Node: %s", sequenceService.getCurrent(), sequenceClusterConfig.getNode().getName());
    }

    @GetMapping(value = "/next")
    public String getNext(){
        return String.format("Value: %d, Node: %s", sequenceService.getNext(), sequenceClusterConfig.getNode().getName());
    }

    @GetMapping(value = "/reset")
    public String reset(){
        return String.format("Value: %d, Node: %s", sequenceService.reset(), sequenceClusterConfig.getNode().getName());
    }
}
