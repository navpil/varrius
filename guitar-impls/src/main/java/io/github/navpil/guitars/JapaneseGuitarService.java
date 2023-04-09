package io.github.navpil.guitars;

import java.util.List;

public class JapaneseGuitarService implements GuitarService {

    @Override
    public List<String> list() {
        return List.of("Yamaha", "Ibanez");
    }

    @Override
    public int getPriority() {
        return 1;
    }


}
