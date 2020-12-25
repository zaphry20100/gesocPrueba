package domain.repositories.daos;

import db.EntityManagerHelper;
import org.hibernate.Criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class DAOHibernate<T> implements DAO<T> {
    private Class<T> type;

    public DAOHibernate(Class<T> type){
        this.type = type;
    }

    @Override
    public List<T> buscarTodos() {

        CriteriaBuilder builder = EntityManagerHelper.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> critera = builder.createQuery(this.type);

        critera.from(type);
        List<T> entities = EntityManagerHelper.getEntityManager().createQuery(critera).getResultList();
        EntityManagerHelper.closeEntityManager();
        return entities;
    }

    @Override
    public List<T> buscarTodos(int id) {

        CriteriaBuilder builder = EntityManagerHelper.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> critera = builder.createQuery(this.type);
        Root<T> root = critera.from(type);
        critera.select(root).where(builder.equal(root.get("idEntidadJuridica"), id));
        List<T> entities = EntityManagerHelper.getEntityManager().createQuery(critera).getResultList();
        EntityManagerHelper.closeEntityManager();
        return entities;
    }


    @Override
    public T buscar(int id) {
        T obj = EntityManagerHelper.getEntityManager().find(type, id);
        EntityManagerHelper.closeEntityManager();
        return obj;
    }

    @Override
    public void agregar(Object unObjeto) {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().persist(unObjeto);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
        EntityManagerHelper.closeEntityManager();
    }

    @Override
    public void modificar(Object unObjeto) {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
//        EntityManagerHelper.getEntityManager().merge(unObjeto);
        EntityManagerHelper.getEntityManager().merge(unObjeto);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
        EntityManagerHelper.closeEntityManager();
    }

    @Override
    public void eliminar(Object unObjeto) {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().remove(unObjeto);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
        EntityManagerHelper.closeEntityManager();
    }
}