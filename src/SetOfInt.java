/**
 * The SetOfInt class stores integers in a set.
 *
 * @author Bryan Keech
 * <A HREF="mailto:bryan.keech@asu.edu">(bryan.keech@asu.edu)</A>
 **/

public class SetOfInt implements Cloneable {
    // Invariant of the SetOfInt class:
    // 		1. The number of members in the set is stored in manyItems.
    //		2. For an empty set we do not care what is stored in any of data;
    //			For a nonempty set, the members in the set are stored in data[0] through
    //			 data[manyItems -1], and we don't case what's in the rest of data.
    private int[] data;
    private int manyItems;

    /**
     * Initializes an empty set with an initial capacity of 10. Note that the add method works
     * efficiently (without needing more memory) until this capacity is reached.
     *
     * @param - none
     *          <dt><b>PostCondition</b><dd>
     *          This set is empty and has an initial capacity of 10.
     * @throws OutOfMemoryError Indicates insufficient memory for: new int[10].
     **/
    public SetOfInt() {
        final int INITIAL_CAPACITY = 10;
        manyItems = 0;
        data = new int[INITIAL_CAPACITY];

    }

    /**
     * Initialize an empty set with a specified initial capacity. Note that the add method works
     * efficiently (without needing more memory) until this capacity is reached.
     *
     * @param initialCapacity the initial capacity of set.
     *                        <dt><b>Precondition</b><dd>
     *                        initialCapacity is non-negative
     *                        <dt><b>Postcondition</b><dd>
     *                        This set is empty and hass the given initial capacity.
     * @throws IllegalArgumentException Indicates that initialCapacity is negative.
     * @throws OutOfMemoryError         Indicates insufficient memory for: new int[initialCapacity].
     **/
    public SetOfInt(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException
                    ("initialCapacity is negative: " + initialCapacity);
        manyItems = 0;
        data = new int[initialCapacity];

    }

    /**
     * add an element to set
     *
     * @param element the element to add
     *                <dt><b>Precondition</b><dd>
     *                element is an integer
     *                <dt><b>Postcondition</b><dd>
     *                element will be added to the set.
     **/
    public void add(int element) {
        if (!contains(element)) {
            if (manyItems == data.length) {
                //Double the capacity and add 1; this works even if manyItems is 0.
                ensureCapacity(manyItems * 2 + 1);
            }

            data[manyItems] = element;
            manyItems++;

        }

    }

    /**
     * add all numbers from a SetOfInt object
     *
     * @param addend addend is of SetOfInt type
     *               <dt><b>Precondition</b><dd>
     *               addend is of SetOfInt type
     *               <dt><b>Postcondition</b><dd>
     *               all numbers from addend, that are not already in set, will be added to current array.
     **/
    public void addAll(SetOfInt addend) {
        // If addend is null, then a NullPointerException is thrown.
        // In the case that the total number of items is beyond Integer.MAX_VALUE, there will
        // be an arithmetic overflow and the set will fail


        ensureCapacity(manyItems + addend.manyItems);

        System.arraycopy(addend.data, 0, data, manyItems, addend.manyItems);
        manyItems += addend.manyItems;
        printSet();
        for (int index = 0; index < manyItems; index++) {

            for (int comIndex = index + 1; comIndex < manyItems; comIndex++) {
                if (data[index] == data[comIndex]) {
                    System.out.println("comparing " + data[index] + " and " + data[comIndex]);
                    System.out.println("removing " + data[comIndex]);
                    remove(data[comIndex]);
                }
            }
        }
    }

    /**
     * Clones SetOfInt object
     *
     * @param none <dt><b>Precondition</b><dd>
     *             class must implement Cloneable
     *             <dt><b>Postcondition</b><dd>
     *             object will be cloned
     **/
    public Object clone() {
        //Clone and SetOfInt object.
        SetOfInt answer;

        try {
            answer = (SetOfInt) super.clone();
        } catch (CloneNotSupportedException e) {
            // This exception should not occur. But if it does, it would probably indicate a
            // programming error that made super.clone unavailable. The most common
            // error would be forgetting the "Implements Cloneable"
            // clause at the start of this class.
            throw new RuntimeException ("This class does not implement Cloneable.");
        }

        answer.data = (int[]) data.clone();
        return answer;
    }

    /**
     * Checks to see if a number is already in set
     *
     * @param target number to be searched for
     * @return returns true if number was found in set and false if it was not
     **/
    public boolean contains(int target) {
        // Postcondition: The return value is true if
        // target is in the set; otherwise the return value is false
        boolean answer;
        int index;

        answer = false;
        for (index = 0; index < manyItems; index++)
            if (target == data[index])
                answer = true;
        return answer;
    }

    /**
     * Checks to see if array is large enough for new members, and if it is not
     * the array is resized.
     *
     * @param minimumCapacity the minimum size the array needs to be for new member
     **/
    public void ensureCapacity(int minimumCapacity) {
        int biggerArray[];

        if (data.length < minimumCapacity) {
            biggerArray = new int[minimumCapacity];
            System.arraycopy(data, 0, biggerArray, 0, manyItems);
            data = biggerArray;
        }
    }

    /**
     * Get the current capacity of set.
     *
     * @param none
     * @return data.length
     **/
    public int getCapacity() {
        return data.length;
    }

    /**
     * Remove an member from set
     *
     * @param target member to remove
     * @return true if the member was removed. false if member was not found.
     **/
    public boolean remove(int target) {
        int index; // The location of target in the data array

        // First, set index to the location of target in the data array,
        // which could be as small as 0 or as large as mandyItems-1.
        // If target is not in the array, then index will be set equal to manyItems.
        for (index = 0; (index < manyItems) && (target != data[index]); index++)
            // No work is needed in the body of this for-loop.
            ;

        if (index == manyItems)
            // The target was not found, so nothing was removed.
            return false;
        else { // The target was found at data[index].
            manyItems--;
            data[index] = data[manyItems];
            return true;
        }
    }

    /**
     * find the number of members in set
     *
     * @param none
     * @return manyItems
     **/
    public int size() {
        return manyItems;
    }

    /**
     * Trim array to size of number of members
     *
     * @param none
     **/
    public void trimToSize() {
        int trimmedArray[];

        if (data.length != manyItems) {
            trimmedArray = new int[manyItems];
            System.arraycopy(data, 0, trimmedArray, 0, manyItems);
            data = trimmedArray;
        }
    }

    /**
     * Print all members in set
     *
     * @param none
     **/
    public void printSet() {
        int index;
        for (index = 0; index < manyItems; index++)
            System.out.println(data[index]);
    }
}

