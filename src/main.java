
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import BusinessLogic.*;
import BusinessLogic.Entities.*;
import Framework.AppConfiguration;

public class main {
    //Declaracion de variables globales y del patron de expresion regular correspondiente
    static final String JLNOMBRE_COMPLETO= "JONATHAN JAIME LUZURIAGA VELEZ";
    static final int JLCEDULA_LUZU= 1753716867;
    static final String JLREGEX= "a*b+c";

    /**
     * Procedimiento principal bajo el cual corre el programa
     * @param args
     */
    public static void main(String[] args) {
        //Empezamos comprobando la inicializacion de la base de datos
        AppConfiguration.load("src/config.properties"); 
        System.out.println(AppConfiguration.getDBName());
        System.out.println(AppConfiguration.getDBPathConnection());
        //Declaramos un escaner para el log in
        Scanner jlScanner = new Scanner(System.in);
        //Booleano, strings y numero de intentos para el log in
        boolean jlLoggedIn = false;
        int attempts=0;
        String jlEnteredUsername;
        String jlEnteredPassword;
        try{
            //Inicializamos los usuarios
            UserBL jlUser =  new UserBL();
            System.out.println("[Usuarios]" );
            do{
                //Procedemos a pedir el input del usuario
                System.out.print("Ingrese usuario: ");
                jlEnteredUsername = jlScanner.nextLine();
                System.out.print("Ingrese clave: ");
                jlEnteredPassword = jlScanner.nextLine();
                //Comprobamos con la lista de usuarios lo ingresado
                for (User p : jlUser.jlGetAllUsers()) {
                    if(jlEnteredUsername.equals(p.jlGetID()) && jlEnteredPassword.equals(p.jlGetPSWRD())){
                        //Mostramos el login correcto
                        System.out.println("Login correcto!");
                        jlLoggedIn = true;
                    }
                }
                //Mostramos el error en login
                if(!jlLoggedIn){
                    attempts++;
                    System.out.println("Clave o usuario incorrectos. Intentos restantes: " + (3 - attempts));
                }
            } while (!jlLoggedIn && attempts < 3);
            //Cerramos el programa tras los 3 intentos fallidos
            if(attempts==3){
                System.out.println("Demasiados intentos incorrectos, cerrando el programa!");
                System.exit(0);
            }
            //Inicializamos las coordenadas
            CoordBl jlCoord = new CoordBl();
            System.out.println();
            System.out.println("[Coordenadas]");
            //Obtenemos la lista de coordenadas, dependiendo del usuario
            List<Coord> jlCoords = jlCoord.jlGetAllCoords(jlEnteredPassword);
            List<Coord> jlFixed = new ArrayList<Coord>();
            int jlCont=1;
            int jlCapBel=0;
            int jlCoordTotal=0;
            //Agregamos siempre la primer coordenada, luego chequeamos si la zona se repite y aumentamos si no
            for (Coord coord : jlCoords) {
                boolean jlFlag=true;
                if(jlCont>0){
                    jlFixed.add(coord);
                    jlCont--;
                }
                for (Coord coord2 : jlFixed) {
                    if(coord.jlGetjlGeo().equals(coord2.jlGetjlGeo()))
                        jlFlag=false;
                }
                if(jlFlag==true)
                    jlFixed.add(coord);
            }
            //Tras la depuracion de zonas repetidas, podemos mostrar las zonas
            System.out.println("        CAP     GEO     TIPO ARSENAL");
            for(Coord jlP : jlFixed){
                //Animacion de 0 a 100 % con movimiento
                String[] jlAnimationFrames = {"-", "\\", "|", "/"};
                int jlFrameIndex = 0;

                for (int i = 0; i <= 100; i++) {
                    System.out.print("\r" + i + "%" + jlAnimationFrames[jlFrameIndex]);
                    jlFrameIndex = (jlFrameIndex + 1) % jlAnimationFrames.length;
                    Thread.sleep(10);
                }
                System.out.println("    "+jlP.jlGetjlCap()+"      "+jlP.jlGetjlGeo()+"      "+jlP.jlGetjlArs());
                jlCapBel=jlP.jlGetjlCap()+jlCapBel;
                jlCoordTotal++;
            }
            System.out.println();
            //Dependiendo del usuario que haya ingresado mostramos los datos
            if(jlEnteredPassword.equals(Integer.toString(JLCEDULA_LUZU))){
                System.out.println("Developer-Nombre: "+JLNOMBRE_COMPLETO);
                System.out.println("Developer-Cedula: "+JLCEDULA_LUZU);
            }
            
            if(jlEnteredPassword.equals("1234")){
                System.out.println("Developer-Nombre: Profe");
                System.out.println("Developer-Cedula: 1234");
            }
            System.out.println("Capacidad Belica: "+jlCapBel);
            System.out.println("Coordenada-Total: "+jlCoordTotal);
            System.out.print("Coordenada-SecCarga: ");
            //Mostramos las coordenadas cargadas en orden
            for (Coord jlP : jlFixed) {
                System.out.print(jlP.jlGetjlCap()+" ");
            }
            System.out.println();
            System.out.println();
            //Procedemos con la identificacion de sectores para aplicar la expresion regular que nos ha tocado
            System.out.println("[Bombas]");
            Pattern pattern = Pattern.compile(JLREGEX);
            //Comprobamos con cada zona si aplica o no, denotando con un mensaje si lo hacen.
            for (Coord jlP : jlFixed) {
                Matcher matcher = pattern.matcher(jlP.jlGetjlArs());
                if(matcher.find()){
                    jlP.jlSetjlArs(jlP.jlGetjlArs()+"dt  BOMB-IP");
                    System.out.println("La zona: "+jlP.jlGetjlGeo()+" contiene la bomba BOMB-IP, su arsenal es: "+jlP.jlGetjlArs());
                }
            }
            System.out.println();
            System.out.println("[+] Arbol binario de coordenadas y bomba");
            System.out.println();
            //Ordenamos las zonas en la lista de mayor a menor
            for (int jlI = 0; jlI < jlFixed.size() - 1; jlI++) {
                for (int jlJ = 0; jlJ < jlFixed.size() - jlI - 1; jlJ++) {
                    if ((jlFixed.get(jlJ)).jlGetjlCap() < (jlFixed.get(jlJ + 1)).jlGetjlCap()) {
                        Coord jlTemp = jlFixed.get(jlJ);
                        jlFixed.set(jlJ, jlFixed.get(jlJ + 1));
                        jlFixed.set(jlJ + 1, jlTemp);
                    }
                }
            }
            //Imprimimos las zonas para nuestro arbol
            jlCont=0;
            for (Coord jlP : jlFixed) {
                for(int jlI=0;jlI<jlCont*8;jlI++)
                    System.out.print(" ");
                System.out.print(jlP.jlGetjlGeo()+"{ "+jlP.jlGetjlArs()+" }"+"\n");
                jlCont++;
            }
        } catch (Exception e) { }
        
        
    }
}
