package settings.variants;

import java.util.List;
import java.util.stream.Stream;

public enum AnimalBehaviorVariant {
    FULL_PREDICTABLE,
    SOME_CRAZY_MOVES;

    public final double normalMoveProbability = 0.8;

    public double getNormalMoveProbability() {
        return this.normalMoveProbability;
    }

    @Override
    public String toString() {
        return this.name().substring(0,1).toUpperCase() + this.name().substring(1, this.name().indexOf("_")).toLowerCase() + " "+ this.name().substring(this.name().indexOf("_") + 1).toLowerCase();
    }
    public AnimalBehaviorVariant fromString(String value) {
        return AnimalBehaviorVariant.valueOf(value.toUpperCase());
    }

    public static List<String> getValues() {
        return Stream.of(AnimalBehaviorVariant.values()).map(AnimalBehaviorVariant::toString).toList();
    }
}
