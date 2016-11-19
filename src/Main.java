import java.util.Arrays;

/**
 * PingTester - PACKAGE_NAME
 * Created by matthew on 19/11/16.
 */
public class Main
{
    public static void main(String[] args)
    {
        System.out.println(Arrays.toString(new PingTester(PingTester.Mode.IPV4, "google.co.uk").run()));
    }
}
