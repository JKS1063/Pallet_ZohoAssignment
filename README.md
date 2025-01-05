### Pallet_Assignment

#### 1. Project Overview
  * **Project Name:** Zoho CRM Leads Module Automated Tests.
  * **Description:** This project is an automated test suite for the Leads module in the Zoho CRM, designed to test the core functionalities such as Creating, Editing, Filtering and Deleting leads.

#### 2. Prerequisites
  * Java JDK
  * IntelliJ IDE
  * Maven
  * Dependencies
      * Playwright (Automation Library)
      * TestNG (Framework)
      * Apache POI (Excel Read/Write Library)
        
#### 3. Test Cases:
  
  * **Login:** This automation class designed to handle the login functionality of the Zoho CRM application.
    * **Key Features:**
        * **Data Provider:** getLoginData @DataProvider supplies test Data for loggin into the Zoho CRM. This data includes parameters like username, password along with test description and expected outcomes.
        * **Data Handling:** zohoLoginData HashMap reads data from Excel sheet called "website".
        * **Login Process:** The zohoLogin method performs the actual login process. It interacts with various UI elements to enter username and password.
        * **Validation:** The loginValidations method checks if the login was successfully by verifying the state of specific elements on the home page, such as welcome messages and module lists.
        * **Logging:** This class uses a logger to record information about login process and any exceptions that occur.
    
  * **Create Lead:** This automation class designed to create a new lead in a CRM application using Playwright for browser automation and TestNG for test management.
     * **Key Features:**
         * **Data Provider:** The getCreateLeadData method is a @DataProvider supplies test data for creating a lead. It retruns a two-dimensional array containing lead details such as Company name, Contact information etc.,
         * **Data Handling:** The createLeadData HashMap reads data from Excel sheet called "create_lead".
         * **Lead Creation:** The leadCreate method automates the process the filling out the lead creation form. It includes error handling to catch and log exceptions.
         * **Validation:** The createLeadValidations method checks if the lead was created successfully by asserting the visibilty of specific elements.
         * **Logging:** This class uses a logger to record information about the lead creation process and any exceptions that occur.
    
  * **Edit Lead:** This automation class designed to edit and validate a lead's details in a CRM application.
     * **Key Features:**
       * **Data Provider:** The getEditLeadData methods is a @DataProvider supplies test data for editing a lead. This data includes parameters like the title, mobile number, and annual revenue along with test descriptions and expected outcomes.
       * **Data Handling:** editLeadData HashMap reads data from a Excel Sheet called "edit_lead".
       * **Lead Editing:** The leadEdit method performs the actual lead editing process. It interacts with UI elements and update the lead details.
       * **Validation:** The validateEditLead method checks if the lead was edited successfully by verifying the visability of specific elements on the page and comparing previous and edited values.
       * **Logging:** This class uses a logger to record information about lead editing process and any exceptions that occur.

  * **Filter Lead:** This automation class designed to filter and validate leads in a CRM application.
     * **Key Features:**
       * **Data Provider:** getFilterData @DataProvider supplies test Data for filtering the leads. This data includes parameters like company name, lead name, and lead source along with test description and expected outcomes.
       * **Data Handling:** filterDataLead HashMap reads data from Excel sheet called "filter_lead".
       * **Lead Filtering:** The leadFilter method performs the actual lead filtering process. It interacts with various UI elements to apply filters.
       * **Validation:** The validateFilterLead method checks if the lead was filtered successfully by verifying the state of specific elements on the page, such as checkboxes and visibilty of records.
       * **Logging:** This class uses a logger to record information about lead filtering process and any exceptions that occur.

  
  * **Delete Lead:** This automation class designed to delete and validate the deletion of leads in a CRM application.
     * **Key Features:**
       * **Data Provider:** getDeleteData @DataProvider supplies test Data for deleting a leads. This data includes parameters like lead name and test description and expected outcomes.
       * **Data Handling:** deleteDataLead HashMap reads data from Excel sheet called "delete_lead".
       * **Lead Deletion:** The leadDelete method performs the actual lead deletion process. It interacts with various UI elements and delete a lead.
       * **Validation:** The validateFilterLead method checks if the lead was deleted successfully by verifying the absence of lead in the filtered results.
       * **Logging:** This class uses a logger to record information about lead deletion process and any exceptions that occur.

