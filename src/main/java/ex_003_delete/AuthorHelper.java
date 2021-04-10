package ex_003_delete;


import ex_003_delete.entity.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by Asus on 01.11.2017.
 */
public class AuthorHelper {

    private SessionFactory sessionFactory;

    public AuthorHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Author> getAuthorList(){
        // открыть сессию - для манипуляции с персист. объектами
        Session session = sessionFactory.openSession();


        // этап подготовки запроса

        // объект-конструктор запросов для Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated

        CriteriaQuery<Author> cq = cb.createQuery(Author.class);

        Root<Author> root = cq.from(Author.class);// первостепенный, корневой entity (в sql запросе - from)

        Selection[] selections = {root.get("id"), root.get("name")};

        cq.select(cb.construct(Author.class, selections));


         //этап выполнения запроса
        Query query = session.createQuery(cq);


        List<Author> authorList = query.getResultList();

        session.close();

        return authorList;
    }

    public Author getAuthorById(long id) {
        Session session = sessionFactory.openSession();
        Author author = session.get(Author.class, id); // получение объекта по id
        session.close();
        return author;
    }

    public Author addAuthor(Author author){

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(author); // сгенерит ID и вставит в объект

        session.getTransaction().commit();

        session.close();


        return author;

    }

    public Author deleteById(long id) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Author author = session.get(Author.class, id);

        session.delete(author); // сгенерит ID и вставит в объект

        session.getTransaction().commit();

        session.close();

        return author;

    }

    public int deleteCriteria(int id)  {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated
        CriteriaDelete<Author> cd = cb.createCriteriaDelete(Author.class);
        Root<Author> root = cd.from(Author.class);// первостепенный, корневой entity (в sql запросе - from)
        cd.where( cb.greaterThan(root.<Integer>get("id"), id));
        Query query = session.createQuery(cd);
        int deletedValues = query.executeUpdate();
        System.out.println("Deleted values: " + deletedValues);
        session.getTransaction().commit();
        session.close();
        return deletedValues;
    }

    public int deleteCriteriaLogic(String lastName, String var1, String var2)  {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated
        CriteriaDelete<Author> cd = cb.createCriteriaDelete(Author.class);
        Root<Author> root = cd.from(Author.class);
        cd.where(cb.or(
                cb.equal(root.get(lastName), var1),
                cb.like(root.<String>get(lastName), var2)
                )
        );


        //этап выполнения запроса
        Query query = session.createQuery(cd);
        int deletedValues = query.executeUpdate();
        System.out.println("Deleted values: " + deletedValues);

        session.getTransaction().commit();

        session.close();

        return deletedValues;
    }

}
