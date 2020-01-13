package io.github.onlyeat3.bordercorllie.shutdown.graceful;

import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShutdownController {
    @Autowired private DiscoveryClient discoveryClient;

    @GetMapping("/shutdown")
    public void shutdown(){
        this.discoveryClient.shutdown();
    }
}
