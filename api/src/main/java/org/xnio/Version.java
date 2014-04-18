
package org.xnio;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * The version class.
 *
 * @apiviz.exclude
 */
public final class Version {
    private Version() {}

    private static final String JAR_NAME;

    /**
     * The current XNIO version.
     */
    public static final String VERSION;

    static {
        Properties versionProps = new Properties();
        String jarName = "(unknown)";
        String versionString = "(unknown)";
        try {
            final InputStream stream = Version.class.getResourceAsStream("Version.properties");
            try {
                final InputStreamReader reader = new InputStreamReader(stream);
                try {
                    versionProps.load(reader);
                    jarName = versionProps.getProperty("jarName", jarName);
                    versionString = versionProps.getProperty("version", versionString);
                } finally {
                    try {
                        reader.close();
                    } catch (Throwable ignored) {
                    }
                }
            } finally {
                try {
                    stream.close();
                } catch (Throwable ignored) {
                }
            }
        } catch (IOException ignored) {
        }
        JAR_NAME = jarName;
        VERSION = versionString;
    }

    /**
     * Get the JAR name.
     *
     * @return the JAR name
     */
    public static String getJarName() {
        return JAR_NAME;
    }

    /**
     * Get the version string.
     *
     * @return the version string
     */
    public static String getVersionString() {
        return VERSION;
    }

    /**
     * Print out the current XNIO version on {@code System.out}.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        System.out.print(VERSION);
    }
}
