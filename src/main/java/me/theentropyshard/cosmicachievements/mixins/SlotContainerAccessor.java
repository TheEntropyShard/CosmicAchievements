package me.theentropyshard.cosmicachievements.mixins;

import com.badlogic.gdx.utils.Array;
import finalforeach.cosmicreach.items.containers.SlotContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SlotContainer.class)
public interface SlotContainerAccessor {
    @Accessor("slots")
    Array getSlots();
}
