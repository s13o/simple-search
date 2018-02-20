#language: en
Feature: Simple Index Test

  In order to check all methods in Index in common
  We will add set of Documents
  Each search results should be as in tables below

  Scenario: check all documents are always found
    Given set of documents
      | key       | content             |
      | letter1   | 1 the letter 1      |
      | letter2   | 2 the letter 2      |
      | letter3   | 3 the letter 3      |
    Then findAny by "letter" will find
      | letter1   |
      | letter2   |
      | letter3   |
    And findAll by "the letter" will find
      | letter1   |
      | letter2   |
      | letter3   |

  Scenario: check some documents are found but another don't
    Given set of documents
      | key     | content                                 |
      | mail1   | How many cookies could a good cook cook |
      | mail2   | If a good cook could cook cookies?      |
      | mail3   | A good cook could cook as many cookies  |
      | mail4   | As a good cook who could cook cookies   |
    Then findAny by "cook" will find
      | mail1   |
      | mail2   |
      | mail3   |
      | mail4   |
    And findAll by "many" will find
      | mail1   |
      | mail3   |

