package LeadsModule;
import Utilities.Reports;
import com.microsoft.playwright.Locator;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.DataProvider;

import java.util.HashMap;

public class Test000_ZohoLogin extends Reports {

    private static String username = "jayakrishna.automation@gmail.com";
    private static String password = "JKS@zoho";

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][]{{this.username, this.password,
        "Login Test",
        "Login to Zoho CRM Account",
        "Test Passed: Successfully logged into Zoho CRM Account",
        "Test Failed: Unable to login to Zoho CRM Account",
        "Login Screen"}
        };
    }

    private static HashMap<String, String> zohoLoginData = readExcelToHashMap("website");

    public static void navigate() {
        page.navigate(zohoLoginData.get("website"));
    }

    private static Locator loginDataVals(LoginElements elementName) {
        String value = zohoLoginData.get(elementName.name().toLowerCase());
        return locate(value);
    }

    private static Locator loginDataVals(String elementName) {
        return mapNav(zohoLoginData, elementName);
    }

    private enum LoginElements {
        WEBSITE,
        SIGN_IN_BUTTON_HOMEPAGE,
        EMAIL_INPUT,
        NEXT_BUTTON,
        PASSWORD_INPUT,
        SIGN_IN_BUTTON_LOGINPAGE,
        WELCOME_VAL,
        TASKS_VAL,
        MEETINGS_VAL,
        MODULES_VAL,
        MODULES_LIST_VAL,
        PROFILE,
        LOGOUT_BTN,
        HOME_PAGE
    }

    /**
     * - Automates the login process to Zoho CRM by interacting with UI elements.
     * - Clicks the sign-in button, fills in the email and password fields, and navigates through login steps.
     * - Waits for the page to load completely after login to ensure successful navigation.
     * - Logs a success message upon successful login or logs an error message with details if an exception occurs.
     * - Utilizes the `LoginElements` enum to manage UI element locators for better maintainability.
     */
    public static void zohoLogin(String username, String password) {
        try{
            loginDataVals(LoginElements.SIGN_IN_BUTTON_HOMEPAGE).click();
            loginDataVals(LoginElements.EMAIL_INPUT).fill(username);
            loginDataVals(LoginElements.NEXT_BUTTON).click();
            loginDataVals(LoginElements.PASSWORD_INPUT).fill(password);
            loginDataVals(LoginElements.SIGN_IN_BUTTON_LOGINPAGE).click();
            loginDataVals(LoginElements.HOME_PAGE).click();
            page.waitForLoadState(LoadState.LOAD);
            logger.info("Logged in successfully");
        } catch (Exception e) {
            logger.error("Login failed", e);
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * - Performs post-login validations by checking the visibility of key UI elements.
     * - Uses assertions to ensure elements like welcome message, tasks, meetings, and modules are visible.
     * - Logs a success message if validations pass or logs an error message with details if they fail.
     */
    public static boolean loginValidations() throws Exception{
        try {
            page.waitForLoadState(LoadState.LOAD);
            assertThat(loginDataVals(LoginElements.WELCOME_VAL)).isVisible();
            assertThat(loginDataVals(LoginElements.TASKS_VAL)).isVisible();
            assertThat(loginDataVals(LoginElements.MEETINGS_VAL)).isVisible();
            assertThat(loginDataVals(LoginElements.MODULES_VAL)).isVisible();
            logger.info("Login Validations Successful");
            return true;
        } catch (Exception e) {
            logger.error("Unable to validate/ Elements are not visible", e);
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static void logOut() {
        loginDataVals(LoginElements.PROFILE).click();
        loginDataVals(LoginElements.LOGOUT_BTN).click();
    }
}
