package au.com.worryfreeconsulting.DIObernasbeermanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/beerstock")

public class BeerController implements BeerControllerDocs{

    @GetMapping
    public String helloAPI() {
        return "Olá API!!";
    }
}
