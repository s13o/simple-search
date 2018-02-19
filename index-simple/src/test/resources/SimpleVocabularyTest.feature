#language: en
Feature: Simple Vocabulary

  In order to populate Vocabulary of the Index
  As a result of adding set of Documents to Index
  We want process SimpleVocabularyTest test below

  Scenario: check one document found
    Given set of tokens to be added to Vocabulary
      | docIndex   | token        |
      | 1          | some         |
      | 1          | text         |
      | 1          | found        |
      | 2          | another      |
      | 2          | missed       |
    Then with token "found" we have found such documents
      | 1          |

  Scenario: check both documents found
    Given set of tokens to be added to Vocabulary
      | docIndex   | token       |
      | 3          | vocabulary  |
      | 3          | works       |
      | 4          | vocabulary  |
      | 4          | broken      |
    Then with token "vocabulary" we have found such documents
      | 3          |
      | 4          |

