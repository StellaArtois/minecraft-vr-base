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

gradlew setupDecompWorkspace will create a development workspace
gradlew eclipse will create an eclipse project in this directory
gradlew idea will create an intelij project in this directory
gradlew natives will copy the minecraft-vr natives into the dev env

gradlew build will build all dependencies and the mod.

gradlew cleanCache clean setupDecompWorkspace --refresh-dependencies --info
gradlew idea natives --info

Launch configuration:

MainClass: net.minecraft.launchwrapper.Launch
Args: --version 1.7 --tweakClass cpw.mods.fml.common.launcher.FMLTweaker --accessToken test
JVM Args: -Dfml.ignoreInvalidMinecraftCertificates=true -Dfml.coreMods.load=com.minecraft_vr.VRLoadingPlugin 

Forge gradle 1.7.10 no assets bug workaround:
   add Program Args:  --assetIndex 1.7.10 --assetsDir C:\Users\<username>\.gradle\caches\minecraft\assets
