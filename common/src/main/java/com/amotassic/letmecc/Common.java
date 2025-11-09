package com.amotassic.letmecc;

import com.amotassic.letmecc.platform.IPlatformHelper;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ServiceLoader;

@SuppressWarnings("SameParameterValue")
public class Common {

    public static final String MOD_ID = "letmecc";
    public static final Logger LOGGER = LoggerFactory.getLogger("Let Me CC");
    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

    private static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz).findFirst().orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }

    public static void init() {
        LOGGER.info("Ciallo～(∠·ω< )⌒★");
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
