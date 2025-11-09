package com.amotassic.letmecc.platform;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface IPlatformHelper {

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    void openFullInv(Player player, LivingEntity target, boolean editable);

    /**查找最近的一个符合条件的实体，不包括第一个参数实体本身*/
    default @Nullable <T extends Entity> T getClosestEntity(Entity entity, Class<T> clazz, double boxLength, Predicate<T> predicate) {
        if (entity.level() instanceof ServerLevel world) {
            AABB box = new AABB(entity.getOnPos()).inflate(boxLength);
            List<T> entities = world.getEntitiesOfClass(clazz, box, predicate.and(e -> e != entity));
            if (!entities.isEmpty()) {
                Map<Float, T> map = new HashMap<>();
                for (var e : entities) {
                    map.put(e.distanceTo(entity), e);
                }
                float min = Collections.min(map.keySet());
                return map.get(min);
            }
        }
        return null;
    }
}
