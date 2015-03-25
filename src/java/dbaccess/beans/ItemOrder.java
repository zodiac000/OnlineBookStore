package dbaccess.beans;

import java.text.NumberFormat;

/**
 * Associates a catalog Item with a specific order by keeping track of the
 * number ordered and the total price. Also provides some convenience methods to
 * get at the CatalogItem data without extracting the CatalogItem separately.
 * <P>
 * Adapted from Core Servlets and JavaServer Pages 2nd Edition from Prentice
 * Hall and Sun Microsystems Press, http://www.coreservlets.com/. &copy; 2003
 * Marty Hall; may be freely used or adapted.
 */
public final class ItemOrder {

    private CatalogItem item;
    private int numItems;

    public ItemOrder(CatalogItem item) {
        setItem(item);
        setNumItems(1);
    }

    public CatalogItem getItem() {
        return (item);
    }

    protected void setItem(CatalogItem item) {
        this.item = item;
    }

    public String getItemID() {
        return (getItem().getItemID());
    }

    public String getName() {
        return (getItem().getName());
    }

    public String getLongDescription() {
        return (getItem().getDetail());
    }

    public double getUnitCost() {
        return (Double.parseDouble(getItem().getCost()));
    }

    public int getNumItems() {
        return (numItems);
    }

    public String getAuthor() {
        return (getItem().getAuthor());
    }

    public void setNumItems(int n) {
        this.numItems = n;
    }

    public void incrementNumItems() {
        setNumItems(getNumItems() + 1);
    }

    public void cancelOrder() {
        setNumItems(0);
    }

    public String getTotalCost() {

        NumberFormat formatter
                = NumberFormat.getCurrencyInstance();
        return formatter.format(getNumItems() * getUnitCost());
    }
}
