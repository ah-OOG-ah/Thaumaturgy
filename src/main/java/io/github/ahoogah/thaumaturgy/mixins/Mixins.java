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

package io.github.ahoogah.thaumaturgy.mixins;

import static com.gtnewhorizon.gtnhmixins.builders.IBaseTransformer.Phase.EARLY;
import static io.github.ahoogah.thaumaturgy.mixins.Mixins.Util.ALWAYS;
import static io.github.ahoogah.thaumaturgy.mixins.Mixins.Util.NEVER;

import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.gtnewhorizon.gtnhmixins.builders.MixinBuilder;
import java.util.function.Supplier;
import javax.annotation.Nonnull;

public enum Mixins implements IMixins {
    ADD_MATERIALS(new MixinBuilder().addCommonMixins("ToolMaterialExt", "ArmorMaterialExt").setPhase(EARLY).setApplyIf(ALWAYS));

    private final MixinBuilder builder;

    Mixins(MixinBuilder builder) {
        this.builder = builder;
    }

    @Nonnull
    @Override
    public MixinBuilder getBuilder() {
        return builder;
    }

    static final class Util {
        static final Supplier<Boolean> ALWAYS = () -> true;
        static final Supplier<Boolean> NEVER = () -> false;
    }
}
