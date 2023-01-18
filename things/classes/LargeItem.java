package things.classes;

import exceptions.BusyLocationException;
import things.enums.Condition;
import things.interfaces.Lyable;
import things.interfaces.Sitable;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;


public class LargeItem extends PhysicalItem implements Lyable, Sitable {
    private final List<Condition> conditionsWhileLying;
    private final List<Condition> conditionsWhileSitting;

    public LargeItem(String name) {
        super(name);
        this.conditionsWhileLying = new ArrayList<>();
        this.conditionsWhileSitting = new ArrayList<>();
        this.conditionsWhileSitting.add(Condition.COMFORTABLE);
        this.conditionsWhileLying.add(Condition.COMFORTABLE);
    }

    private void beginAction(Person person, List<Condition> conditions) {
        person.addConditions(conditions);
    }

    private void endAction(Person person, List<Condition> conditions) {
        person.delConditions(conditions);
    }

    @Override
    public List<Condition> getConditionsWhileLying() {
        return this.conditionsWhileLying;
    }

    @Override
    public void setConditionsWhileLying(Condition condition) {
        this.conditionsWhileLying.add(condition);
    }

    @Override
    public List<Condition> getConditionsWhileSitting() {
        return this.conditionsWhileSitting;
    }

    @Override
    public void setConditionsWhileSitting(Condition condition) {
        this.conditionsWhileSitting.add(condition);
    }

    @Override
    public void clearConditionsWhileSitting() {
        this.conditionsWhileSitting.clear();
    }

    @Override
    public void clearConditionsWhileLying() {
        this.conditionsWhileLying.clear();
    }

    @Override
    public void occupySit(Person subject) {
        this.beginAction(subject, this.conditionsWhileSitting);
    }

    @Override
    public void unoccupySit(Person subject) {
        this.endAction(subject, conditionsWhileSitting);
    }

    @Override
    public void occupyLie(Person subject) {
        this.beginAction(subject, this.conditionsWhileLying);
    }

    @Override
    public void unoccupyLie(Person subject) {
        this.endAction(subject, this.conditionsWhileLying);
    }

    public SmallItem makeSmall() {
        SmallItem copy = new SmallItem(this.name);
        copy.setBusy(this.busy);
        copy.addConditions(this.conditions);
        try {
            copy.setLocation(this.getLocation());
        } catch (BusyLocationException ignored) {
        }
        return copy;
    }

    @Override
    public int hashCode() {
        int arrHash1 = 0;
        for (int i = 0; i < conditionsWhileSitting.size(); i++) {
            arrHash1 += pow(100, i) * conditionsWhileSitting.get(i).hashCode();
        }
        int arrHash2 = 0;
        for (int i = 0; i < conditionsWhileLying.size(); i++) {
            arrHash2 += pow(100, i) * conditionsWhileLying.get(i).hashCode();
        }
        return super.hashCode() + arrHash1 + arrHash2;
    }
}
