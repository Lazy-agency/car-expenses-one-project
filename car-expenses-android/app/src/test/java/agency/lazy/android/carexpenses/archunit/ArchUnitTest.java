package agency.lazy.android.carexpenses.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

@AnalyzeClasses(packages = {ArchUnitTest.SEARCHABLE_PACKAGE_PATH, ArchUnitTest.SEARCHABLE_PACKAGE_CORE_PATH}, importOptions = {ImportOption.DoNotIncludeTests.class, ImportOption.DoNotIncludeJars.class})
public class ArchUnitTest {

    static final String SEARCHABLE_PACKAGE_PATH = "agency.lazy.android.carexpenses";
    static final String SEARCHABLE_PACKAGE_CORE_PATH = "agency.lazy.android.core.carexpenses";
    private static final String MODELS_PACKAGE = "..models..";
    private static final String MODEL_SUFFIX = "Model";
    private static final String ACTIVITIES_PACKAGE = "..activities..";
    private static final String ACTIVITY_SUFFIX = "Activity";
    private static final String FRAGMENTS_PACKAGE = "..fragments..";
    private static final String FRAGMENT_SUFFIX = "Fragments";
    private static final String VIEWMODEL_PACKAGE = "..fragments.viewmodels..";
    private static final String VIEWMODEL_SUFFIX = "ViewModel";
    private static final String UTILS_PACKAGE = "..utils..";
    private static final String UTIL_SUFFIX = "Util";
    private static final String CONSTANTS_PACKAGE = "..constants..";
    private static final String CONSTANTS_SUFFIX = "Constant";


    //region Arch Units for Models

    @ArchTest
    public void modelsShouldHaveModelSuffix(final JavaClasses importedClasses){
        classes()
                .that()
                .resideInAPackage(MODELS_PACKAGE)
                .should()
                .haveSimpleNameEndingWith(MODEL_SUFFIX)
                .because("Model classes should have suffix Model")
                .check(importedClasses);
    }

    @ArchTest
    public void modelsShouldBeInModelsPackage(final JavaClasses importedClasses){
        classes()
                .that()
                .haveSimpleNameEndingWith(MODEL_SUFFIX)
                .should()
                .resideInAPackage(MODELS_PACKAGE)
                .because("Model classes should be placed in models package")
                .check(importedClasses);
    }

    @ArchTest
    public void modelsShouldHavePublicConstructorAndGetters(final JavaClasses importClasses){
        methods()
                .that()
                .areDeclaredInClassesThat()
                .resideInAPackage(MODELS_PACKAGE)
                .should()
                .bePublic()
                .andShould()
                .notBeStatic()
                .because("Methods in models should be public, and should not be static!")
                .check(importClasses);

    }

    @ArchTest
    public void modelsShouldHavePrivateOrStaticFinalFields(final JavaClasses importClasses){
        fields()
                .that()
                .areDeclaredInClassesThat()
                .resideInAPackage(MODELS_PACKAGE)
                .should()
                .bePrivate()
                .orShould()
                .beStatic()
                .andShould()
                .beFinal()
                .because("Fields in models should be private or static final.")
                .check(importClasses);

    }

    //endregion

    //region Arch Units for Activities
    @ArchTest
    public void activitiesShouldBePlacedInActivitiesPackage(final JavaClasses importedClasses){
        classes()
                .that()
                .resideInAPackage(ACTIVITIES_PACKAGE)
                .should()
                .haveSimpleNameEndingWith(ACTIVITY_SUFFIX)
                .because("Activity classes should be placed in activities package.")
                .check(importedClasses);
    }
    //endregion

    //region Arch Units for Fragments
    @ArchTest
    public void fragmentsShouldBePlacedInFragmentsPackage(final JavaClasses importedClasses){
        classes()
                .that()
                .resideInAPackage(FRAGMENTS_PACKAGE)
                .should()
                .haveSimpleNameEndingWith(FRAGMENT_SUFFIX)
                .because("Fragment classes should be placed in fragments package.")
                .check(importedClasses);
    }
    //endregion

    //region Arch Units for ViewModels
    @ArchTest
    public void viewModelsShouldBePlacedInViewModelsPackage(final JavaClasses importedClasses){
        classes()
                .that()
                .resideInAPackage(VIEWMODEL_PACKAGE)
                .should()
                .haveSimpleNameEndingWith(VIEWMODEL_SUFFIX)
                .because("ViewModel classes should be placed in fragments.viewmodels package.")
                .check(importedClasses);

        classes()
                .that()
                .haveSimpleNameEndingWith(VIEWMODEL_SUFFIX)
                .should()
                .resideInAPackage(VIEWMODEL_PACKAGE)
                .because("ViewModel classes should be placed in fragments.viewmodels package.")
                .check(importedClasses);
    }
    //endregion

    //region Arch Units for Utils
    @ArchTest
    public void utilsShouldBeInUtilsPackageAndHaveUtilSuffix(final JavaClasses importedClasses){
        classes()
                .that()
                .haveSimpleNameEndingWith(UTIL_SUFFIX)
                .should()
                .resideInAPackage(UTILS_PACKAGE)
                .because("Util classes should be placed in utils package.")
                .check(importedClasses);

        classes()
                .that()
                .resideInAPackage(UTILS_PACKAGE)
                .should()
                .haveSimpleNameEndingWith(UTIL_SUFFIX)
                .because("Util classes should have suffix Util")
                .check(importedClasses);
    }
    //endregion

    //region Arch Units for Constants
    @ArchTest
    public void constantClassesShouldHaveConstantSuffix(final JavaClasses importedClasses){
        classes()
                .that()
                .resideInAPackage(CONSTANTS_PACKAGE)
                .should()
                .haveSimpleNameEndingWith(CONSTANTS_SUFFIX)
                .because("Constant classes should have Constant suffix.")
                .check(importedClasses);
    }

    @ArchTest
    public void constantClassesShouldResignInConstantsPackage(final JavaClasses importedClasses){
        classes()
                .that()
                .haveSimpleNameEndingWith(CONSTANTS_SUFFIX)
                .should()
                .resideInAPackage(CONSTANTS_PACKAGE)
                .because("Constant classes should be placed in constants package.")
                .check(importedClasses);
    }
    //endregion

}
