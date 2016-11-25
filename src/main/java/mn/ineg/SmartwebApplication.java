package mn.ineg;

import mn.ineg.properties.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SmartwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartwebApplication.class, args);
	}
}
