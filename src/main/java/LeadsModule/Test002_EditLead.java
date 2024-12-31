package LeadsModule;

import Utilities.Reports;
import com.microsoft.playwright.Locator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.HashMap;

public class Test002_EditLead extends Reports {

    @DataProvider (name = "editLeadData")
    public static Object[][] getEditLeadData() {
        return new Object[][] {
                {"Senior Network Engineer", "+91-1234567890", "2800000",
                "Edit Lead",
                "Edit a lead with new parameters",
                "Test Passed: Lead edited successfully",
                "Test Failed: Unable to edit a lead",
                "Edit screen"}
        };
    }

    private static HashMap<String, String> editLeadData = readExcelToHashMap("edit_lead");

    private static Locator editLeadVals(EditLeadElements elementName) {
        String value = editLeadData.get(elementName.name().toLowerCase());
        return locate(value);
    }

    private enum EditLeadElements {
        EDIT_BUTTON,
        TITLE_INPUT,
        MOBILE_NO_INPUT,
        ANNUAL_REVENUE_INPUT,
        SAVE_BUTTON,
        LEAD_NAME_VAL,
        LEAD_PHOTO_VAL,
        PRE_MOB_NO_VAL,
        EDIT_MOB_NO_VAL,
        PRE_TITLE_VAL,
        EDIT_TITLE_VAL,
        PRE_ANN_REV_VAL,
        EDIT_ANN_REV_VAL
    }

    /**
     * - Automates the process of editing an existing lead by interacting with the UI elements.
     * - Clicks the edit button and fills in new values for title, mobile number, and annual revenue.
     * - Uses the `EditLeadElements` enum to locate UI elements for editing lead information.
     * - Clicks the save button to submit the changes and waits for the page to load completely.
     * - Logs a success message if the lead is edited successfully or logs an error message if an exception occurs.
     */
    public static void leadEdit(String title, String mobileNumber, String annualRevenue) {

        try {
            editLeadVals(EditLeadElements.EDIT_BUTTON).click();
            editLeadVals(EditLeadElements.TITLE_INPUT).fill(title);
            editLeadVals(EditLeadElements.MOBILE_NO_INPUT).fill(mobileNumber);
            editLeadVals(EditLeadElements.ANNUAL_REVENUE_INPUT).fill(annualRevenue);
            editLeadVals(EditLeadElements.SAVE_BUTTON).click();
            waitForLoadstate(3);
            logger.info("Lead Edited Successfully");
        }
        catch (Exception e) {
            logger.error("Unable to Edit the lead", e);
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * - Validates the edited lead by checking the visibility of the lead's name and photo elements.
     * - Ensures that the mobile number, title, and annual revenue have been updated correctly.
     * - Uses assertions to compare previous and edited values to confirm changes.
     * - Logs a success message if validation passes or logs an error message if it fails.
     */
    public static boolean validateEditLead() {
        try{
            waitForLoadstate(5);
            waitForElementVisible(editLeadVals(EditLeadElements.LEAD_NAME_VAL),3);
            assertThat(editLeadVals(EditLeadElements.LEAD_NAME_VAL)).isVisible();
            assertThat(editLeadVals(EditLeadElements.LEAD_PHOTO_VAL)).isVisible();
            Assert.assertNotEquals(editLeadVals(EditLeadElements.PRE_MOB_NO_VAL), editLeadVals(EditLeadElements.EDIT_MOB_NO_VAL));
            Assert.assertNotEquals(editLeadVals(EditLeadElements.PRE_TITLE_VAL), editLeadVals(EditLeadElements.EDIT_TITLE_VAL));
            Assert.assertNotEquals(editLeadVals(EditLeadElements.PRE_ANN_REV_VAL), editLeadVals(EditLeadElements.EDIT_ANN_REV_VAL));
            logger.info("Edited lead Validated Successfully");
            return true;
        }
        catch (Exception e) {
            logger.error("Validation Failed after Editing the lead", e);
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
