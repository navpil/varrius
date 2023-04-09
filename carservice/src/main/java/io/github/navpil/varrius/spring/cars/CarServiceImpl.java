package io.github.navpil.varrius.spring.cars;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    public List<String> list() {
        return List.of("Mazda", "BMW");
    }
}
