Feature: OpenApplication
@openApp
Scenario: Open Application
Given Wait 5 seconds

@BasicTiklamalarIOS
Scenario: Open Application
Given Wait 5 seconds
  Then Element with xpath "//XCUIElementTypeButton[@name='Markalar']" is clicked
  Then Wait 2 seconds
  Then Element with xpath "//XCUIElementTypeButton[@name='Kategoriler']" is clicked
  Then Wait 2 seconds
  Then Element with xpath "//XCUIElementTypeButton[@name='Kampanyalar']" is clicked
  Then Wait 2 seconds

@oapp3
Scenario: Open Application
Given Wait 5 seconds

@app4
Scenario: Open Application
Given Wait 5 seconds

@open5
Scenario: Open Application
Given Wait 5 seconds

@open6
Scenario: Open Application
Given Wait 5 seconds

