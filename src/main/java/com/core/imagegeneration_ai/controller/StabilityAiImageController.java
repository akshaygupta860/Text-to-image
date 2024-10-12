package com.core.imagegeneration_ai.controller;

import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.stabilityai.api.StabilityAiImageOptions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class StabilityAiImageController {

    private final ImageModel imageModel;

    public StabilityAiImageController(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    @GetMapping("/image-gen")
    public ResponseEntity<byte[]> imageGen(@RequestParam String message) {

       /* ImageOptions options = ImageOptionsBuilder.builder()
                .withHeight(1024)
                .withWidth(1024)
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(message, options);
        ImageResponse response = imageModel.call(imagePrompt);
        String imageUrl = response.getResult().getOutput().getUrl();*/

        ImageResponse response = imageModel.call(
                new ImagePrompt(message,
                        StabilityAiImageOptions.builder()
                                .withStylePreset("cinematic")
                                .withN(4)
                                .withHeight(1024)
                                .withWidth(1024).build()));


        String base64Image = response.getResult().getOutput().getB64Json();
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageBytes,headers, HttpStatus.OK);
    }
}
