package BusinessLogic.Entities;
/**
 * Clase para representar a los usuarios de la tabla en el programa
 */
public class User {
    String jlID;
    String jlPSWRD;
    
    /**
     * Constructor de la clase user
     * @param ID Nombre de usuario
     * @param PSWRD Clave del usuario
     */
    public User(String ID, String PSWRD){
        this.jlID=ID;
        this.jlPSWRD=PSWRD;
    }

    public String jlGetID() {
        return jlID;
    }

    public void jlSetID(String iD) {
        jlID = iD;
    }

    public String jlGetPSWRD() {
        return jlPSWRD;
    }

    public void jlSetPSWRD(String PSWRD) {
        jlPSWRD = PSWRD;
    }
    
}
