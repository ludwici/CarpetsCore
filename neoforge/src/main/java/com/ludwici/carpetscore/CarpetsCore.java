package com.ludwici.carpetscore;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.HashMap;
import java.util.Map;

@Mod(CarpetsCore.MODID)
public class CarpetsCore
{
    public static final String MODID = "carpetscore";
    protected static Map<String, DeferredBlock<Block>> replaceMap;

    public CarpetsCore(IEventBus modEventBus, ModContainer modContainer) {
        replaceMap = new HashMap<>();
    }

    public static <T> void register(String name, T block) {
        replaceMap.put(name, (DeferredBlock<Block>) block);
    }

    public static Block replace(Block block) {
        String blockName = name(block);
        if (replaceMap.containsKey(blockName)) {
            return replaceMap.get(blockName).get();
        }
        return null;
    }

    public static String name(Block block) {
        return key(block).getPath();
    }
    public static String name(Item item) {
        return key(item).getPath();
    }

    public static Identifier key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }
    public static Identifier key(Item item) {
        return BuiltInRegistries.ITEM.getKey(item);
    }
}
