package test.java.utils;

import java.util.ResourceBundle;

public class PropertyLoader {
    private static ResourceBundle src = ResourceBundle.getBundle("config");

    public static String loadProperty(String name) {

        return src.getString(name);
    }
}
