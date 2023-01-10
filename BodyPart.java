public class BodyPart extends SmallItem{

    Living living;
    boolean is_shaking = false;
    public BodyPart(String s, Living x){
        super(s);
        this.living = x;
    }
    public void shake(){
        if (this.living.findCondition(Condition.COLD)){
            this.is_shaking = true;
        }
        //else raise exception
    }
}
