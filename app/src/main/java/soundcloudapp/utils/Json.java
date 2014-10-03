package soundcloudapp.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Json {
    public static Object fromStringToJson(String string) {
        // Convert string into a JSONArray/JSONObject

        Object json = null;

        if (string.startsWith("[")) {
            try {
                json = new JSONArray(string);
            } catch (JSONException e) {
                Log.e("fromStringToJson", "Error string to jsonArray");
                e.printStackTrace();
            }
        } else if (string.startsWith("{")) {
            try {
                json = new JSONObject(string);
            } catch (JSONException e) {
                Log.e("fromStringToJson", "Error string to jsonObject");
                e.printStackTrace();
            }
        }
        return json;
    }
}
