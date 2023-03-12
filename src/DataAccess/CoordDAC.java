package DataAcess;
import java.sql.ResultSet;
import java.sql.SQLException;

import Framework.AppConfiguration;
import Framework.AppException;

public class CoordDAC extends SQLiteDataHelper {
    
    public CoordDAC(){
        super(AppConfiguration.getDBPathConnection());
    }
    /**
     * Metodo usado para obtener todas las coordenadas de las tablas dependiendo del usuario
     * @return Retorna todos los datos que la tabla nos envia
     * @throws AppException
     */
    public ResultSet jlGetAllCoords(String jlUser) throws AppException{
        String sql="";
        try{
            if(jlUser.equals("1753716867")){
                sql="SELECT *"
                    +"FROM JL_COORDS";
            }
            if(jlUser.equals("1234")){
                sql="SELECT *"
                    +"FROM PF_COORDS";
            }
            return getResultSet(sql);
        }
        catch (SQLException e){
            throw new AppException(e, getClass(), "getAllCoords()");
        }
    }
}
