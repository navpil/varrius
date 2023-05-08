package io.github.navpil.varrius.spring;

import io.github.navpil.varrius.spring.cars.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("all")
    public List<String> list() {
        return carService.list();
    }

}
