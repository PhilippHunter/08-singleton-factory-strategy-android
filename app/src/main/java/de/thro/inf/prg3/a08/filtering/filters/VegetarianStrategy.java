package de.thro.inf.prg3.a08.filtering.filters;

import java.util.List;

import de.thro.inf.prg3.a08.model.Meal;
import de.thro.inf.prg3.a08.filtering.FilterBase;

public class VegetarianStrategy extends FilterBase {
    @Override
    protected boolean include(Meal m) {
        return m.isVegetarian();
    }
}
