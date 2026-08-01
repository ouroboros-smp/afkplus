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

package com.sakuraryoko.afkplus.impl.player;

class MovementIntentTest
{
    public static void main(String[] args)
    {
        assertFalse(MovementIntent.hasMovementInput(false, false, false, false, false),
                    "Forced displacement without client input must not count as a return");
        assertTrue(MovementIntent.hasMovementInput(true, false, false, false, false),
                   "Forward input must count as a player return");
        assertTrue(MovementIntent.hasMovementInput(false, false, false, false, true),
                   "Jump input must count as a player return");
    }

    private static void assertFalse(boolean condition, String message)
    {
        if (condition)
        {
            throw new AssertionError(message);
        }
    }

    private static void assertTrue(boolean condition, String message)
    {
        if (!condition)
        {
            throw new AssertionError(message);
        }
    }
}
