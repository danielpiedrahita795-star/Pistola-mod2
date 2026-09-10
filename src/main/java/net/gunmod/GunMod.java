package net.gunmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.gunmod.entity.BulletEntity;
import net.gunmod.network.NetworkingConstants;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GunMod implements ModInitializer {

    public static final String MOD_ID = "gunmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // velocidad de la bala: mucho más rápido que una flecha para que
    // se sienta como un disparo casi instantáneo, en línea recta
    private static final double BULLET_SPEED = 3.5;

    @Override
    public void onInitialize() {
        ModSounds.registerModSounds();
        ModEntities.registerModEntities();
        ModItems.registerModItems();
        registerShootPacketReceiver();
        LOGGER.info("GunMod inicializado: Desert Eagle (normal y dorada) registradas.");
    }

    private void registerShootPacketReceiver() {
        ServerPlayNetworking.registerGlobalReceiver(NetworkingConstants.SHOOT_PACKET_ID,
                (server, player, handler, buf, responseSender) -> {
                    // el disparo se procesa en el hilo del servidor
                    server.execute(() -> spawnBullet(player));
                });
    }

    private void spawnBullet(ServerPlayerEntity player) {
        ItemStack held = player.getMainHandStack();
        Item item = held.getItem();
        if (item != ModItems.DESERT_EAGLE && item != ModItems.DESERT_EAGLE_GOLD) {
            return;
        }

        double damage = 20.0; // igual para la normal y la dorada

        BulletEntity bullet = new BulletEntity(player.getWorld(), player, damage);

        Vec3d look = player.getRotationVector();
        Vec3d startPos = player.getEyePos().add(look.multiply(0.3));
        bullet.setPosition(startPos.x, startPos.y, startPos.z);
        bullet.setVelocity(look.x * BULLET_SPEED, look.y * BULLET_SPEED, look.z * BULLET_SPEED);

        player.getWorld().spawnEntity(bullet);
        player.getWorld().playSound(null, player.getBlockPos(),
                net.gunmod.ModSounds.DESERT_EAGLE_SHOOT, net.minecraft.sound.SoundCategory.PLAYERS,
                1.0F, 1.0F);
    }
}
