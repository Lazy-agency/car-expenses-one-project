package lazy.agency.spring.carexpenses.main.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "lazy.agency.spring.carexpenses", importOptions = {ImportOption.DoNotIncludeTests.class})
public class MainArchitecturalTest {

    @ArchTest
    public void mainClassLocation(final JavaClasses importedClasses) {
        classes().that().resideInAPackage("..main..")
                .should().beAnnotatedWith(SpringBootApplication.class)
                .andShould().haveSimpleNameStartingWith("Application")
                .check(importedClasses);
    }

    @ArchTest
    public void servicesShouldOnlyBeAccessibleByControllers(final JavaClasses importedClasses) {
        classes().that().resideInAPackage("..services..")
                .should().onlyBeAccessed().byAnyPackage("..services..", "..controllers..")
                .check(importedClasses);

    }

    @ArchTest
    public void modelsShouldOnlyBeAccessibleByServicesAndRepositories(final JavaClasses importedClasses) {
        classes().that().resideInAPackage("..models..")
                .should().onlyBeAccessed().byAnyPackage("..services..", "..repositories..")
                .check(importedClasses);
    }

    @ArchTest
    public void serviceComponentsNamingConvention(final JavaClasses importedClasses) {
        classes()
                .that().resideInAPackage("..services..")
                .should().haveSimpleNameEndingWith("Service")
                .andShould().beInterfaces()
                .andShould().beAnnotatedWith(Service.class)
                .check(importedClasses);
    }

    @ArchTest
    public void serviceImplementationLocation(final JavaClasses importedClasses) {
        classes()
                .that().resideInAPackage("..impl..")
                .should().haveSimpleNameEndingWith("ServiceImpl")
                .andShould().notBeInterfaces()
                .check(importedClasses);
    }

    @ArchTest
    public void serviceImplementationCannotBeInterface(final JavaClasses importedClasses) {
        classes()
                .that().resideInAPackage("..impl..")
                .should().haveSimpleNameEndingWith("ServiceImpl")
                .check(importedClasses);
    }

    @ArchTest
    public void controllersComponentsNamingConvention(final JavaClasses importedClasses) {
        classes()
                .that().resideInAPackage("..controllers..")
                .should().haveSimpleNameEndingWith("Controller")
                .andShould().beAnnotatedWith(RestController.class)
                .check(importedClasses);
    }

    @ArchTest
    public void modelsComponentsNamingConvention(final JavaClasses importedClasses) {
        classes()
                .that().resideInAPackage("..models..")
                .should().haveSimpleNameEndingWith("Model")
                .check(importedClasses);
    }

    @ArchTest
    public void repositoryComponentsNamingConvention(final JavaClasses importedClasses) {
        classes()
                .that().resideInAPackage("..repositories..")
                .should().haveSimpleNameEndingWith("Repository")
                .andShould().beAnnotatedWith(Repository.class)
                .check(importedClasses);
    }

}
