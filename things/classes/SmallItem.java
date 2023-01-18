package things.classes;

import exceptions.BusyLocationException;
import things.enums.Condition;

public class SmallItem extends PhysicalItem {

    private SmallItem dirt = null;

    public SmallItem(String name) {
        super(name);
    }

    public void broke() {
        this.addCondition(Condition.BROKEN);
    }

    public void fall() {
        try {
            this.setLocation(new LargeItem("пол"));
        } catch (BusyLocationException ignored) {
        }
    }

    public void makeDirty(SmallItem dirt) {
        this.addCondition(Condition.DIRTY);
        this.dirt = dirt;
    }

    public SmallItem getDirt() {
        return this.dirt;
    }

    public LargeItem makeLarge() {
        LargeItem copy = new LargeItem(this.name);
        try {
            copy.setLocation(this.getLocation());
        } catch (BusyLocationException ignored) {
        }
        copy.addConditions(this.conditions);
        copy.setBusy(this.busy);
        return copy;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + dirt.hashCode();
    }
}
