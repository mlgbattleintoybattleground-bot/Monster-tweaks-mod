package com.monstertweaks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityEvents;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.ZombieEntity;

public class MonsterTweaksMod implements ModInitializer {

    // Vanilla zombie base speed is 0.23. We bump it up a bit so zombies feel
    // noticeably quicker without approaching player sprint speed (~0.13 extra).
    private static final double ZOMBIE_SPEED = 0.26D;

    @Override
    public void onInitialize() {
        // ServerEntityEvents.ENTITY_LOAD can fire more than once for the same
        // entity (chunk loads, dimension changes, etc.), so we set an ABSOLUTE
        // value here rather than multiplying — that keeps it safe to run repeatedly.
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof ZombieEntity zombie) {
                EntityAttributeInstance speed =
                        zombie.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
                if (speed != null) {
                    speed.setBaseValue(ZOMBIE_SPEED);
                }
            }
        });
    }
}
