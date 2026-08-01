/*
 * This file is part of the AfkPlus project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2026  Sakura Ryoko and contributors
 *
 * AfkPlus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AfkPlus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with AfkPlus.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.sakuraryoko.afkplus.impl.mixin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class MixinServerPlayerMovementTest
{
    private static final Path MIXIN_SOURCE = Path.of(
            "src/main/java/com/sakuraryoko/afkplus/impl/mixin/MixinServerPlayer.java");

    public static void main(String[] args) throws IOException
    {
        String mixinSource = Files.readString(MIXIN_SOURCE);

        if (mixinSource.contains("void setPos(double x, double y, double z)"))
        {
            throw new AssertionError("Server-side setPos calls include forced movement and must not reset AFK state");
        }
    }
}
