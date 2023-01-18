package things.classes;

import exceptions.InvalidConditionsException;
import things.enums.Condition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Illness extends AbstractItem {
    private final Random random = new Random();
    private int strength;

    public Illness(String name) {
        super(name);
        this.strength = 50;
    }

    public int getStrength() {
        return this.strength;
    }

    public void setStrength(int value) {
        this.strength = value;
    }

    public List<Condition> applyIllness() {
        List<Condition> conditions = new ArrayList<>();
        for (Condition condition : this.getEffects()) {
            if (random.nextInt(100) <= this.getStrength()) conditions.add(condition);
        }
        return conditions;
    }

    @Override
    public void setEffects(Condition... conditions) throws InvalidConditionsException {
        for (Condition item : conditions) {
            if (!item.isForLiving()) throw new InvalidConditionsException("Conditions must be applicable to Person");
        }
        super.setEffects(conditions);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + strength;
    }
}
