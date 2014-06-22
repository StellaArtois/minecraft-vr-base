/**
 * Copyright 2013 Mark Browning, StellaArtois
 * Licensed under the LGPL 3.0 or later (See LICENSE.md for details)
 */
package com.mtbs3d.minecrift.gui;

import java.util.List;

import com.mtbs3d.minecrift.MCHydra;
import com.mtbs3d.minecrift.MCOculus;
import com.mtbs3d.minecrift.api.IBasePlugin;
import com.mtbs3d.minecrift.api.PluginManager;
import com.mtbs3d.minecrift.settings.VRSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StringTranslate;

public class GuiHeadPositionSettings extends BaseGuiSettings implements GuiEventEx
{
    /** An array of all of EnumOption's head position options. */

    static VRSettings.VrOptions[] neckModelOptions = new VRSettings.VrOptions[] {
            VRSettings.VrOptions.EYE_PROTRUSION,
            VRSettings.VrOptions.NECK_LENGTH,
            VRSettings.VrOptions.DUMMY,
            VRSettings.VrOptions.POS_TRACK_OFFSET_SET_DEFAULT,
    };

    static VRSettings.VrOptions[] hydraOptions = new VRSettings.VrOptions[] {
            VRSettings.VrOptions.POS_TRACK_HYDRALOC,
            VRSettings.VrOptions.POS_TRACK_HYDRA_DISTANCE_SCALE,
            VRSettings.VrOptions.POS_TRACK_HYDRA_USE_CONTROLLER_ONE,
            VRSettings.VrOptions.POS_TRACK_HYDRA_OFFSET_X,
            VRSettings.VrOptions.HYDRA_USE_FILTER,
            VRSettings.VrOptions.POS_TRACK_HYDRA_OFFSET_Y,
            VRSettings.VrOptions.POS_TRACK_HYDRA_AT_BACKOFHEAD_IS_POINTING_LEFT,
            VRSettings.VrOptions.POS_TRACK_HYDRA_OFFSET_Z,
            VRSettings.VrOptions.DUMMY,
        //VRSettings.VrOptions.EYE_PROTRUSION,
        //VRSettings.VrOptions.POS_TRACK_Y_AXIS_DISTANCE_SKEW,
            VRSettings.VrOptions.POS_TRACK_OFFSET_SET_DEFAULT,
    };

    static VRSettings.VrOptions[] oculusOptions = new VRSettings.VrOptions[] {
            VRSettings.VrOptions.POS_TRACK_OFFSET_SET_DEFAULT,
    };

    protected boolean reinit = false;
    protected boolean reinitOffsetDefaults = false;

	private PluginModeChangeButton pluginModeChangeButton;

