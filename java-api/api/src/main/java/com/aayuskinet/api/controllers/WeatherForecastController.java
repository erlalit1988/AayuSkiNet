package com.aayuskinet.api.controllers;

import com.aayuskinet.api.WeatherForecast;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("weatherforecast")
public class WeatherForecastController {

    private static final String[] SUMMARIES = new String[]{
            "Freezing", "Bracing", "Chilly", "Cool", "Mild", "Warm", "Balmy", "Hot", "Sweltering", "Scorching"
    };

    @GetMapping
    public List<WeatherForecast> get() {
        Random random = new Random();
        return Arrays.stream(new int[5])
                .mapToObj(i -> new WeatherForecast(
                        LocalDate.now().plusDays(i + 1),
                        random.nextInt(76) - 20, // -20 to 55
                        SUMMARIES[random.nextInt(SUMMARIES.length)]
                ))
                .collect(Collectors.toList());
    }
}
