package kz.test.demo.migration;

import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class MigrationReader {
    public MigrationReader() {
    }

    public abstract void migrate();

    public InputStreamReader getInputStreamReader(String fileName) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream in = classLoader.getResourceAsStream(fileName);
        if (in == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new InputStreamReader(in);
        }
    }
}
