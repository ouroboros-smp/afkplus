/*
 * This file is part of the AfkPlus project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2025  Sakura Ryoko and contributors
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

package com.sakuraryoko.afkplus.impl.commands;

import javax.annotation.Nonnull;

//#if MC >= 26.3
//$$ import net.minecraft.resources.Identifier;
//#elseif MC >= 1.16.5
//$$ import me.lucko.fabric.api.permissions.v0.Permissions;
//#else
//#endif

//#if MC >= 1.21.11
//$$ import net.minecraft.server.permissions.PermissionLevel;
//$$ import net.minecraft.util.Mth;
//#endif
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import com.sakuraryoko.afkplus.impl.Reference;

/**
 * (Lucko) Fabric Permissions API support only begins with MC 1.16.4+
 */
public class PermsWrap
{
	public static final String REGEX_ALLOWED = "[^a-z0-9:_./\\-]+";        // Identifier Safe

	public static boolean check(@Nonnull CommandSourceStack src, @Nonnull String node, int pl)
	{
	//#if MC >= 1.21.11
		//$$return check(src, node, PermissionLevel.byId(Mth.clamp(pl, 0, PermissionLevel.OWNERS.id())));
	//$$}

	//$$public static boolean check(@Nonnull CommandSourceStack src, @Nonnull String node, @Nonnull PermissionLevel pl)
	//$${
	//#endif
	//#if MC >= 26.3
		//$$ final String prefixed = Reference.MOD_ID + ":" + node;
		//$$ Identifier id = Identifier.tryParse(sanitizeNode(prefixed));

		//$$ if (id != null)
		//$${
			//$$ ServerPlayer p = playerOrNull(src);

			//$$if (p != null)
			//$${
				//$$return p.checkPermission(id, pl);
			//$$}

			//$$return src.checkPermission(id, pl);
		//$$}

		//$$ return false;
	//#elseif MC >= 1.16.5
		//$$ final String prefixed = Reference.MOD_ID + "." + node;
		//$$ return Permissions.check(src, prefixed, pl);
	//#else
		return src.hasPermission(pl);
	//#endif
	}

	public static boolean check(@Nonnull Entity entity, @Nonnull String node, int pl)
	{
	//#if MC >= 1.21.11
		//$$return check(entity, node, PermissionLevel.byId(Mth.clamp(pl, 0, PermissionLevel.OWNERS.id())));
	//$$}

	//$$public static boolean check(@Nonnull Entity entity, @Nonnull String node, @Nonnull PermissionLevel pl)
	//$${
	//#endif
	//#if MC >= 26.3
		//$$ final String prefixed = Reference.MOD_ID + ":" + node;
		//$$ Identifier id = Identifier.tryParse(sanitizeNode(prefixed));

		//$$ if (id != null)
		//$${
			//$$return entity.checkPermission(id, pl);
		//$$}

		//$$ return false;
	//#elseif MC >= 1.16.5
		//$$ final String prefixed = Reference.MOD_ID + "." + node;
		//$$ return Permissions.check(entity, prefixed, pl);
	//#else
		return entity.hasPermissions(pl);
	//#endif
	}

	public static String sanitizeNode(@Nonnull final String node)
	{
		return node.toLowerCase().replaceAll(REGEX_ALLOWED, "");
	}

	public static ServerPlayer playerOrNull(@Nonnull CommandSourceStack src)
	{
		try
		{
			return src.getPlayerOrException();
		}
		catch (Exception ignored) {}
		return null;
	}
}
