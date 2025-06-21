plugins {
    id("com.gtnewhorizons.gtnhconvention")
}

tasks.jar {
    // Include license files in the production jar
    from(
        "COPYING",
        "COPYING.LESSER",
        "3RD-PARTY-LICENSES",
        "LICENSE")
}
