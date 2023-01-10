public class Anything {
    protected String name;
    protected Place location;

    public Anything(String s){
        name = s;
    }
    public void setLocation(Place p){
        this.location = p;
    }
    public Place getLocation(){return this.location;}



    @Override
    public int hashCode(){
        return name.hashCode();
    }
    @Override
    public boolean equals(Object o){
        return this.getClass() == o.getClass() && this.hashCode() == o.hashCode();
    }
    @Override
    public String toString(){
        return name;
    }
}
