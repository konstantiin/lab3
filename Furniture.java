public class Furniture extends SmallItem implements Sitable, Lyable{

    public Furniture(String name){
        super(name);
    }
    @Override
    public void takeASit(Living subject){
        if (!this.busy){
            subject.location = this;
            this.setBusy(true);
        }
        //else raise exception
    }
    @Override
    public void startLying(Living subject){
        if (!this.busy){
            subject.location = this;
            this.setBusy(false);
        }
        //else raise exception
    }
}
