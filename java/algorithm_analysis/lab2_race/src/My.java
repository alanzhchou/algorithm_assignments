import org.junit.Test;

/**
 * @author : Alan
 * @version : 1.0
 * @serial : 2018/3/13
 * @since : Java_8
 */
public class My {
    @Test
    public void testMyNextInt(){
        RaceEvent event = new RaceEvent();
        System.out.println(event);
        int next = RaceEvent.reader.nextInt();
        System.out.println(next);
    }
}
