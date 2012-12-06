package com.ds.test;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public class TestHibernate {
  static SessionFactory sessionFactory = null;

  public static Session getHibernateSession() {
    if (sessionFactory == null) {
      sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    }
    return sessionFactory.openSession();
  }

  public static void main(String[] args) {
    Session session = getHibernateSession();

    try {
      String str = "select c from City c left join fetch c.locationNames lm where c.id = :cityId";
      String st1 = "select bsc from BusStationConflict bsc join fetch bsc.state where bsc.provider.id = :providerId";

      /*Query query = session.createQuery("select it from ImpressionTracking it " +
          "where it.marketingMaterial.id = :mmId and it.affiliate.id = :affId " +
          "and it.companyShortName =:companyShortName and date(it.impressionDate) =:impressionDate");

      query.setParameter("mmId", 2L);
      query.setParameter("impressionDate", new Date());
      query.setParameter("affId", 999L);
      query.setParameter("companyShortName", "hk");
*/

Query query = session.createQuery("select it from ImpressionTracking it " +
          "where it.id = 1 " );

      

      //query.setParameter("allowedPos", "IN");
      //query.setParameter("destination", "BOM");

      //query.setMaxResults(100);
      List result = query.list();

      /*Map<Long, Long> resultsCount = new HashMap<Long, Long>();

      List<Object[]> temp  = (List<Object[]> )result;
      for (Object[] objectArr : temp) {
        resultsCount.put((Long) objectArr[0], (Long) objectArr[1]);
      }*/

      System.out.println(result);

      // Hotel hotel = (Hotel) result.get(0);

      // String propertyTypeName = hotel.getPropertyType().getPropertyTypeNames()

      System.out.println("tets");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
