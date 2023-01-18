package things.classes;

import exceptions.BusyLocationException;
import things.enums.Condition;
import things.interfaces.Place;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;


public class PhysicalItem extends Anything implements Place {

    protected boolean busy = false;
    protected List<Condition> conditions = new ArrayList<>();
    private Place location = null;

    public PhysicalItem(String name) {
        super(name);
    }

    public Place getLocation() {
        return this.location;
    }

    public void setLocation(Place place) throws BusyLocationException {
        if (place != null && place.ifBusy()) throw new BusyLocationException();
        this.location = place;
    }

    public void addCondition(Condition condition) {
        if (this.findCondition(condition)) return;
        this.conditions.add(condition);
    }

    public void addConditions(List<Condition> conditions) {
        for (Condition condition : conditions) this.addCondition(condition);
    }

    public void delCondition(Condition condition) {
        this.conditions.remove(condition);
    }

    public boolean findCondition(Condition condition) {
        return conditions.contains(condition);
    }

    public List<Condition> getConditions() {
        return this.conditions;
    }

    public void delConditions(List<Condition> conditions) {
        this.conditions.removeAll(conditions);
    }

    public boolean ifBusy() {
        return busy;
    }

    @Override
    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Condition condition : this.conditions) {
            res.append(condition.toString());
            res.append(" ");
        }
        return res + super.toString();
    }

    @Override
    public int hashCode() {
        int arrHash = 0;
        for (int i = 0; i < conditions.size(); i++) {
            arrHash += pow(100, i) * conditions.get(i).hashCode();
        }
        return super.hashCode() + location.hashCode() + arrHash;
    }
}
