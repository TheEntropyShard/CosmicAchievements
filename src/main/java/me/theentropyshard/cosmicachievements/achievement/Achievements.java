package me.theentropyshard.cosmicachievements.achievement;

import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.entities.DroneEntity;
import finalforeach.cosmicreach.entities.DroneTrapEntity;
import finalforeach.cosmicreach.entities.Entity;
import finalforeach.cosmicreach.items.ItemStack;

public class Achievements {
    public static void registerAllAchievements() {
        Achievement.registerAchievement(
            AchievementTrigger.ITEM_PICKUP,
            new Achievement("Getting wood") {
                @Override
                public boolean triggersOnItem(ItemStack itemStack) {
                    String id = itemStack.getItem().getID();

                    return id.startsWith("base:tree_log");
                }
            }
        );

        Achievement.registerAchievement(
            AchievementTrigger.ITEM_CRAFTED,
            new Achievement("Sawmill") {
                @Override
                public boolean triggersOnItem(ItemStack itemStack) {
                    String id = itemStack.getItem().getID();

                    return id.startsWith("base:wood_planks");
                }
            }
        );

        Achievement.registerAchievement(
            AchievementTrigger.ITEM_SMELTED,
            new Achievement("Shiny") {
                @Override
                public boolean triggersOnItem(ItemStack itemStack) {
                    String id = itemStack.getItem().getID();

                    return id.startsWith("base:ingot_gold");
                }
            }
        );

        Achievement.registerAchievement(
            AchievementTrigger.BLOCKENTITY_INTERACT,
            new Achievement("Interactor") {
                private boolean containerOpened;
                private boolean furnaceOpened;

                @Override
                public boolean triggersOnBlockEntityInteract(BlockEntity blockEntity) {
                    String id = blockEntity.getBlockEntityId();

                    if (id.equals("base:container")) {
                        this.containerOpened = true;
                    }

                    if (id.equals("base:furnace")) {
                        this.furnaceOpened = true;
                    }

                    return this.containerOpened && this.furnaceOpened;
                }
            }
        );

        Achievement.registerAchievement(
            AchievementTrigger.ENTITY_INTERACT,
            new Achievement("Make a friend") {
                @Override
                public boolean triggersOnEntityInteract(Entity entity) {
                    String id = entity.entityTypeId;

                    if (id.equals("base:entity_drone_interceptor")) {
                        DroneEntity drone = (DroneEntity) entity;

                        return drone.isFriendly();
                    }

                    return false;
                }
            }
        );

        Achievement.registerAchievement(
            AchievementTrigger.ENTITY_DEATH,
            new Achievement("Disassembler") {
                @Override
                public boolean triggersOnEntityDeath(Entity entity) {
                    String id = entity.entityTypeId;

                    if (id.equals("base:entity_drone_interceptor")) {
                        DroneEntity drone = (DroneEntity) entity;

                        return !drone.isFriendly();
                    }

                    return false;
                }
            }
        );

        Achievement.registerAchievement(
            AchievementTrigger.ENTITY_DEATH,
            new Achievement("Quick Hand") {
                @Override
                public boolean triggersOnEntityDeath(Entity entity) {
                    String id = entity.entityTypeId;

                    if (id.equals("base:entity_drone_trap_interceptor")) {
                        DroneTrapEntity droneTrap = (DroneTrapEntity) entity;

                        return !droneTrap.friendly;
                    }

                    return false;
                }
            }
        );
    }
}
