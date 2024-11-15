package com.ludwici.carpetscore;

import com.ludwici.carpetscore.platform.Services;
import net.minecraft.world.level.block.Block;


public class CarpetsCore {

    public static <T> void register(String name, T block) {
        Services.REGISTRY.register(name, block);
    }

    public static Block replace(Block block) {
        return Services.REGISTRY.replace(block);
    }
}