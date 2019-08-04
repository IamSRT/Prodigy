package com.prodigy.extractor.service.impl.cleaner.filters.impl;

import com.prodigy.extractor.service.impl.cleaner.filters.Filter;
import com.prodigy.extractor.vo.inbound.DataExtractionRequest;
import org.springframework.util.StringUtils;

public class FeaturesFilter implements Filter<DataExtractionRequest> {

    @Override public boolean filter (DataExtractionRequest data) {
        return StringUtils.isEmpty(data.getFeatures()) ;
    }

    @Override public void clean (DataExtractionRequest data) { data.setFeatures(null); }

}
