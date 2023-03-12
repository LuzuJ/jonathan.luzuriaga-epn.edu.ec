package BusinessLogic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Framework.AppException;
import BusinessLogic.Entities.User;
import DataAccess.UserDAC;

public class UserBL {
    /**
     * Este metodo nos permite enlistar todos los usuarios de la base de datos
     * @return Retorna la lista de los usuarios que hay en la abse de datos
     * @throws AppException
     */
    public List<User> jlGetAllUsers()  throws AppException{
        try{
            UserDAC jlUserDAC = new UserDAC();
            List<User> jlUser = new ArrayList<User>();
            ResultSet rs = jlUserDAC.jlGetAllUsers();
            while(rs.next()){
                User jlP = new User(rs.getString("JL_ID"),rs.getString("JL_PSWRD"));
                jlUser.add(jlP);
            }
            return jlUser;
        }
        catch (SQLException e) {
            throw new AppException(e, getClass());
        }
    }
    
}
