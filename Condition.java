public enum Condition{
    COLD(true, "озноб"), BROKEN(false, "сломан"), STUNNED(true, "остолбенелый"), MAD(true, "сошёл с ума"),
    WORRIED(true, "волнение"), DARK(false, "темно"), LIGHT(false, "светло"),
    DRUNK(true, "пьян"), DIRTY(false,"загрязнён");
    private final boolean forLiving;
    private final String name;
    Condition (boolean x, String n){
        this.forLiving = x;
        this.name = n;
    }
    public boolean isForLiving(){
        return this.forLiving;
    }
    @Override
    public String toString(){
        return name;
    }
}
