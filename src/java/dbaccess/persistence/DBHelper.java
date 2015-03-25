/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccess.persistence;


import dbaccess.beans.CatalogItem;
import dbaccess.beans.ItemOrder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author ssome
 */
public class DBHelper {

    public static Book findBook(EntityManager em, String id) {
        Book b = em.find(Book.class, id);
        return b;
    }

    public static CatalogItem findCatalogItem(EntityManager em, String id) {
        Book b = findBook(em, id);
        CatalogItem ci = new CatalogItem(b.getBOOK_ID(), b.getSHORT(), b.getAUTHOR(), b.getLONG(), String.valueOf(b.getCOST()), b.getCATEGORY());
        return ci;
    }

    public static List findAllBook(EntityManager em) {
        Query q = em.createQuery("SELECT b FROM Book b");

        return performQuery(q);
    }

    public static List findBookByName(EntityManager em, String name) {
        Query query = em.createQuery(
                "SELECT b FROM Book b"
                + " WHERE b.SHORT = :shortDescription");
        query.setParameter("name", name);
        return performQuery(query);
    }

    public static List findBooksByCategory(EntityManager em, String scat) {
        try {

            Query query = em.createQuery(
                    "SELECT b FROM Book b"
                    + " WHERE b.CATEGORY = :category");
            query.setParameter("category", scat);
            return performQuery(query);
        } catch (IllegalArgumentException e) {
        }
        return null;
    }

    private static List performQuery(final Query query) {
        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        ArrayList<Book> results = new ArrayList<>();
        results.addAll(resultList);
        return results;
    }

    public static boolean update(EntityManager em, UserTransaction utx, String id, String name, String author, String desc, String cost, String category) {
        try {
            utx.begin();
            Query query = em.createQuery("UPDATE Book b SET b.SHORT = :name, b.AUTHOR = :author, b.LONG = :long, b.COST = :cost, b.CATEGORY = :category"
                    + "    WHERE b.BOOK_ID = :id");
            query.setParameter("id", id);
            query.setParameter("name", name);
            query.setParameter("author", author);
            query.setParameter("long", desc);
            query.setParameter("cost", Double.parseDouble(cost));
            query.setParameter("category", category);
            query.executeUpdate();
            utx.commit();
            return true;
        } catch (IllegalArgumentException | NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            return false;
        }
    }

//@transactional
    public static boolean deleteBookById(EntityManager em, UserTransaction utx, String id) {
        try {
            utx.begin();
            Query query = em.createQuery(
                    "DELETE FROM Book b"
                    + " WHERE b.BOOK_ID = :id");
            query.setParameter("id", id);

            query.executeUpdate();
            //em.persist(nbook);
            utx.commit();
            return true;
        } catch (IllegalArgumentException | NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
        }
        return false;
    }

    public static boolean addBook(EntityManager em, UserTransaction utx, CatalogItem catalogItem) {
        try {
            utx.begin();
            Book nbook = new Book();
            nbook.setBOOK_ID(catalogItem.getItemID());
            nbook.setSHORT(catalogItem.getName());
            nbook.setAUTHOR(catalogItem.getAuthor());
            nbook.setLONG(catalogItem.getDetail());
            nbook.setCOST(Double.parseDouble(catalogItem.getCost()));
            nbook.setCATEGORY(catalogItem.getCategory());
            em.persist(nbook);
            utx.commit();
            return true;
        } catch (IllegalArgumentException | NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
