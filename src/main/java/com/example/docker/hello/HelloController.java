package com.example.docker.hello;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/ping")
    public String ping() {
        return helloService.getResult();
    }

    // Custom my metric

    @Autowired
    private MeterRegistry meterRegistry;

    @GetMapping("/hi/{name}")
    public String hi(@PathVariable String name) {

        // Metric name = tracking.user
        Counter counter = meterRegistry.counter("tracking.user", "username", name);
        counter.increment();

        return "Hi, " + name;
    }


    // Call service 2

    @Autowired
    RestTemplate restTemplate;

    @Value("${service2.url}")
    private String service2Url;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/call")
    public String callAnotherService() {
        String result = restTemplate.getForObject(service2Url + "/target", String.class);
        return result;
    }

}
// mvnw spring-boot:run
