package gamelibx.resource_loader;

import java.io.InputStream;
import java.net.URL;

public class ResourceLoader {
    public static InputStream getResourceAsStream(String path) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }

    public static URL getResource(String path) {
        return Thread.currentThread().getContextClassLoader().getResource(path);
    }
}
