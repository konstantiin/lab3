package things.classes;


import static java.lang.Math.pow;

public class Anything {
    protected String name;

    public Anything(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    private int hashName() {
        int s = 0;
        for (int i = 0; i < name.length(); i++) {
            s += pow(10, i) * (int) name.charAt(i);
        }
        return s;
    }

    @Override
    public int hashCode() {
        return hashName();
    }

    @Override
    public boolean equals(Object obj) {
        return this.getClass() == obj.getClass() && this.hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

}
