import java.util.*;
public class Illness extends AbstractItem{
    private ArrayList<Condition> effects = new ArrayList<>();
    public Illness(String n){
        super(n);
    }
    public ArrayList<Condition> getEffects(){
        return this.effects;
    }
    public void setEffects(Condition... x){
        // можно проверить подходит ли condition для людей
        effects.addAll(Arrays.asList(x));
    }

}
