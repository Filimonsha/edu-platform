# Homework management module
The homework management module. The main point is to create homework assignments by the teacher, which students will solve and receive an assessment by sending for verification if there are assignments with an attached answer in the homework.

#### Feature tech: 
```Based on MongoDB```


## Contents

### Domains

- Homework - collection of tasks with additional general information. Contains description, correct answer and
  interaction type. Have refs on solvers and homework answers.
- Solver - person who can create homework or give the answer on it. Have refs with homeworks and his homework answers.

### Subdomains

- Task - Generic over type of interaction such as multiple choice etc. 
- Homework answer - collection of tasks answers. Have ref with solver.
- Task answer - the answer to the task, which contains either a literal answer or a link to the file with the answer
- Evaluation - The assessment that the teacher can give both for all homework and for a specific task