    public GuiHeadPositionSettings(GuiScreen par1GuiScreen,
                            VRSettings par2vrSettings)
    {
    	super(par1GuiScreen, par2vrSettings );
        screenTitle = "Positional Tracking Configuration";
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        if (this.reinitOffsetDefaults)
        {
            this.reinitOffsetDefaults = false;
            setLocOffsetDefaults();
        }

        if (this.reinit)
        {
            this.guivrSettings.posTrackResetPosition = true;
            if (vrRenderer != null)
                vrRenderer.resetGuiYawOrientation();
        }

        this.buttonList.clear();
        this.buttonList.add(new GuiButtonEx(200, this.width / 2 - 100, this.height / 6 + 168, "Done"));

        pluginModeChangeButton = new PluginModeChangeButton(201, this.width / 2 - 78, this.height / 6 - 14, (List<IBasePlugin>)(List<?>) PluginManager.thePluginManager.positionPlugins, this.guivrSettings.headPositionPluginID );
        this.buttonList.add(pluginModeChangeButton);

        VRSettings.VrOptions[] var10 = null;
        if( Minecraft.getMinecraft().positionTracker instanceof MCHydra )
        {
        	GuiButtonEx resetPosButton = new GuiButtonEx(202, this.width / 2 - 100, this.height / 6 + 128, "Reset Origin");
            this.buttonList.add(resetPosButton);

            GuiButtonEx recalibrate = new GuiButtonEx(203, this.width / 2 - 100, this.height / 6 + 148, "Recalibrate...");
            this.buttonList.add(recalibrate);
            var10 = hydraOptions;
        }
        else if ( Minecraft.getMinecraft().positionTracker instanceof MCOculus )
        {
            GuiButtonEx resetPosButton = new GuiButtonEx(202, this.width / 2 - 100, this.height / 6 + 128, "Reset Origin");
            this.buttonList.add(resetPosButton);
            var10 = oculusOptions;
        }
        else
            var10 = neckModelOptions;

        int var11 = var10.length;

        for (int var12 = 2; var12 < var11 + 2; ++var12)
        {
            VRSettings.VrOptions var8 = var10[var12 - 2];
            int width = this.width / 2 - 155 + var12 % 2 * 160;
            int height = this.height / 6 + 21 * (var12 / 2) - 10;

            if (isVisible(var8))
            {
                if (var8.getEnumFloat())
                {
                    float minValue = 0.0f;
                    float maxValue = 1.0f;
                    float increment = 0.01f;

                    if (var8 == VRSettings.VrOptions.POS_TRACK_HYDRA_OFFSET_X || var8 == VRSettings.VrOptions.POS_TRACK_HYDRA_OFFSET_Y)
                    {
                        minValue = -0.30f;
                        maxValue = 0.30f;
                        increment = 0.001f;
                    }
                    else if (var8 == VRSettings.VrOptions.POS_TRACK_HYDRA_OFFSET_Z)
                    {
                        minValue = -0.30f;
                        maxValue = 0.30f;
                        increment = 0.001f;
                    }
                    else if (var8 == VRSettings.VrOptions.POS_TRACK_HYDRA_DISTANCE_SCALE)
                    {
                        minValue = 0.8f;
                        maxValue = 1.2f;
                        increment = 0.001f;
                    }
                    else if (var8 == VRSettings.VrOptions.POS_TRACK_Y_AXIS_DISTANCE_SKEW)
                    {
                        minValue = -45.0f;
                        maxValue = 45.0f;
                        increment = 0.1f;
                    }
                    if (var8 == VRSettings.VrOptions.EYE_PROTRUSION)
                    {
                        minValue = 0.00f;
                        maxValue = 0.25f;
                        increment = 0.001f;
                    }
                    if (var8 == VRSettings.VrOptions.NECK_LENGTH)
                    {
                        minValue = 0.00f;
                        maxValue = 0.25f;
                        increment = 0.001f;
                    }

                    GuiSliderEx slider = new GuiSliderEx(var8.returnEnumOrdinal(), width, height, var8, this.guivrSettings.getKeyBinding(var8), minValue, maxValue, increment, this.guivrSettings.getOptionFloatValue(var8));
                    slider.setEventHandler(this);
                    slider.enabled = getEnabledState(var8);
                    this.buttonList.add(slider);
                }
                else
                {
                    String keyText = this.guivrSettings.getKeyBinding(var8);
                    if (var8 == VRSettings.VrOptions.POS_TRACK_OFFSET_SET_DEFAULT)
                    {
                        keyText = "Set Default Offsets";
                    }

                    GuiSmallButtonEx smallButton = new GuiSmallButtonEx(var8.returnEnumOrdinal(), width, height, var8, keyText);
                    smallButton.setEventHandler(this);
                    smallButton.enabled = getEnabledState(var8);
                    this.buttonList.add(smallButton);
                }
            }
        }
    }

