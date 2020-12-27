package domain.repositories.daos;

import java.util.List;

public interface DAO<T> {
    List<T> buscarTodos();
    public List<T> buscarTodos(int id);
    T buscar(int id);
    void agregar(Object unObjeto);
    void modificar(Object unObjeto);
    void eliminar(Object unObjeto);
}
