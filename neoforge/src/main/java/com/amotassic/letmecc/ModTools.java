package com.amotassic.letmecc;

import net.minecraft.util.Tuple;
import net.minecraft.world.entity.LivingEntity;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.ArrayList;
import java.util.List;

import static top.theillusivec4.curios.api.CuriosApi.getCuriosInventory;

public class ModTools {

    public static boolean isCuriosLoaded() {return Common.PLATFORM.isModLoaded("curios");}

    public static List<Tuple<ICurioStacksHandler, Integer>> trinketsWithSlots(LivingEntity entity) {
        List<Tuple<ICurioStacksHandler, Integer>> pairs = new ArrayList<>();
        getCuriosInventory(entity).ifPresent(c -> c.getCurios().values().forEach(group -> {
            for (int i = 0; i < group.getSlots(); i++) {
                pairs.add(new Tuple<>(group, i));
            }
        }));
        return pairs;
    }
}
