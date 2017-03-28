package sapotero.farm.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {
  private static final String PREFERENCES_FILE = "reader_settings";

  public static void saveSharedSetting(Context ctx, String settingName, String settingValue) {
    SharedPreferences sharedPref = ctx.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPref.edit();
    editor.putString(settingName, settingValue);
    editor.apply();
  }

  public static String readSharedSetting(Context ctx, String settingName, String defaultValue) {
    SharedPreferences sharedPref = ctx.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
    return sharedPref.getString(settingName, defaultValue);
  }
}