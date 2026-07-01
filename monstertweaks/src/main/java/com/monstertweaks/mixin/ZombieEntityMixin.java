package com.monstertweaks.mixin;

import net.minecraft.entity.mob.ZombieEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Vanilla only lets zombies break doors on Hard difficulty (via the
 * shouldBreakDoors() check that feeds the BreakDoorGoal). We override that
 * check to always return true, so zombies path to a closed door, start
 * hitting it, and eventually break through — on any difficulty.
 */
@Mixin(ZombieEntity.class)
public abstract class ZombieEntityMixin {

    @Inject(method = "shouldBreakDoors", at = @At("HEAD"), cancellable = true)
    private void monstertweaks$alwaysAllowDoorBreaking(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
