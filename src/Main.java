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

        String line;
        while ((line = br.readLine()) != null)
        {
            line = line + "," +
                    Arrays.toString(
                            new PingTester(PingTester.Mode.IPV4, line.split(",")[1]).run())
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
