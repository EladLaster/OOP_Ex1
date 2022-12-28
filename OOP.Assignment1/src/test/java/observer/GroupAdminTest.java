package observer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupAdminTest {

    GroupAdmin ga = new GroupAdmin();
    ConcreteMember test_1 = new ConcreteMember();
    ConcreteMember test_2 = new ConcreteMember();
    ConcreteMember test_3 = new ConcreteMember();

    @Test
    void register() {
        ga.register(test_1);
        assertEquals(1,ga.arrMember.size());
        ga.register(test_2);
        assertEquals(2,ga.arrMember.size());
        ga.register(test_3);
        assertEquals(3,ga.arrMember.size());

    }

    @Test
    void unregister() {
        ga.register(test_1);
        assertEquals(1,ga.arrMember.size());
        ga.register(test_2);
        assertEquals(2,ga.arrMember.size());
        ga.register(test_3);
        assertEquals(3,ga.arrMember.size());
        ga.unregister(test_1);
        assertEquals(2,ga.arrMember.size());
        ga.unregister(test_2);
        assertEquals(1,ga.arrMember.size());
        ga.unregister(test_3);
        assertEquals(0,ga.arrMember.size());
    }

    @Test
    void insert() {
        ga.register(test_1);
        ga.register(test_2);
        ga.append("check function");
        ga.insert(6,"insert ");
        assertEquals("check insert function",test_1.toString());
        ga.insert(5," please");
        assertEquals("check please insert function",test_1.toString());
    }
    @Test
    void append() {
        ga.register(test_1);
        ga.register(test_2);
        ga.append("check append function");
        assertEquals("check append function",test_1.toString());
        ga.append(" please");
        assertEquals("check append function please",test_1.toString());
    }


    @Test
    void delete() {
        ga.register(test_1);
        ga.register(test_2);
        ga.append("please check delete function");
        ga.delete(0,7);
        assertEquals("check delete function",test_1.toString());
        ga.delete(6,13);
        assertEquals("check function",test_2.toString());

    }

    @Test
    void undo() {
        ga.register(test_1);
        ga.append("to be or to be");
        assertEquals("to be or to be", test_1.toString());
        ga.insert(8, " not");
        assertEquals("to be or not to be", test_1.toString());
        ga.undo();
        assertEquals("to be or to be", test_1.toString());
        ga.delete(5,8);
        assertEquals("to be to be", test_1.toString());
        ga.undo();
        assertEquals("to be or to be", test_1.toString());
        ga.undo();
        assertEquals("", test_1.toString());


    }

    @Test
    void Notify() {
        UndoableStringBuilder usb = new UndoableStringBuilder();
        usb.append("David Stern and Elad Laster");
        ga.register(test_1);
        ga.register(test_2);
        ga.register(test_3);
        ga.append("to be or to be");
        assertEquals("to be or to be", test_1.toString());
        ga.Notify(usb);
        for (int i = 0; i < ga.arrMember.size(); i++) {
            assertEquals("David Stern and Elad Laster",(ga.arrMember.get(i)).toString() );
        }


    }
}