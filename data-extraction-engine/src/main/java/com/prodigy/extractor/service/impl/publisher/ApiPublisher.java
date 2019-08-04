package com.prodigy.extractor.service.impl.publisher;

import com.prodigy.extractor.http.Config;
import com.prodigy.extractor.service.Publisher;
import com.prodigy.extractor.vo.inbound.DataExtractionRequest;
import com.prodigy.extractor.vo.outbound.AttributionRequest;
import com.prodigy.extractor.vo.translator.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Qualifier ("apiPublisher")
@Service public class ApiPublisher implements Publisher<DataExtractionRequest> {

    private Config config;
    private Translator translator;

    @Autowired public ApiPublisher (
            Config config,
            Translator translator) {

        this.config = config;
        this.translator = translator;
    }


    @Override public boolean publish (DataExtractionRequest data) {
        AttributionRequest body = translator.translate(data);
        //System.out.println("api request :: " + body.toString());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response;
        try {
            HttpEntity<AttributionRequest> entity = new HttpEntity<>(body, new HttpHeaders());
            response = restTemplate.exchange (
                    config.getUrl(),
                    HttpMethod.POST,
                    entity,
                    String.class
            );
        } catch (RestClientException rce) {
            System.out.println("error while hitting api, " + rce.getMessage());
            return false;
        }
        try {
            Thread.currentThread().sleep(1);
        } catch (Exception e) {
            return true;
        }
        System.out.println("successfully sent :: " + body.getId());
        return true;
    }

}
