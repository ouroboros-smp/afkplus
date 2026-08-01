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

final class PositionLock<D>
{
    private boolean locked;
    private double x;
    private double y;
    private double z;
    private D dimension;
    private boolean previousNoGravity;

    boolean isLocked()
    {
        return this.locked;
    }

    double getX()
    {
        return this.x;
    }

    double getY()
    {
        return this.y;
    }

    double getZ()
    {
        return this.z;
    }

    boolean getPreviousNoGravity()
    {
        return this.previousNoGravity;
    }

    void lock(double x, double y, double z, D dimension, boolean previousNoGravity)
    {
        this.rebase(x, y, z, dimension);
        this.previousNoGravity = previousNoGravity;
        this.locked = true;
    }

    void rebase(double x, double y, double z, D dimension)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.dimension = dimension;
    }

    void unlock()
    {
        this.locked = false;
        this.dimension = null;
    }

    boolean hasChangedDimension(D dimension)
    {
        return this.locked && !this.dimension.equals(dimension);
    }

    boolean shouldRestore(double x, double y, double z)
    {
        return this.locked && (this.x != x || this.y != y || this.z != z);
    }
}
