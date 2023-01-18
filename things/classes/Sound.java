package things.classes;

import exceptions.InvalidConditionsException;
import things.enums.Condition;

import java.util.List;

public class Sound extends AbstractItem {
    private int volume;

    public Sound(String name) {
        super(name);
        this.volume = 100;

    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public List<Condition> makeSound() {
        if (this.getVolume() > 200) {
            try {
                this.setEffects(Condition.WORRIED);
            } catch (InvalidConditionsException ignored) {
            }
        }
        if (this.getVolume() > 70) {
            try {
                this.setEffects(Condition.WAKED);
            } catch (InvalidConditionsException ignored) {
            }
        }
        return this.getEffects();
    }

    @Override
    public int hashCode() {
        return super.hashCode() + this.volume;
    }
}
