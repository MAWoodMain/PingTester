import java.io.*;
import java.util.Arrays;

/**
 * PingTester - PACKAGE_NAME
 * Created by matthew on 19/11/16.
 */
public class Main
{
    public static void main(String[] args) throws IOException
    {
        FileInputStream fs = new FileInputStream(args[0]);
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));

        FileOutputStream fos = new FileOutputStream(args[1]);
        PrintWriter pw = new PrintWriter(fos);

        pw.println(",,IPV4,,,,IPV6,,,");
        System.out.println(",,IPV4,,,,IPV6,,,");
        pw.println("Rank,Hostname,min,avg,max,mdev,min,avg,max,mdev");
        System.out.println("Rank,Hostname,min,avg,max,mdev,min,avg,max,mdev");

        String line;
        while ((line = br.readLine()) != null)
        {
            line += "," +
                    Arrays.toString(
                            new PingTester(PingTester.Mode.IPV4, "www." + line.split(",")[1]).run())
                            .replace("[", "").replace("]", "").replace(" ", "");
            line += "," +
                    Arrays.toString(
                            new PingTester(PingTester.Mode.IPV6, "www." + line.split(",")[1]).run())
                            .replace("[", "").replace("]", "").replace(" ", "");
            pw.println(line);
            System.out.println(line);
        }

        pw.close();
        fos.close();

        br.close();
        fs.close();
    }
}
