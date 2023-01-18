package things.enums;

public enum Condition {
    COLD(true, "озноб"), BROKEN(false, "сломан"), STUNNED(true, "остолбенелый"), MAD(true, "сошёл с ума"),
    WORRIED(true, "волнение"), DARK(false, "темно"), LIGHT(false, "светло"),
    DRUNK(true, "пьян"), DIRTY(false, "загрязнён"), COMFORTABLE(true, "комфортно"), UNCOMFORTABLE(true, "некомфортно"),
    SITTING(true, "сидит"), LYING(true, "лежит"), TORN(false, "порвана"), WAKED(true, "не спит"), SLEEP(true, "спит");
    private final boolean forLiving;
    private final String name;

    Condition(boolean forLiving, String name) {
        this.forLiving = forLiving;
        this.name = name;
    }

    public boolean isForLiving() {
        return this.forLiving;
    }

    @Override
    public String toString() {
        return name;
    }
}
