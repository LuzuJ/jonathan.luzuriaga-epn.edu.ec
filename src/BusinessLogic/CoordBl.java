package BusinessLogic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Framework.AppException;
import BusinessLogic.Entities.Coord;
import DataAccess.CoordDAC;

public class CoordBl {
    /**
     * Este metodo nos permite enlistar todos las coordenadas de la tabla correspondiente al usuario.
     * @return Retorna la lista de los usuarios que hay en la abse de datos
     * @throws AppException
     */
    public List<Coord> jlGetAllCoords(String jlUser) throws AppException{
        try{
            CoordDAC jlCoordDAC = new CoordDAC();
            List<Coord> jlCoord = new ArrayList<Coord>();
            ResultSet rs = jlCoordDAC.jlGetAllCoords(jlUser);
            if(jlUser.equals("1753716867")){
                while(rs.next()){
                    Coord jlP = new Coord(rs.getInt("JL_CAP"),rs.getString("JA_GEO"),rs.getString("JA_ARS"));
                    jlCoord.add(jlP);
                }
            }
            if(jlUser.equals("1234")){
                while(rs.next()){
                    Coord jlP = new Coord(rs.getInt("PF_CAP"),rs.getString("PF_GEO"),rs.getString("PF_ARS"));
                    jlCoord.add(jlP);
                }
            }
            return jlCoord;
        }
        catch (SQLException e) {
            throw new AppException(e, getClass());
        }
    }
}
