import PersistSchema.Location;
import jakarta.persistence.*;
import org.apache.log4j.Logger;

import java.util.List;

public class LocationRepository {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("Employee_DB");



    public static void addLocation(String address, int sqft, String name){
        EntityManager em = factory.createEntityManager();

        try {
            em.getTransaction().begin();
            Location loc = new Location(address,sqft,name);
            em.persist(loc);
            em.getTransaction().commit();

        } catch (Exception ex){
            if (em.getTransaction() != null){
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }

    }


    public static Location getLocation(int id) {
        EntityManager em = factory.createEntityManager();
        String sql = "SELECT l FROM Location l WHERE l.id = :locID";

        TypedQuery<Location> query = em.createQuery(sql,Location.class);
        query.setParameter("locID",id);
        Location loc = null;

        try {
            loc = query.getSingleResult();
        } catch (NoResultException ex){
            ex.printStackTrace();
        } finally {
            em.close();
        }

        return loc;

    }
    public static List<Location> getLocations() {
        EntityManager em = factory.createEntityManager();
        String sql = "SELECT l FROM Location l WHERE l.id is not null";

        TypedQuery<Location> query = em.createQuery(sql,Location.class);
        List<Location> locs = null;

        try {
            locs = query.getResultList();


        } catch (NoResultException ex){
            ex.printStackTrace();
        } finally {
            em.close();
        }

        return locs;
    }

// should i pass in whole obj then deconstruc or leabe be
    public static void updateLocationName(int id, String name) {
        EntityManager em = factory.createEntityManager();

        try {
            em.getTransaction().begin();
            Location loc = em.find(Location.class,id);

            loc.setName(name);
            em.persist(loc);
            em.getTransaction().commit();

        } catch (Exception ex){
            if (em.getTransaction() != null){
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }

    }

    public static void deleteLocation(int id) {
        EntityManager em = factory.createEntityManager();

        try {
            em.getTransaction().begin();
            Location loc = em.find(Location.class,id);

            em.remove(loc);
            em.getTransaction().commit();

        } catch (Exception ex){
            if (em.getTransaction() != null){
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }

    }


    public static void main(String[] args) {


//        addLocation("123 Seaseme st",4000,"Elmos House");

//        updateLocation(1," 447 California blvd");
//        deleteLocation(1);
               Location xx =  getLocation(2);

xx.getAddress();
    }


}
