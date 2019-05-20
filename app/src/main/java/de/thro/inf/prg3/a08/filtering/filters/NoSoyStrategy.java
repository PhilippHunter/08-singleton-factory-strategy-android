package de.thro.inf.prg3.a08.filtering.filters;

import java.util.List;

import de.thro.inf.prg3.a08.model.Meal;
import de.thro.inf.prg3.a08.filtering.FilterBase;

public class NoSoyStrategy extends FilterBase {
    @Override
    protected boolean include(Meal m) {
        boolean containsSoy = false;

        for(String s : m.getNotes()) {
            containsSoy = containsSoy || s.contains("Soja") || s.contains("soja");
        }

        return containsSoy;
    }
}
