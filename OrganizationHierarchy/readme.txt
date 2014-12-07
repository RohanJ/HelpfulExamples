Suppose you are given a data file containing a list of employees separated by --. With each employee you are given the following information:


Brian,Peter,Software Engineer,2000--Peter,NULL, CEO,2000

where
Brian is the employee name,
Peter is Brian's Boss,
Software Engineer is Brian's title
2000 is the year Brian joined

and NULL indicates that Peter is on the top level

Output the organizational hierarchy in alphabetical order using dashes as a indication of level.



The input is follows:
The first line will be the number of organizational hierarchies in the input file
Each consequent line is a list of employees, separated by --, in an organization.

For example:

2
Fred,Karl,Technician,2010--Karl,Cathy,VP,2009--Cathy,NULL,CEO,2007
Adam,Karl,Technician,2010--Bob,Karl,Technician,2012--Cathy,Karl,Technician,2013--Karl,Nancy,Manager,2009--Wendy,Nancy,Technician,2012--Nancy,NULL,CEO,2007


The output to this will be:
Case #1
Cathy (CEO) 2007
-Karl (VP) 2009
--Fred (Technician) 2010
Case #2
Nancy (CEO) 2007
-Karl (Manager) 2009
--Adam (Technician) 2010
--Bob (Technician) 2012
--Cathy (Technician) 2013
-Wendy (Technician) 2012
