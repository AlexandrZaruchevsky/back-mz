package ru.az.mz;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AppStarter {

	public static void main(String[] args) {
		new SpringApplicationBuilder(AppStarter.class)
				.bannerMode(Banner.Mode.OFF)
				.run(args);
	}

}
