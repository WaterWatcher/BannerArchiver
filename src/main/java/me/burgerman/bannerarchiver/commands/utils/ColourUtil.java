package me.burgerman.bannerarchiver.commands.utils;

/**
 * to retrieve the link part for each colour
 *
 * Author: Hamburger 19/05/2021
 */

public class ColourUtil {

    public static String basecolourGetter (String colour){
        String coloururl = "";
        // I couldn't find any logic to the resulting letters, so this is the best i could think of for now
        switch (colour) {
            case "black":  coloururl = "a";
                break;
            case "gray":  coloururl= "i";
                break;
            case "silver":  coloururl = "h";
                break;
            case "white":  coloururl = "p";
                break;
            case "pink":  coloururl = "j";
                break;
            case "magenta":  coloururl = "n";
                break;
            case "purple":  coloururl = "f";
                break;
            case "blue":  coloururl = "e";
                break;
            case "cyan":  coloururl = "g";
                break;
            case "light_blue":
            case "lightBlue":
                coloururl = "m";
                break;
            case "green":  coloururl = "c";
                break;
            case "lime":  coloururl = "k";
                break;
            case "yellow":  coloururl = "l";
                break;
            case "orange":  coloururl = "o";
                break;
            case "brown":  coloururl = "d";
                break;
            case "red":  coloururl = "b";
                break;

        }

        return coloururl;
    }
}
