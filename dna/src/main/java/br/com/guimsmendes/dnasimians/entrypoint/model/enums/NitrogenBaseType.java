package br.com.guimsmendes.dnasimians.entrypoint.model.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public enum NitrogenBaseType {
    A, T, C, G;

    public static Optional<List<Character>> fromString(String dnaNitrogenBases) {
        List<Character> chars = new ArrayList<>();
        for (char nitrogenBase : dnaNitrogenBases.toCharArray()) {
            for (NitrogenBaseType nitrogenBaseType : values()) {
                if (nitrogenBaseType.name().equalsIgnoreCase(String.valueOf(nitrogenBase))) {
                    chars.add(Character.toUpperCase(nitrogenBase));
                }
            }
        }
        return (chars.size() == dnaNitrogenBases.toCharArray().length) ? Optional.of(chars) : Optional.empty();
    }
}
