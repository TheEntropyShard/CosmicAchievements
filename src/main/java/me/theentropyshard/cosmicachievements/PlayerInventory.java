package me.theentropyshard.cosmicachievements;

import finalforeach.cosmicreach.items.ItemStack;
import finalforeach.cosmicreach.items.containers.SlotContainer;
import me.theentropyshard.cosmicachievements.achievement.Achievement;

public class PlayerInventory extends SlotContainer {
    public PlayerInventory(int numSlots) {
        super(numSlots);
    }

    @Override
    public boolean addItemStack(ItemStack itemStack) {
        boolean b = super.addItemStack(itemStack);

        if (b) {
            Achievement.onItemPickup(itemStack);
        }

        return b;
    }
}
