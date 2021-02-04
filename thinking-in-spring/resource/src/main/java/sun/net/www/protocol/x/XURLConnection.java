package sun.net.www.protocol.x;

import com.sun.org.apache.bcel.internal.util.ClassPath;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class XURLConnection extends URLConnection {

    private ClassPathResource resource;
    /**
     * Constructs a URL connection to the specified URL. A connection to
     * the object referenced by the URL is not created.
     *
     * @param url the specified URL.
     */
    //  x:///META-INF/default.properties
    protected XURLConnection(URL url) {
        super(url);
        //  /META-INF/default.properties
        this.resource = new ClassPathResource(url.getPath());
    }

    @Override
    public void connect() throws IOException {

    }
    @Override
    public InputStream getInputStream() throws IOException {
        return this.resource.getInputStream();
    }
}
