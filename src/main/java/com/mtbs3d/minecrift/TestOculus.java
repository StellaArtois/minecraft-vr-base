/**
 * Copyright 2013 Mark Browning, StellaArtois
 * Licensed under the LGPL 3.0 or later (See LICENSE.md for details)
 */
package com.mtbs3d.minecrift;

import de.fruitfly.ovr.OculusRift;

public class TestOculus extends OculusRift {
	public boolean isInitialized() { return true; };

	public float getHeadYawDegrees_LH() { return 180.0f; };
	public float getPitchDegrees() { return 0.0f; };
	public float getHeadRollDegrees_LH() { return 0.0f; };
}
