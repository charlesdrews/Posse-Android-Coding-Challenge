package com.charlesdrews.charlesdrewsdemoapp;

import android.graphics.Color;
import android.support.annotation.NonNull;

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
public class ColorUtil {

    private static final List<String> PARSEABLE_COLORS = Arrays.asList(
            "red", "blue", "green", "black", "white", "gray", "cyan", "magenta", "yellow",
            "lightgray", "darkgray", "grey", "lightgrey", "darkgrey", "aqua", "fuchsia",
            "lime", "maroon", "navy", "olive", "purple", "silver", "teal");

    /**
     * Convert a string representing a color name to the corresponding color int
     * @param colorName
     * @return the int representation of the color if name is parseable, else -1
     */
    public static int ParseColorByName(@NonNull String colorName) {
        if (PARSEABLE_COLORS.contains(colorName)) {
            return Color.parseColor(colorName);
        } else {
            return -1;
        }
    }

    /**
     * Select white or black text color based on the specified background color
     * Formula taken from: http://stackoverflow.com/a/3943023
     *
     * @param backgroundColor int representation of background color
     * @return int value of white or black
     */
    public static int GetAppropriateTextColor(int backgroundColor) {
        double redPart = (double) Color.red(backgroundColor);
        double greenPart = (double) Color.green(backgroundColor);
        double bluePart = (double) Color.blue(backgroundColor);

        double luminance = 0.2126 * convertColorPart(redPart) +
                0.7152 * convertColorPart(greenPart) +
                0.0722 * convertColorPart(bluePart);

        return luminance > 0.179 ? Color.BLACK : Color.WHITE;
    }

    private static double convertColorPart(double colorPart) {
        colorPart /= 255.0;
        if (colorPart <= 0.03928) {
            return colorPart / 12.92;
        } else {
            return Math.pow(((colorPart + 0.055) / 1.055), 2.4);
        }
    }
}
