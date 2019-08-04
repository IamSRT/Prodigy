package com.prodigy.extractor.service.impl.cleaner.filters.impl;

import com.prodigy.extractor.service.impl.cleaner.filters.Filter;
import com.prodigy.extractor.vo.inbound.DataExtractionRequest;
import org.springframework.util.StringUtils;

public class RatingsFilter implements Filter<DataExtractionRequest> {

    @Override public boolean filter (DataExtractionRequest data) {
        if (StringUtils.isEmpty(data.getNo_reviews())) return true;
        float rating = 0.0f;
        try {
            rating = Float.parseFloat(data.getNo_reviews());
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }

    @Override public void clean (DataExtractionRequest data) { data.setNo_reviews(null); }

}
