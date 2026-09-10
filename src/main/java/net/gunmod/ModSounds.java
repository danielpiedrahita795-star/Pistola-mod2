package net.gunmod;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent DESERT_EAGLE_SHOOT = registerSound("desert_eagle_shoot");

    private static SoundEvent registerSound(String name) {
        Identifier id = new Identifier(GunMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerModSounds() {
        // El registro real ocurre en la inicialización de los campos estáticos de arriba;
        // este método solo se llama para forzar que la clase se cargue.
    }
}
