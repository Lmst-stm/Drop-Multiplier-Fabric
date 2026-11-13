# Drop Multiplier (Fabric)

An auxiliary mod that can modify the drop rate of game items — for players who want to
slack off in casual play or challenge the limits of their graphics card.

* **This branch**: Minecraft `1.21.10` · Fabric Loader `0.20.1` · Fabric API `0.138.4+1.21.10` · mod `1.21.10-1.0.1`
* **Requires**: Minecraft 1.21.10 + Fabric Loader 0.20.1+ + Fabric API

## Features

* Multiplies the output of every loot table (mobs, blocks, chests, fishing) via a `LootTable` mixin
* Multiplies experience orb values
* `/dropmultiplier <value>` sets the multiplier, `/dropmultiplier info` shows the state
* `/dropmultiplier enable|disable|toggle|reset`
* Config is in-memory (no config file on the Fabric build yet)

## Building

```bash
./gradlew build
```

The jar is written to `build/libs/`.

## Status

built & server-tested
