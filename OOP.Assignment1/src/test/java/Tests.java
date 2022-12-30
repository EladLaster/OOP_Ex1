ver.ConcreteMember;
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
        System.out.println("memory in group before register:");
        logger.info(() -> JvmUtilities.objectFootprint(ga));
        logger.info(() -> JvmUtilities.objectTotalSize(ga));
        logger.info(() -> JvmUtilities.jvmInfo());

        ga.register(cm);
        assertEquals(1, ga.arrMember.size());
        ga.register(cm2);
        assertEquals(2, ga.arrMember.size());

        System.out.println("memory in group after register:");
        logger.info(() -> JvmUtilities.objectFootprint(ga));
        logger.info(() -> JvmUtilities.objectTotalSize(ga));
        logger.info(() -> JvmUtilities.jvmInfo());
    }

    @Test
    public void testUnregister() {
        ga.register(cm);
        ga.register(cm2);

        System.out.println("memory in group before unregister:");
        logger.info(() -> JvmUtilities.objectFootprint(ga));
        logger.info(() -> JvmUtilities.objectTotalSize(ga));
        logger.info(() -> JvmUtilities.jvmInfo());

        ga.unregister(cm);
        assertEquals(1, ga.arrMember.size());
        ga.unregister(cm2);
        assertEquals(0, ga.arrMember.size());

        System.out.println("memory in group after unregister:");
        logger.info(() -> JvmUtilities.objectFootprint(ga));
        logger.info(() -> JvmUtilities.objectTotalSize(ga));
        logger.info(() -> JvmUtilities.jvmInfo());

    }


    @Test
    void testAppend() {
        ga.register(cm);

        System.out.println("memory  in member before append:");
        logger.info(() -> JvmUtilities.objectFootprint(cm));
        logger.info(() -> JvmUtilities.objectTotalSize(cm));
        logger.info(() -> JvmUtilities.jvmInfo());

        ga.append("hello");
        assertEquals("hello", cm.toString());

        logger.info(() -> "memory in member after append:");
        logger.info(() -> JvmUtilities.objectFootprint(cm));
        logger.info(() -> JvmUtilities.objectTotalSize(cm));
        logger.info(() -> JvmUtilities.jvmInfo());

    }

    @Test
    void testDelete() {
        ga.register(cm);
        ga.append("hello world");

        System.out.println("memory in member before delete:");
        logger.info(() -> JvmUtilities.objectFootprint(cm));
        logger.info(() -> JvmUtilities.objectTotalSize(cm));
        logger.info(() -> JvmUtilities.jvmInfo());

        ga.delete(0, 6);
        assertEquals("world", cm.toString());

        System.out.println("memory in member after delete:");
        logger.info(() -> JvmUtilities.objectFootprint(cm));
        logger.info(() -> JvmUtilities.objectTotalSize(cm));
        logger.info(() -> JvmUtilities.jvmInfo());
    }

    @Test
    void testInsert() {
        ga.register(cm);
        ga.append("world");

        System.out.println("memory in member before insert:");
        logger.info(() -> JvmUtilities.objectFootprint(cm));
        logger.info(() -> JvmUtilities.objectTotalSize(cm));
        logger.info(() -> JvmUtilities.jvmInfo());

        ga.insert(0, "hello ");
        assertEquals("hello world", cm.toString());

        System.out.println("memory in member after insert:");
        logger.info(() -> JvmUtilities.objectFootprint(cm));
        logger.info(() -> JvmUtilities.objectTotalSize(cm));
        logger.info(() -> JvmUtilities.jvmInfo());
    }


    @Test
    void testUndo() {
        ga.register(cm);
        ga.append("world");

        System.out.println("memory in member before the second append:");
        logger.info(() -> JvmUtilities.objectFootprint(cm));
        logger.info(() -> JvmUtilities.objectTotalSize(cm));
        logger.info(() -> JvmUtilities.jvmInfo());

        ga.append("s");

        System.out.println("memory in member after the second append:");
        logger.info(() -> JvmUtilities.objectFootprint(cm));
        logger.info(() -> JvmUtilities.objectTotalSize(cm));
        logger.info(() -> JvmUtilities.jvmInfo());

        ga.undo();
        assertEquals("world", cm.toString());

        System.out.println("memory in member after undo:");
        logger.info(() -> JvmUtilities.objectFootprint(cm));
        logger.info(() -> JvmUtilities.objectTotalSize(cm));
        logger.info(() -> JvmUtilities.jvmInfo());
    }

    @Test
    public void testNotify() {
        UndoableStringBuilder usb = new UndoableStringBuilder();
        usb.append("David Stern and Elad Laster");
        ga.register(cm);
        ga.register(cm2);

        System.out.println("memory in member before Notify:");
        logger.info(() -> JvmUtilities.objectFootprint(cm));
        logger.info(() -> JvmUtilities.objectTotalSize(cm));
        logger.info(() -> JvmUtilities.jvmInfo());

        System.out.println("memory in group before Notify:");
        logger.info(() -> JvmUtilities.objectFootprint(ga));
        logger.info(() -> JvmUtilities.objectTotalSize(ga));
        logger.info(() -> JvmUtilities.jvmInfo());

        ga.append("to be or to be");
        assertEquals("to be or to be", cm.toString());
        ga.Notify(usb);
        for (int i = 0; i < ga.arrMember.size(); i++) {
            assertEquals("David Stern and Elad Laster", (ga.arrMember.get(i)).toString());
        }
        System.out.println("memory in member after Notify:");
        logger.info(() -> JvmUtilities.objectFootprint(cm));
        logger.info(() -> JvmUtilities.objectTotalSize(cm));
        logger.info(() -> JvmUtilities.jvmInfo());

        System.out.println("memory in group after Notify:");
        logger.info(() -> JvmUtilities.objectFootprint(ga));
        logger.info(() -> JvmUtilities.objectTotalSize(ga));
        logger.info(() -> JvmUtilities.jvmInfo());

    }
        @Test
        public void testUpdate () {
            UndoableStringBuilder usb = new UndoableStringBuilder();
            GroupAdmin ga = new GroupAdmin();
            ConcreteMember cm = new ConcreteMember();
            ga.register(cm);

            System.out.println("memory in member before update:");
            logger.info(() -> JvmUtilities.objectFootprint(cm));
            logger.info(() -> JvmUtilities.objectTotalSize(cm));
            logger.info(() -> JvmUtilities.jvmInfo());

            ga.append("elad laster");
            assertEquals("elad laster", cm.toString());
            usb.append("David Stern");
            cm.update(usb);
            assertEquals("David Stern", cm.toString());

            System.out.println("memory in member after update:");
            logger.info(() -> JvmUtilities.objectFootprint(cm));
            logger.info(() -> JvmUtilities.objectTotalSize(cm));
            logger.info(() -> JvmUtilities.jvmInfo());
        }

    }


