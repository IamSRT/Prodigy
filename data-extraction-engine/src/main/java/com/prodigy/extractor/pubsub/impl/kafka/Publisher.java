package com.prodigy.extractor.pubsub.impl.kafka;

import com.prodigy.extractor.pubsub.EventPublisher;
import com.prodigy.extractor.pubsub.vo.Event;
import org.springframework.stereotype.Service;

@Service class Publisher implements EventPublisher {

    @Override public boolean publish (Event event) {
        return false;
    }

}
