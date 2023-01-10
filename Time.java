public class Time extends AbstractItem{

    private boolean dark = false;
    public Time(String n){
        super(n);
    }
    public void setDark(boolean d){this.dark = d;}
    public boolean ifDark(){
        return dark;
    }
}
