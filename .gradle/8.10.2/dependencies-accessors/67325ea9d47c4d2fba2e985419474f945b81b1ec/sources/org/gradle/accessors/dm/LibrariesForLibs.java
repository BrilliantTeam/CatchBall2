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
 * A catalog of dependencies accessible via the {@code libs} extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

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
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

    /**
     * Group of libraries at <b>br</b>
     */
    public BrLibraryAccessors getBr() {
        return laccForBrLibraryAccessors;
    }

    /**
     * Group of libraries at <b>com</b>
     */
    public ComLibraryAccessors getCom() {
        return laccForComLibraryAccessors;
    }

    /**
     * Group of libraries at <b>de</b>
     */
    public DeLibraryAccessors getDe() {
        return laccForDeLibraryAccessors;
    }

    /**
     * Group of libraries at <b>io</b>
     */
    public IoLibraryAccessors getIo() {
        return laccForIoLibraryAccessors;
    }

    /**
     * Group of libraries at <b>me</b>
     */
    public MeLibraryAccessors getMe() {
        return laccForMeLibraryAccessors;
    }

    /**
     * Group of libraries at <b>org</b>
     */
    public OrgLibraryAccessors getOrg() {
        return laccForOrgLibraryAccessors;
    }

    /**
     * Group of libraries at <b>res</b>
     */
    public ResLibraryAccessors getRes() {
        return laccForResLibraryAccessors;
    }

    /**
     * Group of versions at <b>versions</b>
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Group of bundles at <b>bundles</b>
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Group of plugins at <b>plugins</b>
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class BrLibraryAccessors extends SubDependencyFactory {
        private final BrNetLibraryAccessors laccForBrNetLibraryAccessors = new BrNetLibraryAccessors(owner);

        public BrLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>br.net</b>
         */
        public BrNetLibraryAccessors getNet() {
            return laccForBrNetLibraryAccessors;
        }

    }

    public static class BrNetLibraryAccessors extends SubDependencyFactory {
        private final BrNetFabiozumbi12LibraryAccessors laccForBrNetFabiozumbi12LibraryAccessors = new BrNetFabiozumbi12LibraryAccessors(owner);

        public BrNetLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>br.net.fabiozumbi12</b>
         */
        public BrNetFabiozumbi12LibraryAccessors getFabiozumbi12() {
            return laccForBrNetFabiozumbi12LibraryAccessors;
        }

    }

    public static class BrNetFabiozumbi12LibraryAccessors extends SubDependencyFactory {
        private final BrNetFabiozumbi12RedprotectLibraryAccessors laccForBrNetFabiozumbi12RedprotectLibraryAccessors = new BrNetFabiozumbi12RedprotectLibraryAccessors(owner);

        public BrNetFabiozumbi12LibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>br.net.fabiozumbi12.redprotect</b>
         */
        public BrNetFabiozumbi12RedprotectLibraryAccessors getRedprotect() {
            return laccForBrNetFabiozumbi12RedprotectLibraryAccessors;
        }

    }

    public static class BrNetFabiozumbi12RedprotectLibraryAccessors extends SubDependencyFactory {
        private final BrNetFabiozumbi12RedprotectRedprotectLibraryAccessors laccForBrNetFabiozumbi12RedprotectRedprotectLibraryAccessors = new BrNetFabiozumbi12RedprotectRedprotectLibraryAccessors(owner);

        public BrNetFabiozumbi12RedprotectLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>br.net.fabiozumbi12.redprotect.redprotect</b>
         */
        public BrNetFabiozumbi12RedprotectRedprotectLibraryAccessors getRedprotect() {
            return laccForBrNetFabiozumbi12RedprotectRedprotectLibraryAccessors;
        }

    }

    public static class BrNetFabiozumbi12RedprotectRedprotectLibraryAccessors extends SubDependencyFactory {

        public BrNetFabiozumbi12RedprotectRedprotectLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>core</b> with <b>br.net.fabiozumbi12.RedProtect:RedProtect-Core</b> coordinates and
         * with version reference <b>br.net.fabiozumbi12.redprotect.redprotect.core</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCore() {
            return create("br.net.fabiozumbi12.redprotect.redprotect.core");
        }

        /**
         * Dependency provider for <b>spigot</b> with <b>br.net.fabiozumbi12.RedProtect:RedProtect-Spigot</b> coordinates and
         * with version reference <b>br.net.fabiozumbi12.redprotect.redprotect.spigot</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getSpigot() {
            return create("br.net.fabiozumbi12.redprotect.redprotect.spigot");
        }

    }

    public static class ComLibraryAccessors extends SubDependencyFactory {
        private final ComGithubLibraryAccessors laccForComGithubLibraryAccessors = new ComGithubLibraryAccessors(owner);
        private final ComJeffLibraryAccessors laccForComJeffLibraryAccessors = new ComJeffLibraryAccessors(owner);
        private final ComSk89qLibraryAccessors laccForComSk89qLibraryAccessors = new ComSk89qLibraryAccessors(owner);

        public ComLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.github</b>
         */
        public ComGithubLibraryAccessors getGithub() {
            return laccForComGithubLibraryAccessors;
        }

        /**
         * Group of libraries at <b>com.jeff</b>
         */
        public ComJeffLibraryAccessors getJeff() {
            return laccForComJeffLibraryAccessors;
        }

        /**
         * Group of libraries at <b>com.sk89q</b>
         */
        public ComSk89qLibraryAccessors getSk89q() {
            return laccForComSk89qLibraryAccessors;
        }

    }

    public static class ComGithubLibraryAccessors extends SubDependencyFactory {
        private final ComGithubAngeschossenLibraryAccessors laccForComGithubAngeschossenLibraryAccessors = new ComGithubAngeschossenLibraryAccessors(owner);
        private final ComGithubTechfortressLibraryAccessors laccForComGithubTechfortressLibraryAccessors = new ComGithubTechfortressLibraryAccessors(owner);
        private final ComGithubXynessLibraryAccessors laccForComGithubXynessLibraryAccessors = new ComGithubXynessLibraryAccessors(owner);

        public ComGithubLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.github.angeschossen</b>
         */
        public ComGithubAngeschossenLibraryAccessors getAngeschossen() {
            return laccForComGithubAngeschossenLibraryAccessors;
        }

        /**
         * Group of libraries at <b>com.github.techfortress</b>
         */
        public ComGithubTechfortressLibraryAccessors getTechfortress() {
            return laccForComGithubTechfortressLibraryAccessors;
        }

        /**
         * Group of libraries at <b>com.github.xyness</b>
         */
        public ComGithubXynessLibraryAccessors getXyness() {
            return laccForComGithubXynessLibraryAccessors;
        }

    }

    public static class ComGithubAngeschossenLibraryAccessors extends SubDependencyFactory {

        public ComGithubAngeschossenLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>landsapi</b> with <b>com.github.angeschossen:LandsAPI</b> coordinates and
         * with version reference <b>com.github.angeschossen.landsapi</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getLandsapi() {
            return create("com.github.angeschossen.landsapi");
        }

    }

    public static class ComGithubTechfortressLibraryAccessors extends SubDependencyFactory {

        public ComGithubTechfortressLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>griefprevention</b> with <b>com.github.TechFortress:GriefPrevention</b> coordinates and
         * with version reference <b>com.github.techfortress.griefprevention</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getGriefprevention() {
            return create("com.github.techfortress.griefprevention");
        }

    }

    public static class ComGithubXynessLibraryAccessors extends SubDependencyFactory {

        public ComGithubXynessLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>simpleclaimsystem</b> with <b>com.github.Xyness:SimpleClaimSystem</b> coordinates and
         * with version reference <b>com.github.xyness.simpleclaimsystem</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getSimpleclaimsystem() {
            return create("com.github.xyness.simpleclaimsystem");
        }

    }

    public static class ComJeffLibraryAccessors extends SubDependencyFactory {
        private final ComJeffMediaLibraryAccessors laccForComJeffMediaLibraryAccessors = new ComJeffMediaLibraryAccessors(owner);

        public ComJeffLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.jeff.media</b>
         */
        public ComJeffMediaLibraryAccessors getMedia() {
            return laccForComJeffMediaLibraryAccessors;
        }

    }

    public static class ComJeffMediaLibraryAccessors extends SubDependencyFactory {

        public ComJeffMediaLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>spigotupdatechecker</b> with <b>com.jeff_media:SpigotUpdateChecker</b> coordinates and
         * with version reference <b>com.jeff.media.spigotupdatechecker</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getSpigotupdatechecker() {
            return create("com.jeff.media.spigotupdatechecker");
        }

    }

    public static class ComSk89qLibraryAccessors extends SubDependencyFactory {
        private final ComSk89qWorldguardLibraryAccessors laccForComSk89qWorldguardLibraryAccessors = new ComSk89qWorldguardLibraryAccessors(owner);

        public ComSk89qLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.sk89q.worldguard</b>
         */
        public ComSk89qWorldguardLibraryAccessors getWorldguard() {
            return laccForComSk89qWorldguardLibraryAccessors;
        }

    }

    public static class ComSk89qWorldguardLibraryAccessors extends SubDependencyFactory {
        private final ComSk89qWorldguardWorldguardLibraryAccessors laccForComSk89qWorldguardWorldguardLibraryAccessors = new ComSk89qWorldguardWorldguardLibraryAccessors(owner);

        public ComSk89qWorldguardLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.sk89q.worldguard.worldguard</b>
         */
        public ComSk89qWorldguardWorldguardLibraryAccessors getWorldguard() {
            return laccForComSk89qWorldguardWorldguardLibraryAccessors;
        }

    }

    public static class ComSk89qWorldguardWorldguardLibraryAccessors extends SubDependencyFactory {

        public ComSk89qWorldguardWorldguardLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>bukkit</b> with <b>com.sk89q.worldguard:worldguard-bukkit</b> coordinates and
         * with version reference <b>com.sk89q.worldguard.worldguard.bukkit</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getBukkit() {
            return create("com.sk89q.worldguard.worldguard.bukkit");
        }

    }

    public static class DeLibraryAccessors extends SubDependencyFactory {
        private final DeTr7zwLibraryAccessors laccForDeTr7zwLibraryAccessors = new DeTr7zwLibraryAccessors(owner);

        public DeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>de.tr7zw</b>
         */
        public DeTr7zwLibraryAccessors getTr7zw() {
            return laccForDeTr7zwLibraryAccessors;
        }

    }

    public static class DeTr7zwLibraryAccessors extends SubDependencyFactory {
        private final DeTr7zwItemLibraryAccessors laccForDeTr7zwItemLibraryAccessors = new DeTr7zwItemLibraryAccessors(owner);

        public DeTr7zwLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>de.tr7zw.item</b>
         */
        public DeTr7zwItemLibraryAccessors getItem() {
            return laccForDeTr7zwItemLibraryAccessors;
        }

    }

    public static class DeTr7zwItemLibraryAccessors extends SubDependencyFactory {
        private final DeTr7zwItemNbtLibraryAccessors laccForDeTr7zwItemNbtLibraryAccessors = new DeTr7zwItemNbtLibraryAccessors(owner);

        public DeTr7zwItemLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>de.tr7zw.item.nbt</b>
         */
        public DeTr7zwItemNbtLibraryAccessors getNbt() {
            return laccForDeTr7zwItemNbtLibraryAccessors;
        }

    }

    public static class DeTr7zwItemNbtLibraryAccessors extends SubDependencyFactory {

        public DeTr7zwItemNbtLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>de.tr7zw:item-nbt-api</b> coordinates and
         * with version reference <b>de.tr7zw.item.nbt.api</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getApi() {
            return create("de.tr7zw.item.nbt.api");
        }

    }

    public static class IoLibraryAccessors extends SubDependencyFactory {
        private final IoLumineLibraryAccessors laccForIoLumineLibraryAccessors = new IoLumineLibraryAccessors(owner);

        public IoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>io.lumine</b>
         */
        public IoLumineLibraryAccessors getLumine() {
            return laccForIoLumineLibraryAccessors;
        }

    }

    public static class IoLumineLibraryAccessors extends SubDependencyFactory {
        private final IoLumineMythicLibraryAccessors laccForIoLumineMythicLibraryAccessors = new IoLumineMythicLibraryAccessors(owner);

        public IoLumineLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>io.lumine.mythic</b>
         */
        public IoLumineMythicLibraryAccessors getMythic() {
            return laccForIoLumineMythicLibraryAccessors;
        }

    }

    public static class IoLumineMythicLibraryAccessors extends SubDependencyFactory {

        public IoLumineMythicLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>dist</b> with <b>io.lumine:Mythic-Dist</b> coordinates and
         * with version reference <b>io.lumine.mythic.dist</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getDist() {
            return create("io.lumine.mythic.dist");
        }

    }

    public static class MeLibraryAccessors extends SubDependencyFactory {
        private final MeClipLibraryAccessors laccForMeClipLibraryAccessors = new MeClipLibraryAccessors(owner);

        public MeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>me.clip</b>
         */
        public MeClipLibraryAccessors getClip() {
            return laccForMeClipLibraryAccessors;
        }

    }

    public static class MeClipLibraryAccessors extends SubDependencyFactory {

        public MeClipLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>placeholderapi</b> with <b>me.clip:placeholderapi</b> coordinates and
         * with version reference <b>me.clip.placeholderapi</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getPlaceholderapi() {
            return create("me.clip.placeholderapi");
        }

    }

    public static class OrgLibraryAccessors extends SubDependencyFactory {
        private final OrgBstatsLibraryAccessors laccForOrgBstatsLibraryAccessors = new OrgBstatsLibraryAccessors(owner);
        private final OrgSpigotmcLibraryAccessors laccForOrgSpigotmcLibraryAccessors = new OrgSpigotmcLibraryAccessors(owner);

        public OrgLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.bstats</b>
         */
        public OrgBstatsLibraryAccessors getBstats() {
            return laccForOrgBstatsLibraryAccessors;
        }

        /**
         * Group of libraries at <b>org.spigotmc</b>
         */
        public OrgSpigotmcLibraryAccessors getSpigotmc() {
            return laccForOrgSpigotmcLibraryAccessors;
        }

    }

    public static class OrgBstatsLibraryAccessors extends SubDependencyFactory {
        private final OrgBstatsBstatsLibraryAccessors laccForOrgBstatsBstatsLibraryAccessors = new OrgBstatsBstatsLibraryAccessors(owner);

        public OrgBstatsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.bstats.bstats</b>
         */
        public OrgBstatsBstatsLibraryAccessors getBstats() {
            return laccForOrgBstatsBstatsLibraryAccessors;
        }

    }

    public static class OrgBstatsBstatsLibraryAccessors extends SubDependencyFactory {

        public OrgBstatsBstatsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>bukkit</b> with <b>org.bstats:bstats-bukkit</b> coordinates and
         * with version reference <b>org.bstats.bstats.bukkit</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getBukkit() {
            return create("org.bstats.bstats.bukkit");
        }

    }

    public static class OrgSpigotmcLibraryAccessors extends SubDependencyFactory {
        private final OrgSpigotmcSpigotLibraryAccessors laccForOrgSpigotmcSpigotLibraryAccessors = new OrgSpigotmcSpigotLibraryAccessors(owner);

        public OrgSpigotmcLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.spigotmc.spigot</b>
         */
        public OrgSpigotmcSpigotLibraryAccessors getSpigot() {
            return laccForOrgSpigotmcSpigotLibraryAccessors;
        }

    }

    public static class OrgSpigotmcSpigotLibraryAccessors extends SubDependencyFactory {

        public OrgSpigotmcSpigotLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>org.spigotmc:spigot-api</b> coordinates and
         * with version reference <b>org.spigotmc.spigot.api</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getApi() {
            return create("org.spigotmc.spigot.api");
        }

    }

    public static class ResLibraryAccessors extends SubDependencyFactory {

        public ResLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>residence</b> with <b>res:Residence</b> coordinates and
         * with version reference <b>res.residence</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getResidence() {
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
         * Group of versions at <b>versions.br</b>
         */
        public BrVersionAccessors getBr() {
            return vaccForBrVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.com</b>
         */
        public ComVersionAccessors getCom() {
            return vaccForComVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.de</b>
         */
        public DeVersionAccessors getDe() {
            return vaccForDeVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.io</b>
         */
        public IoVersionAccessors getIo() {
            return vaccForIoVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.me</b>
         */
        public MeVersionAccessors getMe() {
            return vaccForMeVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org</b>
         */
        public OrgVersionAccessors getOrg() {
            return vaccForOrgVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.res</b>
         */
        public ResVersionAccessors getRes() {
            return vaccForResVersionAccessors;
        }

    }

    public static class BrVersionAccessors extends VersionFactory  {

        private final BrNetVersionAccessors vaccForBrNetVersionAccessors = new BrNetVersionAccessors(providers, config);
        public BrVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.br.net</b>
         */
        public BrNetVersionAccessors getNet() {
            return vaccForBrNetVersionAccessors;
        }

    }

    public static class BrNetVersionAccessors extends VersionFactory  {

        private final BrNetFabiozumbi12VersionAccessors vaccForBrNetFabiozumbi12VersionAccessors = new BrNetFabiozumbi12VersionAccessors(providers, config);
        public BrNetVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.br.net.fabiozumbi12</b>
         */
        public BrNetFabiozumbi12VersionAccessors getFabiozumbi12() {
            return vaccForBrNetFabiozumbi12VersionAccessors;
        }

    }

    public static class BrNetFabiozumbi12VersionAccessors extends VersionFactory  {

        private final BrNetFabiozumbi12RedprotectVersionAccessors vaccForBrNetFabiozumbi12RedprotectVersionAccessors = new BrNetFabiozumbi12RedprotectVersionAccessors(providers, config);
        public BrNetFabiozumbi12VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.br.net.fabiozumbi12.redprotect</b>
         */
        public BrNetFabiozumbi12RedprotectVersionAccessors getRedprotect() {
            return vaccForBrNetFabiozumbi12RedprotectVersionAccessors;
        }

    }

    public static class BrNetFabiozumbi12RedprotectVersionAccessors extends VersionFactory  {

        private final BrNetFabiozumbi12RedprotectRedprotectVersionAccessors vaccForBrNetFabiozumbi12RedprotectRedprotectVersionAccessors = new BrNetFabiozumbi12RedprotectRedprotectVersionAccessors(providers, config);
        public BrNetFabiozumbi12RedprotectVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.br.net.fabiozumbi12.redprotect.redprotect</b>
         */
        public BrNetFabiozumbi12RedprotectRedprotectVersionAccessors getRedprotect() {
            return vaccForBrNetFabiozumbi12RedprotectRedprotectVersionAccessors;
        }

    }

    public static class BrNetFabiozumbi12RedprotectRedprotectVersionAccessors extends VersionFactory  {

        public BrNetFabiozumbi12RedprotectRedprotectVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>br.net.fabiozumbi12.redprotect.redprotect.core</b> with value <b>7.7.3</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getCore() { return getVersion("br.net.fabiozumbi12.redprotect.redprotect.core"); }

        /**
         * Version alias <b>br.net.fabiozumbi12.redprotect.redprotect.spigot</b> with value <b>LATEST</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
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
         * Group of versions at <b>versions.com.github</b>
         */
        public ComGithubVersionAccessors getGithub() {
            return vaccForComGithubVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.com.jeff</b>
         */
        public ComJeffVersionAccessors getJeff() {
            return vaccForComJeffVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.com.sk89q</b>
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
         * Group of versions at <b>versions.com.github.angeschossen</b>
         */
        public ComGithubAngeschossenVersionAccessors getAngeschossen() {
            return vaccForComGithubAngeschossenVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.com.github.techfortress</b>
         */
        public ComGithubTechfortressVersionAccessors getTechfortress() {
            return vaccForComGithubTechfortressVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.com.github.xyness</b>
         */
        public ComGithubXynessVersionAccessors getXyness() {
            return vaccForComGithubXynessVersionAccessors;
        }

    }

    public static class ComGithubAngeschossenVersionAccessors extends VersionFactory  {

        public ComGithubAngeschossenVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>com.github.angeschossen.landsapi</b> with value <b>7.8.5</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getLandsapi() { return getVersion("com.github.angeschossen.landsapi"); }

    }

    public static class ComGithubTechfortressVersionAccessors extends VersionFactory  {

        public ComGithubTechfortressVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>com.github.techfortress.griefprevention</b> with value <b>17.0.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getGriefprevention() { return getVersion("com.github.techfortress.griefprevention"); }

    }

    public static class ComGithubXynessVersionAccessors extends VersionFactory  {

        public ComGithubXynessVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>com.github.xyness.simpleclaimsystem</b> with value <b>1.10.0.4</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getSimpleclaimsystem() { return getVersion("com.github.xyness.simpleclaimsystem"); }

    }

    public static class ComJeffVersionAccessors extends VersionFactory  {

        private final ComJeffMediaVersionAccessors vaccForComJeffMediaVersionAccessors = new ComJeffMediaVersionAccessors(providers, config);
        public ComJeffVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.jeff.media</b>
         */
        public ComJeffMediaVersionAccessors getMedia() {
            return vaccForComJeffMediaVersionAccessors;
        }

    }

    public static class ComJeffMediaVersionAccessors extends VersionFactory  {

        public ComJeffMediaVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>com.jeff.media.spigotupdatechecker</b> with value <b>3.0.3</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getSpigotupdatechecker() { return getVersion("com.jeff.media.spigotupdatechecker"); }

    }

    public static class ComSk89qVersionAccessors extends VersionFactory  {

        private final ComSk89qWorldguardVersionAccessors vaccForComSk89qWorldguardVersionAccessors = new ComSk89qWorldguardVersionAccessors(providers, config);
        public ComSk89qVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.sk89q.worldguard</b>
         */
        public ComSk89qWorldguardVersionAccessors getWorldguard() {
            return vaccForComSk89qWorldguardVersionAccessors;
        }

    }

    public static class ComSk89qWorldguardVersionAccessors extends VersionFactory  {

        private final ComSk89qWorldguardWorldguardVersionAccessors vaccForComSk89qWorldguardWorldguardVersionAccessors = new ComSk89qWorldguardWorldguardVersionAccessors(providers, config);
        public ComSk89qWorldguardVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.sk89q.worldguard.worldguard</b>
         */
        public ComSk89qWorldguardWorldguardVersionAccessors getWorldguard() {
            return vaccForComSk89qWorldguardWorldguardVersionAccessors;
        }

    }

    public static class ComSk89qWorldguardWorldguardVersionAccessors extends VersionFactory  {

        public ComSk89qWorldguardWorldguardVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>com.sk89q.worldguard.worldguard.bukkit</b> with value <b>7.0.10</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getBukkit() { return getVersion("com.sk89q.worldguard.worldguard.bukkit"); }

    }

    public static class DeVersionAccessors extends VersionFactory  {

        private final DeTr7zwVersionAccessors vaccForDeTr7zwVersionAccessors = new DeTr7zwVersionAccessors(providers, config);
        public DeVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.de.tr7zw</b>
         */
        public DeTr7zwVersionAccessors getTr7zw() {
            return vaccForDeTr7zwVersionAccessors;
        }

    }

    public static class DeTr7zwVersionAccessors extends VersionFactory  {

        private final DeTr7zwItemVersionAccessors vaccForDeTr7zwItemVersionAccessors = new DeTr7zwItemVersionAccessors(providers, config);
        public DeTr7zwVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.de.tr7zw.item</b>
         */
        public DeTr7zwItemVersionAccessors getItem() {
            return vaccForDeTr7zwItemVersionAccessors;
        }

    }

    public static class DeTr7zwItemVersionAccessors extends VersionFactory  {

        private final DeTr7zwItemNbtVersionAccessors vaccForDeTr7zwItemNbtVersionAccessors = new DeTr7zwItemNbtVersionAccessors(providers, config);
        public DeTr7zwItemVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.de.tr7zw.item.nbt</b>
         */
        public DeTr7zwItemNbtVersionAccessors getNbt() {
            return vaccForDeTr7zwItemNbtVersionAccessors;
        }

    }

    public static class DeTr7zwItemNbtVersionAccessors extends VersionFactory  {

        public DeTr7zwItemNbtVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>de.tr7zw.item.nbt.api</b> with value <b>2.13.3-SNAPSHOT</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getApi() { return getVersion("de.tr7zw.item.nbt.api"); }

    }

    public static class IoVersionAccessors extends VersionFactory  {

        private final IoLumineVersionAccessors vaccForIoLumineVersionAccessors = new IoLumineVersionAccessors(providers, config);
        public IoVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.io.lumine</b>
         */
        public IoLumineVersionAccessors getLumine() {
            return vaccForIoLumineVersionAccessors;
        }

    }

    public static class IoLumineVersionAccessors extends VersionFactory  {

        private final IoLumineMythicVersionAccessors vaccForIoLumineMythicVersionAccessors = new IoLumineMythicVersionAccessors(providers, config);
        public IoLumineVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.io.lumine.mythic</b>
         */
        public IoLumineMythicVersionAccessors getMythic() {
            return vaccForIoLumineMythicVersionAccessors;
        }

    }

    public static class IoLumineMythicVersionAccessors extends VersionFactory  {

        public IoLumineMythicVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>io.lumine.mythic.dist</b> with value <b>5.7.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getDist() { return getVersion("io.lumine.mythic.dist"); }

    }

    public static class MeVersionAccessors extends VersionFactory  {

        private final MeClipVersionAccessors vaccForMeClipVersionAccessors = new MeClipVersionAccessors(providers, config);
        public MeVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.me.clip</b>
         */
        public MeClipVersionAccessors getClip() {
            return vaccForMeClipVersionAccessors;
        }

    }

    public static class MeClipVersionAccessors extends VersionFactory  {

        public MeClipVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>me.clip.placeholderapi</b> with value <b>2.11.6</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getPlaceholderapi() { return getVersion("me.clip.placeholderapi"); }

    }

    public static class OrgVersionAccessors extends VersionFactory  {

        private final OrgBstatsVersionAccessors vaccForOrgBstatsVersionAccessors = new OrgBstatsVersionAccessors(providers, config);
        private final OrgSpigotmcVersionAccessors vaccForOrgSpigotmcVersionAccessors = new OrgSpigotmcVersionAccessors(providers, config);
        public OrgVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.bstats</b>
         */
        public OrgBstatsVersionAccessors getBstats() {
            return vaccForOrgBstatsVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org.spigotmc</b>
         */
        public OrgSpigotmcVersionAccessors getSpigotmc() {
            return vaccForOrgSpigotmcVersionAccessors;
        }

    }

    public static class OrgBstatsVersionAccessors extends VersionFactory  {

        private final OrgBstatsBstatsVersionAccessors vaccForOrgBstatsBstatsVersionAccessors = new OrgBstatsBstatsVersionAccessors(providers, config);
        public OrgBstatsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.bstats.bstats</b>
         */
        public OrgBstatsBstatsVersionAccessors getBstats() {
            return vaccForOrgBstatsBstatsVersionAccessors;
        }

    }

    public static class OrgBstatsBstatsVersionAccessors extends VersionFactory  {

        public OrgBstatsBstatsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.bstats.bstats.bukkit</b> with value <b>3.0.3</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getBukkit() { return getVersion("org.bstats.bstats.bukkit"); }

    }

    public static class OrgSpigotmcVersionAccessors extends VersionFactory  {

        private final OrgSpigotmcSpigotVersionAccessors vaccForOrgSpigotmcSpigotVersionAccessors = new OrgSpigotmcSpigotVersionAccessors(providers, config);
        public OrgSpigotmcVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.spigotmc.spigot</b>
         */
        public OrgSpigotmcSpigotVersionAccessors getSpigot() {
            return vaccForOrgSpigotmcSpigotVersionAccessors;
        }

    }

    public static class OrgSpigotmcSpigotVersionAccessors extends VersionFactory  {

        public OrgSpigotmcSpigotVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.spigotmc.spigot.api</b> with value <b>1.20.5-R0.1-SNAPSHOT</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getApi() { return getVersion("org.spigotmc.spigot.api"); }

    }

    public static class ResVersionAccessors extends VersionFactory  {

        public ResVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>res.residence</b> with value <b>5.1.4.3</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getResidence() { return getVersion("res.residence"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

    }

}
