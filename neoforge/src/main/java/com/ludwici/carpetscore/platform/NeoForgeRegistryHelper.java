package com.ludwici.carpetscore.platform;

import com.ludwici.carpetscore.platform.services.IRegistryHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.HashMap;
import java.util.Map;

public class NeoForgeRegistryHelper implements IRegistryHelper {

    protected Map<String, DeferredBlock<Block>> replaceMap;

    public NeoForgeRegistryHelper() {
        replaceMap = new HashMap<>();
    }

    @Override
    public <T> void register(String name, T block) {
        replaceMap.put(name, (DeferredBlock<Block>) block);
    }

    @Override
    public Block replace(Block block) {
        String name = BuiltInRegistries.BLOCK.getKey(block).getPath();
        return replaceMap.get(name).get();
    }
}
