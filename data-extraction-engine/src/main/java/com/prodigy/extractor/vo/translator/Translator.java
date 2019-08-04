package com.prodigy.extractor.vo.translator;

import com.prodigy.extractor.vo.inbound.DataExtractionRequest;
import com.prodigy.extractor.vo.outbound.AttributionRequest;
import org.springframework.stereotype.Service;

@Service public class Translator {

    public AttributionRequest translate (DataExtractionRequest request) {
        return new AttributionRequest(request);
    }

}
