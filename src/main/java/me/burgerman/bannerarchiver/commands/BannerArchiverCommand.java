package me.burgerman.bannerarchiver.commands;

import com.google.common.collect.Lists;
import me.burgerman.bannerarchiver.commands.utils.PatternUtil;
import me.burgerman.bannerarchiver.commands.utils.ColourUtil;
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
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;

import java.util.List;

/**
 * Command to create the needcoolshoes url for banner pattern.
 *
 * Author: Hamburger 19/05/2021
 */

public class BannerArchiverCommand extends CommandBase {

    private final List<String> aliases = Lists.newArrayList("bannerarchiver", "BannerArchiver");
    private Minecraft mc = Minecraft.getMinecraft();

    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "bannerarchiver";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        if (mc.player == null) return;                                                                                                                                          //hypebeast check

        if (mc.player.getHeldItemMainhand().getItem() instanceof ItemBanner) {
            String baseurl = "https://www.needcoolshoes.com/banner?=";
            ItemStack banner = mc.player.getHeldItemMainhand();
            NBTTagCompound nbttagcompound = banner.getSubCompound("BlockEntityTag");
            baseurl = baseurl + ColourUtil.basecolourGetter(EnumDyeColor.byDyeDamage(banner.getMetadata() & 15).getDyeColorName()) + "a";                                       //base banner layer gets an extra a at the end

            if (nbttagcompound != null && nbttagcompound.hasKey("Patterns")) {
                NBTTagList nbttaglist = nbttagcompound.getTagList("Patterns", 10);

                // retrieves the bannerlayers to add to the url, similar to the original class for banners
                for (int i = 0; i < nbttaglist.tagCount() && i < 6; ++i) {
                    NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
                    baseurl += ColourUtil.basecolourGetter(EnumDyeColor.byDyeDamage(nbttagcompound1.getInteger("Color")).toString());
                    baseurl += PatternUtil.basepatternGetter(BannerPattern.byHash(nbttagcompound1.getString("Pattern")).getFileName());

                }
            }
            sender.sendMessage(new TextComponentString(TextFormatting.GOLD + "Display name is: " + banner.getDisplayName()));
            TextComponentString msg = new TextComponentString(TextFormatting.GOLD + "Your link is: " + TextFormatting.BLUE + TextFormatting.UNDERLINE +  baseurl);
            sender.sendMessage(msg.setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, baseurl))));                                                   //Clickevent to actually make the link clickable
        }
    }
}