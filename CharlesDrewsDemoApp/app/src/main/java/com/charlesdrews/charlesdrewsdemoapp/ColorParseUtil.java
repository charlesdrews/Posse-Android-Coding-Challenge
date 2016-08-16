package com.charlesdrews.charlesdrewsdemoapp;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;

import java.util.Arrays;
import java.util.List;

/**
 * Tool to translate the "favorite color" values in the raw data to actual color ints
 *
 * List of parseable colors is from
 * https://developer.android.com/reference/android/graphics/Color.html#parseColor(java.lang.String)
 *
 * Created by charlie on 8/16/16.
 */
public class ColorParseUtil {

    private static final List<String> PARSEABLE_COLORS = Arrays.asList(
            "red", "blue", "green", "black", "white", "gray", "cyan", "magenta", "yellow",
            "lightgray", "darkgray", "grey", "lightgrey", "darkgrey", "aqua", "fuchsia",
            "lime", "maroon", "navy", "olive", "purple", "silver", "teal");

    public static int ParseColorByName(String colorName) {
        if (PARSEABLE_COLORS.contains(colorName)) {
            return Color.parseColor(colorName);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return Resources.getSystem().getColor(R.color.colorPrimary, null);
            } else {
                return Resources.getSystem().getColor(R.color.colorPrimary);
            }
        }
    }
}
