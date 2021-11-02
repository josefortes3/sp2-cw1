import java.util.ArrayList;

/**
 * A Knapsack holds zero or more Items and can provide information about the
 * Items. One can add Items to a Knapsack during its lifetime, reset the
 * Knapsack, create a copy which contains Items only up to a certain weight,
 * and make various queries to the Knapsack. (Thus, the number of Items that
 * will be stored by a Knapsack object is not yet known when the new object
 * is created, and it may grow and shrink over the lifetime of a Knapsack
 * object.)
 *
 * @author Jose Do Rosario Fortes
 */
public class Knapsack {

    // Attributes

    private static int numberOfItems;
    private static int totalWeight;
    //creates list of knapsacks
    private static ArrayList<Knapsack> knapsackList = new ArrayList<>();
    private static Item greatestItem;
    // Creates an empty Item array list
    private ArrayList<Item> itemList = new ArrayList<Item>();

    /* Constructors */

    /**
     * Constructs a new Knapsack without any Items.
     */
    public Knapsack() {
        reset(); // clear the itemList
        numberOfItems = 0;
        totalWeight = 0;
        knapsackList.add(this);
    }

    /**
     * Constructs a new Knapsack containing the non-null Items in items.
     * The items array may be modified by the caller afterwards without
     * affecting this Knapsack, and it will not be modified by this
     * constructor.
     *
     * @param items must not be null; non-null elements are added to the
     *              constructed Knapsack
     */
    public Knapsack(Item[] items) {
        this(); // call the first constructor.
        for (Item item : items) {

            this.add(item);

        }
    }


    /* Modifiers */

    /**
     * Class method to return a Knapsack with the highest total weight from an
     * array of Knapsacks. If we have an array with a Knapsack of 3000 grammes
     * and a Knapsack with 4000 grammes, the Knapsack with 4000 grammes is
     * returned.
     * <p>
     * Entries of the array may be null, and your method should work also in
     * the presence of such entries. So if in the above example we had an
     * additional third array entry null, the result would be exactly the same.
     * <p>
     * If there are several Knapsacks with the same weight, it is up to the
     * method implementation to choose one of them as the result (i.e., the
     * choice is implementation-specific, and method users should not rely on
     * any particular behaviour).
     *
     * @param knapsacks must not be null, but may contain null
     * @return one of the Knapsacks with the highest total weight among all
     * Knapsacks in the parameter array; null if there is no non-null
     * reference in knapsacks
     */
    public static Knapsack heaviestKnapsack(Knapsack[] knapsacks) {
        int i = 0;
        int j = i + 1;
        Knapsack heaviestKnapsack = knapsacks[i];
        do {

            if (knapsacks[i] == null)
                i++;
            if (knapsacks[j] == null) {
                j++;

                assert knapsacks[i] != null;
                if (knapsacks[i].totalWeightInGrammes() > knapsacks[j].totalWeightInGrammes()) {
                    heaviestKnapsack = knapsacks[i];
                }

            }

        } while (i < knapsacks.length);
        return heaviestKnapsack;
    }

    /**
     * Adds an Item e to this Knapsack if e is not null; does not modify this
     * Knapsack otherwise. Returns true if e is not null, false otherwise.
     *
     * @param e an item to be added to this Knapsack
     * @return true if e is not null, false otherwise
     */
    public boolean add(Item e) {

        greatestItem = e;
        if (e != null) {
            numberOfItems++;
            totalWeight = +e.getWeightInGrammes();

            if (greatestItem.compareTo(e) < 0) {
                greatestItem = e;
            }
            return true;
        }
        return false;
    }

