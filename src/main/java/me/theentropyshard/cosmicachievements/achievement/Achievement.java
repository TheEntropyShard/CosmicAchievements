package me.theentropyshard.cosmicachievements.achievement;

import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.chat.Chat;
import finalforeach.cosmicreach.entities.Entity;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.items.ItemStack;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Achievement {
    private static final EnumMap<AchievementTrigger, List<Achievement>> achievements = new EnumMap<>(AchievementTrigger.class);

    private final String name;

    private boolean activated;

    public Achievement(String name) {
        this.name = name;
    }

    public static void registerAchievement(AchievementTrigger trigger, Achievement achievement) {
        List<Achievement> achievementList = achievements.computeIfAbsent(trigger, list -> new ArrayList<>());
        achievementList.add(achievement);
    }

    public static void onItemPickup(ItemStack itemStack) {
        List<Achievement> achievementList = achievements.get(AchievementTrigger.ITEM_PICKUP);
        for (Achievement achievement : achievementList) {
            if (achievement.triggersOnItem(itemStack)) {
                achievement.announce();
            }
        }
    }

    public static void onItemCrafted(ItemStack itemStack) {
        List<Achievement> achievementList = achievements.get(AchievementTrigger.ITEM_CRAFTED);
        for (Achievement achievement : achievementList) {
            if (achievement.triggersOnItem(itemStack)) {
                achievement.announce();
            }
        }
    }

    public static void onItemSmelted(ItemStack itemStack) {
        List<Achievement> achievementList = achievements.get(AchievementTrigger.ITEM_SMELTED);
        for (Achievement achievement : achievementList) {
            if (achievement.triggersOnItem(itemStack)) {
                achievement.announce();
            }
        }
    }

    public static void onBlockEntityInteract(BlockEntity blockEntity) {
        List<Achievement> achievementList = achievements.get(AchievementTrigger.BLOCKENTITY_INTERACT);
        for (Achievement achievement : achievementList) {
            if (achievement.triggersOnBlockEntityInteract(blockEntity)) {
                achievement.announce();
            }
        }
    }

    public static void onEntityInteract(Entity e) {
        List<Achievement> achievementList = achievements.get(AchievementTrigger.ENTITY_INTERACT);
        for (Achievement achievement : achievementList) {
            if (achievement.triggersOnEntityInteract(e)) {
                achievement.announce();
            }
        }
    }

    public static void onEntityDeath(Entity entity) {
        List<Achievement> achievementList = achievements.get(AchievementTrigger.ENTITY_DEATH);
        for (Achievement achievement : achievementList) {
            if (achievement.triggersOnEntityDeath(entity)) {
                achievement.announce();
            }
        }
    }

    public boolean triggersOnItem(ItemStack itemStack) {
        return false;
    }

    public boolean triggersOnBlockEntityInteract(BlockEntity blockEntity) {
        return false;
    }

    public boolean triggersOnEntityInteract(Entity entity) {
        return false;
    }

    public boolean triggersOnEntityDeath(Entity entity) {
        return false;
    }

    public void announce() {
        if (this.activated) {
            return;
        }

        Player player = InGame.getLocalPlayer();

        Chat.MAIN_CHAT.sendMessage(
            InGame.world,
            player,
            null,
            player.username + " has just earned the achievement [" + this.name + "]"
        );

        this.activated = true;
    }

    public boolean isActivated() {
        return this.activated;
    }
}
