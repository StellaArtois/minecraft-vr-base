/**
 * Copyright 2013 Mark Browning, StellaArtois
 * Licensed under the LGPL 3.0 or later (See LICENSE.md for details)
 */
package com.mtbs3d.minecrift.control;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;

public class WalkRightBinding extends ControlBinding {
	public WalkRightBinding() {
		super("key.right","key.right");
	}

	@Override
	public void setValue(float value) {
        EntityClientPlayerMP thePlayer = Minecraft.getMinecraft().thePlayer;
        if( thePlayer != null )
        	thePlayer.movementInput.baseMoveStrafe = -value;
	}

	@Override
	public void setState(boolean state) {
		setValue( state ? Minecraft.getMinecraft().vrSettings.movementSpeedMultiplier: 0.0f );
	}
}
