package ex_003_delete;


import ex_003_delete.entity.Author;
import org.jboss.logging.Logger;

import java.util.List;

/**
 * Created by Asus on 01.11.2017.
 */
public class Main {

    public static void main(String[] args) {

        AuthorHelper ah = new AuthorHelper();

       int deletedValues = ah.deleteCriteriaLogic("lastName", "Bikov", "%ush%");
        System.out.println(deletedValues);
        HibernateUtil.getSessionFactory().close();

    }

}
