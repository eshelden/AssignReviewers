# AssignReviewers
A java application that randomly assigns reviewers to applications. The .jar
file is a standalone executable application that can be downloaded and used
or you can download the source code and build your own application.

The program accepts 2-3 .csv files, minimally a file of applicant names and
a file of reviewer names with one name per line, called "Applicants.csv" and 
"Reviewers.csv", respectively. Reviewer names can have a
comma followed by a number to limit the number of assigned reviews. Do not
include spaces between or around names. 

Optionally, a file called "Conflicts.csv" can be included with potential
conflicts listed as comma separated Reviewer,Applicant pairs. If there
are no conflicts, the "No Conflicts" check box should be checked to 
prevent the program from looking for the conflicts file.

Operation:
Specify the directory to find the input files and the number of reviews to
assign each application. The output file "Results.csv" will also be written 
to the chosen directory. The absolute maximum number of assignments/reviewer
should be specified. The app will use the smaller of this value or any value 
included for a reviewer in the "Reviewers.csv" file. "Tries" is the number 
of attempts the program will make to find a reviewer that meets all criteria 
without giving up.

It is possible to specify criteria that require several runs before a
solution is found. It is also possible to specify criteria that have
no possible solution.

If a solution is found, a preview will be produced in the text box and
the results will be written to a file called "Results.csv".
The program will overwrite any existing results file without asking.
