package things.interfaces;

import exceptions.ActionException;
import things.classes.Person;
import things.enums.Condition;

import java.util.List;

public interface Sitable {

    void occupySit(Person subject);

    void unoccupySit(Person subject) throws ActionException;

    List<Condition> getConditionsWhileSitting();

    void setConditionsWhileSitting(Condition condition);

    void clearConditionsWhileSitting();

}
