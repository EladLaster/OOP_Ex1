package observer;

import java.util.Stack;

public class UndoableStringBuilder {

    private Stack<StringBuilder> stack;
    private StringBuilder str;
    /**
     * Empty constructor that creates an empty stack and an empty StringBuilder.
     */
    public UndoableStringBuilder() {
        str = new StringBuilder();
        stack = new Stack<StringBuilder>();
    }
    /**
     * Appends the specified string to this character sequence.
     * @param st - The string to append.
     * @return A reference to the new object.
     */
    public UndoableStringBuilder append(String st) {
        StringBuilder newstr = new StringBuilder(str);
        str.append(st);
        stack.push(newstr);
        return this;
    }
    /**
     * Removes the characters in a substring of this sequence. The substring begins
     * at the specified start and extends to the character at index
     * end - 1 or to the end of the sequence if no such character exists.
     * If start is equal to end, no changes are made.
     * @param start - The starting index to be removed, including that index.
     * @param end - The ending index to be removed, not including that index.
     * @return A reference to the new object.
     */
    public UndoableStringBuilder delete(int start, int end) {
        try {
            StringBuilder newstr = new StringBuilder(str);
            str.delete(start, end);
            stack.push(newstr);
        } catch (StringIndexOutOfBoundsException e) {
            if ((start < 0) || (end < 0))
                System.out.println("start and end points must be both positive numbers!");
            else if (start > end)
                System.out.println("start point must be lower then the end point!");
            else
                System.out.println("start must be 0<start point<" + str.length());
        }
        return this;
    }
    /**
     * Inserts the string into this character sequence.
     * @param offset -The starting index for the inserted string.
     * @param st - The string that will be inserted.
     * @return A reference to the new object.
     */
    public UndoableStringBuilder insert(int offset, String st) {
        try {
            StringBuilder newstr = new StringBuilder(str);
            str.insert(offset, st);
            stack.push(newstr);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("offset must be 0<=offset<=" + str.length());
        }
        return this;
    }
    /**
     * Replaces the characters in a substring of this sequence with characters in
     * the specified String. The substring begins at the specified start and
     * extends to the character at index end - 1 or to the end of the sequence if
     * no such character exists. First the characters in the substring are removed
     * and then the specified String is inserted at start. (This sequence will be
     * lengthened to accommodate the specified String if necessary).
     * @param start - The starting index to be replaced, including that index.
     * @param end - The ending index to be replaced, not including that index.
     * @param st - The string that will replace the removed sequence.
     * @return A reference to the new object.
     */
    public UndoableStringBuilder replace(int start, int end, String st) {
        try {
            StringBuilder newstr = new StringBuilder(str);
            str.replace(start, end, st);
            stack.push(newstr);
        } catch (StringIndexOutOfBoundsException e) {
            if ((start < 0) || (end < 0))
                System.out.println("start and end points must be both positive numbers!");
            else if (start > end)
                System.out.println("start point must be lower then the end point!");
            else
                System.out.println("start must be 0<start point<" + str.length());
        }
        return this;
    }
    /**
     * Causes this character sequence to be replaced by the reverse of the
     * sequence.
     * @return A reference to the new object.
     */
    public UndoableStringBuilder reverse(){
        StringBuilder st = new StringBuilder(str);
        stack.push(st);
        str.reverse();
        return this;
    }
    /**
     * Cancels the last change that was made to the UndoableStringBuilder sequence and goes back to the previous
     * sequence.
     */
    public void undo()
    {
        if(!stack.isEmpty())
        {
            str = stack.pop();
        }
    }
    /**
     * Checks the length of the sequence.
     * @return - Sequence length.
     */
    public int length() {
        return str.length();
    }
    /**
     * Returns a string representing the data in this sequence
     * @return The data of the sequence as a string.
     */
    public String toString() {
        return str.toString();
    }
}
