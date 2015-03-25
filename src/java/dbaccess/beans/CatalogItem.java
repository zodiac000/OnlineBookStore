package dbaccess.beans;

/**
 * Describes a catalog item for on-line store. The itemID uniquely identifies
 * the item, the short description gives brief info like the book title and
 * author, the long description describes the item in a couple of sentences, and
 * the cost gives the current per-item price. Both the short and long
 * descriptions can contain HTML markup.
 * <P>
 * Adapted from Core Servlets and JavaServer Pages 2nd Edition from Prentice
 * Hall and Sun Microsystems Press, http://www.coreservlets.com/. &copy; 2003
 * Marty Hall; may be freely used or adapted.
 */
public final class CatalogItem {

    private String itemID;
    private String name;
    private String author;
    private String detail;
    private String cost;
    private String category;

    public CatalogItem() {

    }

    public CatalogItem(String itemID, String name, String author,
            String description, String cost, String cat) {
        setItemID(itemID);
        setName(name);
        setAuthor(author);
        setDetail(description);
        setCost(cost);
        setCategory(cat);

    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isComplete() {
        if (this.getItemID() == null || this.getName() == null || this.getDetail() == null || this.category == null || this.getCost() == null || this.author == null) {
            return false;
        }

        return !"".equals(itemID) && !"".equals(name) && !"".equals(detail) && !"".equals(category) && !"".equals(cost)&&!"".equals(author);
    }

    /**
     * @return the itemID
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * @param itemID the itemID to set
     */
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail the detail to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @return the cost
     */
    public String getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(String cost) {
        this.cost = cost;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }
}
