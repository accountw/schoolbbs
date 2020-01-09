package cdu.zb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zb.mapper")
public class SchoolbbsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolbbsServerApplication.class, args);
    }

}
