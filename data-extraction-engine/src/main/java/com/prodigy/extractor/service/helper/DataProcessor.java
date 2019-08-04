package com.prodigy.extractor.service.helper;

import com.prodigy.extractor.offline.OfflineProcessor;
import com.prodigy.extractor.service.Cleaner;
import com.prodigy.extractor.service.Publisher;
import com.prodigy.extractor.vo.inbound.DataExtractionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service public class DataProcessor {

    private Cleaner cleaner;
    private Publisher publisher;
    private OfflineProcessor offlineProcessor;

    @Autowired public DataProcessor (
            @Qualifier("dataCleaner") Cleaner cleaner,
            @Qualifier("apiPublisher") Publisher publisher,
            @Qualifier("offlineProcessor") OfflineProcessor offlineProcessor) {

        this.publisher = publisher;
        this.cleaner = cleaner;
        this.offlineProcessor = offlineProcessor;
    }

    public boolean process (DataExtractionRequest data) {
        boolean filter = cleaner.clean(data);
        if (filter) return false;
        return publisher.publish(data);
    }

    public void process () {
        offlineProcessor.ingest();
    }

}
