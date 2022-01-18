<p>Write a simple function to mask the Bank Account Number</p>

<p>Function is expected to take in 4 parameters.</p>
<ul>
<li>bankAccountNumber – original string to be masked (e.g. “101-76074-5”)</li>
<li>maskedWith – character to replace for masking (e.g. ‘*’)</li>
<li>ignoredChar – character to ignore (e.g. ‘-‘)</li>
<li>remainingChar – character to retain without masking (e.g. 5)</li>
<ul>

<br>
<p>Below table illustrates the expected outcome:</p>

 
|Bank Account Number |Masked Bank Account Number |
|---                 |---                        | 
|  101-76074-5       |      ***-*6074-5          |
|356-5-5059302       |    ***-*-**59302          |
|78-19084-84         |    **-**084-84            |
|1875-2788-9-008-1   |    ****- ****-9-008-1     |  

<br>
<p>To run file in Command Prompt:
<ol>
<li>Change directory to that holding Masked.java</li>
<li> javac Masked.java (java compiler)</li>
<li> java Masked</li> 
</ol>
  
