package observer;

public class ConcreteMember implements Member {

    UndoableStringBuilder usb;

    public ConcreteMember() {
        usb = new UndoableStringBuilder();
    }

    /**
     * Updating the UndoableStringBuilder to the member by a shallow copy.
     * @param usb - the UndoableStringBuilder we are updating the member with.
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
    }

    /**
     * Returns a string representing the data saved in the member.
     * @return The saved data in the member as a string.
     */
    public String toString() {
        String st = usb.toString();
        return st;
    }
}
