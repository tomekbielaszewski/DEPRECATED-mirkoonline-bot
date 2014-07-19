package org.grizz.springconfig;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Grizz on 2014-07-19.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("org.grizz")
public class MainConfig {
}
