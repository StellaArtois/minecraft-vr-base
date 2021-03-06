package com.minecraft_vr;

import com.minecraft_vr.api.IBodyOrientation;
import com.minecraft_vr.api.IHMD;
import com.minecraft_vr.api.IHeadOrientation;
import com.minecraft_vr.api.IHeadPosition;
import com.minecraft_vr.render.FrameBufferShim;
import com.minecraft_vr.render.VRRenderer;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;

import java.io.File;

@Mod(modid = VRBase.MODID, version = VRBase.VERSION)
public class VRBase
{
    public static final String MODID = "com.minecraft-vr.base";
    public static final String VERSION = "1.0";
    
    public static VRBase GetInstance()
    {
    	return inst;
    	
    }
    private static VRBase inst;

    public void preInit(FMLPreInitializationEvent event)
    {
        File cfg = event.getSuggestedConfigurationFile();
        String cfgPath = cfg.getAbsolutePath();

        // Read the Vrbase.cfg (old VROptions.txt)
        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        config.save();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	inst = this;

    	//Swap out renderer
        Minecraft mc = Minecraft.getMinecraft();
        //mc.entityRenderer = new VRRenderer(mc.entityRenderer);
        //mc.framebufferMc = new FrameBufferShim( mc.framebufferMc );
    }
    
    public void RegisterPlugin( IBodyOrientation bodyOrient )
    {
    	 // This is the
    }
    
    public static IBodyOrientation bodyOrientation;
    public static IHeadOrientation headOrientation;
    public static IHeadPosition    headPosition;
    public static IHMD             hmd;
    public static Configuration    config;
    
}
