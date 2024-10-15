//
//import com.tngtech.archunit.core.domain.JavaClasses;
//import com.tngtech.archunit.core.importer.ClassFileImporter;
//import com.tngtech.archunit.core.importer.ImportOption;
//import com.tngtech.archunit.junit.AnalyzeClasses;
//import com.tngtech.archunit.junit.ArchTest;
//import com.tngtech.archunit.lang.ArchRule;
//import com.tngtech.archunit.library.Architectures;
//import org.junit.jupiter.api.Test;
//
//import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
//import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
//
//@AnalyzeClasses(packages = "be.kdg.prog6", importOptions = ImportOption.DoNotIncludeTests.class)
//public class ArchitectureTest {
//
//    private static final String DOMAIN_LAYER = "be.kdg.prog6.landSideBoundedContext.domain..";
//    private static final String ADAPTER_LAYER = "be.kdg.prog6.landSideBoundedContext.adapters..";
//    private static final String CORE_LAYER = "be.kdg.prog6.landSideBoundedContext.core..";
//    private static final String PORT_LAYER = "be.kdg.prog6.landSideBoundedContext.port..";
//
//    @ArchTest
//    static final ArchRule domainShouldNotDependOnAnyOtherLayerRule =
//            noClasses().that().resideInAPackage(DOMAIN_LAYER)
//                    .should().dependOnClassesThat().resideInAnyPackage(
//                            ADAPTER_LAYER,
//                            PORT_LAYER,
//                            CORE_LAYER
//                    )
//                    .because("This conflicts with hexagonal architecture: Domain should not depend on other layers.");
//
//    @ArchTest
//    static final ArchRule repositoriesShouldBeInAdapterLayer =
//            noClasses().that().haveSimpleNameEndingWith("Repository")
//                    .should().resideOutsideOfPackage(ADAPTER_LAYER)
//                    .because("Repository classes should only reside in the adapter layer.");
//
//
//    @ArchTest
//    static final ArchRule adaptersShouldDependOnlyOnPortsAndExternalLibs =
//            noClasses().that().resideInAPackage(ADAPTER_LAYER)
//                    .should().dependOnClassesThat().resideInAnyPackage(DOMAIN_LAYER, CORE_LAYER)
//                    .because("Adapters should interact with the domain and core through ports only, not directly.");
//
//
//    @Test
//    void givenApplicationClasses_thenNoLayerViolationsShouldExist() {
//        JavaClasses jc = new ClassFileImporter().importPackages("be.kdg.prog6.landSideBoundedContext");
//
//        final Architectures.LayeredArchitecture arch = layeredArchitecture().consideringOnlyDependenciesInLayers()
//                .layer("ADAPTERS").definedBy(ADAPTER_LAYER)
//                .layer("PORTS").definedBy(PORT_LAYER)
//                .layer("CORE").definedBy(CORE_LAYER)
//                .whereLayer("ADAPTERS").mayNotBeAccessedByAnyLayer()
//                .whereLayer("PORTS").mayOnlyBeAccessedByLayers("ADAPTERS", "CORE");
//
//        arch.check(jc);
//    }
//
//}
