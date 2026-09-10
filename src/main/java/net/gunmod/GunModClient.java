package net.gunmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.gunmod.client.BulletEntityRenderer;

public class GunModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Registra cómo se ve la bala en el mundo
        EntityRendererRegistry.register(ModEntities.BULLET, BulletEntityRenderer::new);
        // El mixin de MinecraftClient se encarga de mandar el disparo al servidor
    }
}
