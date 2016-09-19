package dropwizard;

import com.codahale.metrics.*;
import java.util.concurrent.TimeUnit;

public class GetStarted{
    static final MetricRegistry metrics = new MetricRegistry();
    public static void main(String args[]) {
    
        startReport();
        Meter requests = metrics.meter("requests");
        requests.mark();
        requests.mark();
        wait5Seconds();
    }
    
    static void startReport() {
    
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS).build();
        reporter.start(4, TimeUnit.SECONDS);
    }
    
    static void wait5Seconds() {
    
        try {
            Thread.sleep(50 * 1000);
        } catch (InterruptedException e) {
        }
    }
}