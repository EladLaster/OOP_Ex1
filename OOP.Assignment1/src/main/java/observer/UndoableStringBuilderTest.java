package observer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UndoableStringBuilderTest {
    UndoableStringBuilder test_1 = new UndoableStringBuilder();
    UndoableStringBuilder test_2 = new UndoableStringBuilder();

    @Test
    void append() {
        test_1.append("to be or not to be");
        assertEquals("to be or not to be", test_1.toString());
        test_1.append(" OR NOT TO BE");
        assertEquals("to be or not to be OR NOT TO BE", test_1.toString());
    }

    @Test
    void delete() {
        test_1.append("to be or not to be");
        test_1.delete(8, 12);
        assertEquals("to be or to be", test_1.toString());
        test_1.delete(5, 8);
        assertEquals("to be to be", test_1.toString());
    }

    @Test
    void insert() {
        test_1.append("to be or to be");
        test_1.insert(8, " not");
        assertEquals("to be or not to be", test_1.toString());
        test_1.insert(18, " a bee");
        assertEquals("to be or not to be a bee", test_1.toString());
        test_2.append("Elad Stern");
        test_2.insert(5, "Laster and David ");
        assertEquals("Elad Laster and David Stern", test_2.toString());


    }

    @Test
    void replace() {
        test_1.append("Sunday");
        test_1.replace(0, 3, "Satur");
        assertEquals("Saturday", test_1.toString());
        test_2.append("abcddddnop");
        test_2.replace(4, 7, "efghijklm");
        assertEquals("abcdefghijklmnop", test_2.toString());
    }

    @Test
    void reverse() {
        test_1.append("David Stern");
        test_1.reverse();
        assertEquals("nretS divaD", test_1.toString());
        test_1.reverse();
        assertEquals("David Stern", test_1.toString());
        test_2.append("Elad Laster");
        test_2.reverse();
        assertEquals("retsaL dalE", test_2.toString());
        test_2.reverse();
        assertEquals("Elad Laster", test_2.toString());

    }

    @Test
    void undo() {

        test_1.append("to be or not to be");
        assertEquals("to be or not to be", test_1.toString());
        test_1.replace(3, 5, "eat");
        assertEquals("to eat or not to be", test_1.toString());
        test_1.replace(17, 19, "eat");
        assertEquals("to eat or not to eat", test_1.toString());
        test_1.reverse();
        assertEquals("tae ot ton ro tae ot", test_1.toString());
        test_1.undo();
        assertEquals("to eat or not to eat", test_1.toString());
        test_1.undo();
        assertEquals("to eat or not to be", test_1.toString());
        test_1.undo();
        assertEquals("to be or not to be", test_1.toString());
        test_1.undo();
        assertEquals("", test_1.toString());
        test_2.append("to be or to be");
        assertEquals("to be or to be", test_2.toString());
        test_2.insert(8, " not");
        test_2.undo();
        assertEquals("to be or to be", test_2.toString());
        test_2.delete(5,8);
        assertEquals("to be to be", test_2.toString());
        test_2.undo();
        assertEquals("to be or to be", test_2.toString());
    }

    @Test
    void length() {
        test_1.append("David Stern");
        assertEquals(11,test_1.length());
        test_2.append("Elad Laster");
        assertEquals(11,test_2.length());
    }

    @Test
    void testToString() {
        test_1.append("The week starts on Sunday");
        assertEquals("The week starts on Sunday", test_1.toString());
        test_2.append("The school week starts on Monday");
        assertEquals("The school week starts on Monday",test_2.toString());


    }

}