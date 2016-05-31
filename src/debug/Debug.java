package debug;

/**
 * Created by JUNO_XPS on 2016-05-13.
 */
public class Debug {
    public static boolean flag = true;

    public static void println(String msg) {
        if (flag) {
            System.err.println(msg);
        }
    }

    public static void print(String msg) {
        if (flag) {
            System.err.print(msg);
        }
    }
}
