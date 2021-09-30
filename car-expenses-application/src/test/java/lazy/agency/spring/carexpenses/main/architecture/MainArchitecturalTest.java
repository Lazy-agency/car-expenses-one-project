package lazy.agency.spring.carexpenses.main.architecture;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

@AnalyzeClasses(packages = "lazy.agency.spring.carexpenses", importOptions = {ImportOption.DoNotIncludeTests.class})
public class MainArchitecturalTest {

    private static final String MAIN_PACKAGE = "..main..";
    private static final String MAIN_APPLICATION_NAME = "Application";
    private static final String CONSTANTS_PACKAGE = "..constants..";
    private static final String CONSTANTS_SUFFIX = "Constants";
    private static final String UTILS_PACKAGE = "..utils..";
    private static final String UTIL_SUFFIX = "Util";
    private static final String SERVICES_PACKAGE = "..services..";
    private static final String CONTROLLERS_PACKAGE = "..controllers..";
    private static final String IMPLEMENTATION_PACKAGE = "..service.impl..";
    private static final String MODELS_PACKAGE = "..models..";
    private static final String REPOSITORIES_PACKAGE = "..repositories..";
    private static final String SERVICES_SUFFIX = "Service";
    private static final String SERVICE_IMPL_SUFFIX = "ServiceImpl";
    private static final String CONTROLLER_SUFFIX = "Controller";
    private static final String MODEL_SUFFIX = "Model";
    private static final String REPOSITORY_SUFFIX = "Repository";

    @ArchTest
    public void mainClassLocationAndMethodSignature(final JavaClasses importedClasses) {
        classes().that().resideInAPackage(MAIN_PACKAGE)
                .should().beAnnotatedWith(SpringBootApplication.class)
                .andShould().haveSimpleNameStartingWith(MAIN_APPLICATION_NAME)
                .because("Main application should be located in main package, should be annotated with @SpringBootApplication and should be named Application")
                .check(importedClasses);

        methods().that()
                .areDeclaredInClassesThat()
                .resideInAPackage(MAIN_PACKAGE)
                .should().bePublic()
                .andShould().beStatic()
                .andShould().haveNameMatching("main")
                .because("Methods declared in Main class should be public, static and the name of the main function should be main")
                .check(importedClasses);

    }

    @ArchTest
    public void locationOfConstants(final JavaClasses importedClasses) {
        classes().that().resideInAPackage(CONSTANTS_PACKAGE)
                .should().haveSimpleNameEndingWith(CONSTANTS_SUFFIX)
                .andShould().haveOnlyFinalFields()
                .andShould().haveOnlyPrivateConstructors()
                .because("Constant classes should only be placed in constants package.")
                .check(importedClasses);

    }

    @ArchTest
    public void constantFieldSignature(final JavaClasses importedClasses) {
        fields().that()
                .areDeclaredInClassesThat()
                .resideInAPackage(CONSTANTS_PACKAGE)
                .should().beFinal()
                .andShould().beStatic()
                .andShould().bePublic()
                .orShould().bePrivate()
                .because("Constant field should be public or private static and final.")
                .check(importedClasses);
    }

    @ArchTest
    public void locationOfUtils(final JavaClasses importedClasses) {
        classes().that().resideInAPackage(UTILS_PACKAGE)
                .should().haveSimpleNameEndingWith(UTIL_SUFFIX)
                .because("Util classes should only be placed in utils package.")
                .check(importedClasses);
    }

    @ArchTest
    public void methodTypesInUtils(final JavaClasses importedClasses) {
        methods().that()
                .areDeclaredInClassesThat()
                .resideInAPackage(UTILS_PACKAGE)
                .should().beStatic()
                .andShould().bePublic()
                .orShould().bePrivate()
                .because("Methods in util classes should be public or private and static.")
                .check(importedClasses);
    }

    @ArchTest
    public void servicesShouldOnlyBeAccessibleByControllers(final JavaClasses importedClasses) {
        classes().that()
                .resideInAPackage(SERVICES_PACKAGE)
                .should().onlyBeAccessed()
                .byAnyPackage(SERVICES_PACKAGE, CONTROLLERS_PACKAGE)
                .because("Service classes should only be accessed by the service or controller.")
                .check(importedClasses);

    }

    @ArchTest
    public void servicesShouldNotBeInImplementationPackage(final JavaClasses importedClasses) {
        noClasses().that()
                .resideInAPackage(IMPLEMENTATION_PACKAGE)
                .should().beInterfaces()
                .because("Service implementation shouldn't be an interface.")
                .check(importedClasses);
    }

