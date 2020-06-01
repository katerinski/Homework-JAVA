package test.java.utils;

import java.util.ResourceBundle;

//Вынести общие переменные (URL и т.п.) в .properties файл
public class PropertyLoader {
    private static ResourceBundle src = ResourceBundle.getBundle("config");

    public static String loadProperty(String name) {

        return src.getString(name);
    }
}
