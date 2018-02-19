#language: en
Feature: Simple Token Factory

  In order to split a simple text document on tokens (words)
  As a result of adding it to Index
  We want process TokFactory test below

  Scenario: check empty document
    Given text content is ""
    Then we have another tokens
      | token    | offset     |

  Scenario: check one word document
    Given text content is "test"
    Then we have another tokens
      | token    | offset     |
      | test     | 0          |

  Scenario: check two words document
    Given text content is " test  test"
    Then we have another tokens
      | token    | offset     |
      | test     | 1          |
      | test     | 7          |

  Scenario: check some document
    Given text content is "A bargain is a bargain"
    Then we have another tokens
      | token   | offset      |
      | a       | 0           |
      | bargain | 2           |
      | is      | 10          |
      | a       | 13          |
      | bargain | 15          |