    /**
     * Adds all non-null Items in items to this Knapsack.
     *
     * @param items contains the Item objects to be added to
     *              this Knapsack; must not be null (but may contain null)
     * @return true if at least one element of items is non-null;
     * false otherwise
     */
    public boolean addAll(Item[] items) {
        int count = 0;
        for (Item item : items) {
            if (item != null) {

                count++;

                add(item);
            }
            if (count > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Resets this Knapsack to a Knapsack that contains 0 Items.
     */
    public void reset() {
        itemList.clear();
    }
    /* Accessors */

    /**
     * Removes certain Items from this Knapsack. Exactly those Items are kept
     * whose weight in grammes is less than or equal to the specified maximum
     * weight in grammes.
     *
     * @param maxItemWeightInGrammes the maximum weight in grammes for the
     *                               Items that are kept
     */
    public void keepOnlyItemsWith(int maxItemWeightInGrammes) {
        for (Item item : itemList) {
            if (item != null) {
                if (item.getWeightInGrammes() < maxItemWeightInGrammes) {
                    itemList.remove(item);
                }
            }
        }
    }

    /**
     * Returns the number of non-null Items in this Knapsack.
     *
     * @return the number of non-null Items in this Knapsack
     */
    public int numberOfItems() {
        return numberOfItems;
    }

    /**
     * Returns the total weight of the Items in this Knapsack.
     *
     * @return the total weight of the Items in this Knapsack.
     */
    public int totalWeightInGrammes() {

        return totalWeight;
    }

    /**
     * Returns the average weight in grammes of the (non-null) Items
     * in this Knapsack. In case there is no Item in this Knapsack,
     * -1.0 is returned.
     * <p>
     * For example, if this Knapsack has the contents
     * new Item("Soda", 400)
     * and
     * new Item("Water", 395),
     * the result is: 397.5
     *
     * @return the average length of the Items in this Knapsack,
     * or -1.0 if there is no such Item.
     */
    public double averageWeightInGrammes() {
        if (this.numberOfItems() > 0) {
            return this.totalWeightInGrammes() / this.numberOfItems();
        } else
            return -1.0;
    }

    /**
     * ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ
     * Returns the greatest Item in this Knapsack according to the
     * natural ordering of Item given by its compareTo method;
     * null if this Knapsack does not contain any Item objects
     *
     * @return the greatest Item in this Knapsack according to the
     * natural ordering of Item given by its compareTo method;
     * null if this Knapsack does not contain any Item objects
     */
    public Item greatestItem() {
        return greatestItem;
    }

    /**
     * Returns a new Knapsack with exactly those Items of this Knapsack
     * whose weight is less than or equal to the specified method parameter.
     * Does not modify this Knapsack.
     *
     * @param maxItemWeightInGrammes the maximum weight in grammes for the
     *                               Items in the new Knapsack
     * @return a new Knapsack with exactly those Items of this Knapsack
     * whose weight is less than or equal to the specified method parameter
     */
    public Knapsack makeNewKnapsackWith(int maxItemWeightInGrammes) {
        Knapsack k = new Knapsack();
        for (Item item : itemList) {
            if (item.getWeightInGrammes() <= maxItemWeightInGrammes) {
                k.add(item); //add the articles to a new warehouse
            }
        }
        return k;
    }

    /* class methods */

    /**
     * Returns a string representation of this Knapsack. The string
     * representation consists of a list of the Knapsack's contents,
     * enclosed in square brackets ("[]"). Adjacent Items are
     * separated by the characters ", " (comma and space). Items are
     * converted to strings as by their toString() method. The
     * representation does not mention any null references.
     * <p>
     * So for
     * <p>
     * Item i1 = new Item("Pen", 15);
     * Item i2 = new Item("Letter", 20);
     * Item i3 = null;
     * Item[] items = { i1, i2, i3, i1 };
     * Knapsack k = new Knapsack(items);
     * <p>
     * the call k.toString() will return one of the three following Strings:
     * <p>
     * "[(Pen, 15), (Pen, 15), (Letter, 20)]"
     * "[(Pen, 15), (Letter, 20), (Pen, 15)]"
     * "[(Letter, 20), (Pen, 15), (Pen, 15)]"
     *
     * @return a String representation of this Knapsack
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        ArrayList<String> output = new ArrayList<>();
        for (Item item : itemList) {
            output.add("[" + "(" + item.getName() + "," + item.getWeightInGrammes() + ")]");
        }
        return String.valueOf(output);
    }
}
