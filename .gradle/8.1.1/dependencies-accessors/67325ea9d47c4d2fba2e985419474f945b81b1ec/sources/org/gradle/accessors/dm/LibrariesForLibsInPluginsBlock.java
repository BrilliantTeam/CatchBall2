package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
 */
@NonNullApi
public class LibrariesForLibsInPluginsBlock extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final BrLibraryAccessors laccForBrLibraryAccessors = new BrLibraryAccessors(owner);
    private final ComLibraryAccessors laccForComLibraryAccessors = new ComLibraryAccessors(owner);
    private final DeLibraryAccessors laccForDeLibraryAccessors = new DeLibraryAccessors(owner);
    private final IoLibraryAccessors laccForIoLibraryAccessors = new IoLibraryAccessors(owner);
    private final MeLibraryAccessors laccForMeLibraryAccessors = new MeLibraryAccessors(owner);
    private final OrgLibraryAccessors laccForOrgLibraryAccessors = new OrgLibraryAccessors(owner);
    private final ResLibraryAccessors laccForResLibraryAccessors = new ResLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibsInPluginsBlock(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

    /**
     * Returns the group of libraries at br
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public BrLibraryAccessors getBr() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
        return laccForBrLibraryAccessors;
    }

    /**
     * Returns the group of libraries at com
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public ComLibraryAccessors getCom() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
        return laccForComLibraryAccessors;
    }

    /**
     * Returns the group of libraries at de
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public DeLibraryAccessors getDe() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
        return laccForDeLibraryAccessors;
    }

    /**
     * Returns the group of libraries at io
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public IoLibraryAccessors getIo() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
        return laccForIoLibraryAccessors;
    }

    /**
     * Returns the group of libraries at me
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public MeLibraryAccessors getMe() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
        return laccForMeLibraryAccessors;
    }

    /**
     * Returns the group of libraries at org
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public OrgLibraryAccessors getOrg() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
        return laccForOrgLibraryAccessors;
    }

    /**
     * Returns the group of libraries at res
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public ResLibraryAccessors getRes() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
        return laccForResLibraryAccessors;
    }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Returns the group of bundles at bundles
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public BundleAccessors getBundles() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
        return baccForBundleAccessors;
    }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class BrLibraryAccessors extends SubDependencyFactory {
        private final BrNetLibraryAccessors laccForBrNetLibraryAccessors = new BrNetLibraryAccessors(owner);

        public BrLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at br.net
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public BrNetLibraryAccessors getNet() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForBrNetLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class BrNetLibraryAccessors extends SubDependencyFactory {
        private final BrNetFabiozumbi12LibraryAccessors laccForBrNetFabiozumbi12LibraryAccessors = new BrNetFabiozumbi12LibraryAccessors(owner);

        public BrNetLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at br.net.fabiozumbi12
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public BrNetFabiozumbi12LibraryAccessors getFabiozumbi12() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForBrNetFabiozumbi12LibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class BrNetFabiozumbi12LibraryAccessors extends SubDependencyFactory {
        private final BrNetFabiozumbi12RedprotectLibraryAccessors laccForBrNetFabiozumbi12RedprotectLibraryAccessors = new BrNetFabiozumbi12RedprotectLibraryAccessors(owner);

        public BrNetFabiozumbi12LibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at br.net.fabiozumbi12.redprotect
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public BrNetFabiozumbi12RedprotectLibraryAccessors getRedprotect() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForBrNetFabiozumbi12RedprotectLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class BrNetFabiozumbi12RedprotectLibraryAccessors extends SubDependencyFactory {
        private final BrNetFabiozumbi12RedprotectRedprotectLibraryAccessors laccForBrNetFabiozumbi12RedprotectRedprotectLibraryAccessors = new BrNetFabiozumbi12RedprotectRedprotectLibraryAccessors(owner);

        public BrNetFabiozumbi12RedprotectLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at br.net.fabiozumbi12.redprotect.redprotect
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public BrNetFabiozumbi12RedprotectRedprotectLibraryAccessors getRedprotect() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForBrNetFabiozumbi12RedprotectRedprotectLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class BrNetFabiozumbi12RedprotectRedprotectLibraryAccessors extends SubDependencyFactory {

        public BrNetFabiozumbi12RedprotectRedprotectLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (br.net.fabiozumbi12.RedProtect:RedProtect-Core)
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getCore() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("br.net.fabiozumbi12.redprotect.redprotect.core");
        }

            /**
             * Creates a dependency provider for spigot (br.net.fabiozumbi12.RedProtect:RedProtect-Spigot)
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getSpigot() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("br.net.fabiozumbi12.redprotect.redprotect.spigot");
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class ComLibraryAccessors extends SubDependencyFactory {
        private final ComGithubLibraryAccessors laccForComGithubLibraryAccessors = new ComGithubLibraryAccessors(owner);
        private final ComJeffLibraryAccessors laccForComJeffLibraryAccessors = new ComJeffLibraryAccessors(owner);
        private final ComSk89qLibraryAccessors laccForComSk89qLibraryAccessors = new ComSk89qLibraryAccessors(owner);

        public ComLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.github
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public ComGithubLibraryAccessors getGithub() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForComGithubLibraryAccessors;
        }

        /**
         * Returns the group of libraries at com.jeff
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public ComJeffLibraryAccessors getJeff() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForComJeffLibraryAccessors;
        }

        /**
         * Returns the group of libraries at com.sk89q
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public ComSk89qLibraryAccessors getSk89q() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForComSk89qLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class ComGithubLibraryAccessors extends SubDependencyFactory {
        private final ComGithubAngeschossenLibraryAccessors laccForComGithubAngeschossenLibraryAccessors = new ComGithubAngeschossenLibraryAccessors(owner);
        private final ComGithubTechfortressLibraryAccessors laccForComGithubTechfortressLibraryAccessors = new ComGithubTechfortressLibraryAccessors(owner);
        private final ComGithubXynessLibraryAccessors laccForComGithubXynessLibraryAccessors = new ComGithubXynessLibraryAccessors(owner);

        public ComGithubLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.github.angeschossen
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public ComGithubAngeschossenLibraryAccessors getAngeschossen() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForComGithubAngeschossenLibraryAccessors;
        }

        /**
         * Returns the group of libraries at com.github.techfortress
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public ComGithubTechfortressLibraryAccessors getTechfortress() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForComGithubTechfortressLibraryAccessors;
        }

        /**
         * Returns the group of libraries at com.github.xyness
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public ComGithubXynessLibraryAccessors getXyness() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForComGithubXynessLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class ComGithubAngeschossenLibraryAccessors extends SubDependencyFactory {

        public ComGithubAngeschossenLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for landsapi (com.github.angeschossen:LandsAPI)
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getLandsapi() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("com.github.angeschossen.landsapi");
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class ComGithubTechfortressLibraryAccessors extends SubDependencyFactory {

        public ComGithubTechfortressLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for griefprevention (com.github.TechFortress:GriefPrevention)
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getGriefprevention() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("com.github.techfortress.griefprevention");
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class ComGithubXynessLibraryAccessors extends SubDependencyFactory {

        public ComGithubXynessLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for simpleclaimsystem (com.github.Xyness:SimpleClaimSystem)
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getSimpleclaimsystem() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("com.github.xyness.simpleclaimsystem");
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class ComJeffLibraryAccessors extends SubDependencyFactory {
        private final ComJeffMediaLibraryAccessors laccForComJeffMediaLibraryAccessors = new ComJeffMediaLibraryAccessors(owner);

        public ComJeffLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.jeff.media
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public ComJeffMediaLibraryAccessors getMedia() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForComJeffMediaLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class ComJeffMediaLibraryAccessors extends SubDependencyFactory {

        public ComJeffMediaLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for spigotupdatechecker (com.jeff_media:SpigotUpdateChecker)
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getSpigotupdatechecker() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("com.jeff.media.spigotupdatechecker");
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class ComSk89qLibraryAccessors extends SubDependencyFactory {
        private final ComSk89qWorldguardLibraryAccessors laccForComSk89qWorldguardLibraryAccessors = new ComSk89qWorldguardLibraryAccessors(owner);

        public ComSk89qLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.sk89q.worldguard
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public ComSk89qWorldguardLibraryAccessors getWorldguard() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForComSk89qWorldguardLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class ComSk89qWorldguardLibraryAccessors extends SubDependencyFactory {
        private final ComSk89qWorldguardWorldguardLibraryAccessors laccForComSk89qWorldguardWorldguardLibraryAccessors = new ComSk89qWorldguardWorldguardLibraryAccessors(owner);

        public ComSk89qWorldguardLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.sk89q.worldguard.worldguard
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public ComSk89qWorldguardWorldguardLibraryAccessors getWorldguard() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForComSk89qWorldguardWorldguardLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class ComSk89qWorldguardWorldguardLibraryAccessors extends SubDependencyFactory {

        public ComSk89qWorldguardWorldguardLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for bukkit (com.sk89q.worldguard:worldguard-bukkit)
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getBukkit() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("com.sk89q.worldguard.worldguard.bukkit");
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class DeLibraryAccessors extends SubDependencyFactory {
        private final DeTr7zwLibraryAccessors laccForDeTr7zwLibraryAccessors = new DeTr7zwLibraryAccessors(owner);

        public DeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at de.tr7zw
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public DeTr7zwLibraryAccessors getTr7zw() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForDeTr7zwLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class DeTr7zwLibraryAccessors extends SubDependencyFactory {
        private final DeTr7zwItemLibraryAccessors laccForDeTr7zwItemLibraryAccessors = new DeTr7zwItemLibraryAccessors(owner);

        public DeTr7zwLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at de.tr7zw.item
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public DeTr7zwItemLibraryAccessors getItem() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForDeTr7zwItemLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class DeTr7zwItemLibraryAccessors extends SubDependencyFactory {
        private final DeTr7zwItemNbtLibraryAccessors laccForDeTr7zwItemNbtLibraryAccessors = new DeTr7zwItemNbtLibraryAccessors(owner);

        public DeTr7zwItemLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at de.tr7zw.item.nbt
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public DeTr7zwItemNbtLibraryAccessors getNbt() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForDeTr7zwItemNbtLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class DeTr7zwItemNbtLibraryAccessors extends SubDependencyFactory {

        public DeTr7zwItemNbtLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for api (de.tr7zw:item-nbt-api)
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getApi() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("de.tr7zw.item.nbt.api");
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class IoLibraryAccessors extends SubDependencyFactory {
        private final IoLumineLibraryAccessors laccForIoLumineLibraryAccessors = new IoLumineLibraryAccessors(owner);

        public IoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at io.lumine
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public IoLumineLibraryAccessors getLumine() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForIoLumineLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class IoLumineLibraryAccessors extends SubDependencyFactory {
        private final IoLumineMythicLibraryAccessors laccForIoLumineMythicLibraryAccessors = new IoLumineMythicLibraryAccessors(owner);

        public IoLumineLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at io.lumine.mythic
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public IoLumineMythicLibraryAccessors getMythic() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForIoLumineMythicLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class IoLumineMythicLibraryAccessors extends SubDependencyFactory {

        public IoLumineMythicLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for dist (io.lumine:Mythic-Dist)
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getDist() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("io.lumine.mythic.dist");
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class MeLibraryAccessors extends SubDependencyFactory {
        private final MeClipLibraryAccessors laccForMeClipLibraryAccessors = new MeClipLibraryAccessors(owner);

        public MeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at me.clip
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public MeClipLibraryAccessors getClip() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForMeClipLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class MeClipLibraryAccessors extends SubDependencyFactory {

        public MeClipLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for placeholderapi (me.clip:placeholderapi)
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getPlaceholderapi() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("me.clip.placeholderapi");
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class OrgLibraryAccessors extends SubDependencyFactory {
        private final OrgBstatsLibraryAccessors laccForOrgBstatsLibraryAccessors = new OrgBstatsLibraryAccessors(owner);
        private final OrgSpigotmcLibraryAccessors laccForOrgSpigotmcLibraryAccessors = new OrgSpigotmcLibraryAccessors(owner);

        public OrgLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.bstats
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public OrgBstatsLibraryAccessors getBstats() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForOrgBstatsLibraryAccessors;
        }

        /**
         * Returns the group of libraries at org.spigotmc
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public OrgSpigotmcLibraryAccessors getSpigotmc() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForOrgSpigotmcLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class OrgBstatsLibraryAccessors extends SubDependencyFactory {
        private final OrgBstatsBstatsLibraryAccessors laccForOrgBstatsBstatsLibraryAccessors = new OrgBstatsBstatsLibraryAccessors(owner);

        public OrgBstatsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.bstats.bstats
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public OrgBstatsBstatsLibraryAccessors getBstats() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForOrgBstatsBstatsLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class OrgBstatsBstatsLibraryAccessors extends SubDependencyFactory {

        public OrgBstatsBstatsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for bukkit (org.bstats:bstats-bukkit)
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getBukkit() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("org.bstats.bstats.bukkit");
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class OrgSpigotmcLibraryAccessors extends SubDependencyFactory {
        private final OrgSpigotmcSpigotLibraryAccessors laccForOrgSpigotmcSpigotLibraryAccessors = new OrgSpigotmcSpigotLibraryAccessors(owner);

        public OrgSpigotmcLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.spigotmc.spigot
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public OrgSpigotmcSpigotLibraryAccessors getSpigot() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForOrgSpigotmcSpigotLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class OrgSpigotmcSpigotLibraryAccessors extends SubDependencyFactory {

        public OrgSpigotmcSpigotLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for api (org.spigotmc:spigot-api)
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getApi() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("org.spigotmc.spigot.api");
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class ResLibraryAccessors extends SubDependencyFactory {

        public ResLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for residence (res:Residence)
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getResidence() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("res.residence");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final BrVersionAccessors vaccForBrVersionAccessors = new BrVersionAccessors(providers, config);
        private final ComVersionAccessors vaccForComVersionAccessors = new ComVersionAccessors(providers, config);
        private final DeVersionAccessors vaccForDeVersionAccessors = new DeVersionAccessors(providers, config);
        private final IoVersionAccessors vaccForIoVersionAccessors = new IoVersionAccessors(providers, config);
        private final MeVersionAccessors vaccForMeVersionAccessors = new MeVersionAccessors(providers, config);
        private final OrgVersionAccessors vaccForOrgVersionAccessors = new OrgVersionAccessors(providers, config);
        private final ResVersionAccessors vaccForResVersionAccessors = new ResVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.br
         */
        public BrVersionAccessors getBr() {
            return vaccForBrVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.com
         */
        public ComVersionAccessors getCom() {
            return vaccForComVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.de
         */
        public DeVersionAccessors getDe() {
            return vaccForDeVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.io
         */
        public IoVersionAccessors getIo() {
            return vaccForIoVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.me
         */
        public MeVersionAccessors getMe() {
            return vaccForMeVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.org
         */
        public OrgVersionAccessors getOrg() {
            return vaccForOrgVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.res
         */
        public ResVersionAccessors getRes() {
            return vaccForResVersionAccessors;
        }

    }

    public static class BrVersionAccessors extends VersionFactory  {

        private final BrNetVersionAccessors vaccForBrNetVersionAccessors = new BrNetVersionAccessors(providers, config);
        public BrVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.br.net
         */
        public BrNetVersionAccessors getNet() {
            return vaccForBrNetVersionAccessors;
        }

    }

    public static class BrNetVersionAccessors extends VersionFactory  {

        private final BrNetFabiozumbi12VersionAccessors vaccForBrNetFabiozumbi12VersionAccessors = new BrNetFabiozumbi12VersionAccessors(providers, config);
        public BrNetVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.br.net.fabiozumbi12
         */
        public BrNetFabiozumbi12VersionAccessors getFabiozumbi12() {
            return vaccForBrNetFabiozumbi12VersionAccessors;
        }

    }

    public static class BrNetFabiozumbi12VersionAccessors extends VersionFactory  {

        private final BrNetFabiozumbi12RedprotectVersionAccessors vaccForBrNetFabiozumbi12RedprotectVersionAccessors = new BrNetFabiozumbi12RedprotectVersionAccessors(providers, config);
        public BrNetFabiozumbi12VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.br.net.fabiozumbi12.redprotect
         */
        public BrNetFabiozumbi12RedprotectVersionAccessors getRedprotect() {
            return vaccForBrNetFabiozumbi12RedprotectVersionAccessors;
        }

    }

    public static class BrNetFabiozumbi12RedprotectVersionAccessors extends VersionFactory  {

        private final BrNetFabiozumbi12RedprotectRedprotectVersionAccessors vaccForBrNetFabiozumbi12RedprotectRedprotectVersionAccessors = new BrNetFabiozumbi12RedprotectRedprotectVersionAccessors(providers, config);
        public BrNetFabiozumbi12RedprotectVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.br.net.fabiozumbi12.redprotect.redprotect
         */
        public BrNetFabiozumbi12RedprotectRedprotectVersionAccessors getRedprotect() {
            return vaccForBrNetFabiozumbi12RedprotectRedprotectVersionAccessors;
        }

    }

    public static class BrNetFabiozumbi12RedprotectRedprotectVersionAccessors extends VersionFactory  {

        public BrNetFabiozumbi12RedprotectRedprotectVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: br.net.fabiozumbi12.redprotect.redprotect.core (7.7.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCore() { return getVersion("br.net.fabiozumbi12.redprotect.redprotect.core"); }

            /**
             * Returns the version associated to this alias: br.net.fabiozumbi12.redprotect.redprotect.spigot (LATEST)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getSpigot() { return getVersion("br.net.fabiozumbi12.redprotect.redprotect.spigot"); }

    }

    public static class ComVersionAccessors extends VersionFactory  {

        private final ComGithubVersionAccessors vaccForComGithubVersionAccessors = new ComGithubVersionAccessors(providers, config);
        private final ComJeffVersionAccessors vaccForComJeffVersionAccessors = new ComJeffVersionAccessors(providers, config);
        private final ComSk89qVersionAccessors vaccForComSk89qVersionAccessors = new ComSk89qVersionAccessors(providers, config);
        public ComVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.com.github
         */
        public ComGithubVersionAccessors getGithub() {
            return vaccForComGithubVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.com.jeff
         */
        public ComJeffVersionAccessors getJeff() {
            return vaccForComJeffVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.com.sk89q
         */
        public ComSk89qVersionAccessors getSk89q() {
            return vaccForComSk89qVersionAccessors;
        }

    }

    public static class ComGithubVersionAccessors extends VersionFactory  {

        private final ComGithubAngeschossenVersionAccessors vaccForComGithubAngeschossenVersionAccessors = new ComGithubAngeschossenVersionAccessors(providers, config);
        private final ComGithubTechfortressVersionAccessors vaccForComGithubTechfortressVersionAccessors = new ComGithubTechfortressVersionAccessors(providers, config);
        private final ComGithubXynessVersionAccessors vaccForComGithubXynessVersionAccessors = new ComGithubXynessVersionAccessors(providers, config);
        public ComGithubVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.com.github.angeschossen
         */
        public ComGithubAngeschossenVersionAccessors getAngeschossen() {
            return vaccForComGithubAngeschossenVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.com.github.techfortress
         */
        public ComGithubTechfortressVersionAccessors getTechfortress() {
            return vaccForComGithubTechfortressVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.com.github.xyness
         */
        public ComGithubXynessVersionAccessors getXyness() {
            return vaccForComGithubXynessVersionAccessors;
        }

    }

    public static class ComGithubAngeschossenVersionAccessors extends VersionFactory  {

        public ComGithubAngeschossenVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: com.github.angeschossen.landsapi (7.8.5)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getLandsapi() { return getVersion("com.github.angeschossen.landsapi"); }

    }

    public static class ComGithubTechfortressVersionAccessors extends VersionFactory  {

        public ComGithubTechfortressVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: com.github.techfortress.griefprevention (17.0.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getGriefprevention() { return getVersion("com.github.techfortress.griefprevention"); }

    }

    public static class ComGithubXynessVersionAccessors extends VersionFactory  {

        public ComGithubXynessVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: com.github.xyness.simpleclaimsystem (1.10.0.4)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getSimpleclaimsystem() { return getVersion("com.github.xyness.simpleclaimsystem"); }

    }

    public static class ComJeffVersionAccessors extends VersionFactory  {

        private final ComJeffMediaVersionAccessors vaccForComJeffMediaVersionAccessors = new ComJeffMediaVersionAccessors(providers, config);
        public ComJeffVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.com.jeff.media
         */
        public ComJeffMediaVersionAccessors getMedia() {
            return vaccForComJeffMediaVersionAccessors;
        }

    }

    public static class ComJeffMediaVersionAccessors extends VersionFactory  {

        public ComJeffMediaVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: com.jeff.media.spigotupdatechecker (3.0.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getSpigotupdatechecker() { return getVersion("com.jeff.media.spigotupdatechecker"); }

    }

    public static class ComSk89qVersionAccessors extends VersionFactory  {

        private final ComSk89qWorldguardVersionAccessors vaccForComSk89qWorldguardVersionAccessors = new ComSk89qWorldguardVersionAccessors(providers, config);
        public ComSk89qVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.com.sk89q.worldguard
         */
        public ComSk89qWorldguardVersionAccessors getWorldguard() {
            return vaccForComSk89qWorldguardVersionAccessors;
        }

    }

    public static class ComSk89qWorldguardVersionAccessors extends VersionFactory  {

        private final ComSk89qWorldguardWorldguardVersionAccessors vaccForComSk89qWorldguardWorldguardVersionAccessors = new ComSk89qWorldguardWorldguardVersionAccessors(providers, config);
        public ComSk89qWorldguardVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.com.sk89q.worldguard.worldguard
         */
        public ComSk89qWorldguardWorldguardVersionAccessors getWorldguard() {
            return vaccForComSk89qWorldguardWorldguardVersionAccessors;
        }

    }

    public static class ComSk89qWorldguardWorldguardVersionAccessors extends VersionFactory  {

        public ComSk89qWorldguardWorldguardVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: com.sk89q.worldguard.worldguard.bukkit (7.0.10)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getBukkit() { return getVersion("com.sk89q.worldguard.worldguard.bukkit"); }

    }

    public static class DeVersionAccessors extends VersionFactory  {

        private final DeTr7zwVersionAccessors vaccForDeTr7zwVersionAccessors = new DeTr7zwVersionAccessors(providers, config);
        public DeVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.de.tr7zw
         */
        public DeTr7zwVersionAccessors getTr7zw() {
            return vaccForDeTr7zwVersionAccessors;
        }

    }

    public static class DeTr7zwVersionAccessors extends VersionFactory  {

        private final DeTr7zwItemVersionAccessors vaccForDeTr7zwItemVersionAccessors = new DeTr7zwItemVersionAccessors(providers, config);
        public DeTr7zwVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.de.tr7zw.item
         */
        public DeTr7zwItemVersionAccessors getItem() {
            return vaccForDeTr7zwItemVersionAccessors;
        }

    }

    public static class DeTr7zwItemVersionAccessors extends VersionFactory  {

        private final DeTr7zwItemNbtVersionAccessors vaccForDeTr7zwItemNbtVersionAccessors = new DeTr7zwItemNbtVersionAccessors(providers, config);
        public DeTr7zwItemVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.de.tr7zw.item.nbt
         */
        public DeTr7zwItemNbtVersionAccessors getNbt() {
            return vaccForDeTr7zwItemNbtVersionAccessors;
        }

    }

    public static class DeTr7zwItemNbtVersionAccessors extends VersionFactory  {

        public DeTr7zwItemNbtVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: de.tr7zw.item.nbt.api (2.13.3-SNAPSHOT)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getApi() { return getVersion("de.tr7zw.item.nbt.api"); }

    }

    public static class IoVersionAccessors extends VersionFactory  {

        private final IoLumineVersionAccessors vaccForIoLumineVersionAccessors = new IoLumineVersionAccessors(providers, config);
        public IoVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.io.lumine
         */
        public IoLumineVersionAccessors getLumine() {
            return vaccForIoLumineVersionAccessors;
        }

    }

    public static class IoLumineVersionAccessors extends VersionFactory  {

        private final IoLumineMythicVersionAccessors vaccForIoLumineMythicVersionAccessors = new IoLumineMythicVersionAccessors(providers, config);
        public IoLumineVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.io.lumine.mythic
         */
        public IoLumineMythicVersionAccessors getMythic() {
            return vaccForIoLumineMythicVersionAccessors;
        }

    }

    public static class IoLumineMythicVersionAccessors extends VersionFactory  {

        public IoLumineMythicVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: io.lumine.mythic.dist (5.7.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getDist() { return getVersion("io.lumine.mythic.dist"); }

    }

    public static class MeVersionAccessors extends VersionFactory  {

        private final MeClipVersionAccessors vaccForMeClipVersionAccessors = new MeClipVersionAccessors(providers, config);
        public MeVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.me.clip
         */
        public MeClipVersionAccessors getClip() {
            return vaccForMeClipVersionAccessors;
        }

    }

    public static class MeClipVersionAccessors extends VersionFactory  {

        public MeClipVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: me.clip.placeholderapi (2.11.6)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getPlaceholderapi() { return getVersion("me.clip.placeholderapi"); }

    }

    public static class OrgVersionAccessors extends VersionFactory  {

        private final OrgBstatsVersionAccessors vaccForOrgBstatsVersionAccessors = new OrgBstatsVersionAccessors(providers, config);
        private final OrgSpigotmcVersionAccessors vaccForOrgSpigotmcVersionAccessors = new OrgSpigotmcVersionAccessors(providers, config);
        public OrgVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.bstats
         */
        public OrgBstatsVersionAccessors getBstats() {
            return vaccForOrgBstatsVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.org.spigotmc
         */
        public OrgSpigotmcVersionAccessors getSpigotmc() {
            return vaccForOrgSpigotmcVersionAccessors;
        }

    }

    public static class OrgBstatsVersionAccessors extends VersionFactory  {

        private final OrgBstatsBstatsVersionAccessors vaccForOrgBstatsBstatsVersionAccessors = new OrgBstatsBstatsVersionAccessors(providers, config);
        public OrgBstatsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.bstats.bstats
         */
        public OrgBstatsBstatsVersionAccessors getBstats() {
            return vaccForOrgBstatsBstatsVersionAccessors;
        }

    }

    public static class OrgBstatsBstatsVersionAccessors extends VersionFactory  {

        public OrgBstatsBstatsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.bstats.bstats.bukkit (3.0.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getBukkit() { return getVersion("org.bstats.bstats.bukkit"); }

    }

    public static class OrgSpigotmcVersionAccessors extends VersionFactory  {

        private final OrgSpigotmcSpigotVersionAccessors vaccForOrgSpigotmcSpigotVersionAccessors = new OrgSpigotmcSpigotVersionAccessors(providers, config);
        public OrgSpigotmcVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.spigotmc.spigot
         */
        public OrgSpigotmcSpigotVersionAccessors getSpigot() {
            return vaccForOrgSpigotmcSpigotVersionAccessors;
        }

    }

    public static class OrgSpigotmcSpigotVersionAccessors extends VersionFactory  {

        public OrgSpigotmcSpigotVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.spigotmc.spigot.api (1.20.5-R0.1-SNAPSHOT)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getApi() { return getVersion("org.spigotmc.spigot.api"); }

    }

    public static class ResVersionAccessors extends VersionFactory  {

        public ResVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: res.residence (5.1.4.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getResidence() { return getVersion("res.residence"); }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

    }

}
