package db;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;


public class EntityManagerHelper {

    private static EntityManagerFactory emf;
    public static EntityManager manager;
    //private static ThreadLocal<EntityManager> threadLocal;

    static {
        try {
            //emf = Persistence.createEntityManagerFactory("db");
//            Map<String, String> properties = new HashMap<String, String>();
//            properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
//            properties.put("hibernate.connection.url", "jdbc:mysql://de1tmi3t63foh7fa.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/mt202zr9n0vdd7ho");
//            properties.put("hibernate.connection.username", "mmxsqb7gh3ot4pu3");
//            properties.put("hibernate.connection.password", "l3vm1ykn5vcc0plo");
//            properties.put("hibernate.connection.username", "mmxsqb7gh3ot4pu3");
//
//            properties.put("hibernate.show_sql", "true");
//            properties.put("hibernate.format_sql", "true");
//            properties.put("use_sql_comments", "true");
//            properties.put("hibernate.hbm2ddl.auto", "update");
//            properties.put("hibernate.archive.autodetection", "class");

            Map<String, String> env = System.getenv();
            Map<String, Object> configOverrides = new HashMap<String, Object>();
            for (String envName : env.keySet()) {
                if (envName.contains("JDBC_DATABASE_URL")) {
                    configOverrides.put("javax.persistence.jdbc.url", env.get(envName));
                }
            }
            emf = Persistence.createEntityManagerFactory("db", configOverrides);
            //emf = Persistence.createEntityManagerFactory("db");
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
        //EntityManager manager = threadLocal.get();
        if (manager == null) {

            Map<String, String> env = System.getenv();
            Map<String, Object> configOverrides = new HashMap<String, Object>();
            for (String envName : env.keySet()) {
                if (envName.contains("JDBC_DATABASE_URL")) {
                    configOverrides.put("javax.persistence.jdbc.url", env.get(envName));
                }
            }
            emf = Persistence.createEntityManagerFactory("db", configOverrides);
            //emf = Persistence.createEntityManagerFactory("db");
            manager = emf.createEntityManager();

        }
        return manager;
    }

    public static void closeEntityManager() {
        //EntityManager em = threadLocal.get();
        //threadLocal.set(null);
        //manager.clear();
        //manager.close();
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

//public class EntityManagerHelper {
//
//    private static EntityManagerFactory emf;
//    private static ThreadLocal<EntityManager> threadLocal;
//
//    static {
//        try {
//            emf = Persistence.createEntityManagerFactory("db");
//            threadLocal = new ThreadLocal<>();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static EntityManager entityManager() {
//        return getEntityManager();
//    }
//
//    public static EntityManager getEntityManager() {
//        EntityManager manager = threadLocal.get();
//        if (manager == null || !manager.isOpen()) {
//            manager = emf.createEntityManager();
//            threadLocal.set(manager);
//        }
//        return manager;
//    }
//
//    public static void closeEntityManager() {
//        EntityManager em = threadLocal.get();
//        threadLocal.set(null);
//        em.clear();
//        em.close();
//    }
//
//    public static void beginTransaction() {
//        EntityManager em = EntityManagerHelper.getEntityManager();
//        EntityTransaction tx = em.getTransaction();
//
//        if(!tx.isActive()){
//            tx.begin();
//        }
//    }
//
//    public static void commit() {
//        EntityManager em = EntityManagerHelper.getEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        if(tx.isActive()){
//            tx.commit();
//        }
//
//    }
//
//    public static void rollback(){
//        EntityManager em = EntityManagerHelper.getEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        if(tx.isActive()){
//            tx.rollback();
//        }
//    }
//
//    public static Query createQuery(String query) {
//        return getEntityManager().createQuery(query);
//    }
//
//    public static void persist(Object o){
//        entityManager().persist(o);
//    }
//
//    public static void withTransaction(Runnable action) {
//        withTransaction(() -> {
//            action.run();
//            return null;
//        });
//    }
//    public static <A> A withTransaction(Supplier<A> action) {
//        beginTransaction();
//        try {
//            A result = action.get();
//            commit();
//            return result;
//        } catch(Throwable e) {
//            rollback();
//            throw e;
//        }
//    }
//}