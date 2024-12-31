package LeadsModule;

import Utilities.Reports;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.annotations.DataProvider;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.HashMap;

public class Test001_CreateLead extends Reports {

    @DataProvider (name = "createLeadData")
    public static Object[][] getCreateLeadData() {
        return new Object[][]{
                {"Strong Force", "Hari", "Hara", "Network Engineer", "harihara@email.com",
                "0877-1234567890", "+91-12345690", "https://www.strongforce.com", "1000000",
                "harahara", "harihara", "Mada Veedhi", "Tirumala", "Andhra Pradesh", "517504", "India",
                "Create Lead test",
                "Create a new lead in the leads module",
                "Test passed: Lead created successfully",
                "Test failed: Unable to create a lead",
                "Lead screen"}
        };
    }

    private static HashMap<String, String> createLeadData = readExcelToHashMap("create_lead");

    private static Locator createLeadVals(CreateLeadElements elementName) {
        String value = createLeadData.get(elementName.name().toLowerCase());
        return locate(value);
    }

    private enum CreateLeadElements {
        LEADS_MODULE,
        CREATE_LEAD_BUTTON,
        COMPANY_NAME_INPUT,
        FIRST_NAME_DROPDOWN,
        FIRST_NAME_SALUTATION,
        FIRST_NAME_INPUT,
        LAST_NAME_INPUT,
        TITLE_INPUT,
        EMAIL_INPUT,
        PHONE_NO_INPUT,
        MOBILE_NO_INPUT,
        COMPANY_WEBSITE_INPUT,
        LEAD_SOURCE_DROPDOWN,
        LEAD_SOURCE_SELECT,
        INDUSTRY_DROPDOWN,
        INDUSTRY_SELECT,
        ANNUAL_REVENUE_INPUT,
        SKYPE_INPUT,
        TWITTER_INPUT,
        STREET_INPUT,
        CITY_INPUT,
        STATE_INPUT,
        ZIPCODE_INPUT,
        COUNTRY_INPUT,
        SAVE_BUTTON,
        LEAD_NAME_VAL,
        LEAD_PHOTO_VAL
    }


    /**
     * - Automates the process of creating a new lead in the application by navigating to the leads module.
     * - Fills in required fields such as company details, contact information, and address using provided parameters.
     * - Utilizes the `CreateLeadElements` enum to locate UI elements for filling in lead information.
     * - Clicks the save button to submit the lead details and create the lead in the system.
     * - Logs the operation status, indicating success or failure, in the application logs.
     */
    public static void leadCreate(String companyName, String firstName, String lastName, String title,
                                  String emailAddress, String phoneNumber, String mobileNumber, String companyWebsite,
                                  String annualRevenue, String skypeId, String twitterId, String streetName, String cityName,
                                  String stateName, String zipCode, String countryName) throws Exception{
        
        try {
            createLeadVals(CreateLeadElements.LEADS_MODULE).click(); // click on the lead module
            waitForElementVisible( createLeadVals(CreateLeadElements.CREATE_LEAD_BUTTON), 3); 
            page.waitForLoadState(LoadState.LOAD);
            createLeadVals(CreateLeadElements.CREATE_LEAD_BUTTON).click();
            waitForElementVisible(createLeadVals(CreateLeadElements.COMPANY_NAME_INPUT), 3);
            page.waitForLoadState(LoadState.LOAD);
            createLeadVals(CreateLeadElements.COMPANY_NAME_INPUT).fill(companyName);
            createLeadVals(CreateLeadElements.FIRST_NAME_DROPDOWN).click();
            createLeadVals(CreateLeadElements.FIRST_NAME_SALUTATION).click();
            createLeadVals(CreateLeadElements.FIRST_NAME_INPUT).fill(firstName);
            createLeadVals(CreateLeadElements.LAST_NAME_INPUT).fill(lastName);
            createLeadVals(CreateLeadElements.TITLE_INPUT).fill(title);
            createLeadVals(CreateLeadElements.EMAIL_INPUT).fill(emailAddress);
            createLeadVals(CreateLeadElements.PHONE_NO_INPUT).fill(phoneNumber);
            createLeadVals(CreateLeadElements.MOBILE_NO_INPUT).fill(mobileNumber);
            createLeadVals(CreateLeadElements.COMPANY_WEBSITE_INPUT).fill(companyWebsite);
            createLeadVals(CreateLeadElements.LEAD_SOURCE_DROPDOWN).click();
            createLeadVals(CreateLeadElements.LEAD_SOURCE_SELECT).click();
            createLeadVals(CreateLeadElements.INDUSTRY_DROPDOWN).click();
            createLeadVals(CreateLeadElements.INDUSTRY_SELECT).click();
            createLeadVals(CreateLeadElements.ANNUAL_REVENUE_INPUT).fill(annualRevenue);
            createLeadVals(CreateLeadElements.SKYPE_INPUT).fill(skypeId);
            page.keyboard().press("PageDown");
            waitForElementVisible(createLeadVals(CreateLeadElements.TWITTER_INPUT), 3);
            createLeadVals(CreateLeadElements.TWITTER_INPUT).fill(twitterId);
            createLeadVals(CreateLeadElements.STREET_INPUT).fill(streetName);
            createLeadVals(CreateLeadElements.CITY_INPUT).fill(cityName);
            createLeadVals(CreateLeadElements.STATE_INPUT).fill(stateName);
            createLeadVals(CreateLeadElements.ZIPCODE_INPUT).fill(zipCode);
            createLeadVals(CreateLeadElements.COUNTRY_INPUT).fill(countryName);
            createLeadVals(CreateLeadElements.SAVE_BUTTON).click();
            logger.info("Lead Created Successfully");
        }
        catch (Exception e) {
            logger.info("Unable to create a lead");
            logger.info(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * - Checks the visibility of key elements to confirm successful lead creation.
     * - Uses assertions to ensure the lead's name and photo are visible on the page.
     * - Logs success if validation passes or logs an error with details if it fails.
     */
    public static boolean validateCreateLead() {
        try{
            waitForLoadstate(5);
            createLeadVals(CreateLeadElements.LEAD_NAME_VAL).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            assertThat(createLeadVals(CreateLeadElements.LEAD_NAME_VAL)).isVisible();
            assertThat(createLeadVals(CreateLeadElements.LEAD_PHOTO_VAL)).isVisible();
            logger.info("Created Lead Validated Successfully");
            return true;
        } catch (Exception e) {
            logger.error("Validation Failed after Creating the lead", e);
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
