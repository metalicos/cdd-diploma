package ua.com.cyberdone.discoverymicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DiscoveryMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryMicroserviceApplication.class, args);
    }

}
