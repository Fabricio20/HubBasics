package net.notfab.hubbasics.spigot.nms;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum CraftBukkitVersion {

    UNSUPPORTED("Unsupported", Double.MAX_VALUE, "Unknown"),

    v1_7_X("1.7.x", 1.07, "v1_7_R1", "v1_7_R2", "v1_7_R3", "v1_7_R4"),
    v1_7_1("1.7.1", 1.071, "v1_7_R1"),
    v1_7_2("1.7.2", 1.072, "v1_7_R2"),
    v1_7_3("1.7.3", 1.073, "v1_7_R3"),
    v1_7_4("1.7.4", 1.074, "v1_7_R4"),

    v1_8_X("1.8.x", 1.08, "v1_8_R1", "v1_8_R2", "v1_8_R3"),
    v1_8_1("1.8.1", 1.081, "v1_8_R1"),
    v1_8_3("1.8.3", 1.083, "v1_8_R2"),
    v1_8_4("1.8.4", 1.084, "v1_8_R3"),

    v1_9_X("1.9.x", 1.09, "v1_9_R1", "v1_9_R2"),
    v1_9_1("1.9.1", 1.091, "v1_9_R1"),
    v1_9_4("1.9.4", 1.094, "v1_9_R2"),

    v1_10_X("1.10.x", 1.10, "v1_10_R1"),
    v1_11_X("1.11.x", 1.11, "v1_11_R1"),
    v1_12_X("1.12.x", 1.12, "v1_12_R1"),

    v1_13_X("1.13.x", 1.13, "v1_13_R1", "v1_13_R2"),
    v1_13_1("1.13.1", 1.131, "v1_13_R1"),
    v1_13_2("1.13.2", 1.132, "v1_13_R2"),

    v1_14_X("1.14.x", 1.14, "v1_14_R1"),

    v1_15_X("1.15.x", 1.15, "v1_15_R1");

    private final String name;
    private final double version;
    private final String[] nmsNames;

    CraftBukkitVersion(String name, double version, String... nmsNames) {
        this.name = name;
        this.version = version;
        this.nmsNames = nmsNames;
    }

    public boolean isNewer(CraftBukkitVersion other) {
        return this.getVersion() >= other.getVersion();
    }

    public static CraftBukkitVersion find(String packageName) {
        String versionString = packageName.substring(packageName.lastIndexOf('.') + 1);
        for (CraftBukkitVersion version : CraftBukkitVersion.values()) {
            if (version.getNmsNames().length > 1) {
                // Ignores the "X" versions.
                continue;
            }
            if (versionString.equals(version.getNmsNames()[0])) {
                return version;
            }
        }
        return CraftBukkitVersion.UNSUPPORTED;
    }

    public List<String> getVersionList() {
        return Stream.of(CraftBukkitVersion.values())
                .filter(v -> v.getName().endsWith("x"))
                .map(v -> v.getName().replace(".x", ""))
                .collect(Collectors.toList());
    }

}