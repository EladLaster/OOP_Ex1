import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.UndoableStringBuilder;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);

    // stub method to check external dependencies compatibility
    @Test
    public void test() {
        String s1 = "Alice";
        String s2 = "Bob";

        logger.info(() -> JvmUtilities.objectFootprint(s1));

        logger.info(() -> JvmUtilities.objectFootprint(s1, s2));

        logger.info(() -> JvmUtilities.objectTotalSize(s1));

        logger.info(() -> JvmUtilities.jvmInfo());
    }

    GroupAdmin ga = new GroupAdmin();
    ConcreteMember cm = new ConcreteMember();
    ConcreteMember cm2 = new ConcreteMember();

    @Test
    public void testRegister() {
        ga.register(cm);
        assertEquals(1, ga.arrMember.size());
        ga.register(cm2);
        assertEquals(2, ga.arrMember.size());

        logger.info(() -> JvmUtilities.objectFootprint(cm, cm2));
        logger.info(() -> "memory size in member after register:");
        logger.info(() -> JvmUtilities.objectTotalSize(cm));
        logger.info(() -> JvmUtilities.objectTotalSize(cm2));
        logger.info(() -> JvmUtilities.jvmInfo());
    }

    @Test
    public void testUnregister() {
        ga.register(cm);
        ga.register(cm2);
        ga.unregister(cm);
        assertEquals(1, ga.arrMember.size());
        ga.unregister(cm2);
        assertEquals(0, ga.arrMember.size());

        logger.info(() -> JvmUtilities.objectFootprint(cm, cm2));
        logger.info(() -> "memory size in member after unregister:");
        logger.info(() -> JvmUtilities.objectTotalSize(cm));
        logger.info(() -> JvmUtilities.objectTotalSize(cm2));
        logger.info(() -> JvmUtilities.jvmInfo());

    }


    @Test
    void testAppend() {
        ga.register(cm);
        ga.append("hello");
        assertEquals("hello", cm.toString());

        logger.info(() -> JvmUtilities.objectFootprint(cm));
        logger.info(() -> "memory size in member after append:");
        logger.info(() -> JvmUtilities.objectTotalSize(cm));
        logger.info(() -> JvmUtilities.jvmInfo());

    }

    @Test
    void testDelete() {
        ga.register(cm);
        ga.append("hello world");
        ga.delete(0, 6);
        assertEquals("world", cm.toString());

        logger.info(() -> JvmUtilities.objectFootprint(cm));
        logger.info(() -> "memory size in member after delete:");
        logger.info(() -> JvmUtilities.objectTotalSize(cm));
        logger.info(() -> JvmUtilities.jvmInfo());
    }

    @Test
    void testInsert() {
        ga.register(cm);
        ga.append("world");
        ga.insert(0, "hello ");
        assertEquals("hello world", cm.toString());

        logger.info(() -> JvmUtilities.objectFootprint(cm));
        logger.info(() -> "memory size in member after insert:");
        logger.info(() -> JvmUtilities.objectTotalSize(cm));
        logger.info(() -> JvmUtilities.jvmInfo());
    }


    @Test
    void testUndo() {
        ga.register(cm);
        ga.append("world");
        ga.append("s");
        ga.undo();
        assertEquals("world", cm.toString());

        logger.info(() -> JvmUtilities.objectFootprint(cm));
        logger.info(() -> "memory size in member after undo:");
        logger.info(() -> JvmUtilities.objectTotalSize(cm));
        logger.info(() -> JvmUtilities.jvmInfo());
    }

    @Test
    public void testNotify() {
        UndoableStringBuilder usb = new UndoableStringBuilder();
        usb.append("David Stern and Elad Laster");
        ga.register(cm);
        ga.register(cm2);
        ga.append("to be or to be");
        assertEquals("to be or to be", cm.toString());
        ga.Notify(usb);
        for (int i = 0; i < ga.arrMember.size(); i++) {
            assertEquals("David Stern and Elad Laster", (ga.arrMember.get(i)).toString());

            logger.info(() -> JvmUtilities.objectFootprint(cm, cm2));
            logger.info(() -> "memory size in member after Notify:");
            logger.info(() -> JvmUtilities.objectTotalSize(cm));
            logger.info(() -> JvmUtilities.objectTotalSize(cm2));
            logger.info(() -> JvmUtilities.jvmInfo());
        }
    }
        @Test
        public void testUpdate () {
            UndoableStringBuilder usb = new UndoableStringBuilder();
            GroupAdmin ga = new GroupAdmin();
            ConcreteMember cm = new ConcreteMember();
            ga.register(cm);
            ga.append("elad laster");
            assertEquals("elad laster", cm.toString());
            usb.append("David Stern");
            cm.update(usb);
            assertEquals("David Stern", cm.toString());

            logger.info(() -> JvmUtilities.objectFootprint(cm));
            logger.info(() -> "memory size in member after update:");
            logger.info(() -> JvmUtilities.objectTotalSize(cm));
            logger.info(() -> JvmUtilities.jvmInfo());
        }

    }


