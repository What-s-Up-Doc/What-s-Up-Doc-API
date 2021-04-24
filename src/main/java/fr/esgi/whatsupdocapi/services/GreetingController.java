package fr.esgi.whatsupdocapi.services;

import java.util.concurrent.atomic.AtomicLong;

import fr.esgi.whatsupdocapi.repository.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private static final String template2 = "Test, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final AtomicLong counterDeploy = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/deploy")
    public Greeting deploy(@RequestParam(value = "name", defaultValue = "Deploy") String name) {
        return new Greeting(counterDeploy.incrementAndGet(), String.format(template2, name));
    }
}