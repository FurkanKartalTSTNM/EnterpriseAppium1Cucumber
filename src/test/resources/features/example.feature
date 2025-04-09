Feature: OpenApplication

@BasicTiklamalarIOS
Scenario: Open Application
Given Wait 5 seconds
  Then Element with xpath "//XCUIElementTypeButton[@name='Markalar']" is clicked
  Then Wait 2 seconds
  Then Element with xpath "//XCUIElementTypeButton[@name='Kategoriler']" is clicked
  Then Wait 2 seconds
  Then Element with xpath "//XCUIElementTypeButton[@name='Kampanyalar']" is clicked
  Then Wait 2 seconds

