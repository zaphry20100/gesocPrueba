package db;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.function.Supplier;

public class EntityManagerHelper {

    public static EntityManager manager;

    private static EntityManagerFactory emf;

    //private static ThreadLocal<EntityManager> threadLocal;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("db");
            manager = emf.createEntityManager();
            //threadLocal = new ThreadLocal<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EntityManager entityManager() {
        return getEntityManager();
    }

    public static EntityManager getEntityManager() {
//        EntityManager manager = threadLocal.get();
//        if (manager == null || !manager.isOpen()) {
//            manager = emf.createEntityManager();
//            threadLocal.set(manager);
//        }

        if (emf == null){
            emf = Persistence.createEntityManagerFactory("db");
        }

        if (manager == null){
            manager = emf.createEntityManager();
        }

        return manager;
    }

    public static void closeEntityManager() {
//        EntityManager em = threadLocal.get();
//        threadLocal.set(null);
//        em.clear();
//        em.close();

        EntityManager em = EntityManagerHelper.getEntityManager();
        em.clear();
        em.close();

    }

    public static void beginTransaction() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        if(!tx.isActive()){
            tx.begin();
        }
    }

    public static void commit() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        if(tx.isActive()){
            tx.commit();
        }

    }

    public static void rollback(){
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        if(tx.isActive()){
            tx.rollback();
        }
    }

    public static Query createQuery(String query) {
        return getEntityManager().createQuery(query);
    }

    public static void persist(Object o){
        entityManager().persist(o);
    }

    public static void withTransaction(Runnable action) {
        withTransaction(() -> {
            action.run();
            return null;
        });
    }
    public static <A> A withTransaction(Supplier<A> action) {
        beginTransaction();
        try {
            A result = action.get();
            commit();
            return result;
        } catch(Throwable e) {
            rollback();
            throw e;
        }
    }
}