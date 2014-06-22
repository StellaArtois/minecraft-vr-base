/**
 * Copyright 2014 StellaArtois
 * Licensed under the LGPL 3.0 or later (See LICENSE.md for details)
 */
package com.mtbs3d.minecrift.api;


import de.fruitfly.ovr.enums.EyeType;
import de.fruitfly.ovr.structs.FovPort;
import de.fruitfly.ovr.structs.FrameTiming;
import de.fruitfly.ovr.structs.Matrix4f;
import de.fruitfly.ovr.structs.Posef;

/**
 * Implement this to provide Minecrift with stereo rendering services
 *
 * @author StellaArtois
 *
 */
public interface IStereoProvider extends IBasePlugin
{
    //public EyeType eyeRenderOrder(int index);

    public FrameTiming getFrameTiming();

    public Posef beginEyeRender(EyeType eye);

    public Matrix4f getMatrix4fProjection(FovPort fov,
                                          float nearClip,
                                          float farClip);

    public void endEyeRender(EyeType eye);
}