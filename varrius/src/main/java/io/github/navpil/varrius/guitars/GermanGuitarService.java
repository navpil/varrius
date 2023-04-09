package io.github.navpil.varrius.guitars;

import io.github.navpil.guitars.GuitarService;
import org.jvnet.hk2.annotations.Service;

import java.util.List;

public class GermanGuitarService implements GuitarService {

    @Override
    public List<String> list() {
        return List.of("Musima");
    }

    @Override
    public int getPriority() {
        return 5;
    }
}
