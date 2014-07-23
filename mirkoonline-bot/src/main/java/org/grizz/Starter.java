package org.grizz;

import org.grizz.springconfig.MainConfig;
import org.springframework.boot.SpringApplication;

/**
 * Hello world!
 *
 */
public class Starter {
    public static void main(String... args) {
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }
        System.setProperty("server.port", webPort);

        SpringApplication.run(MainConfig.class, args);
    }
}
