package BusinessLogic.Entities;

public class Coord {
    int jlCap;
    String jlGeo;
    String jlArs;
    
    public Coord(int jlCap, String jlGeo, String jlArs) {
        this.jlCap = jlCap;
        this.jlGeo = jlGeo;
        this.jlArs = jlArs;
    }

    public int jlGetjlCap() {
        return jlCap;
    }

    public void jlSetjlCap(int jlCap) {
        this.jlCap = jlCap;
    }

    public String jlGetjlGeo() {
        return jlGeo;
    }

    public void jlSetjlGeo(String jlGeo) {
        this.jlGeo = jlGeo;
    }

    public String jlGetjlArs() {
        return jlArs;
    }

    public void jlSetjlArs(String jlArs) {
        this.jlArs = jlArs;
    }
    
}
