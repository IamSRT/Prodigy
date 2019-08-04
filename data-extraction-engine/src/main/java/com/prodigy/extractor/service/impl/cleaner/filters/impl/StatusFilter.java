package com.prodigy.extractor.service.impl.cleaner.filters.impl;

import com.prodigy.extractor.service.impl.cleaner.filters.Filter;
import com.prodigy.extractor.vo.inbound.DataExtractionRequest;
import org.springframework.util.StringUtils;

public class StatusFilter implements Filter<DataExtractionRequest> {

    private int start = 200;
    private int end = 299;


    /**
     * This filter checks `http_status` field and
     * filters if code is not of 2xx series.
     */
    @Override public boolean filter (DataExtractionRequest data) {
        String statusCode = data.getHttp_status();
        if (StringUtils.isEmpty(statusCode)) return true;
        int code = 0;
        try { code = Integer.parseInt(statusCode); } catch (NumberFormatException nfe) { return true; }
        return code > end || code < start;
    }

    @Override public void clean (DataExtractionRequest data) { data.setHttp_status(null); }

}
