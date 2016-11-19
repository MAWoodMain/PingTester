import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * PingTester - PACKAGE_NAME
 * Created by matthew on 19/11/16.
 */
public class PingTester
{
    enum Mode
    {
        IPV4,
        IPV6
    }

    private final Mode mode;
    private final String hostname;
    private static final String ARGUMENTS = " -c 10 ";

    PingTester(Mode mode, String hostname)
    {
        this.mode = mode;
        this.hostname = hostname;
    }

    float[] run()
    {
        String cmd = mode == Mode.IPV4? "ping" : "ping6";
        cmd += ARGUMENTS;
        cmd += hostname;

        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String output = "";
            String s;
            while ((s = inputStream.readLine()) != null) {
                output = s; // capture last line of output: the stats
            }

            String[] parts = output.split("= "); // split data from preamble
            if (parts.length < 2) return null;
            output = parts[1];
            output = output.substring(0,output.length() - 3); // remove end junk

            parts = output.split("/"); // split on data separator
            if (parts.length < 4) return null; // check length

            float[] data = new float[4];
            for (int i = 0; i<4; i++) data[i] = Float.parseFloat(parts[i]); // convert data
            return data;
        } catch (IOException e) {
            return null;
        }

    }
}
