package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {

      sessionFactory.getCurrentSession().save(user);
   }
   @Override
   public void add1(Car car) {

      sessionFactory.getCurrentSession().save(car);
   }
   @Override
   public List selectUser(String model, int series) {
      String hql = "SELECT c.id FROM Car as c WHERE c.model = :model and c.series = :series";
      Query query = sessionFactory.createEntityManager().createQuery(hql);
      query.setParameter("model",model);
      query.setParameter("series",series);
      return query.getResultList();
   }
   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
