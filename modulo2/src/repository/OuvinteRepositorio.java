package repository;

import models.Ouvinte;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OuvinteRepositorio implements Repositorio<Integer, Ouvinte>{

    @Override
    public Integer getProximoId(Connection connection) {

        try{
            String sql = "SELECT seq_id_ouvinte.nextval ouvinteSequence FROM OUVINTE";
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sql);

            if(result.next()){
                return result.getInt("ouvinteSequence");
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e.getCause());
        }

    }

    @Override
    public Ouvinte adicionar(Ouvinte ouvinte) {

        Connection con = null;
        try{

            con = ConexaoBancoDeDados.getConnection();
            Integer idOuvinte = this.getProximoId(con);
            ouvinte.setIdOuvinte(idOuvinte);

        }catch (SQLException ex){
            throw new RuntimeException(ex.getCause());
        }

        return null;
    }

    @Override
    public boolean remover(Integer id) {
        return false;
    }

    @Override
    public boolean editar(Integer id, Ouvinte ouvinte) {
        return false;
    }

    @Override
    public List<Ouvinte> listar() {
        return null;
    }
}
