package ex_004_relations;


import ex_004_relations.entity.Author;
import ex_004_relations.entity.Book;
import org.jboss.logging.Logger;

import java.util.List;

/**
 * Created by Asus on 01.11.2017.
 */
public class Main {


    public static void main(String[] args) {

       BookHelper bookHelper = new BookHelper();

       AuthorHelper authorHelper = new AuthorHelper();

       Author author = authorHelper.getAuthorById(1);

       List<Book> books = author.getBooks();

       for (Book book : books) {
           System.out.println(book.getId() + " " + book.getName()
                   + " " + book.getAuthor().getName() + " " + book.getAuthor().getLastName());
       }

       HibernateUtil.getSessionFactory().close();

    }

}
