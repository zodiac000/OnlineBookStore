/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbaccess.persistence;

import dbaccess.beans.ItemOrder;
import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ssome
 */
@Entity
@Table(name="Order")
public class Order implements Serializable {
    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    
    
     @Id
    private String BOOK_ID;
    private int NUMBER;
    
    
    /** Creates a new instance of User */
    public Order() {
      
    }
    public Order(String id, int num) {
        this.BOOK_ID = id;
        this.NUMBER = num;
        
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += this.getBOOK_ID().hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order)) {
            return false;
        }
        Order other = (Order)object;
        return (this.getBOOK_ID().equals(other.getBOOK_ID()));
    }

    @Override
    public String toString() {
        return "dbaccess.persistence.Order[id=" + getBOOK_ID() + "]";
    }

    /**
     * 
     * @param userData
     * @return true if this User matches the userData bean
     */
    public boolean matches(ItemOrder order) {
        if (!"".equals(order.getItemID()) && !this.getBOOK_ID().trim().equals(order.getItemID().trim())) {
            return false;
        } else if (this.getNUMBER()!=order.getNumItems()) {
                return false;
        } 
        return true;
    }

    /**
     * @return the BOOK_ID
     */
    public String getBOOK_ID() {
        return BOOK_ID;
    }

    /**
     * @param BOOK_ID the BOOK_ID to set
     */
    public void setBOOK_ID(String BOOK_ID) {
        this.BOOK_ID = BOOK_ID;
    }

    /**
     * @return the NUMBER
     */
    public int getNUMBER() {
        return NUMBER;
    }

    /**
     * @param NUMBER the NUMBER to set
     */
    public void setNUMBER(int NUMBER) {
        this.NUMBER = NUMBER;
    }

    
    
}
