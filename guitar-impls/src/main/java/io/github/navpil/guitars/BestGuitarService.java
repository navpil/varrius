package io.github.navpil.guitars;

import java.util.List;

public class BestGuitarService implements GuitarService {

    @Override
    public List<String> list() {
        return List.of("Fender Stratocaster", "Gipson Les Paul");
    }

    @Override
    public int getPriority() {
        return 1000;
    }


}
