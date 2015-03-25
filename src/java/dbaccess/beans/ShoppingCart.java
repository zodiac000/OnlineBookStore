package dbaccess.beans;

import java.util.*;
import dbaccess.persistence.DBHelper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import dbaccess.servlets.LookupServlet;

/** A shopping cart data structure used to track orders.
 *  The OrderPage servlet associates one of these carts
 *  with each user session.
 *  <P>
 *  Adapted from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */
 
public class ShoppingCart {
  private final ArrayList itemsOrdered;
  
  /** Builds an empty shopping cart. */
  
  public ShoppingCart() {
    itemsOrdered = new ArrayList();
  }

  /** Returns List of ItemOrder entries giving
   *  Item and number ordered. Declared as List instead
   *  of ArrayList so that underlying implementation
   *  can be changed later.
   */
  
  public List getItemsOrdered() {
    return(itemsOrdered);
  }

  /** Looks through cart to see if it already contains
   *  an order entry corresponding to item ID. If it does,
   *  increments the number ordered. If not, looks up
   *  Item in catalog and adds an order entry for it.
   */
  
  public synchronized void addItem(CatalogItem ci) {
    ItemOrder order;
    for(int i=0; i<itemsOrdered.size(); i++) {
      order = (ItemOrder)itemsOrdered.get(i);
      if (order.getItemID().equals(ci.getItemID())) {
        order.incrementNumItems();
        return;
      }
    }
      
    
    itemsOrdered.add(new ItemOrder(ci));
  }
  
  
  

  /** Looks through cart to find order entry corresponding
   *  to item ID listed. If the designated number
   *  is positive, sets it. If designated number is 0
   *  (or, negative due to a user input error), deletes
   *  item from cart.
   */
  
  public synchronized void setNumOrdered(String itemID,
                                         int numOrdered) {
    ItemOrder order;
    for(int i=0; i<itemsOrdered.size(); i++) {
      order = (ItemOrder)itemsOrdered.get(i);
      if (order.getItemID().trim().equals(itemID.trim())) {
        if (numOrdered <= 0) {
          itemsOrdered.remove(i);
        } else {
          order.setNumItems(numOrdered);
        }//end of else
        return;
      }//end of if
    }//end of loop
    
  }

    public void persist(Object object) {
        /* Add this to the deployment descriptor of this module (e.g. web.xml, ejb-jar.xml):
         * <persistence-context-ref>
         * <persistence-context-ref-name>persistence/LogicalName</persistence-context-ref-name>
         * <persistence-unit-name>OnlineStore2PU</persistence-unit-name>
         * </persistence-context-ref>
         * <resource-ref>
         * <res-ref-name>UserTransaction</res-ref-name>
         * <res-type>javax.transaction.UserTransaction</res-type>
         * <res-auth>Container</res-auth>
         * </resource-ref> */
        try {
            Context ctx = new InitialContext();
            UserTransaction utx = (UserTransaction) ctx.lookup("java:comp/env/UserTransaction");
            utx.begin();
            EntityManager em = (EntityManager) ctx.lookup("java:comp/env/persistence/LogicalName");
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    public void persist1(Object object) {
        /* Add this to the deployment descriptor of this module (e.g. web.xml, ejb-jar.xml):
         * <persistence-context-ref>
         * <persistence-context-ref-name>persistence/LogicalName</persistence-context-ref-name>
         * <persistence-unit-name>OnlineStore2PU</persistence-unit-name>
         * </persistence-context-ref>
         * <resource-ref>
         * <res-ref-name>UserTransaction</res-ref-name>
         * <res-type>javax.transaction.UserTransaction</res-type>
         * <res-auth>Container</res-auth>
         * </resource-ref> */
        try {
            Context ctx = new InitialContext();
            UserTransaction utx = (UserTransaction) ctx.lookup("java:comp/env/UserTransaction");
            utx.begin();
            EntityManager em = (EntityManager) ctx.lookup("java:comp/env/persistence/LogicalName");
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
}
    