#### 4. Test Specification:
  * **Login Test:**
    * **Scenario:** This test verifies the login functionality of the Zoho CRM application.
    * **Expected Result:** The user should be able to login succesfully.

  * **Create Lead Test:**
    * **Scenario:** This test checks the ability to create a new lead in the CRM application.
    * **Expected Result:** A lead should be created successfully with the provided results.

  * **Edit Lead Test:**
    * **Scenario:** This test verifies the functionality to edit an existing lead's details.
    * **Expected Result:** The lead details should be updated successfully.

  * **Filter Lead Test:**
    * **Scenario:** This test checks the ability to filter leads based on the specific criteria.
    * **Expected Result:** The lead should be filtered successfully.

  * **Delete Lead Test:**
    * **Scenario:** This test verifies the functionality to delete a lead from CRM system.
    * **Expected Result:** The lead should be deleted successfully.

#### 5. Test Results:
  * **Login Test:**
    * **Result:** The test verfies successful login by checking the visibility of specific elements on the homepage.
    * **Outcome:** The result is logged with a screenshot, indicating whether the login is successful or not.

  * **Create Lead Test:**
    * **Result:** This test checks creation of new lead. It validates presence the lead's name and photo after creation.
    * **Outcome:** The result is logged with a screenshot showing whether the lead was created successfully.

  * **Edit Lead Test:**
    * **Result:** This test verifies the editing of existing lead's details. It compares previous and edited values.
    * **Outcome:** The result is logged with a screeshot, indicating whether the lead was edited successfully.

  * **Filter Lead Test:**
    * **Result:** This test checks the ability to filter leads based on the specific criteria. It validates the presence of filtered records.
    * **Outcome:** The result is logged with a screeshot, indicating whether the lead was filtered successfully.

  * **Delete Lead Test:**
    * **Result:** This test verifies the deletion of a lead. It checks the absence of the lead in the filtered results to confirm deletion.
    * **Outcome:** he result is logged with a screeshot, indicating whether the lead was deleted successfully.

#### Test Approach Reasoning:
  #### Data-Driven Testing:
   * Each test method uses a @DataProvider to supply test data, allowing for flexible and reusable test cases. This approach enables testing with various input scenarios without modifying the test logic.
  #### Modular Design:
   * The test suite is divided into distinct test methods, each targeting a specific functionality (login, create, edit, filter, delete). This modular design ensures that each feature is independently verified, making it easier to identify and isolate issues.
  #### Validation and Reporting:
   * Each test method includes validation steps to confirm the expected outcomes. The results are logged and reported using the Reports class, which captures screenshots and generates detailed test reports. This provides clear visibility into the test execution and outcomes.
  #### Prioritization:
   * Tests are prioritized using the priority attribute in the @Test annotation. This ensures that critical tests, such as login, are executed first, establishing a valid session for subsequent tests.
  #### Error Handling:
   * The test methods include try-catch blocks to handle exceptions gracefully, ensuring that failures are logged and do not disrupt the entire test suite execution.
  #### Coverage Assessment:
  * **Login Functionality:** The login test verifies the ability to access the CRM system, which is a prerequisite for all other operations. It checks for the presence of key elements on the homepage to confirm successful login.
  * **Lead Creation:** The create lead test covers the process of adding a new lead with various details. It validates the presence of the lead's name and photo to ensure successful creation.
  * **Lead Editing:** The edit lead test verifies the ability to update existing lead details. It checks that changes are applied correctly by comparing previous and new values.
  * **Lead Filtering:** The filter lead test assesses the ability to filter leads based on specific criteria. It ensures that the filtering logic works as expected and that the correct records are displayed.
  * **Lead Deletion:** The delete lead test confirms the ability to remove a lead from the system. It validates that the lead is no longer present in the filtered results.

    
