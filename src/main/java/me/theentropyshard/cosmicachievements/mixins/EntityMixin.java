package me.theentropyshard.cosmicachievements.mixins;

import finalforeach.cosmicreach.entities.Entity;
import finalforeach.cosmicreach.world.Zone;
import me.theentropyshard.cosmicachievements.achievement.Achievement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onEntityDeath(Zone zone, CallbackInfo ci) {
        Achievement.onEntityDeath((Entity) ((Object) this));
    }
}
