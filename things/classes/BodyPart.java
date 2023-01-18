package things.classes;

public class BodyPart extends SmallItem {
    boolean isShaking = false;

    public BodyPart(String name) {
        super(name);
    }

    public void shake() {
        this.isShaking = true;
    }

    public void stopShake() {
        this.isShaking = false;
    }
}
