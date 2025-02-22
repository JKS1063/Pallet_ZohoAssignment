package LeadsModule;

import com.microsoft.playwright.Locator;
import org.testng.annotations.DataProvider;

import java.util.HashMap;

import static Utilities.Drivers.logger;
import static Utilities.Drivers.page;
import static Utilities.Utils.locate;
import static Utilities.Utils.readExcelToHashMap;

public class Test005_ConvertLead {

    @DataProvider(name = "convertLeadData")
    public static Object[][] getDeleteLeadData() {
        return new Object[][]{
                {"Hari Hara",
                        "Convert Lead test",
                        "Convert the selected lead",
                        "Test Passed: Lead converted successfully",
                        "Test Failed: Unable to convert the lead",
                        "Delete screen"}
        };

    }

    private static HashMap<String, String> convertLeadData = readExcelToHashMap("lead_convert");

    private enum ConvertLeadElements {
        LEAD_NAME,
        CONVERT_BUTTON,
        NEWDEAL_CHECKBOX,
        AMOUNT_INPUT,
        DATE_INPUT,
        CONVERT_BUTTON2,
        LEADS_MODULE,
        FILTER_SEARCH,
        LEAD_NAME_CHKBX,
        LEAD_NAME_INP,
        APPLY_FILTER_BUTTON
        }

    private static Locator convertLeadVals(Test005_ConvertLead.ConvertLeadElements elementName) {
        String value = convertLeadData.get(elementName.name().toLowerCase());
        return locate(value);
    }

    public static void leadConvert() {

        try{
            convertLeadVals(ConvertLeadElements.LEAD_NAME).click(); //
            page.waitForLoadState();
            convertLeadVals(ConvertLeadElements.CONVERT_BUTTON).click();
            convertLeadVals(ConvertLeadElements.NEWDEAL_CHECKBOX).click();
            convertLeadVals(ConvertLeadElements.AMOUNT_INPUT).fill(100000);
            
            page.waitForLoadState();
            logger.info("Lead Deleted Successfully");
        } catch (Exception e) {
            logger.error("Unable to delete lead", e);
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}

