package com.prodigy.extractor.controller;

import com.prodigy.extractor.controller.routes.Routes;
import com.prodigy.extractor.service.helper.DataProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController public class Offline {

    private DataProcessor dataProcessor;

    @Autowired public Offline (DataProcessor dataProcessor) {
        this.dataProcessor = dataProcessor;
    }

    @GetMapping (Routes.PROCESS_FILE) public void start () {
        dataProcessor.process();
    }

}
