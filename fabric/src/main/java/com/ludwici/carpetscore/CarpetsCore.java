package com.ludwici.carpetscore;

import net.fabricmc.api.ModInitializer;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class CarpetsCore implements ModInitializer {
	public static final String MOD_ID = "carpetscore";
	protected static Map<String, Block> replaceMap = new HashMap<>();;

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

	}

	public static <T> void register(String name, T block) {
		replaceMap.put(name, (Block) block);
	}

	public static Block replace(Block block) {
		String blockName = name(block);
		if (replaceMap.containsKey(blockName)) {
			return replaceMap.get(blockName);
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