package ex_002_select_where;




import ex_002_select_where.entity.Author;
import org.jboss.logging.Logger;

import java.util.List;

/**
 * Created by Asus on 01.11.2017.
 */
public class Main {

    public static void main(String[] args) {
        AuthorHelper ah = new AuthorHelper();

        List<Author> authorList = ah.getAuthorList("lastname", "Pushkin");

        for (Author author : authorList) {
            System.out.println(author.getId() + " " + author.getName() + " " + author.getLastName());
        }

        HibernateUtil.getSessionFactory().close();

    }

}
