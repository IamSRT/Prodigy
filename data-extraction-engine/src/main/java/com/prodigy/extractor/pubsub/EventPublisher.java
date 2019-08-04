package com.prodigy.extractor.pubsub;

import com.prodigy.extractor.pubsub.vo.Event;

public interface EventPublisher {

    boolean publish (Event event);

}
