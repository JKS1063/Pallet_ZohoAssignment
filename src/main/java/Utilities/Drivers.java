package Utilities;

import com.microsoft.playwright.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;



public class Drivers{

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    public static Page page;
    public static final Logger logger = LoggerFactory.getLogger(Drivers.class);

    @BeforeSuite
    public void chromeDriver() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setSlowMo(500).setHeadless(false));
        context = browser.newContext();
        page = context.newPage();

        page.setDefaultTimeout(30000);
    }

    @AfterSuite
    public void quit(){
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}
