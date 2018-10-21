The main part of this solution is **InvestmentService** and its implementation.
The code can be run using **InvestmenrServiceImplText** class. I've implemented there three acceptance tests using data from task description.

Solution is divided into three parts:
* *service* containing core business logic
* *model* containing data-specific logic
* *factory* - code reflecting configuration or persistence part (investment style definitions)

There is no database or GUI, so all input data has to be put in tests.