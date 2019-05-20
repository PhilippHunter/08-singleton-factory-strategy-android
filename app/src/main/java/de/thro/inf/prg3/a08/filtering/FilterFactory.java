package de.thro.inf.prg3.a08.filtering;

import de.thro.inf.prg3.a08.filtering.filters.NoPorkStrategy;
import de.thro.inf.prg3.a08.filtering.filters.NoSoyStrategy;
import de.thro.inf.prg3.a08.filtering.filters.VegetarianStrategy;

public abstract class FilterFactory {
    public static MealsFilter getFilter(String key) {
        switch (key) {
            case "Vegetarian":
                return new VegetarianStrategy();
            case "Soy":
                return new NoSoyStrategy();
            case "Pork":
                return new NoPorkStrategy();
        }
        return new AllMealsStrategy();
    }
}
