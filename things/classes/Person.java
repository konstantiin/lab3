package things.classes;

import event.Event;
import exceptions.*;
import things.enums.Condition;
import things.interfaces.Lyable;
import things.interfaces.Place;
import things.interfaces.Sitable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.pow;

public class Person extends SmallItem {
    private final List<Clothes> clothes = new ArrayList<>();
    private final List<BodyPart> body = new ArrayList<>();
    private final List<Condition> forgotten = new ArrayList<>();
    private Place previousLocation = null;

    public Person(String name) {
        super(name);
        this.addCondition(Condition.WAKED);
    }

    public Person(String name, BodyPart... parts) {
        super(name);
        this.addCondition(Condition.WAKED);
        this.body.addAll(Arrays.asList(parts));
        for (BodyPart part : parts) {
            try {
                part.setLocation(this);
            } catch (BusyLocationException ignored) {
            }
        }
        this.setBusy(true);
    }

    public void hear(Sound sound) {
        List<Condition> conditions = sound.makeSound();
        if (conditions.contains(Condition.WAKED)) {
            this.wakeUp();
            conditions.remove(Condition.WAKED);
        }
        this.addConditions(conditions);
    }

    public List<Condition> lookAt(PhysicalItem item) throws AwakeException {
        if (!this.findCondition(Condition.WAKED)) throw new AwakeException("Person must be awake!");
        return item.getConditions();
    }

    public List<Condition> lookAt(List<PhysicalItem> items) throws AwakeException {
        List<Condition> res = new ArrayList<>();
        for (PhysicalItem item : items) res.addAll(this.lookAt(item));
        return res;
    }

    public List<Condition> lookAtSelf() throws AwakeException {
        List<Condition> res = new ArrayList<>();
        for (Clothes clothes : this.clothes) {
            res.addAll(this.lookAt(clothes));
        }
        return res;
    }

    public void wakeUp() {
        this.delCondition(Condition.SLEEP);
        this.addCondition(Condition.WAKED);

    }

    public void sleep() {
        this.delCondition(Condition.WAKED);
        this.addCondition(Condition.SLEEP);
        this.addCondition(Condition.STUNNED);
    }

    public void think(List<Event> thought) throws AwakeException {
        if (!this.findCondition(Condition.WAKED)) throw new AwakeException("Person must be awake!");
        Object current = null;
        for (Event event : thought) {

            current = event.exec(current);
        }
    }

    public void sit(Sitable s) throws AwakeException {
        if (!this.findCondition(Condition.WAKED)) throw new AwakeException("Person must be awake!");

        this.previousLocation = this.getLocation();
        s.occupySit(this);
        this.addCondition(Condition.SITTING);
    }

    public void lie(Lyable s) throws AwakeException {
        if (!this.findCondition(Condition.WAKED)) throw new AwakeException("Person must be awake!");
        this.previousLocation = (PhysicalItem) s;
        s.occupyLie(this);
        this.addCondition(Condition.LYING);
    }

    public void standUp() throws AwakeException, ActionException {
        if (!this.findCondition(Condition.WAKED)) throw new AwakeException("Person must be awake!");
        if (this.findCondition(Condition.SITTING)) {
            ((Sitable) this.getLocation()).unoccupySit(this);
        } else if (this.findCondition(Condition.LYING)) {
            ((Lyable) this.getLocation()).unoccupyLie(this);
        }
        try {
            this.setLocation(this.previousLocation);
        } catch (BusyLocationException ignored) {
        }
    }

    public void putOnClothes(Clothes item, BodyPart part) throws AwakeException, ClothesIsAlreadyPutException {
        if (!this.findCondition(Condition.WAKED)) throw new AwakeException("Person must be awake!");
        try {
            item.setLocation(part);
        } catch (BusyLocationException e) {
            throw new ClothesIsAlreadyPutException(("Clothes is already put on" + part.getName()), e);
        }
        clothes.add(item);
    }

    public void startIllness(Illness illness) {
        List<Condition> effects = illness.applyIllness();
        this.addConditions(effects);
    }

    public List<Clothes> takeOfClothes() throws AwakeException {
        if (!this.findCondition(Condition.WAKED)) throw new AwakeException("Person must be awake!");
        List<Clothes> clothes = new ArrayList<>(this.clothes);
        this.clothes.clear();
        for (Clothes item : clothes) {
            try {
                item.setLocation(this.getLocation());
            } catch (BusyLocationException ignored) {
            }
        }
        return clothes;
    }

    public void passThrough(OpenableItem openableItem) throws AwakeException, LocationException {
        if (!this.findCondition(Condition.WAKED)) throw new AwakeException("Person must be awake!");
        if (this.getLocation() == openableItem.getLocation2()) {
            this.open(openableItem);
            try {
                this.setLocation(openableItem.getLocation2());
            } catch (BusyLocationException ignored) {
            }
        } else if (this.getLocation() == openableItem.getLocation()) {
            this.open(openableItem);
            try {
                this.setLocation(openableItem.getLocation());
            } catch (BusyLocationException ignored) {
            }
        } else throw new LocationException("Person must be near the OpenableItem!");
        this.previousLocation = this.getLocation();
    }

    public void remember() {
        for (Condition condition : forgotten) {
            this.addCondition(condition);
        }
        forgotten.clear();
    }

    public void addForgotten(Condition condition) {
        this.forgotten.add(condition);
    }

    public void goOut() {
        Place c = this.getLocation();
        try {
            this.setLocation(this.previousLocation);
        } catch (BusyLocationException ignored) {
        }
        this.previousLocation = c;
    }

    public void open(OpenableItem openable) throws AwakeException {
        if (!this.findCondition(Condition.WAKED)) throw new AwakeException("Person must be awake!");
        if (openable.getIfOpened()) return;
        if (openable.getLocked()) {
            try {
                openable.changeLock();
            } catch (LockException e) {
                e.printStackTrace();
            }

        }
        try {
            openable.changeIfOpen();
        } catch (LockException e) {
            e.printStackTrace();
        }
    }

    public void close(OpenableItem openable) throws AwakeException {
        if (!this.findCondition(Condition.WAKED)) throw new AwakeException("Person must be awake!");
        if (!openable.getIfOpened()) {
            if (!openable.getLocked()) {
                try {
                    openable.changeLock();
                } catch (LockException e) {
                    e.printStackTrace();
                }
            }
            return;
        }
        try {
            openable.changeIfOpen();
            if (!openable.getLocked()) openable.changeLock();
        } catch (LockException e) {
            e.printStackTrace();
        }
    }

    public void forgetToLock(OpenableItem openable) throws AwakeException {
        this.close(openable);
        try {
            openable.changeLock();
        } catch (LockException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int hashCode() {
        int arrHashClo = 0;
        for (int i = 0; i < clothes.size(); i++) {
            arrHashClo += pow(100, i) * clothes.get(i).hashCode();
        }
        int arrHashForg = 0;
        for (int i = 0; i < forgotten.size(); i++) {
            arrHashForg += pow(100, i) * forgotten.get(i).hashCode();
        }
        return super.hashCode() * 31 + arrHashClo + arrHashForg;
    }

    public void shakeAll() {
        for (BodyPart part : body) {
            part.shake();
        }
    }
}
