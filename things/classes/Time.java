package things.classes;

import exceptions.InvalidConditionsException;
import things.enums.Condition;

public class Time extends AbstractItem {

    public Time(String name) {
        super(name);
        try {
            this.setEffects(Condition.LIGHT);
        } catch (InvalidConditionsException ignored) {
        }
    }

    public void setDark(boolean dark) {
        if (dark) {
            this.delEffects(Condition.LIGHT);
            try {
                this.setEffects(Condition.DARK);
            } catch (InvalidConditionsException ignored) {
            }
        } else {
            this.delEffects(Condition.DARK);
            try {
                this.setEffects(Condition.LIGHT);
            } catch (InvalidConditionsException ignored) {
            }
        }
    }

    public boolean ifDark() {
        return this.findEffect(Condition.DARK);
    }
}
