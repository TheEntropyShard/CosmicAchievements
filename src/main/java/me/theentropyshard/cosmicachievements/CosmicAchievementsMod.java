package me.theentropyshard.cosmicachievements;

import dev.crmodders.cosmicquilt.api.entrypoint.ModInitializer;
import me.theentropyshard.cosmicachievements.achievement.Achievements;
import org.quiltmc.loader.api.ModContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CosmicAchievementsMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("CosmicAchievements");

    @Override
    public void onInitialize(ModContainer mod) {
        Achievements.registerAllAchievements();

        LOGGER.info("CosmicAchievements initialized!");
    }
}

