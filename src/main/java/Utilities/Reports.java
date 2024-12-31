package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.microsoft.playwright.Page.ScreenshotOptions;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Reports extends Utils{

    private static ExtentReports extentReports;
    private static String reportPath = System.getProperty("user.dir") + "\\reports\\";

    public static void reports(String reportName, String docTitle) {
        extentReports = new ExtentReports();
        File file = new File(reportPath + "Pallet_ZohoCRMLeads.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
        ExtentSparkReporterConfig config =sparkReporter.config();

        config.setReportName(reportName);
        config.setDocumentTitle(docTitle);

        extentReports.attachReporter(sparkReporter);
    }

    public static void result (boolean status, String testName, String testInfo, String passInfo, String failInfo, String screenShotName) throws IOException {
        ScreenshotOptions screenshotOptions = new ScreenshotOptions();
        String screenShotPath = System.getProperty("user.dir") + "\\snaps\\" + screenShotName + ".jpg";
        page.screenshot(screenshotOptions.setFullPage(true).setPath(Paths.get(screenShotPath)));

        if(status) {
            extentReports.createTest(testName, testInfo)
                    .assignDevice("chrome")
                    .assignAuthor("Jaya Krishna Sekhar")
                    .pass(passInfo, MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
        }
        else {
            Throwable failure = new Throwable(failInfo);
            extentReports.createTest(testName, testInfo)
                    .assignDevice("chrome")
                    .assignAuthor("Jaya Krishna Sekhar")
                    .fail(failure, MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
        }
    }

    public static void quitReport() throws Exception{
        extentReports.flush();
        Desktop.getDesktop().browse(new File(reportPath).toURI());
    }
}
