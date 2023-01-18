package event;

import things.classes.PhysicalItem;
import things.classes.Time;
import things.enums.Condition;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class Event {
    private final ArrayList<Object> args = new ArrayList<>();
    private final Object subj;
    private final Time curTime;
    private Method met;
    private int times = 1;
    private boolean returnVal = false;

    public Event(Object subj, String met, Time time, Class<?>[] Types, Object... Args) {
        this.subj = subj;
        try {
            this.met = subj.getClass().getMethod(met, Types);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        this.curTime = time;
        if (Args == null) return;
        this.args.addAll(Arrays.asList(Args));
    }

    public Event(Object subj, String met, Time time) {
        this.subj = subj;
        try {
            this.met = subj.getClass().getMethod(met);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        this.curTime = time;
    }

    public void setReturnVal(boolean ifReturned) {
        this.returnVal = ifReturned;
    }

    public void setTimes(int time) {
        this.times = time;
    }


    private void applyTime() {
        if (this.subj != null && this.subj instanceof PhysicalItem) {
            if (this.curTime.ifDark()) ((PhysicalItem) this.subj).addCondition(Condition.DARK);
            else ((PhysicalItem) this.subj).addCondition(Condition.LIGHT);
        }
        for (Object obj : this.args) {
            if (obj instanceof PhysicalItem) {
                if (this.curTime.ifDark()) ((PhysicalItem) obj).addCondition(Condition.DARK);
                else ((PhysicalItem) obj).addCondition(Condition.LIGHT);
            }
        }
    }

    public Object exec(Object cur) {
        if (cur != null) {
            this.args.add(cur);
        }

        this.applyTime();
        Object res = null;
        for (int i = 0; i < this.times; i++) {
            try {
                res = this.met.invoke(this.subj, this.args.toArray());
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //System.out.println(subj.toString() + " выполнил " + this.met.getName());
        if (this.returnVal) {
            return res;
        }
        return null;
    }

}
