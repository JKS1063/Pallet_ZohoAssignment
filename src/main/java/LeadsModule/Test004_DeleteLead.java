package LeadsModule;

import static Utilities.Reports.*;
import com.microsoft.playwright.Locator;
import org.testng.annotations.DataProvider;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.HashMap;

public class Test004_DeleteLead {

    @DataProvider (name = "deleteLeadData")
    public static Object[][] getDeleteLeadData() {
        return new Object[][] {
                {"Hari Hara",
                "Delete Lead test",
                "Delete the lead with the given name",
                "Test Passed: Lead deleted successfully",
                "Test Failed: Unable to delete the lead",
                "Delete screen"}
        };
    }

    private static HashMap<String, String> deleteLeadData = readExcelToHashMap("delete_lead");

    private static Locator deleteLeadVals(DeleteLeadElements elementName) {
        String value = deleteLeadData.get(elementName.name().toLowerCase());
        return locate(value);
    }

    private enum DeleteLeadElements {
        SELECT_LEAD,
        OPTIONS_DRPDWN,
        SELECT_DELETE,
        POPUP_DELETE_BTN,
        LEAD_NAME_CHKBX,
        LEAD_NAME_INP,
        APPLY_FILTER_BTN,
        TOTAL_RECORDS_VAL,
        CLEAR_FILTER_BTN
    }

    /**
     * - Automates the deletion of a lead by interacting with UI elements in the leads module.
     * - Clicks on the lead, opens the options dropdown, and selects the delete option.
     * - Confirms the deletion by clicking the popup delete button and waits for the page to load.
     * - Logs a success message if the lead is deleted successfully or logs an error message if an exception occurs.
     */
    public static void leadDelete() {

        try{
            deleteLeadVals(DeleteLeadElements.SELECT_LEAD).click(); //
            page.waitForLoadState();
            deleteLeadVals(DeleteLeadElements.OPTIONS_DRPDWN).click();
            deleteLeadVals(DeleteLeadElements.SELECT_DELETE).click();
            deleteLeadVals(DeleteLeadElements.POPUP_DELETE_BTN).click();
            page.waitForLoadState();
            logger.info("Lead Deleted Successfully");
        } catch (Exception e) {
            logger.error("Unable to delete lead", e);
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * - Validates the deletion of a lead by applying filters and checking the absence of the lead.
     * - Clears existing filters and waits for the lead name checkbox to become visible.
     * - Fills in the lead name, applies the filter, and verifies that the lead is not visible.
     * - Logs a success message if validation passes or logs an error message if it fails.
     */
    public static boolean validateDeleteLead(String leadName) {

        try{
            deleteLeadVals(DeleteLeadElements.CLEAR_FILTER_BTN).click();
            waitForElementVisible(deleteLeadVals(DeleteLeadElements.LEAD_NAME_CHKBX), 3);
            page.waitForLoadState();
            deleteLeadVals(DeleteLeadElements.LEAD_NAME_CHKBX).click();
            page.waitForLoadState();
            deleteLeadVals(DeleteLeadElements.LEAD_NAME_INP).fill(leadName);
            deleteLeadVals(DeleteLeadElements.APPLY_FILTER_BTN).click();
            page.waitForLoadState();
            assertThat(deleteLeadVals(DeleteLeadElements.TOTAL_RECORDS_VAL)).isVisible();
            assertThat(deleteLeadVals(DeleteLeadElements.SELECT_LEAD)).not().isVisible();
            logger.info("Deleted lead validated successfully");
            return true;
        }
        catch (Exception e) {
            logger.error("Validation Failed after deleting the lead", e);
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
 }
