package io.github.navpil.varrius.guitars;

import io.github.navpil.guitars.GuitarService;
import org.jvnet.hk2.annotations.Service;

import java.util.List;

@Service
public class UkrainianGuitarService implements GuitarService {

    @Override
    public List<String> list() {
        return List.of("Woodstock", "Universum", "Trembita");
    }

    @Override
    public int getPriority() {
        return 100000;
    }
}
