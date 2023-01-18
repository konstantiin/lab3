package things.interfaces;

import exceptions.ActionException;
import things.classes.Person;
import things.enums.Condition;

import java.util.List;

public interface Lyable {
    void occupyLie(Person subject);

    void unoccupyLie(Person subject) throws ActionException;

    List<Condition> getConditionsWhileLying();

    void setConditionsWhileLying(Condition condition);

    void clearConditionsWhileLying();
}
