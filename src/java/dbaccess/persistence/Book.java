/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbaccess.persistence;

import dbaccess.beans.CatalogItem;
import java.io.Serializable;
//import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ssome
 */
@Entity
@Table(name="Books")
public class Book implements Serializable {
    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    
     @Id
    private String BOOK_ID;
    private String SHORT;
    private String AUTHOR;
    private String LONG;
    private double COST;
    private String CATEGORY;
    
    /** Creates a new instance of User */
    public Book() {
      
    }
    public Book(String id, String sh, String author,String l,double cost,String category) {
        this.BOOK_ID = id;
        this.SHORT = sh;
        this.AUTHOR = author;
        this.LONG = l;
        this.COST = cost;
        this.CATEGORY = category;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += this.BOOK_ID.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book)object;
        return (this.BOOK_ID.equals(other.BOOK_ID));
    }

    @Override
    public String toString() {
        return "dbaccess.persistence.Book[id=" + BOOK_ID + "]";
    }

    /**
     * 
     * @param catalogItem
     * @return true if this User matches the userData bean
     */
    public boolean matches(CatalogItem catalogItem) {
        if (!"".equals(catalogItem.getItemID()) && !this.getBOOK_ID().trim().equals(catalogItem.getItemID().trim())) {
            return false;
        } else if (!"".equals(catalogItem.getName()) && !this.getSHORT().trim().equals(catalogItem.getName().trim())) {
                return false;
        } else if (!"".equals(catalogItem.getDetail())&& !this.getLONG().trim().equals(catalogItem.getDetail().trim())){
            return false;
        }else if ( !String.valueOf(this.getCOST()).equals(catalogItem.getCost())&&!String.valueOf(this.getCOST()).trim().equals(catalogItem.getCost().trim())){
            return false;
        }else if (!"".equals(catalogItem.getCategory())&& !this.getCATEGORY().trim().equals(catalogItem.getCategory().trim())){
            return false;
        }else if (!"".equals(catalogItem.getAuthor())&& !this.getAUTHOR().trim().equals(catalogItem.getAuthor().trim())){
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
     * @return the SHORT
     */
    public String getSHORT() {
        return SHORT;
    }

    /**
     * @param SHORT the SHORT to set
     */
    public void setSHORT(String SHORT) {
        this.SHORT = SHORT;
    }

    /**
     * @return the LONG
     */
    public String getLONG() {
        return LONG;
    }

    /**
     * @param LONG the LONG to set
     */
    public void setLONG(String LONG) {
        this.LONG = LONG;
    }

    /**
     * @return the COST
     */
    public double getCOST() {
        return COST;
    }

    /**
     * @param COST the COST to set
     */
    public void setCOST(double COST) {
        this.COST = COST;
    }

    /**
     * @return the CATEGOTY
     */
    public String getCATEGORY() {
        return CATEGORY;
    }

    /**
     * @param CATEGOTY the CATEGOTY to set
     */
    public void setCATEGORY(String CATEGORY) {
        this.CATEGORY = CATEGORY;
    }

    /**
     * @return the AUTHOR
     */
    public String getAUTHOR() {
        return AUTHOR;
    }

    /**
     * @param AUTHOR the AUTHOR to set
     */
    public void setAUTHOR(String AUTHOR) {
        this.AUTHOR = AUTHOR;
    }
    
}
