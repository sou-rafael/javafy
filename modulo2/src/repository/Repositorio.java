package repository;

import java.sql.Connection;
import java.util.List;

public interface Repositorio<CHAVE, OBJETO> {

    Integer getProximoId(Connection connection);

    OBJETO adicionar(OBJETO object) ;

    boolean remover(CHAVE id);

    boolean editar(CHAVE id, OBJETO objeto) ;

    List<OBJETO> listar() ;
}