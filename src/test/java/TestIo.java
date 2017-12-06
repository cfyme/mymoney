import com.fshows.proxy.contants.MyConstants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestIo {

    public static void main(String[] args) throws  Exception{
        InputStream in = new URL( "http://www.taofen8.com" ).openStream();
//        try {
//            InputStreamReader inR = new InputStreamReader( in );
//            BufferedReader buf = new BufferedReader( inR );
//            String line;
//            while ( ( line = buf.readLine() ) != null ) {
//                System.out.println( line );
//            }
//        } finally {
//            in.close();
//        }

//        try {
//            System.out.println( IOUtils.toString( in ) );
//        } finally {
//            IOUtils.closeQuietly(in);
//        }


        File file = new File(MyConstants.fileName);

        List<String> lines=new ArrayList<String>();
        lines.add("hello");
        lines.add("world");

        FileUtils.writeLines(file, lines, true);

        lines = FileUtils.readLines(file, "UTF-8");

        System.out.println(lines);
    }
}
