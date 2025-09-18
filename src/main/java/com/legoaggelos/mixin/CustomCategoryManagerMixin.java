package com.legoaggelos.mixin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.redlimerl.speedrunigt.SpeedRunIGT;
import com.redlimerl.speedrunigt.timer.InGameTimerUtils;
import com.redlimerl.speedrunigt.timer.category.CustomCategoryManager;
import com.redlimerl.speedrunigt.timer.category.InvalidCategoryException;
import com.redlimerl.speedrunigt.timer.category.RunCategory;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.SemanticVersion;
import net.fabricmc.loader.api.metadata.version.VersionPredicate;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.io.FileUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import static com.legoaggelos.FASpeedrun.MOD_ID;

@Mixin(CustomCategoryManager.class)
public class CustomCategoryManagerMixin {
    /**
     * @author legoaggelos + redlime
     * @reason use resource location instead of custom directory, to allow for modpack packaging
     */
    @Overwrite(remap = false)
    private static Path getCategoryPath() {
        return FabricLoader.getInstance().getGameDir().resolve("categories");
    }


}
