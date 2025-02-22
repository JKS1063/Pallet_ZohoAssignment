package Utilities;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class Utils extends Drivers{

    public static Locator locate(String xPath) {
        return page.locator(xPath);
    }

    public static HashMap<String, String> readExcelToHashMap(String sheetName) {

        HashMap<String, String> dataMap = new HashMap<>(); //Hashmap to store the data

        try{
            FileInputStream file = new FileInputStream("src/main/resources/zoho_crm_leads.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheet(sheetName);//input sheetname to read

            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {

                String key = sheet.getRow(rowNum).getCell(0).toString(); //first column with keys
                String value = sheet.getRow(rowNum).getCell(1).toString(); //second column with values

                dataMap.put(key, value);
            }
            workbook.close();;
            file.close();
        } catch (IOException e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println("unable to read file");
        }

        return dataMap;
    }

    public static Locator mapNav(HashMap<String, String> dataMap, String key) {
        Locator value = locate(dataMap.get(key));
        return value;
    }

    public static Locator waitForElement(String selector) {
        page.waitForSelector(selector, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        return locate(selector);
    }

    public static void waitForElementVisible(Locator locator) {
        locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public static void waitForElementVisible(Locator locator, Integer seconds) throws Exception{
        locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(seconds * 1000));
    }

    public static void waitForLoadstate(Integer seconds) throws Exception{
        page.waitForLoadState(LoadState.LOAD, new Page.WaitForLoadStateOptions().setTimeout(seconds * 1000));
    }


}
