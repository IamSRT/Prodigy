package com.prodigy.extractor.service;

import com.prodigy.extractor.vo.inbound.DataExtractionRequest;

public interface Cleaner {

    boolean clean (DataExtractionRequest data);

}
