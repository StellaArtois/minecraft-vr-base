Minecraft VR Forge Mod (base)
===================================

Minecraft Version: 1.7.2

Current Version: 0.1 (this doesn't do anything you want it to yet!)

Copyright StellaArtois, mabrowning 2014. See LICENSE.md for more.

See http://minecraft-vr.com/ for full project details.

IMPORTANT NOTE
--------------

This version is extremely work-in-progress. When it is the least bit stable, an
announcement will be made on all the relevant channels.

BUILDING
--------

Uses gradle.

gradlew clean setupDecompWorkspace idea natives --refresh-dependencies --info

Launch configuration:

MainClass: net.minecraft.launchwrapper.Launch
Args: --version 1.7 --tweakClass cpw.mods.fml.common.launcher.FMLTweaker --accessToken test
JVM Args: -Dfml.ignoreInvalidMinecraftCertificates=true -Dfml.coreMods.load=com.minecraft_vr.VRLoadingPlugin 

Forge gradle 1.7.10 no assets bug workaround:
   add Program Args to project run config:  --assetIndex 1.7.10 --assetsDir C:\Users\<username>\.gradle\caches\minecraft\assets
