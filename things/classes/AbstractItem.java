package things.classes;

import exceptions.InvalidConditionsException;
import things.enums.Condition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.pow;


public class AbstractItem extends Anything {
    private final List<Condition> effects = new ArrayList<>();

    public AbstractItem(String name) {
        super(name);
    }

    public List<Condition> getEffects() {
        return this.effects;
    }

    public void setEffects(Condition... conditions) throws InvalidConditionsException {
        effects.addAll(Arrays.asList(conditions));
    }

    public void delEffects(Condition... conditions) {
        this.effects.removeAll(Arrays.asList(conditions));
    }

    public boolean findEffect(Condition condition) {
        return this.effects.contains(condition);
    }

    @Override
    public int hashCode() {
        int arrHash1 = 0;
        for (int i = 0; i < effects.size(); i++) {
            arrHash1 += pow(100, i) * effects.get(i).hashCode();
        }

        return super.hashCode() + arrHash1;
    }
}