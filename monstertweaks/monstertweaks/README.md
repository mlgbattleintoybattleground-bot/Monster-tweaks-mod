# Monster Tweaks (Fabric mod, Minecraft 1.20.1)

## What it does
- **Zombies**: can break down doors on *any* difficulty (vanilla only allows this on Hard). They path to a closed door, start hitting it, and eventually smash through. Movement speed is bumped slightly (not sprint-fast).
- **Creepers**: roam at normal speed, but the moment they spot a player they speed up to slightly faster than player sprint. Fuse time is shortened to 0.3 seconds once ignited.

## ⚠️ Important — I couldn't compile-test this
I don't have network/internet access in this environment, so I can't download the Minecraft/Fabric dependencies and actually build or run this mod myself. I verified all the method and field names against the official Fabric Yarn mappings for 1.20.1, so it *should* work, but please treat this as a first draft you compile and test yourself — you may need to tweak numbers or fix a small mismatch.

## How to build it

1. **Get the base project.** Rather than assembling a Gradle wrapper by hand, start from Fabric's official example mod template — it already includes the `gradlew`/`gradlew.bat` wrapper scripts and wrapper jar that can't be generated as plain text:
   - Go to https://github.com/FabricMC/fabric-example-mod
   - Click "Use this template" (or just download/clone it) into a new folder.

2. **Drop in these files**, overwriting the template's equivalents:
   - `build.gradle` → replace with the one here
   - `gradle.properties` → replace with the one here
   - `src/main/java/com/monstertweaks/MonsterTweaksMod.java`
   - `src/main/java/com/monstertweaks/mixin/ZombieEntityMixin.java`
   - `src/main/java/com/monstertweaks/mixin/CreeperEntityMixin.java`
   - `src/main/resources/fabric.mod.json`
   - `src/main/resources/monstertweaks.mixins.json`

   Delete the template's original example package/files (usually something like `com/example/...`) so there's no leftover mod id clash.

3. **Build it:**
   ```bash
   ./gradlew build
   ```
   (On Windows: `gradlew.bat build`)

   The compiled mod jar will show up in `build/libs/monstertweaks-1.0.0.jar`.

4. **Install it in your game:**
   - Install [Fabric Loader](https://fabricmc.net/use/) for Minecraft 1.20.1.
   - Download [Fabric API](https://modrinth.com/mod/fabric-api) for 1.20.1 and put it in your `.minecraft/mods` folder.
   - Put `monstertweaks-1.0.0.jar` in that same `mods` folder.
   - Launch the game with the Fabric profile.

## Tuning it further
Open the two mixin files and tweak the constants at the top:
- `ZOMBIE_SPEED` in `MonsterTweaksMod.java` (vanilla default: 0.23)
- `CHASE_SPEED` / `NORMAL_SPEED` in `CreeperEntityMixin.java`
- `SHORT_FUSE_TICKS` in `CreeperEntityMixin.java` (20 ticks = 1 second, so 6 = 0.3s)

If a value feels too subtle or too extreme in-game, just change the number and rebuild.
