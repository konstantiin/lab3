package things.classes;

import exceptions.BusyLocationException;
import exceptions.LockException;
import things.interfaces.Place;

public class OpenableItem extends SmallItem {
    private boolean ifOpened = false;
    private boolean locked = false;
    private Place location2;

    public OpenableItem(String s, Place p1, Place p2) throws BusyLocationException {
        super(s);
        this.setLocation(p1);
        this.setLocation2(p2);
    }

    public Place getLocation2() {
        return this.location2;
    }

    public void setLocation2(Place place) throws BusyLocationException {
        if (place.ifBusy()) throw new BusyLocationException();
        this.location2 = place;
    }

    public void changeIfOpen() throws LockException {
        if (this.locked) throw new LockException("Openable must be unlocked first!");
        this.ifOpened = !this.ifOpened;
    }

    public void changeLock() throws LockException {
        if (this.ifOpened) {
            if (!this.locked) throw new LockException("Openable must be closed to be locked!");
        }
        this.locked = !this.locked;
    }

    public boolean getIfOpened() {
        return this.ifOpened;
    }

    public boolean getLocked() {
        return this.locked;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + location2.hashCode();
    }

}
