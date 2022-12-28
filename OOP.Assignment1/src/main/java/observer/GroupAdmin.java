package observer;

import java.util.ArrayList;

public class GroupAdmin implements Sender {

    ArrayList<Member> arrMember;
    UndoableStringBuilder usb;
    /**
     * Empty constructor that creates an empty ArrayList and a new UndoableStringBuilder.
     */
    GroupAdmin() {
        arrMember = new ArrayList<Member>();
        usb = new UndoableStringBuilder();
    }

    /**
     * registers a new member to a GroupAdmin and adds him to the arrMember list.
     * @param obj - the member we want to register.
     */

    @Override
    public void register(Member obj) {
        arrMember.add(obj);
    }

    /**
     * unregister a specific member from the GroupAdmin and remove him from the arrMember list.
     * @param obj - The member we want to remove.
     */

    @Override
    public void unregister(Member obj) {
        if (arrMember.isEmpty()) {
            System.out.println("no one is registered.");
        }
        else if(!arrMember.contains(obj)){
            System.out.println("no such member is registered.");
        }
        else
        arrMember.remove(obj);
    }
    /**
     * Inserts the string into this character sequence.
     * and notifies everyone who's registered.
     * @param offset -The starting index for the inserted string.
     * @param obj - The string that will be inserted.
     */

    @Override
    public void insert(int offset, String obj) {
        usb.insert(offset, obj);
        Notify(usb);
    }
    /**
     * Appends the specified string to the group and notifies everyone who's registered.
     * and notifies everyone who's registered.
     * @param obj - The string to append.
     */

    @Override
    public void append(String obj) {
        usb.append(obj);
        Notify(usb);
    }
    /**
     * Removes the characters in a substring of this sequence. The substring begins
     * at the specified start and extends to the character at index
     * end - 1 or to the end of the sequence if no such character exists.
     * If start is equal to end, no changes are made.
     * and notifies everyone who's registered.
     * @param start - The starting index to be removed, including that index.
     * @param end - The ending index to be removed, not including that index.
     */

    @Override
    public void delete(int start, int end) {
        usb.delete(start, end);
        Notify(usb);
    }
    /**
     * Cancels the last change that was made to the UndoableStringBuilder sequence and goes back to the previous
     * sequence.
     * and notifies everyone who's registered.
     */

    @Override
    public void undo() {
        usb.undo();
        Notify(usb);
    }

    /**
     * notifies all the members in the GroupAdmin with any change that happens in the
     * previous functions about the UndoableStringBuilder.
     * @param usb - the UndoableStringBuilder we are updating all the members with.
     */

    public void Notify(UndoableStringBuilder usb) {
        for (int i = 0; i < arrMember.size(); i++) {
            (arrMember.get(i)).update(usb);
        }
    }
//        public String toString() {
//        for (int i = 0; i < arrMember.size(); i++) {
//            System.out.println(arrMember.get(i).toString());
//        }
//        return null;
//    }
}


