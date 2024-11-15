package com.ludwici.carpetscore.platform.services;

import net.minecraft.world.level.block.Block;

public interface IRegistryHelper {
    <T> void register(String name, T block);
    Block replace(Block block);
}
