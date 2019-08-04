package com.prodigy.extractor.service.impl.cleaner.filters.impl;

import com.prodigy.extractor.service.impl.cleaner.filters.Filter;
import com.prodigy.extractor.vo.inbound.DataExtractionRequest;
import org.springframework.util.StringUtils;

public class HttpStatusFilter implements Filter<DataExtractionRequest> {

    private int start = 200;
    private int end = 299;


    /**
     * This filter checks `http_status` field and
     * filters if code is not of 2xx series.
     */
    @Override public boolean filter (DataExtractionRequest data) {
        String httpStatus = data.getOriginal_http_status();
        if (StringUtils.isEmpty(httpStatus)) return true;
        int httpStatusCode = 0;
        try { httpStatusCode = Integer.parseInt(httpStatus); } catch (NumberFormatException nfe) { return true; }
        return httpStatusCode > end || httpStatusCode < start;
    }

    @Override public void clean (DataExtractionRequest data) { data.setOriginal_http_status(null); }

}
