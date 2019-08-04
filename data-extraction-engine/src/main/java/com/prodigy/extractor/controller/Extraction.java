package com.prodigy.extractor.controller;

import com.prodigy.extractor.controller.routes.Routes;
import com.prodigy.extractor.service.helper.DataProcessor;
import com.prodigy.extractor.vo.inbound.DataExtractionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController public class Extraction {

    private DataProcessor dataProcessor;

    @Autowired public Extraction (
            DataProcessor dataProcessor) {

        this.dataProcessor = dataProcessor;
    }

    @PostMapping(Routes.PROCESS_EVENT) public void process (@RequestBody() DataExtractionRequest data) {
        dataProcessor.process(data);
    }

}
