package com.amotassic.letmecc;

import dev.emi.trinkets.api.TrinketInventory;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

import static dev.emi.trinkets.api.TrinketsApi.getTrinketComponent;

public class ModTools {

    public static boolean isTrinketsLoaded() {return Common.PLATFORM.isModLoaded("trinkets");}

    public static List<Tuple<TrinketInventory, Integer>> trinketsWithSlots(LivingEntity entity) {
        List<Tuple<TrinketInventory, Integer>> pairs = new ArrayList<>();
        getTrinketComponent(entity).ifPresent(c -> c.getInventory().values().forEach(group -> group.values().forEach(inv -> {
            for (int i = 0; i < inv.getContainerSize(); i++) {
                pairs.add(new Tuple<>(inv, i));
            }
        })));
        return pairs;
    }
}
