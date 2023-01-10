import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Living extends PhysicalItem{
    private boolean isAwake = false;
    private Place loc2 = null;
    private ArrayList<Clothes> cls = new ArrayList<Clothes>();
    private ArrayList<Condition> forgotten = new ArrayList<>();
    public Living(String name){
        super(name);
    }

    public void hear(Sound s){
        int x = s.make_sound();
        if (x > 70) {
            wakeUp();
            this.delCondition(Condition.STUNNED);
        }
    }
    public ArrayList<Condition> lookAt(PhysicalItem s){
        //if (isAwake == 0) raise exception
        return s.getConditions();
    }
    public ArrayList<Condition> lookAt(ArrayList<PhysicalItem> x){
        ArrayList <Condition> res = new ArrayList<>();
        for (PhysicalItem s: x) res.addAll(this.lookAt(s));
        return res;
    }
    public ArrayList<Condition> lookAtSelf(){
        ArrayList <Condition> res = new ArrayList<>();
        for (Clothes x: this.cls){
            res.addAll(this.lookAt(x));
        }
        return res;
    }
    public void wakeUp(){
        this.isAwake = true;
    }
    public void sleep(){
        this.isAwake = false;
        this.addCondition(Condition.STUNNED);
    }
    /*public <T1, T2> void speak(ArrayList<Event<T1, T2>> a) throws InvocationTargetException, IllegalAccessException {

        //if (isAwake == 0) raise exception
        for (Event<T1, T2> x: a){
            x.exec();
        }
        Sound s = new Sound("говорит");
        s.setVolume(50);
    }*/
    public void think(ArrayList<Event> a) throws InvocationTargetException, IllegalAccessException {
        //if (isAwake == 0) raise exception
        Object cur = null;
        for (Event x: a){
            cur = x.exec(cur);
        }
    }
    public void sit(Sitable s){
        //if (isAwake == 0) raise exception

        this.loc2 = (PhysicalItem)s;
        s.takeASit(this);
    }
    public void lie(Lyable s) {
        //if (isAwake == 0) raise exception

        this.loc2 = (PhysicalItem)s;
        s.startLying(this);
    }
    public void standUp(){
        this.location.setBusy(false);
        this.location = this.loc2;
    }
    public void startIllness(Illness illness){
        for (Condition x: illness.getEffects()){
            this.addCondition(x);
        }
    }

    public void putOnClothes(Clothes x, BodyPart p){
        x.putOn(p);
        cls.add(x);
    }

    public ArrayList<Clothes> takeOfClothes(){
        ArrayList<Clothes> c = (ArrayList<Clothes>)cls.clone();
        cls.clear();
        return c;
    }
    public void goOut(){
        this.setLocation(new LargeItem("улица"));
    }
    public void remember(){
        for (Condition x: forgotten) {
            this.addCondition(x);
        }
        forgotten.clear();
    }
    public void addForgotten(Condition x){
        this.forgotten.add(x);
    }
    public void open(OpenableItem o){
        o.open();
    }
    public void forgetToLock(OpenableItem o){
        o.close();
        o.setLocked(false);
    }
}
