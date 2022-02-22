package KeeperGroup.KeeperBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class KeeperBackApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(KeeperBackApplication.class);

	public static void main(String[] args) {
		LOGGER.info("This is INFO");
		SpringApplication.run(KeeperBackApplication.class, args);
		LOGGER.info("This is INFO to");
	}

}
