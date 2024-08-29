package me.theentropyshard.cosmicachievements.mixins;

import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.items.ItemStack;
import finalforeach.cosmicreach.items.containers.FurnaceSlotContainer;
import finalforeach.cosmicreach.ui.UI;
import me.theentropyshard.cosmicachievements.achievement.Achievement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FurnaceSlotContainer.class)
public abstract class FurnaceSlotContainerMixin {
    @Shadow public abstract ItemSlot getOutputProductSlot();

    @Inject(method = "onItemSlotUpdate", at = @At("TAIL"))
    private void checkCrafting(ItemSlot itemSlot, CallbackInfo ci) {
        ItemSlot outSlot = this.getOutputProductSlot();

        if (itemSlot == outSlot) {
            ItemStack itemStack = UI.itemCursor.getSlot(0).itemStack;

            if (itemStack != null) {
                Achievement.onItemSmelted(itemStack);
            }
        }
    }
}
