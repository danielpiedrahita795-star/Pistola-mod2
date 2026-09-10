package net.gunmod;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.gunmod.item.GunItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    // 20 de daño, tal como pidió el usuario
    public static final Item DESERT_EAGLE = new GunItem(new FabricItemSettings().maxCount(1), 20.0);
    public static final Item DESERT_EAGLE_GOLD = new GunItem(new FabricItemSettings().maxCount(1), 20.0);

    public static void registerModItems() {
        Registry.register(Registries.ITEM, new Identifier(GunMod.MOD_ID, "desert_eagle"), DESERT_EAGLE);
        Registry.register(Registries.ITEM, new Identifier(GunMod.MOD_ID, "desert_eagle_gold"), DESERT_EAGLE_GOLD);

        // Para que aparezcan en el inventario creativo / se puedan buscar
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(DESERT_EAGLE);
            entries.add(DESERT_EAGLE_GOLD);
        });
    }
}