    @ArchTest
    public void modelsShouldOnlyBeAccessibleByServicesAndRepositories(final JavaClasses importedClasses) {
        classes().that()
                .resideInAPackage(MODELS_PACKAGE)
                .should().onlyBeAccessed()
                .byAnyPackage(SERVICES_PACKAGE, REPOSITORIES_PACKAGE)
                .because("Model classes should only be accessed by the services or repositories.")
                .check(importedClasses);
    }

    @ArchTest
    public void serviceComponentsNamingConvention(final JavaClasses importedClasses) {
        classes()
                .that().resideInAPackage(SERVICES_PACKAGE)
                .should().haveSimpleNameEndingWith(SERVICES_SUFFIX)
                .andShould().beInterfaces()
                .andShould().beAnnotatedWith(Service.class)
                .because("Service classes should only be placed in services package and should be an interface and annotated with @Service")
                .check(importedClasses);
    }

    @ArchTest
    public void serviceImplementationLocation(final JavaClasses importedClasses) {
        classes()
                .that().resideInAPackage(IMPLEMENTATION_PACKAGE)
                .should().haveSimpleNameEndingWith(SERVICE_IMPL_SUFFIX)
                .andShould().notBeInterfaces()
                .andShould().implement(JavaClass.Predicates.resideInAPackage(SERVICES_PACKAGE))
                .because("Service implementation should only be placed in impl package, have a suffix ServiceImpl, should not be an interface, and should implement a service interface")
                .check(importedClasses);
    }

    @ArchTest
    public void controllersComponentsNamingConvention(final JavaClasses importedClasses) {
        classes()
                .that().resideInAPackage(CONTROLLERS_PACKAGE)
                .should().haveSimpleNameEndingWith(CONTROLLER_SUFFIX)
                .andShould().beAnnotatedWith(RestController.class)
                .because("Controller classes should only be placed in controllers package, should have a suffix Controller and be annotated with @RestController")
                .check(importedClasses);
    }

    @ArchTest
    public void modelsComponentsNamingConvention(final JavaClasses importedClasses) {
        classes()
                .that().resideInAPackage(MODELS_PACKAGE)
                .should().haveSimpleNameEndingWith(MODEL_SUFFIX)
                .because("Classes in models package should have Model suffix.")
                .check(importedClasses);
    }

    @ArchTest
    public void repositoryComponentsNamingConvention(final JavaClasses importedClasses) {
        classes()
                .that().resideInAPackage(REPOSITORIES_PACKAGE)
                .should().haveSimpleNameEndingWith(REPOSITORY_SUFFIX)
                .andShould().beAnnotatedWith(Repository.class)
                .andShould().beInterfaces()
                .because("Classes that reside in repositories package should have suffix Repository, annotated with @Repository, and should be an interface")
                .check(importedClasses);
    }

    @ArchTest
    public void classVerificationInIndividualPackage(final JavaClasses importedClasses) {
        classes()
                .that()
                .haveSimpleNameEndingWith(MODEL_SUFFIX)
                .should()
                .resideInAnyPackage(MODELS_PACKAGE)
                .because("Model classes should only be placed in models package.")
                .check(importedClasses);
        classes()
                .that()
                .haveSimpleNameEndingWith(SERVICES_SUFFIX)
                .should()
                .resideInAnyPackage(SERVICES_PACKAGE)
                .because("Service classes should only be placed in services package.")
                .check(importedClasses);
        classes()
                .that()
                .haveSimpleNameEndingWith(CONTROLLER_SUFFIX)
                .should()
                .resideInAnyPackage(CONTROLLERS_PACKAGE)
                .because("Controller classes should only be placed in controllers package.")
                .check(importedClasses);
        classes()
                .that()
                .haveSimpleNameEndingWith(REPOSITORY_SUFFIX)
                .should()
                .resideInAnyPackage(REPOSITORIES_PACKAGE)
                .because("Repository classes should only be placed in repositories package.")
                .check(importedClasses);
        classes()
                .that()
                .haveSimpleNameEndingWith(UTIL_SUFFIX)
                .should()
                .resideInAnyPackage(UTILS_PACKAGE)
                .because("Util classes should only be placed in utils package.")
                .check(importedClasses);
        classes()
                .that()
                .haveSimpleNameEndingWith(CONSTANTS_SUFFIX)
                .should()
                .resideInAnyPackage(CONSTANTS_PACKAGE)
                .because("Constant classes should only be placed in constants package.")
                .check(importedClasses);

    }
}
