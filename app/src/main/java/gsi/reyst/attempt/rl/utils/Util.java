package gsi.reyst.attempt.rl.utils;

public class Util {

    public static final int PERMISSION_REQUEST_CODE = 1001;

    public static String prepareString(String strLat, String strLon) {
        //noinspection StringBufferReplaceableByString
        return new StringBuilder().append(strLat).append(", ").append(strLon).toString();
    }

}
