package me.theentropyshard.cosmicachievements.mixins;

import com.badlogic.gdx.graphics.Camera;
import com.llamalad7.mixinextras.sugar.Local;
import finalforeach.cosmicreach.BlockSelection;
import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.entities.Entity;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.world.Zone;
import me.theentropyshard.cosmicachievements.achievement.Achievement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockSelection.class)
public class BlockSelectionMixin {
    @Inject(method = "interactWith", at = @At("HEAD"))
    private void onInteractBlockEntity(Player player, Zone zone, BlockPosition blockPos, boolean interactJustPressed, boolean interactHeld, double timeSinceLastInteract, CallbackInfo ci) {
        BlockEntity blockEntity = blockPos.getBlockEntity();

        if (blockEntity != null) {
            Achievement.onBlockEntityInteract(blockEntity);
        }
    }

    @Inject(method = "raycastForEntities", at = @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/entities/Entity;onUseInteraction(Lfinalforeach/cosmicreach/world/Zone;Lfinalforeach/cosmicreach/entities/player/Player;Lfinalforeach/cosmicreach/items/ItemStack;)V", shift = At.Shift.AFTER))
    private void onInteractEntity(Zone zone, Camera worldCamera, CallbackInfoReturnable<Boolean> cir, @Local(name = "e") Entity e) {
        Achievement.onEntityInteract(e);
    }
}
