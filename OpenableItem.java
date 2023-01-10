public class OpenableItem extends SmallItem{
    private boolean if_opened = false;
    private boolean locked = false;
    private Place loc1, loc2;
    public OpenableItem(String s){
        super(s);
    }
    public boolean ifOpened(){return this.if_opened;}
    public void change(){
        this.if_opened = !this.if_opened;
    }
    public void setLocked(boolean b){this.locked = b;}
    public void set_places(Place p1, Place p2){
        this.loc1 = p1;
        this.loc2 = p2;
    }
    public Place goThrough(Living p){
        if (!this.if_opened) this.open();
        if (p.location != loc1 && p.location != loc2){
            //raise exception
        }
        if (this.loc1 == p.location) return this.loc2;
        return this.loc1;

    }
    public void lock(){
        this.locked = true;
        this.close();
    }
    public void unlock(){
        this.locked = false;
        this.open();
    }


    public void open(){
        if (!this.if_opened){
            //if (this.locked) raise exception
            this.change();
        }
        //else raise exception
    }
    public void close(){
        if (this.if_opened){
            this.change();
        }
        //else raise exception
    }

}
