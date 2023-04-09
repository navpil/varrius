package io.github.navpil.guitars;

import org.jvnet.hk2.annotations.Contract;

import java.util.List;

@Contract
public interface GuitarService {

    List<String> list();

    int getPriority();
}
