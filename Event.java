import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class Event {
    private ArrayList<Object> args = new ArrayList<>();
    private Object subj;
    private Method met;
    private Time curTime;
    private int times = 1;
    private boolean returnVal = false;
    public Event(Object s, String m, Time t, Class <?>[] Types, Object... Args) throws NoSuchMethodException {
        this.subj = s;
        this.met = s.getClass().getMethod(m, Types);
        this.curTime = t;
        if (Args == null) return;
        this.args.addAll(Arrays.asList(Args));
    }
    public void setReturnVal(boolean b){this.returnVal = b;}
    public void setTimes(int t){this.times = t;}

    public Event(Object s, String m, Time t) throws NoSuchMethodException {
        this.subj = s;
        this.met = s.getClass().getMethod(m);
        this.curTime = t;
    }
    private void applyTime(){
        if (this.subj != null && this.subj instanceof PhysicalItem) {
            if (this.curTime.ifDark()) ((PhysicalItem) this.subj).addCondition(Condition.DARK);
            else ((PhysicalItem) this.subj).addCondition(Condition.LIGHT);
        }
        if (this.args == null) return;
        for (Object obj: this.args) {
            if (obj instanceof PhysicalItem) {
                if (this.curTime.ifDark()) ((PhysicalItem) obj).addCondition(Condition.DARK);
                else ((PhysicalItem) obj).addCondition(Condition.LIGHT);
            }
        }
    }
    public Object exec(Object cur) throws InvocationTargetException, IllegalAccessException {
        if (cur != null) {
            this.args.add(cur);
        }

        this.applyTime();
        Object t = null;
        for (int i = 0; i < this.times; i++) {
            t = this.met.invoke(this.subj, this.args.toArray());
        }
        if (this.returnVal) {
            return t;
        }
        return null;
    }
    private void printCondition(){
        System.out.println(this.subj);
    }
}