    private boolean isVisible(VRSettings.VrOptions var8)
    {
        if (var8 == VRSettings.VrOptions.DUMMY)
            return false;

        //These don't really apply to Oculus head position (which is just neck model)
        if( Minecraft.getMinecraft().positionTracker instanceof MCHydra )
        {
            if (this.guivrSettings.posTrackHydraLoc != VRSettings.POS_TRACK_HYDRA_LOC_BACK_OF_HEAD && var8 == VRSettings.VrOptions.POS_TRACK_HYDRA_AT_BACKOFHEAD_IS_POINTING_LEFT)
                return false;
        }

        return true;
    }

    private boolean getEnabledState(VRSettings.VrOptions var8)
    {
        String s = var8.getEnumString();

        if( ! (Minecraft.getMinecraft().positionTracker instanceof MCHydra) )
        {
            return true;
        }

        if (var8 == VRSettings.VrOptions.POS_TRACK_HYDRALOC ||
            var8 == VRSettings.VrOptions.POS_TRACK_HYDRA_DISTANCE_SCALE ||
            var8 == VRSettings.VrOptions.HYDRA_USE_FILTER ||
            var8 == VRSettings.VrOptions.POS_TRACK_HYDRA_AT_BACKOFHEAD_IS_POINTING_LEFT)
            return true;

        if (this.guivrSettings.posTrackHydraLoc == VRSettings.POS_TRACK_HYDRA_LOC_HMD_LEFT_AND_RIGHT &&
            var8 == VRSettings.VrOptions.POS_TRACK_HYDRA_USE_CONTROLLER_ONE)
        {
            return false;
        }

        return true;
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        VRSettings.VrOptions num = VRSettings.VrOptions.getEnumOptions(par1GuiButton.id);

        if (par1GuiButton.enabled)
        {
            if (par1GuiButton.id < 200 && par1GuiButton instanceof GuiSmallButtonEx)
            {
                this.guivrSettings.setOptionValue(((GuiSmallButtonEx)par1GuiButton).returnVrEnumOptions(), 1);
                par1GuiButton.displayString = this.guivrSettings.getKeyBinding(VRSettings.VrOptions.getEnumOptions(par1GuiButton.id));
            }
            else if (par1GuiButton.id == 200)
            {
                Minecraft.getMinecraft().vrSettings.saveOptions();
                this.mc.displayGuiScreen(this.parentGuiScreen);
            }
            else if (par1GuiButton.id == 201) // Mode Change
            {
                Minecraft.getMinecraft().vrSettings.headPositionPluginID = pluginModeChangeButton.getSelectedID();
                Minecraft.getMinecraft().vrSettings.saveOptions();
                Minecraft.getMinecraft().positionTracker = PluginManager.configurePosition(Minecraft.getMinecraft().vrSettings.headPositionPluginID);
            	this.reinit = true;
            }
            else if (par1GuiButton.id == 202) // Reset origin
            {
                this.guivrSettings.posTrackResetPosition = true;
                if (vrRenderer != null)
                    vrRenderer.resetGuiYawOrientation();
            }
            else if (par1GuiButton.id == 203)
            {
                if (vrRenderer != null)
                    vrRenderer.startCalibration();
            }

            if (num == VRSettings.VrOptions.HYDRA_USE_FILTER)
            {
                if (Minecraft.getMinecraft().positionTracker instanceof MCHydra )
                    Minecraft.getMinecraft().positionTracker.setPrediction(0.0f, this.guivrSettings.hydraUseFilter);
            }
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        if (reinit)
        {
            initGui();
            reinit = false;
        }
        super.drawScreen(par1,par2,par3);
    }

    @Override
    public void event(int id, VRSettings.VrOptions enumm)
    {
        if (enumm == VRSettings.VrOptions.POS_TRACK_OFFSET_SET_DEFAULT)
        {
            this.reinitOffsetDefaults = true;
            this.reinit = true;
        }

        if (enumm == VRSettings.VrOptions.POS_TRACK_HYDRALOC ||
            enumm == VRSettings.VrOptions.POS_TRACK_HYDRA_AT_BACKOFHEAD_IS_POINTING_LEFT)
        {
            this.reinit = true;
        }
    }

    private void setLocOffsetDefaults()
    {
        if (Minecraft.getMinecraft().positionTracker instanceof MCHydra )
        {
            switch (this.guivrSettings.posTrackHydraLoc)
            {
                case VRSettings.POS_TRACK_HYDRA_LOC_HMD_LEFT_AND_RIGHT:
                    this.guivrSettings.posTrackHydraLROffsetX = 0.0f;
                    this.guivrSettings.posTrackHydraLROffsetY = 0.0f;
                    this.guivrSettings.posTrackHydraLROffsetZ = 0.0f;
                    break;
                case VRSettings.POS_TRACK_HYDRA_LOC_HMD_LEFT:
                    this.guivrSettings.posTrackHydraLOffsetX = -0.108f;
                    this.guivrSettings.posTrackHydraLOffsetY = 0.0f;
                    this.guivrSettings.posTrackHydraLOffsetZ = 0.0f;
                    break;
                case VRSettings.POS_TRACK_HYDRA_LOC_HMD_TOP:
                    this.guivrSettings.posTrackHydraTOffsetX = 0.0f;
                    this.guivrSettings.posTrackHydraTOffsetY = 0.085f;
                    this.guivrSettings.posTrackHydraTOffsetZ = 0.0f;
                    break;
                case VRSettings.POS_TRACK_HYDRA_LOC_HMD_RIGHT:
                    this.guivrSettings.posTrackHydraROffsetX = 0.108f;
                    this.guivrSettings.posTrackHydraROffsetY = 0.0f;
                    this.guivrSettings.posTrackHydraROffsetZ = 0.0f;
                    break;
                case VRSettings.POS_TRACK_HYDRA_LOC_BACK_OF_HEAD:
                    if (this.guivrSettings.posTrackHydraBIsPointingLeft)
                    {
                        this.guivrSettings.posTrackHydraBLOffsetX = 0.05f;
                        this.guivrSettings.posTrackHydraBLOffsetY = 0.11f;
                        this.guivrSettings.posTrackHydraBLOffsetZ = -0.225f;
                    }
                    else
                    {
                        this.guivrSettings.posTrackHydraBROffsetX = -0.05f;
                        this.guivrSettings.posTrackHydraBROffsetY = 0.11f;
                        this.guivrSettings.posTrackHydraBROffsetZ = -0.225f;
                    }

                    break;
            }
        }
        else
        {
            this.guivrSettings.eyeProtrusion = 0.185f;
            this.guivrSettings.neckBaseToEyeHeight = 0.225f;
        }
    }

    @Override
    protected String[] getTooltipLines(String displayString, int buttonId)
    {
        VRSettings.VrOptions e = VRSettings.VrOptions.getEnumOptions(buttonId);
        if( e != null )
            switch(e)
            {
                case POS_TRACK_HYDRALOC:
                    return new String[] {
                            "Location(s) of the Hydra(s) used for pos track.",
                            "  L&R - One hydra is mounted to the left side of the ",
                            "        HMD, one to the right side. The Hydra center",
                            "        point is the average of the two reported locations.",
                            "  L   - One Hydra is mounted to the left side of the HMD.",
                            "  R   - One Hydra is mounted to the right side of the HMD.",
                            "  T   - One Hydra is mounted to the top of the HMD.",
                            "  B   - One hydra is mounted to the back of your head."
                    } ;
                case POS_TRACK_HYDRA_DISTANCE_SCALE:
                    return new String[] {
                            "Sets the distance scale factor.",
                            "  Allows adjustment of your perceived body movement",
                            " in-game by the selected factor. Adjust this if the",
                            " distance moved in game does not seem to match actual",
                            " body distance travelled."
                            } ;
                case POS_TRACK_HYDRA_USE_CONTROLLER_ONE:
                    return new String[] {
                            "Sets the controller used for positional tracking.",
                            "  If only one Hydra is used for positional tracking, sets",
                            "  which controller is used. Left / right are as set during",
                            "  the Hydra calibration process."
                    } ;
                case POS_TRACK_HYDRA_OFFSET_X:
                    return new String[] {
                            "Sets the left/right offset in mm of the Hydra center point",
                            "from the HMD center (eye) point. Adjust these offsets if ",
                            "rotational movement in-game does not quite match actual",
                            "bodily rotation.",
                            "  Negative values - The Hydra center is x mm to the left",
                            "                    of the HMD (eye) center point.",
                            "  Positive values - The Hydra center is x mm to the right",
                            "                    of the HMD (eye) center point."
                    };
                case POS_TRACK_HYDRA_OFFSET_Y:
                    return new String[] {
                            "Sets the above/below offset in mm of the Hydra center",
                            "point from the HMD center (eye) point. Adjust these",
                            "offsets if rotational movement in-game does not quite",
                            "match actual bodily rotation.",
                            "  Negative values - The Hydra center point is y mm below",
                            "                    the HMD (eye) center point.",
                            "  Positive values - The Hydra center point is y mm above",
                            "                    the HMD (eye) center."
                    };
                case POS_TRACK_HYDRA_OFFSET_Z:
                    return new String[] {
                            "Sets the towards head/away from head offset in mm of",
                            "the Hydra center point from the HMD (eye) center point.",
                            "Adjust these offsets if rotational movement in-game does",
                            "not quite match actual bodily rotation.",
                            "  Negative values - The Hydra center point is z mm behind",
                            "                    the HMD center (eye) point.",
                            "  Positive values - The Hydra center point is z mm in front",
                            "                    of the HMD center (eye) point."
                    };
                case HYDRA_USE_FILTER:
                    return new String[] {
                            "Use the Hydra positional filter.",
                            "  OFF - No filter is used; less latency but more positional",
                            "        'jitter' may be noticed, especially at a greater",
                            "        distance from the Hydra base unit.",
                            "  ON  - Filter used. Less positional 'jitter', more latency."
                    };
                case POS_TRACK_OFFSET_SET_DEFAULT:
                    return new String[] {
                            "Set offset defaults for positional tracking."
                    };
                case POS_TRACK_HYDRA_AT_BACKOFHEAD_IS_POINTING_LEFT:
                    return new String[] {
                            "Choose the direction the hydra is facing while under",
                            "the top strap of the Rift; either to the left or to",
                            "the right. This affects the offset settings."
                    };
                case EYE_PROTRUSION:
                    return new String[] {
                            "Distance from \"head-center\" to your eyes (in meters)",
                            "  (with pos track affects origin position only)",
                            " (\"X\" distance below)     ____  ",
                            "                              /      \\ ",
                            "                              |    XXo ",
                            "                              |      _\\",
                            "                               \\   /",
                            "                                 | |"
                    };
                case NECK_LENGTH:
                    return new String[] {
                            "Distance from \"head-center\" to your shoulders",
                            "  (with pos track affects origin position only)",
                            " (\"Y\" distance below)     ____  ",
                            "                              /      \\ ",
                            "                              |   Y  o ",
                            "                              |   Y  _\\",
                            "                               \\ Y /",
                            "                                 |Y|"
                    };
//                case POS_TRACK_Y_AXIS_DISTANCE_SKEW:
//                    return new String[] {
//                            "Explain this! Good luck!"
//                    };
                default:
                    return null;
            }
        else
            switch(buttonId)
            {
                case 201:
                    return new String[] {
                            "Changes the method used for positional tracking."
                    };
                case 202:
                    return new String[] {
                            "Resets the origin point to your current head",
                            "position."
                    };
                case 203:
                    return new String[] {
                            "Starts calibration of the Oculus Rift headset",
                            "  Press this button then follow the on screen",
                            "  instructions."
                    };
                default:
                    return null;
            }
    }
}
