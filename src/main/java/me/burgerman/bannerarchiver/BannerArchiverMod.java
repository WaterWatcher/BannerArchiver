package me.burgerman.bannerarchiver;

import me.burgerman.bannerarchiver.commands.BannerArchiverCommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * Mod that makes recipe links for Minecraft banners.
 *
 * Author: Hamburger 19/05/2021
 */

@Mod(modid = BannerArchiverMod.MODID, name = BannerArchiverMod.MODNAME, version = BannerArchiverMod.MODVER)
public class BannerArchiverMod {

    public static final String MODID = "bannerarchiver";
    public static final String MODNAME = "bannerArchiver";
    public static final String MODVER = "b1";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new BannerArchiverCommand());

    }
}

