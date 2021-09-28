package lazy.agency.spring.carexpenses.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"lazy.agency.spring.carexpenses"})
@EnableSwagger2
public class Application
{
    public static void main( String[] args )
    {
    	SpringApplication.run(Application.class, args);
    }

}
