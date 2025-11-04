/// This file is part of Thaumaturgy - a mod that works wonders.
/// Copyright (C) 2025 ah-OOG-ah
///
/// Thaumaturgy is free software: you can redistribute it and/or modify
/// it under the terms of the GNU Lesser General Public License as published by
/// the Free Software Foundation, either version 3 of the License, or
/// (at your option) any later version.
///
/// Thaumaturgy is distributed in the hope that it will be useful,
/// but WITHOUT ANY WARRANTY; without even the implied warranty of
/// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
/// GNU Lesser General Public License for more details.
///
/// You should have received a copy of the GNU Lesser General Public License
/// along with this program. If not, see <https://www.gnu.org/licenses/>.

package io.github.ahoogah.thaumaturgy.mixins.early;

import static io.github.ahoogah.thaumaturgy.api.materials.ToolMats.THAUMIUM;
import static io.github.ahoogah.thaumaturgy.api.materials.ToolMats.THAUMIUM_ELEMENTAL;
import static io.github.ahoogah.thaumaturgy.api.materials.ToolMats.VOID;

import net.minecraft.item.Item.ToolMaterial;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ToolMaterial.class)
public abstract class ToolMaterialExt {

    @Shadow
    @Final
    @Mutable
    private static ToolMaterial[] $VALUES;

    static {
        THAUMIUM = thaum$extendEnum("THAUMIUM", 3, 400, 7F, 2, 22);
        VOID = thaum$extendEnum("VOID", 4, 150, 8F, 3, 10);
        THAUMIUM_ELEMENTAL = thaum$extendEnum("THAUMIUM_ELEMENTAL", 3, 1500, 10F, 3, 18);
    }

    @Invoker("<init>")
    private static ToolMaterial thaum$init(String name, int ordinal, int harvestLevel, int maxUses, float efficiency,
        float damage, int enchantability) {
        throw new AssertionError("The invoker failed to apply!");
    }

    @Unique
    private static ToolMaterial thaum$extendEnum(String name, int harvestLevel, int maxUses, float efficiency,
        float damage, int enchantability) {
        assert $VALUES != null;
        final var mat = thaum$init(name, $VALUES.length, harvestLevel, maxUses, efficiency, damage, enchantability);
        final var newMats = new ToolMaterial[$VALUES.length + 1];
        System.arraycopy($VALUES, 0, newMats, 0, $VALUES.length);
        newMats[$VALUES.length] = mat;
        $VALUES = newMats;
        return mat;
    }
}
