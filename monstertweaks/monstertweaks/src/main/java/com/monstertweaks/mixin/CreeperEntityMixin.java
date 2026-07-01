package com.monstertweaks.mixin;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.CreeperEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Two changes, both applied every tick so they hold no matter what else
 * resets them (difficulty changes, NBT loading, etc.):
 *
 * 1. Fuse time: vanilla is 30 ticks (1.5s) once ignited. We force it to
 *    6 ticks = 0.3 seconds.
 * 2. Speed: creepers roam at normal vanilla speed (0.25, close to player
 *    walk pace) until they have a target (i.e. they've spotted a player),
 *    at which point they speed up to slightly above player sprint speed.
 */
@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin {

    @Shadow
    private int fuseTime;

    private static final int SHORT_FUSE_TICKS = 6;      // 0.3s
    private static final double NORMAL_SPEED = 0.25D;   // vanilla default, ~player walk
    private static final double CHASE_SPEED = 0.34D;    // slightly above player sprint

    @Inject(method = "tick", at = @At("HEAD"))
    private void monstertweaks$onTick(CallbackInfo ci) {
        CreeperEntity self = (CreeperEntity) (Object) this;

        this.fuseTime = SHORT_FUSE_TICKS;

        EntityAttributeInstance speed =
                self.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if (speed != null) {
            speed.setBaseValue(self.getTarget() != null ? CHASE_SPEED : NORMAL_SPEED);
        }
    }
}
