# Lesson-2-Assignment


The Lesson2RegEx class implements a number of methods which can be invoked to
parse  data stored  in a text file. The  name of the file to parse must to be
passed  to  the program  as a  commandline argument.  When invoked without an
argument,  the program displays an error message and a command syntax summary
on  the  console. Lesson2RegEx  uses regular  expressions in conjunction with
Java's  Pattern and  Matcher classes  (java.util.regex) to  extract character
sequences  from  the data.  When  invoked,  Lesson2RegEx generates  a  report
resembling the following:

Processed the following input file:
neighbor-dump.txt
Results are as follows:

- List of PANIDs (Total of 2):

PANID = 04fa
PANID = 329d

- List of MAC addresses (Total of 4):

000781fe0000326f
000781fe0000614e
000781fe00002f76
000781fe0000313e

- List of RF_RSSI_M values for each MAC address

000781fe0000326f -51.984
000781fe0000614e -24.294
000781fe00002f76 -25.5
000781fe0000313e -36.953

The  regular expression "PANID\\s+=\\s+[0-9a-f]{4}" is used to find character
sequences  in the data that match the pattern. This pattern matches sequences
of  characters beginning  with the letters P-A-N-I-D, followed by one or more
spaces (\\s+), followed by an equals sign (=), followed by one or more spaces
(\\s+)  and  ending in  any four  hexadecimal digits ([0-9a-f]{4}). Sequences
matching this pattern represent PANIDs, and are listed on individual lines in
the first section of the report.

The  regular  expression "\\b([0-9a-f]{16})\\b"  is  used  to find  character
sequences  in the data that match the pattern. This pattern matches sequences
of   characters  consisting  of  sixteen  hexadecimal  digits  ([0-9a-f]{16})
surrounded  by  "non-word" characters  (\\b…  \\b).  Sequences matching  this
pattern  represent  MAC addresses, and are  listed on individual lines in the
middle section of the report.

The regular expression "\\b[0-9a-f]{16}\\b|(-[1-9]*\\.[0-9]*" is used to find
character  sequences in the data that match either pattern. The bar character
(|)  in  this expression  is  the  "or"  operator.  It means  that  character
sequences  in the  input will be compared to both expressions. The expression
to  the  left  of  the  "or" operator  (|)  will  match  character  sequences
consisting  of  sixteen hexadecimal digits ([0-9a-f]{16}) surrounded by "non-
word"  characters (\\b…\\b).  Sequences matching  this pattern  represent MAC
addresses.

The  expression to  the  right  of the  "or"  operator  will match  character
sequences  that  begin with  a hyphen  (-), followed by  any number of digits
between  one and  nine inclusive ([1-9]*), followed by a decimal point (\\.),
followed  by  any number of digits  between zero and nine inclusive ([0-9]*).
Sequences matching this pattern represent RSSI values.

MAC  addresses  and their corresponding  RSSI values are listed on individual
lines in the last section of the report.
