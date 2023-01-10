import java.util.ArrayList;

public class PhysicalItem extends Anything implements Place{

    protected boolean busy = false;
    protected ArrayList<Condition> conditions = new ArrayList<>();
    public PhysicalItem(String n){
        super(n);
    }

    public void addCondition(Condition x){
        this.conditions.add(x);
    }
    public void delCondition(Condition x){
        this.conditions.remove(x);
    }
    public boolean findCondition(Condition x){
        return conditions.contains(x);
    }
    public ArrayList<Condition> getConditions(){
        return this.conditions;
    }

    @Override
    public boolean ifBusy(){
        return busy;
    }
    @Override
    public void setBusy(boolean x){
        this.busy = x;
    }
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        for (Condition x: this.conditions){
            res.append(x.toString());
        }
        return res + super.toString();
    }
}
