public class Sound extends AbstractItem{
    private int volume;
    public Sound(String name){
        super(name);
        this.volume = 100;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getVolume() {
        return this.volume;
    }

    public int make_sound(){
        // может делать что-нибудь ещё, но я не придумал
        return this.volume;
    }
}
