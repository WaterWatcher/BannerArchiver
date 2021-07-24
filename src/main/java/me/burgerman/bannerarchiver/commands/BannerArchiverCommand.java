package me.burgerman.bannerarchiver.commands;

import me.burgerman.bannerarchiver.BannerArchiverMod;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;

/**
 * Command to create the NeedCoolShoes url for banner recipes.
 *
 * @author Hamburger
 * @since 19/05/2021
 */
public class BannerArchiverCommand extends CommandBase {
    Minecraft mc = Minecraft.getMinecraft();

    @Override
    public String getName() {
        return "bannerarchiver";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "bannerarchiver <hand | trace>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (!(args.length == 0)) {
            if (args[0].equals("hand")) {
                if (mc.player.getHeldItemMainhand().getItem() instanceof ItemBanner) {
                    ItemStack banner = mc.player.getHeldItemMainhand();
                    generateLink(banner, sender);
                } else {
                    sender.sendMessage(new TextComponentString(BannerArchiverMod.PREFIX + TextFormatting.DARK_RED
                            + "Banner not found, please hold the banner in your main hand"));
                }
            } else if (args[0].equals("trace")) {
                RayTraceResult ray = mc.objectMouseOver;

                if (ray.typeOfHit == RayTraceResult.Type.BLOCK) {
                    TileEntity tileentity = mc.world.getTileEntity(ray.getBlockPos());
                    if (tileentity instanceof TileEntityBanner) {
                        generateLink(((TileEntityBanner) tileentity).getItem(), sender);
                    } else {
                        sender.sendMessage(new TextComponentString(BannerArchiverMod.PREFIX + TextFormatting.DARK_RED
                                + "Incorrect target for " + TextFormatting.AQUA
                                + args[0] + TextFormatting.DARK_RED
                                + ", please look at the banner"));
                    }
                } else {
                    sender.sendMessage(new TextComponentString(BannerArchiverMod.PREFIX + TextFormatting.DARK_RED
                            + "Incorrect target for " + TextFormatting.AQUA
                            + args[0] + TextFormatting.DARK_RED
                            + ", please look at the banner"));
                }
            } else {
                sender.sendMessage(new TextComponentString(BannerArchiverMod.PREFIX + TextFormatting.DARK_RED
                        + "Incorrect argument, please use "
                        + TextFormatting.AQUA + "\"hand\" "
                        + TextFormatting.DARK_RED + "for held banners or "
                        + TextFormatting.AQUA + "\"trace\""
                        + TextFormatting.DARK_RED + " for banners that you're looking at"));
            }
        } else {
            sender.sendMessage(new TextComponentString(BannerArchiverMod.PREFIX + TextFormatting.DARK_RED
                    + "Argument expected, please use "
                    + TextFormatting.AQUA + "\"hand\" "
                    + TextFormatting.DARK_RED + "for held banners or "
                    + TextFormatting.AQUA + "\"trace\" "
                    + TextFormatting.DARK_RED + "for banners that you're looking at"));
        }
    }

    private void generateLink (ItemStack banner, ICommandSender sender){
        String url = "https://www.needcoolshoes.com/banner?=";
        NBTTagCompound nbttagcompound = banner.getSubCompound("BlockEntityTag");
        url = url + basecolourGetter(EnumDyeColor.byDyeDamage(banner.getMetadata() & 15)) + "a";

        if (nbttagcompound != null && nbttagcompound.hasKey("Patterns")) {
            NBTTagList nbttaglist = nbttagcompound.getTagList("Patterns", 10);

            // retrieves the bannerlayers and colours to add to the url, similar to the original class for banners
            for (int i = 0; i < nbttaglist.tagCount() && i < 6; ++i) {
                NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
                url += basecolourGetter(EnumDyeColor.byDyeDamage(nbttagcompound1.getInteger("Color")));
                url += basepatternGetter(BannerPattern.byHash(nbttagcompound1.getString("Pattern")));

            }
        }

        TextComponentString linkMsg = new TextComponentString(BannerArchiverMod.PREFIX + TextFormatting.GREEN
                + "Recipe Link for banner <"
                + TextFormatting.GOLD + banner.getDisplayName()
                + TextFormatting.GREEN + ">: "
                + TextFormatting.BLUE + TextFormatting.UNDERLINE + url);

        // Makes the url clickable
        linkMsg.setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url)));
        sender.sendMessage(linkMsg);
    }

    private String basecolourGetter (EnumDyeColor colour){
        String coloururl = "";

        switch (colour) {
            case BLACK:
                coloururl = "a";
                break;
            case GRAY:
                coloururl= "i";
                break;
            case SILVER:
                coloururl = "h";
                break;
            case WHITE:
                coloururl = "p";
                break;
            case PINK:
                coloururl = "j";
                break;
            case MAGENTA:
                coloururl = "n";
                break;
            case PURPLE:
                coloururl = "f";
                break;
            case BLUE:
                coloururl = "e";
                break;
            case CYAN:
                coloururl = "g";
                break;
            case LIGHT_BLUE:
                coloururl = "m";
                break;
            case GREEN:
                coloururl = "c";
                break;
            case LIME:
                coloururl = "k";
                break;
            case YELLOW:
                coloururl = "l";
                break;
            case ORANGE:
                coloururl = "o";
                break;
            case BROWN:
                coloururl = "d";
                break;
            case RED:
                coloururl = "b";
                break;
        }
        return coloururl;
    }

    private String basepatternGetter (BannerPattern pattern){
        String patternurl = "";

        switch (pattern) {
            case GRADIENT:
                patternurl = "p";
                break;
            case GRADIENT_UP:
                patternurl = "K";
                break;
            case HALF_HORIZONTAL:
                patternurl = "q";
                break;
            case HALF_HORIZONTAL_MIRROR:
                patternurl = "L";
                break;
            case SQUARE_BOTTOM_RIGHT:
                patternurl = "d";
                break;
            case SQUARE_BOTTOM_LEFT:
                patternurl = "b";
                break;
            case SQUARE_TOP_LEFT:
                patternurl = "C";
                break;
            case SQUARE_TOP_RIGHT:
                patternurl = "D";
                break;
            case STRIPE_BOTTOM:
                patternurl = "f";
                break;
            case STRIPE_TOP:
                patternurl = "E";
                break;
            case FLOWER:
                patternurl = "o";
                break;
            case HALF_VERTICAL_MIRROR:
                patternurl = "M";
                break;
            case HALF_VERTICAL:
                patternurl = "H";
                break;
            case STRIPE_LEFT:
                patternurl = "s";
                break;
            case STRIPE_RIGHT:
                patternurl = "y";
                break;
            case BRICKS:
                patternurl = "e";
                break;
            case DIAGONAL_LEFT:
                patternurl = "r";
                break;
            case DIAGONAL_RIGHT_MIRROR:
                patternurl = "J";
                break;
            case DIAGONAL_LEFT_MIRROR:
                patternurl = "I";
                break;
            case DIAGONAL_RIGHT:
                patternurl = "x";
                break;
            case CROSS:
                patternurl = "j";
                break;
            case STRIPE_DOWNLEFT:
                patternurl = "m";
                break;
            case STRIPE_DOWNRIGHT:
                patternurl = "n";
                break;
            case STRAIGHT_CROSS:
                patternurl = "z";
                break;
            case STRIPE_CENTER:
                patternurl = "l";
                break;
            case STRIPE_MIDDLE:
                patternurl = "w";
                break;
            case TRIANGLE_TOP:
                patternurl = "F";
                break;
            case TRIANGLE_BOTTOM:
                patternurl = "g";
                break;
            case RHOMBUS_MIDDLE:
                patternurl = "v";
                break;
            case CIRCLE_MIDDLE:
                patternurl = "t";
                break;
            case TRIANGLES_BOTTOM:
                patternurl = "h";
                break;
            case TRIANGLES_TOP:
                patternurl = "G";
                break;
            case STRIPE_SMALL:
                patternurl = "B";
                break;
            case BORDER:
                patternurl = "c";
                break;
            case CURLY_BORDER:
                patternurl = "i";
                break;
            case CREEPER:
                patternurl = "k";
                break;
            case SKULL:
                patternurl = "A";
                break;
            case MOJANG:
                patternurl = "u";
                break;
        }
        return patternurl;
    }
}
