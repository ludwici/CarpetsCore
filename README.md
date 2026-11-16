# [Usage](https://github.com/ludwici/CarpetsCore/wiki)

[![curseforge](https://cf.way2muchnoise.eu/full_1140721_downloads.svg)](https://www.curseforge.com/minecraft/mc-mods/carpets-core)
[![Modrinth](https://img.shields.io/modrinth/dt/cjKKrGsM?label=Modrinth&logo=modrinth)](https://modrinth.com/mod/carpets-core/)
![versions](https://cf.way2muchnoise.eu/versions/1140721.svg)

![neoforge](https://github.com/thomasglasser/thomasglasser/blob/main/files/badges/cozy/supported/neoforge_64h.png?raw=true)
![fabric](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/supported/fabric_64h.png)
![forge](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/supported/forge_64h.png)

[![patreon-singular](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/donate/patreon-singular_64h.png)](https://www.patreon.com/ludwici)
[![kofi-singular](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/donate/kofi-singular-alt_64h.png)](https://ko-fi.com/ludwici)
## Minecraft 1.20.1 — Carpets Core 1.1.0

Added `com.ludwici.carpetscore.api.block.NoGravityVariantBlock` to the Forge and Fabric versions. It extends `CarpetVariantBlock` and keeps its placed faces when support blocks are removed. The class supports both constructors, with and without an optional replacement item, and allows adding a face while sneaking.

The new API is intended for Copper Deco and other mods using multi-face decorative blocks. No changes are required for existing `CarpetVariantBlock` implementations.

This branch targets Minecraft 1.20.1 and Java 17. Builds are checked by GitHub Actions for both loaders.
