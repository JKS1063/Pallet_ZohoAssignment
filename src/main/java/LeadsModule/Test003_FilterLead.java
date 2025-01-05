package LeadsModule;

import static Utilities.Reports.*;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.DataProvider;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.HashMap;

public class Test003_FilterLead {
    @DataProvider(name = "filterLeadData")
    public static Object[][] getFilterLeadData() {
        return new Object[][] {
                {"Strong Force", "Hari Hara", "Web Download",
                "Filter Lead test",
                "Filter the leads based on the given parameters",
                "Test Passed: Leads filtered successfully",
                "Test Failed: Unable to filter the leads",
                "Filter screen"}
        };
    }

    private static HashMap<String, String> filterLeadData = readExcelToHashMap("filter_lead");

    private static Locator filterLeadVals(FilterLeadElements elementName) {
        String value = filterLeadData.get(elementName.name().toLowerCase());
        return locate(value);
    }

    private enum FilterLeadElements {
        LEADS_MODULE,
        FILTER_SEARCH_INP,
        COMPANY_CHKBX,
        COMPANY_NAME_INP,
        LEAD_NAME_CHKBX,
        LEAD_NAME_INP,
        LEAD_SOURCE_CHKBX,
        LEAD_SOURCE_DRPDWN,
        LEAD_SOURCE_INP,
        LEAD_SOURCE_SELECT,
        APPLY_FILTER_BTN,
        TOTAL_RECORDS_VAL,
        COMPANY_CHKBX_VAL,
        LEAD_NAME_CHKBX_VAL,
        LEAD_SOURCE_CHKBX_VAL,
        NAME_VAL,
        CLEAR_FILTER_BTN
    }

    /**
     * - Automates the filtering of leads by interacting with UI elements in the leads module.
     * - Applies filters based on company name, lead name, and lead source using the `FilterLeadElements` enum.
     * - Utilizes Playwright's `Locator` to interact with elements like checkboxes, input fields, and buttons.
     * - Waits for page load states and element visibility to ensure actions are completed successfully.
     * - Logs a success message if the leads are filtered successfully or logs an error message with details if an exception occurs.
     * - Handles exceptions by logging error details and stack trace for troubleshooting.
     */
    public static void leadFilter(String companyName, String leadName, String leadSource) {

        try{
            filterLeadVals(FilterLeadElements.LEADS_MODULE).click();
            waitForLoadstate(3);
            filterLeadVals(FilterLeadElements.FILTER_SEARCH_INP).fill("company");
            waitForLoadstate(3);
            filterLeadVals(FilterLeadElements.COMPANY_CHKBX).click();
            filterLeadVals(FilterLeadElements.COMPANY_NAME_INP).fill(companyName);
            page.waitForLoadState();
            filterLeadVals(FilterLeadElements.APPLY_FILTER_BTN).click();
            page.waitForLoadState();
            filterLeadVals(FilterLeadElements.FILTER_SEARCH_INP).click();
            page.waitForLoadState();
            filterLeadVals(FilterLeadElements.FILTER_SEARCH_INP).clear();
            waitForElementVisible(filterLeadVals(FilterLeadElements.FILTER_SEARCH_INP), 3);
            page.waitForLoadState();
            filterLeadVals(FilterLeadElements.FILTER_SEARCH_INP).fill("lead name");
            page.waitForLoadState();
            filterLeadVals(FilterLeadElements.LEAD_NAME_CHKBX).click();
            waitForLoadstate(3);
            filterLeadVals(FilterLeadElements.LEAD_NAME_INP).fill(leadName);
            page.waitForLoadState();
            filterLeadVals(FilterLeadElements.APPLY_FILTER_BTN).click();
            page.waitForLoadState();
            filterLeadVals(FilterLeadElements.FILTER_SEARCH_INP).click();
            page.waitForLoadState(LoadState.LOAD);
            filterLeadVals(FilterLeadElements.FILTER_SEARCH_INP).clear();
            waitForElementVisible(filterLeadVals(FilterLeadElements.FILTER_SEARCH_INP), 3);
            page.waitForLoadState();
            filterLeadVals(FilterLeadElements.FILTER_SEARCH_INP).fill("lead source");
            page.waitForLoadState(LoadState.LOAD);
            filterLeadVals(FilterLeadElements.LEAD_SOURCE_CHKBX).click();
            page.waitForLoadState(LoadState.LOAD);
            filterLeadVals(FilterLeadElements.LEAD_SOURCE_DRPDWN).click();
            page.keyboard().press("Enter");
            page.waitForLoadState(LoadState.LOAD);
            filterLeadVals(FilterLeadElements.LEAD_SOURCE_INP).fill("Web Download");
            page.waitForLoadState(LoadState.LOAD);
            filterLeadVals(FilterLeadElements.LEAD_SOURCE_SELECT).click();
            page.waitForLoadState();
            filterLeadVals(FilterLeadElements.APPLY_FILTER_BTN).click();
            page.waitForLoadState();
            filterLeadVals(FilterLeadElements.FILTER_SEARCH_INP).clear();
            logger.info("Lead Filtered Successfully");
        }
        catch (Exception e) {
            logger.error("Unable to filter lead", e);
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * - Validates the filtering of leads by checking the state of filter checkboxes and visibility of results.
     * - Ensures that the company, lead name, and lead source filters are applied correctly.
     * - Uses assertions to verify that the total records and lead name elements are visible.
     * - Logs a success message if validation passes or logs an error message with details if it fails.
     */
    public static boolean validateFilterLead() {
        try{
            assertThat(filterLeadVals(FilterLeadElements.COMPANY_CHKBX_VAL)).isChecked();
            assertThat(filterLeadVals(FilterLeadElements.LEAD_NAME_CHKBX_VAL)).isChecked();
            assertThat(filterLeadVals(FilterLeadElements.LEAD_SOURCE_CHKBX_VAL)).isChecked();
            assertThat(filterLeadVals(FilterLeadElements.TOTAL_RECORDS_VAL)).isVisible();
            assertThat(filterLeadVals(FilterLeadElements.NAME_VAL)).isVisible();
            page.waitForLoadState();
            logger.info("Filtered lead validated successfully");
            return true;
        }
        catch (Exception e) {
            logger.error("Validation Failed after filtering the lead", e);
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
