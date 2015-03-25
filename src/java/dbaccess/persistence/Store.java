/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbaccess.persistence;


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
@Table(name="Stores")
public class Store implements Serializable {
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
    private String NAME;
    private String DESC;
    
    
    /** Creates a new instance of User */
    public Store() {
      
    }
    public Store(String name, String desc) {
        this.NAME = name;
        this.DESC = desc;
        
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += this.NAME.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Store)) {
            return false;
        }
        Store other = (Store)object;
        return (this.NAME.equals(other.NAME));
    }

    @Override
    public String toString() {
        return "dbaccess.persistence.Store[id=" + NAME + "]";
    }

   

    /**
     * @return the NAME
     */
    public String getNAME() {
        return NAME;
    }

    /**
     * @param NAME the NAME to set
     */
    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    /**
     * @return the DESC
     */
    public String getDESC() {
        return DESC;
    }

    /**
     * @param DESC the DESC to set
     */
    public void setDESC(String DESC) {
        this.DESC = DESC;
    }
    
}
