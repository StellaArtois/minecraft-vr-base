package com.mtbs3d.minecrift.gui;

import com.mtbs3d.minecrift.settings.VRSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StringTranslate;

public class GuiCalibrationSettings extends BaseGuiSettings
{
    static VRSettings.VrOptions[] calibrationOptions = new VRSettings.VrOptions[] {
            VRSettings.VrOptions.CALIBRATION_STRATEGY,
    };

    public GuiCalibrationSettings(GuiScreen guiScreen, VRSettings guivrSettings) {
        super( guiScreen, guivrSettings );
        screenTitle = "Calibration Settings";
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        this.buttonList.clear();
//        this.buttonList.add(new GuiSmallButtonEx(VRSettings.VrOptions.USE_VR.returnEnumOrdinal(), this.width / 2 - 78, this.height / 6 - 14, VRSettings.VrOptions.USE_VR, this.guivrSettings.getKeyBinding(VRSettings.VrOptions.USE_VR)));
        this.buttonList.add(new GuiButtonEx(202, this.width / 2 - 100, this.height / 6 + 128, "Recalibrate..."));
        this.buttonList.add(new GuiButtonEx(201, this.width / 2 - 100, this.height / 6 + 148, "Reset To Defaults"));
        this.buttonList.add(new GuiButtonEx(200, this.width / 2 - 100, this.height / 6 + 168, "Done"));
        VRSettings.VrOptions[] buttons = calibrationOptions;

        for (int var12 = 2; var12 < buttons.length + 2; ++var12)
        {
            VRSettings.VrOptions var8 = buttons[var12 - 2];
            int width = this.width / 2 - 155 + var12 % 2 * 160;
            int height = this.height / 6 + 21 * (var12 / 2) - 10;

            if (var8.getEnumFloat())
            {
                float minValue = 0.0f;
                float maxValue = 1.0f;
                float increment = 0.01f;

                if (var8 == VRSettings.VrOptions.HUD_SCALE)
                {
                    minValue = 0.5f;
                    maxValue = 1.5f;
                    increment = 0.01f;
                }
                if (var8 == VRSettings.VrOptions.HUD_DISTANCE)
                {
                    minValue = 0.5f;
                    maxValue = 3.0f;
                    increment = 0.02f;
                }

                this.buttonList.add(new GuiSliderEx(var8.returnEnumOrdinal(), width, height, var8, this.guivrSettings.getKeyBinding(var8), minValue, maxValue, increment, this.guivrSettings.getOptionFloatValue(var8)));
            }
            else
            {
                this.buttonList.add(new GuiSmallButtonEx(var8.returnEnumOrdinal(), width, height, var8, this.guivrSettings.getKeyBinding(var8)));
            }
        }
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton.enabled)
        {
            if (par1GuiButton.id < 200 && par1GuiButton instanceof GuiSmallButtonEx)
            {
                VRSettings.VrOptions num = VRSettings.VrOptions.getEnumOptions(par1GuiButton.id);
                this.guivrSettings.setOptionValue(((GuiSmallButtonEx)par1GuiButton).returnVrEnumOptions(), 1);
                par1GuiButton.displayString = this.guivrSettings.getKeyBinding(VRSettings.VrOptions.getEnumOptions(par1GuiButton.id));
            }
            else if (par1GuiButton.id == 200)
            {
                Minecraft.getMinecraft().vrSettings.saveOptions();
                this.mc.displayGuiScreen(this.parentGuiScreen);
            }
            else if (par1GuiButton.id == 201)
            {
                this.guivrSettings.calibrationStrategy = VRSettings.CALIBRATION_STRATEGY_AT_STARTUP;
                Minecraft.getMinecraft().vrSettings.saveOptions();
                this.reinit = true;
            }
            else if (par1GuiButton.id == 202)
            {
                if (vrRenderer != null)
                    vrRenderer.startCalibration();
            }
        }
    }

    @Override
    protected String[] getTooltipLines(String displayString, int buttonId)
    {
        VRSettings.VrOptions e = VRSettings.VrOptions.getEnumOptions(buttonId);
        if( e != null )
            switch(e)
            {
                case CALIBRATION_STRATEGY:
                    return new String[] {
                            "Sets whether device calibration is performed when",
                            "Minecraft is started.",
                            "  At Startup: Calibration routines for all",
                            "     utilised devices are run at startup.",
                            "  Skip: No calibration will be performed. The user",
                            "     will have to manually trigger calibration",
                            "     at some point for correct device operation."
                    };
                default:
                    return null;
            }
        else
            switch(buttonId)
            {
//                case 201:
//                    return new String[] {
//                            "Open this configuration screen to adjust the Head",
//                            "  Tracker orientation (direction) settings. ",
//                            "  Ex: Head Tracking Selection (Hydra/Oculus), Prediction"
//                    };
                default:
                    return null;
            }
    }
}
