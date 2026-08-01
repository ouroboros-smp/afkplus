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

class PositionLockTest
{
    public static void main(String[] args)
    {
        restoresDisplacedLockedPosition();
        rebasesAfterDimensionChange();
        retainsPreviousGravityState();
        unlockedPositionDoesNotRestore();
    }

    private static void restoresDisplacedLockedPosition()
    {
        PositionLock<String> lock = new PositionLock<>();
        lock.lock(10.0D, 64.0D, -5.0D, "overworld", false);

        assertFalse(lock.shouldRestore(10.0D, 64.0D, -5.0D), "An unchanged position must remain stable");
        assertTrue(lock.shouldRestore(10.0D, 63.9D, -5.0D), "A displaced locked position must be restored");
    }

    private static void rebasesAfterDimensionChange()
    {
        PositionLock<String> lock = new PositionLock<>();
        lock.lock(10.0D, 64.0D, -5.0D, "overworld", false);

        assertTrue(lock.hasChangedDimension("the_nether"), "A dimension change must be detected");
        lock.rebase(1.0D, 80.0D, 1.0D, "the_nether");
        assertFalse(lock.hasChangedDimension("the_nether"), "A rebased lock must use the new dimension");
        assertFalse(lock.shouldRestore(1.0D, 80.0D, 1.0D), "A rebased position must remain stable");
    }

    private static void retainsPreviousGravityState()
    {
        PositionLock<String> lock = new PositionLock<>();
        lock.lock(10.0D, 64.0D, -5.0D, "overworld", true);

        assertTrue(lock.getPreviousNoGravity(), "The prior gravity state must be retained for unlock");
    }

    private static void unlockedPositionDoesNotRestore()
    {
        PositionLock<String> lock = new PositionLock<>();
        lock.lock(10.0D, 64.0D, -5.0D, "overworld", false);
        lock.unlock();

        assertFalse(lock.shouldRestore(10.0D, 63.9D, -5.0D), "An unlocked position must not be restored");
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
