package com.core.imagegeneration_ai;

import org.springframework.ai.image.ImageModel;
import org.springframework.ai.stabilityai.StabilityAiImageModel;
import org.springframework.ai.stabilityai.api.StabilityAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ImagegenerationAiApplication {


	public static void main(String[] args) {
		SpringApplication.run(ImagegenerationAiApplication.class, args);
	}

	@Bean
	ImageModel imageModel(@Value("${spring.ai.stabilityai.api-key}") String apiKey) {
	  return new StabilityAiImageModel(new StabilityAiApi(apiKey));
	}

}
