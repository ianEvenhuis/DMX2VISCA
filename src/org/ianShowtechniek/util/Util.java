package org.ianShowtechniek.util;

import org.ianShowtechniek.App;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.security.CodeSource;

public class Util {

    public static String integerTo4Hex(int i) {
        i--;
        String s = Integer.toHexString(i).toUpperCase();
        if (s.length() == 1) {
            s = "000" + s;
        } else if (s.length() == 2) {
            s = "00" + s;
        } else if (s.length() == 3) {
            s = "0" + s;
        }
        char[] rs = s.toCharArray();
        String res = "";
        for (char c : rs) {
            res += "0" + c;
        }
        return res;
    }

    public static String integerTo2Hex(int i) {
        i--;
        if (i > 256) {
            i = 256;
        }
        String s = Integer.toHexString(i).toUpperCase();

        if (s.length() == 1) {
            s = "0" + s;
        }

        char[] rs = s.toCharArray();
        String res = "";
        for (char c : rs) {
            res += "0" + c;
        }
        return res;
    }

    public static String getJarContainingFolder() {
        CodeSource codeSource = App.class.getProtectionDomain().getCodeSource();

        File jarFile = new File(".");
        try {
            if (codeSource.getLocation() != null) {
                jarFile = new File(codeSource.getLocation().toURI());

            } else {
                String path = App.class.getResource(App.class.getSimpleName() + ".class").getPath();
                String jarFilePath = path.substring(path.indexOf(":") + 1, path.indexOf("!"));
                jarFilePath = URLDecoder.decode(jarFilePath, "UTF-8");
                jarFile = new File(jarFilePath);
            }
        } catch (URISyntaxException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return jarFile.getParentFile().getAbsolutePath();
    }

    public static int get16bitValue(byte val1, byte val2) {
        return (val1 & 0xff) | ((val2 & 0xff) << 8);
    }

    public static int get8bitValue(byte val) {
        return (val & 0xFF);
    }

    public static double mapd(double value, double lowOrg, double highOrg, double lowTar, double highTar) {
        return lowTar + ((value - lowOrg) * (highTar - lowTar)) / (highOrg - lowOrg);
    }

    /**
     * So apperenly the value only goes between (65536 - 2242) and (0 + 2442) which both are camera facing back wards
     * @param degree
     * @return
     */
    public int mapPan(int degree){
        int pan = (int) Util.mapd(degree, 0, 360, 0, 4884);

        if (pan > 2442) {
            return (pan - 2442);
        }else{
            return  65536 + (pan - 2442);
        }
    }
}
