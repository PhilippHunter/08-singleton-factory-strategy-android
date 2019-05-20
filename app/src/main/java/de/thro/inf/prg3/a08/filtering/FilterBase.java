package de.thro.inf.prg3.a08.filtering;

import java.util.LinkedList;
import java.util.List;

import de.thro.inf.prg3.a08.model.Meal;

public abstract class FilterBase implements MealsFilter {

    @Override
    public List<Meal> filter(List<Meal> meals) {
        List<Meal> result = new LinkedList<>();

        for(Meal m : meals) {
            if(include(m))
                result.add(m);
        }

        return result;
    }

    protected abstract boolean include(Meal m);
}
