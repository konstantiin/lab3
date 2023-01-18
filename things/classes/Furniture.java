package things.classes;

import exceptions.ActionException;
import exceptions.BusyLocationException;
import things.enums.Condition;
import things.interfaces.Lyable;
import things.interfaces.Sitable;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

public class Furniture extends SmallItem implements Sitable, Lyable {
    private final List<Condition> conditionsWhileLying;
    private final List<Condition> conditionsWhileSitting;
    private int placesForSit = 3;
    private int defaultPlacesForSit = 3;
    private int placesForLie = 1;
    private int defaultPlacesForLie = 1;

    public Furniture(String name) {
        super(name);
        this.conditionsWhileLying = new ArrayList<>();
        this.conditionsWhileSitting = new ArrayList<>();
        this.conditionsWhileSitting.add(Condition.COMFORTABLE);
        this.conditionsWhileLying.add(Condition.UNCOMFORTABLE);
    }

    public int getDefaultPlacesForSit() {
        return this.defaultPlacesForSit;
    }

    public void setDefaultPlacesForSit(int count) {
        this.placesForSit = count;
        this.defaultPlacesForSit = count;
    }

    public int getDefaultPlacesForLie() {
        return this.defaultPlacesForLie;
    }

    public void setDefaultPlacesForLie(int count) {
        this.placesForLie = count;
        this.defaultPlacesForLie = count;
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

    private int beginAction(Person person, int count, List<Condition> conditions) throws BusyLocationException {
        count--;
        if (count < 0) throw new BusyLocationException();
        if (count == 0) this.setBusy(true);
        person.addConditions(conditions);
        return count;
    }

    private int endAction(Person person, int count, int defaultCount, List<Condition> conditions) throws ActionException {
        count++;
        if (count > defaultCount) throw new ActionException("You can't end action, that didn't start");
        person.delConditions(conditions);
        this.setBusy(false);
        return count;
    }

    @Override
    public void occupySit(Person subject) {
        try {
            this.placesForSit = this.beginAction(subject, this.placesForSit, this.conditionsWhileSitting);
        } catch (BusyLocationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unoccupySit(Person subject) throws ActionException {
        subject.delConditions(this.conditionsWhileSitting);
        this.placesForSit = this.endAction(subject, this.placesForSit, this.defaultPlacesForSit, this.conditionsWhileSitting);
    }

    @Override
    public void occupyLie(Person subject) {
        try {
            this.placesForLie = this.beginAction(subject, this.placesForLie, this.conditionsWhileLying);
        } catch (BusyLocationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unoccupyLie(Person subject) throws ActionException {
        subject.delConditions(this.conditionsWhileLying);
        this.placesForLie = this.endAction(subject, this.placesForLie, this.defaultPlacesForLie, this.conditionsWhileLying);
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
        return super.hashCode() + arrHash1 + arrHash2 + placesForSit + placesForLie + defaultPlacesForLie + defaultPlacesForSit;
    }
}
