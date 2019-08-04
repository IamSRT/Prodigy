package com.prodigy.extractor.service.impl.cleaner.filters.impl;


import com.prodigy.extractor.service.impl.cleaner.filters.Filter;
import com.prodigy.extractor.vo.inbound.DataExtractionRequest;

import java.util.LinkedList;
import java.util.List;

public class TitleFilter implements Filter<DataExtractionRequest> {

    private List<String> invalid = new LinkedList<>(); {
        invalid.add("Access Denied");
        invalid.add("");
        invalid.add(null);
    }

    /**
     * This filter checks `title` field from the incoming
     *
     */
    @Override public boolean filter (DataExtractionRequest data) {
        return invalid.contains(data.getTitle());
    }

    @Override public void clean (DataExtractionRequest data) { data.setTitle(null); }

    @Override public int corruptVal () { return 10; }

}
