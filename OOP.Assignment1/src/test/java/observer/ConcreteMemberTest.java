package observer;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteMemberTest {

    @Test
    void update() {
        UndoableStringBuilder usb = new UndoableStringBuilder();
        GroupAdmin ga = new GroupAdmin();
        ConcreteMember cm = new ConcreteMember();
        ga.register(cm);
        ga.append("elad laster");
        assertEquals("elad laster",cm.toString());
        usb.append("David Stern");
        cm.update(usb);
        assertEquals("David Stern",cm.toString());
    }
}