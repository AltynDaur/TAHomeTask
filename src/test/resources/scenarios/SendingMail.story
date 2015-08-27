Feature: SendingMails
Scenario: save mail to draft
Given the user enters to Gmail
When the user create new mail
And save it to draft
Then they should see mail theme in draft list