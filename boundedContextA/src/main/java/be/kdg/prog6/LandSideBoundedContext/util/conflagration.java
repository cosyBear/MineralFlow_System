package be.kdg.prog6.LandSideBoundedContext.util;


import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
public class conflagration {

//    @PostConstruct
//    public void loadSQL() throws IOException {
//        Resource resource = new ClassPathResource("java/resources/data.sql");
//        String sql = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
//        jdbcTemplate.execute(sql);
//    }

}
