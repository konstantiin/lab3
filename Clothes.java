public class Clothes extends SmallItem{
    public Clothes(String n){
        super(n);
    }
    public void takeOf(){
        this.location = ((PhysicalItem)this.location).location;
    }
    public void putOn(BodyPart p){
        this.location = p;

    }
}
