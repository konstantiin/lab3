package things.classes;

import things.enums.Condition;


public class Clothes extends SmallItem {
    public Clothes(String name) {
        super(name);
    }

    @Override
    public void broke() {
        this.addCondition(Condition.TORN);
    }
}
