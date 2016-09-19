package guava;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class EventBusTest {
    private static Logger LOGGER = LoggerFactory.getLogger(EventBusTest.class);

    public static void main(String[] args) throws InterruptedException {
        EventBusTest test = new EventBusTest();
        EventBus eventBus = test.eventBus();
        eventBus.post(Lists.newArrayList(1, 2, 3));
        TimeUnit.SECONDS.sleep(100);
    }

    public EventBus eventBus() {
        EventBus eventBus = new EventBus();
        eventBus.register(new DeadEventListener());
        eventBus.register(new MessageListener());
        return eventBus;
    }

    class MessageListener {
        @Subscribe
        public void listen(String event) {
            LOGGER.info("MessageListener: {}", event);
        }

        @Subscribe
        public void listen(List<Integer> event) {
            LOGGER.info("MessageListener: {}", event);
        }
    }

    class DeadEventListener {
        @Subscribe
        public void listen(DeadEvent event) {
            LOGGER.info("deadEvent: {}", event);
        }
    }

}
