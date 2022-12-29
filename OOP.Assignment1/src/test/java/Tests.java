import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    @Test
    public void test(){
        String s1 = "Alice";
        String s2 = "Bob";

        logger.info(()->JvmUtilities.objectFootprint(s1));

        logger.info(()->JvmUtilities.objectFootprint(s1,s2));

        logger.info(()->JvmUtilities.objectTotalSize(s1));

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
