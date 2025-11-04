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

import static io.github.ahoogah.thaumaturgy.api.materials.ArmorMats.FORTRESS;
import static io.github.ahoogah.thaumaturgy.api.materials.ArmorMats.SPECIAL;
import static io.github.ahoogah.thaumaturgy.api.materials.ArmorMats.THAUMIUM;
import static io.github.ahoogah.thaumaturgy.api.materials.ArmorMats.VOID;
import static io.github.ahoogah.thaumaturgy.api.materials.ArmorMats.VOIDFORTRESS;

import net.minecraft.item.ItemArmor.ArmorMaterial;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ArmorMaterial.class)
public abstract class ArmorMaterialExt {

    @Shadow
    @Final
    @Mutable
    private static ArmorMaterial[] $VALUES;

    static {
        THAUMIUM = thaum$extendEnum("THAUMIUM", 25, new int[] { 2, 6, 5, 2 }, 25);
        SPECIAL = thaum$extendEnum("SPECIAL", 25, new int[] { 1, 3, 2, 1 }, 25);
        FORTRESS = thaum$extendEnum("FORTRESS", 40, new int[] { 3, 7, 6, 3 }, 25);
        VOID = thaum$extendEnum("VOID", 10, new int[] { 3, 7, 6, 3 }, 10);
        VOIDFORTRESS = thaum$extendEnum("VOIDFORTRESS", 18, new int[] { 4, 8, 7, 4 }, 10);
    }

    @Invoker("<init>")
    private static ArmorMaterial thaum$init(String name, int ordinal, int maxDamageFactor, int[] protections,
        int enchantability) {
        throw new AssertionError("The invoker failed to apply!");
    }

    @Unique
    private static ArmorMaterial thaum$extendEnum(String name, int maxDamageFactor, int[] protections,
        int enchantability) {
        assert $VALUES != null;
        final var mat = thaum$init(name, $VALUES.length, maxDamageFactor, protections, enchantability);
        final var newMats = new ArmorMaterial[$VALUES.length + 1];
        System.arraycopy($VALUES, 0, newMats, 0, $VALUES.length);
        newMats[$VALUES.length] = mat;
        $VALUES = newMats;
        return mat;
    }
}
