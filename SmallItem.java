public class SmallItem extends PhysicalItem{

    private SmallItem dirt = null;
    public SmallItem(String name){
        super(name);
    }

    public void broke(){
        this.addCondition(Condition.BROKEN);
    }
    public void fall(){
        this.setLocation(new LargeItem("пол"));
    }
    public void makeDirty(SmallItem d){
        this.addCondition(Condition.DIRTY);
        this.dirt = d;
    }
}
