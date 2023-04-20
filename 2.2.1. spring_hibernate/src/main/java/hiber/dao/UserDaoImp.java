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
   public void add1(Car car) {

      sessionFactory.getCurrentSession().save(car);
   }

   public List select(String model, int series) {
      String hql="select c.id from Car c where model=:model and series=:series";

      Query query = sessionFactory.createEntityManager().createQuery(hql);
      query.setParameter("model",model);
      query.setParameter("series",series);
      String hql1="from User where id=:id";
      Query query1 = sessionFactory.createEntityManager().createQuery(hql1);
      query1.setParameter("id",query.getResultList());

      return query1.getResultList();
   }
   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
