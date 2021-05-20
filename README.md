# BannerArchiver
Minecraft 1.12.2 Forge mod for creating banner recipe links.

# Usage
Use the following command while holding a banner in your main hand:
```
/bannerarchiver
```
This will create a chat message with the banner display name and a recipe link. This is shown here:![2021-05-20_12 41 42](https://user-images.githubusercontent.com/59456376/118965261-c9e69000-b968-11eb-9da1-3625a1d2282d.png)

This link is clickable and will take you to the resulting needcoolshoes page:

https://www.needcoolshoes.com/banner?=kapmdt

You can then do whatever you want with the recipe, such as archiving it or copying it to a different world or server

# How to build/modify the mod yourself

```
Using Intelij IDEA:
1. Import the build.gradle file as a new project
2. Run SetupDecompWorkspace
3. Run GenIntelijRuns
4. Change Minecraft Client configuration to take bannerarchiver.Main as the module to use
5. You can now test/build the mod
```
It is recommended to clean the build, before actually building it. This can be done by creating a new [clean] task in the run/debug configurations window

# Suggestions / Bug reports etc

Make an issue or let me know on Discord if there are any changes/QOL/Fixes that you want to see. 
