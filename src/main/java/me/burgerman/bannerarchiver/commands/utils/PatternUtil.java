package me.burgerman.bannerarchiver.commands.utils;

/**
 * to retrieve the link part for each part
 * 1.12.2 patterns, so no snout etc
 * Author: Hamburger 19/05/2021
 */

public class PatternUtil {

    public static String basepatternGetter (String pattern){
        String patternurl = "";
        // I couldn't find any logic to the resulting letters, so this is the best i could think of for now. The names for the patterns are also just wow
        switch (pattern) {
            case "gradient":
                patternurl = "p";
                break;
            case "gradient_up":
                patternurl = "K";
                break;
            case "half_horizontal":
                patternurl = "q";
                break;
            case "half_horizontal_bottom":
                patternurl = "L";
                break;
            case "square_bottom_right":
                patternurl = "d";
                break;
            case "square_bottom_left":
                patternurl = "b";
                break;
            case "square_top_left":
                patternurl = "C";
                break;
            case "square_top_right":
                patternurl = "D";
                break;
            case "stripe_bottom":
                patternurl = "f";
                break;
            case "stripe_top":
                patternurl = "E";
                break;
            case "flower":
                patternurl = "o";
                break;
            case "half_vertical_right":
                patternurl = "M";
                break;
            case "half_vertical":
                patternurl = "H";
                break;
            case "stripe_left":
                patternurl = "s";
                break;
            case "stripe_right":
                patternurl = "y";
                break;
            case "bricks":
                patternurl = "e";
                break;
            case "diagonal_left":
                patternurl = "r";
                break;
            case "diagonal_right":
                patternurl = "J";
                break;
            case "diagonal_up_left":
                patternurl = "I";
                break;
            case "diagonal_up_right":
                patternurl = "x";
                break;
            case "cross":
                patternurl = "j";
                break;
            case "stripe_downleft":
                patternurl = "m";
                break;
            case "stripe_downright":
                patternurl = "n";
                break;
            case "straight_cross":
                patternurl = "z";
                break;
            case "stripe_center":
                patternurl = "l";
                break;
            case "stripe_middle":
                patternurl = "w";
                break;
            case "triangle_top":
                patternurl = "F";
                break;
            case "triangle_bottom":
                patternurl = "g";
                break;
            case "rhombus":
                patternurl = "v";
                break;
            case "circle":
                patternurl = "t";
                break;
            case "triangles_bottom":
                patternurl = "h";
                break;
            case "triangles_top":
                patternurl = "G";
                break;
            case "small_stripes":
                patternurl = "B";
                break;
            case "border":
                patternurl = "c";
                break;
            case "curly_border":
                patternurl = "i";
                break;
            case "creeper":
                patternurl = "k";
                break;
            case "skull":
                patternurl = "A";
                break;
            case "mojang":
                patternurl = "u";
                break;
        }
        return patternurl;
    }
}
