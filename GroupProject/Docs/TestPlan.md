# Test Plan


**Author**: Team 1

## 1 Testing Strategy

### 1.1 Overall strategy

Manual testing will primarily be executed by our QA tester (Zain Syed). Some of our testing will be manually done by each developer. At each step we plan to test functionality to ensure everything is working as expected. Since this app is not complex on the back-end level, we are comfortable with using black-box and white-box testing to fulfill any bug catching and fixing.

### 1.2 Test Selection

At the initial checkpoints of the application black box testing will be needed to ensure internal components are functioning correctly. In the final stages of the app's creation, white-box techniques will be used, since the previous black-box techniques are going to be utilized to ensure correct functionality.

### 1.3 Adequacy Criterion

Test cases must be documented thoroughly, with steps being concise enough to reproduce when needed. Test cases should not combine to many functional parts, making sure to isolate certain parts of the application, then slowly building up complexity.

### 1.4 Bug Tracking

- GitHub Issues will be used to track bug and enhancement requests. 

### 1.5 Technology

- Android system required for manual testing

## 2 Test Cases

| Purpose                                | Steps                                    | Expected Result                          | Actual Result(Filled Later) | Pass/Fail Info (Filled Later) | Misc. (Filled Later) |
|----------------------------------------|------------------------------------------|------------------------------------------|-----------------------------|-------------------------------|----------------------|
| List added correctly                   | Start on main page, Enter a non-empty list name and press the add list button | New list appears on the screen with the correct name | New list appears with the correct name | Pass |                      |
| List removed correctly                   | Start on main page, press on a list and select delete list | Removed list no longer appears on the screen | List is removed from the screen | Pass |                      |
| List renamed correctly | Start on main page, press on a list and select  rename list and attempt to rename a list with a non-empty name. | renamed list appears with the new name | List name is changed to the new name                            | Pass |                      |
| List unable to be renamed with a non-empty name | Start on main page, press on a list and select rename list and attempt to rename a list with an empty name | List should not be renamed and error message should appear | List is renamed to the empty name | Fail |                      |
| List unable to be added with empty name | Start on main page, press the add list button and attempt to add a list with a empty name. | error message should appear (toast) | List is unable to be added, error message "Enter a valid name" appears | Pass |                      |
| List can be navigated to | Start on main page, press on a list and select navigate| List should be opened | List is opened on a new screen | Pass |                      |
| User is able to remove an item | Start on list page, press down on the item and select delete item | item should be removed from the list | Item is successfully removed from the list | Pass 
| User is able to check an item | Start on list page, press the check item button and attempt to check an item | item should be marked as checked on the list | Item is checked | Pass
| User is able to uncheck an item | Start on list page, press the uncheck item button and attempt to uncheck an item | item should be marked as unchecked on the list | Item is marked as unchecked | Pass
| User is able to uncheck all items | Start on list page, press the uncheck all items button | all items should be marked as unchecked on the list | All items are successfully unchecked | Pass
| User is able to adjust item quantity | Start on list page, press on an item and select change quantity and enter a positive value | The quantity should be adjusted | Quantity is successfully adjusted | Pass
| User cannot have a negative item quantity | Start on list page, enter a negative quantity| The quantity should not be adjusted | Negative item quantity is added | Fail
| User should be able to search for items and get correct results | Start on list page, click search button, enter item name | search results should display matching search query | Search results display correct items | Pass
| User should be able to add items from search | Start on list page, click search button, enter item name, on search results page check item and click add to list | Item should be added to the list | Item is added to the list | Pass
| User is unable to add items from search with an empty name | Start on list page, click search button, search for item that does not exist, click no results found button, enter empty item name and valid item type and attempt to add to list | Item should not be added to the list | Item is not added to the list or search results | Pass
| User is unable to add items from search with an empty item type | Start on list page, click search button, search for item that does not exist, click no results found button, enter valid item name and empty item type and attempt to add to list | Item should not be added to the list | Item is added to the list or search results | Fail
| User should be able to create an item from search that does not exist | Start on list page, click search button, enter item name that does not exist, click No Results Found button, enter item name, enter item type | new item should be created and show on search | New item is created and appears on the list | Pass | | 
| Data should persist | Test entire system | All data should persist on change of any list/item | Data persists for all flows | Pass

Other issues found:
- If an empty item name is searched for, it returns the user back to the first activity.
- UX around search is not good, too many popups.
