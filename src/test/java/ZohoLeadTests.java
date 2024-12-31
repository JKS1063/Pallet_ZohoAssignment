import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;
import LeadsModule.*;
import Utilities.Reports;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static LeadsModule.Test000_ZohoLogin.*;
import static LeadsModule.Test001_CreateLead.*;
import static LeadsModule.Test002_EditLead.*;
import static LeadsModule.Test003_FilterLead.*;
import static LeadsModule.Test004_DeleteLead.*;
public class ZohoLeadTests extends Reports {

    String reportName = "Zoho CRM";
    String docTitle = "Pallet assignment";

    @BeforeTest
    public void report() { // Set up test report with report name and document title
        reports(reportName, docTitle);
    }

    @Test (dataProvider = "loginData", dataProviderClass = Test000_ZohoLogin.class, priority = 1)
    public void login(String username, String password,
                      String testName,String testInfo, String passInfo, String failInfo,
                      String screenShotName) throws Exception {
        navigate(); // Navigate to the login page
        zohoLogin(username, password); // Perform login with provided credentials
        waitForLoadstate(5); // Wait for the page to fully load
        result(loginValidations(), testName, testInfo, passInfo, failInfo, screenShotName); // Validate login and update the report
    }

    @Test (dataProvider = "createLeadData", dataProviderClass = Test001_CreateLead.class, priority = 2)
    public void createLead(String companyName, String firstName, String lastName, String title,
                           String emailAddress, String phoneNumber, String mobileNumber, String companyWebsite,
                           String annualRevenue, String skypeId, String twitterId, String streetName, String cityName,
                           String stateName, String zipCode, String countryName,
                           String testName, String testInfo, String passInfo, String failInfo,
                           String screenShotName) throws Exception {
    
        leadCreate(companyName, firstName, lastName, title,emailAddress, phoneNumber, mobileNumber, companyWebsite,
                annualRevenue,skypeId,twitterId, streetName, cityName,stateName, zipCode,countryName); // Create new lead with provided details
        page.waitForLoadState(LoadState.LOAD); // Wait for the page to fully load
        result(validateCreateLead(), testName, testInfo, passInfo, failInfo, screenShotName); // Validate lead creation and update the report
        page.waitForLoadState(LoadState.LOAD); // Wait for any additional loading
    }

    @Test (dataProvider = "editLeadData", dataProviderClass = Test002_EditLead.class, priority = 3)
    public void editLead(String title, String mobileNumber, String annualRevenue,
                         String testName, String testInfo, String passInfo,String failInfo,
                         String screenShotName) throws Exception {
        leadEdit(title, mobileNumber, annualRevenue); // Edit lead with updated details
        page.waitForLoadState(LoadState.LOAD); // Wait for the page to fully load
        result(validateEditLead(), testName, testInfo, passInfo, failInfo, screenShotName); // Validate lead editing and update the report
        page.waitForLoadState(LoadState.LOAD); // Wait for any additional loading
    }

    @Test (dataProvider = "filterLeadData", dataProviderClass = Test003_FilterLead.class, priority = 4)
    public void filterLead(String companyName, String leadName, String leadSource,
                           String testName, String testInfo, String passInfo, String failInfo,
                           String screenShotName) throws Exception {
        leadFilter(companyName, leadName, leadSource); // Apply filter on lead with given parameters
        page.waitForLoadState(LoadState.LOAD); // Wait for the page to fully load
        result(validateFilterLead(), testName, testInfo, passInfo, failInfo, screenShotName); // Validate lead filtering and update the report
        page.waitForLoadState(LoadState.LOAD); // Wait for any additional loading
    }

    @Test (dataProvider = "deleteLeadData", dataProviderClass = Test004_DeleteLead.class, priority = 5)
    public void deleteLead(String leadName,
                           String testName, String testInfo, String passInfo, String failInfo,
                           String screenShotName) throws Exception {
        leadDelete(); // Delete lead with specified lead name
        page.waitForLoadState(LoadState.LOAD); // Wait for the page to fully load
        result(validateDeleteLead(leadName), testName, testInfo, passInfo, failInfo, screenShotName); // Validate lead deletion and update the report
        page.waitForLoadState(LoadState.LOAD); // Wait for any additional loading
    }

    @AfterTest
    public void showReport() throws Exception { // Display the final test report and clean up
        quitReport();
    }
}
