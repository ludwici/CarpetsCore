package com.ludwici.carpetscore.platform;

import com.ludwici.carpetscore.platform.services.IRegistryHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class FabricRegistryHelper implements IRegistryHelper {

    protected Map<String, Block> replaceMap;

    public FabricRegistryHelper() {
        replaceMap = new HashMap<>();
    }

    @Override
    public <T> void register(String name, T block) {
        replaceMap.put(name, (Block) block);
    }

    @Override
    public Block replace(Block block) {
        String name = BuiltInRegistries.BLOCK.getKey(block).getPath();
        return replaceMap.get(name);
    }
}
