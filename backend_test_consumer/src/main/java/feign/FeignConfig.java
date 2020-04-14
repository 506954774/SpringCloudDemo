package feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ilinklink on 2020/4/13.
 */
@Configuration
public class FeignConfig {
    @Bean
    public Contract contract(){
        return new feign.Contract.Default();
    }
}
