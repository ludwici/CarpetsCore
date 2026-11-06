# CarpetsCore — Minecraft 26.1.2

This branch contains the Fabric and NeoForge ports as independent Gradle projects, imported from the supplied source archives.

| Loader | Project directory | Minecraft versions |
| --- | --- | --- |
| Fabric | `fabric/` | 26.1, 26.1.1, 26.1.2 |
| NeoForge | `neoforge/` | 26.1, 26.1.1, 26.1.2 |

### Building

Requires JDK 25. From the repository root:

- Windows Fabric: `cd fabric; .\gradlew.bat build`
- Windows NeoForge: `cd neoforge; .\gradlew.bat build`
- Linux/macOS: `cd fabric && ./gradlew build` or `cd neoforge && ./gradlew build`

Build outputs appear under each project's `build/libs/` directory.

**Compatibility note:** The `26.1.2` source is used for the entire Minecraft 26.1.x series. Mod metadata permits the earlier patch versions; actual cross-version runtime compatibility should still be tested on all three versions.
