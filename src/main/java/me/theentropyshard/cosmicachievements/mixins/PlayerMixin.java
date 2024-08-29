package me.theentropyshard.cosmicachievements.mixins;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.items.containers.SlotContainer;
import me.theentropyshard.cosmicachievements.PlayerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;

@Mixin(Player.class)
public class PlayerMixin {
    @Shadow
    public SlotContainer inventory;

    @Inject(method = "<init>()V", at = @At("TAIL"))
    private void createCustomInventory(CallbackInfo ci) {
        this.inventory = new PlayerInventory(this.inventory.getNumSlots());
    }

    @Inject(method = "read", at = @At("TAIL"))
    private void wrapCustomInventory(Json json, JsonValue jsonData, CallbackInfo ci) {
        SlotContainer oldInventory = this.inventory;
        this.inventory = new PlayerInventory(this.inventory.getNumSlots());
        Array slots = ((SlotContainerAccessor) oldInventory).getSlots();
        try {
            Field slotsField = this.inventory.getClass().getDeclaredField("slots");
            slotsField.setAccessible(true);
            slotsField.set(this.inventory, slots);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
