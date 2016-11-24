import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * PingTester - PACKAGE_NAME
 * Created by matthew on 19/11/16.
 */
class PingTester
{
    enum Mode
    {
        IPV4,
        IPV6
    }

    private final Mode mode;
    private final String hostname;
    private static final String ARGUMENTS = " -c 10 -i 0.2 "; // arguments for count 10 interval 200ms

    PingTester(Mode mode, String hostname)
    {
        this.mode = mode;
        this.hostname = hostname;
    }

    float[] run()
    {

        float[] data = {-1f,-1f,-1f,-1f};
        String cmd = mode == Mode.IPV4? "ping" : "ping6"; // select mode
        cmd += ARGUMENTS; // add arguments
        cmd += hostname; // add hostname

        try {
            Process p = Runtime.getRuntime().exec(cmd); // run command
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream())); // lock on output
            String output = "";
            String s;
            while ((s = inputStream.readLine()) != null) {
                output = s; // capture last line of output: the stats
            }

            String[] parts = output.split("= "); // split data from preamble
            if (parts.length < 2) return data;
            output = parts[1];
            output = output.substring(0,output.indexOf("ms")); // remove end junk

            parts = output.split("/"); // split on data separator
            if (parts.length < 4) return data; // check length

            for (int i = 0; i<4; i++) data[i] = Float.parseFloat(parts[i]); // convert data
            return data;
        } catch (IOException e) {
            return data;
        }

    }
}
