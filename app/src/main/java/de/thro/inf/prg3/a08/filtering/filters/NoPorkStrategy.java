package de.thro.inf.prg3.a08.filtering.filters;

import java.util.List;

import de.thro.inf.prg3.a08.model.Meal;
import de.thro.inf.prg3.a08.filtering.FilterBase;

public class NoPorkStrategy extends FilterBase {
    @Override
    protected boolean include(Meal m) {
        boolean containsPork = false;

        for(String s : m.getNotes()){
            containsPork = containsPork || s.contains("Schwein") || s.contains("schwein");
        }

        return !containsPork;
    }
}
