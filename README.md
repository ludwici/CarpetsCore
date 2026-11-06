# CarpetsCore — Minecraft 26.3

This branch contains the Fabric and NeoForge ports as independent Gradle projects, imported from the supplied source archives.

| Loader | Project directory | Minecraft versions |
| --- | --- | --- |
| Fabric | `fabric/` | 26.3 |
| NeoForge | `neoforge/` | 26.2 |

### Building

Requires JDK 25. From the repository root:

- Windows Fabric: `cd fabric; .\gradlew.bat build`
- Windows NeoForge: `cd neoforge; .\gradlew.bat build`
- Linux/macOS: `cd fabric && ./gradlew build` or `cd neoforge && ./gradlew build`

Build outputs appear under each project's `build/libs/` directory.


