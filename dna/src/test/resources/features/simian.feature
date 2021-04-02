@simian
Feature: Dna Type checks

Scenario: Checking Human Dna Type
 Given a dna sequence <dna>
 When is checked its dna type
 Then return as Human Type
 
 Examples:
 |  													 dna|
 |  												   "A"|
 |									 "ATG,CGA,ATG"|
 |"ATGAC,CGATA,ATGAG,ATGAG, CGATA"|
 
 
 
 Scenario: Checking Simian Dna Type
 Given a dna sequence <dna>
 When is checked its dna type
 Then return as Simian Type
 
 Examples:
 | 																							 dna|
 |									 "aaaAC,CGATA,ATGAG,ATGAG,CGATA"|
 |"aa a  A C, C  G ATA,AT GA G ,A   T GAG,  C G ATA"|
 |									"ATGAC,aGATA,aTGAG,aTGAG, CGATA"|
 |									"ATGAC,CAATA,ATAAG,ATGAG, CGATA"|
 |						 	 		"ATGAC,CGACA,ATCAG,ACGAG, CGATA"|
 
 
 