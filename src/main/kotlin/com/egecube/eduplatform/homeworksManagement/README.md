# Homework management module

The homework management module. The main point is to create homework assignments by the teacher, which students will
solve and receive an assessment by sending for verification if there are assignments with an attached answer in the
homework.

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

### Module use-case

1. POST /api/homewroks-management/homeworks

   Create HW. Example JSON DTO:

```json

{
  "title": "New",
  "subjectId": 1,
  "creatorId": 1,
  "description": "lala",
  "deadline": "2015-07-25T22:09:01.795+0700",
  "solversIds": [
    1
  ]
}
```

2. POST /api/homewroks-management/homeworks/{homeworkId}/tasks

   Attach tasks to HW. Example JSON DTO:

```json
[
  {
    "title": "task 111111",
    "description": "lala",
    "priority": 1,
    "correctAnswer": {
      "type": "ANSWER_WITH_ATTACHMENT"
    }
  }
]
```

3. POST /api/homeworks-management/solvers/{solverId}/answers

   Add student answer on this HW. Example JSON DTO:

```json
{
  "homeworkId": "64b94e8d59660a742f278631",
  "tasksAnswer": [
    {
      "taskId": "64b94eeb59660a742f278632",
      "taskAnswer": {
        "answerIsCorrect": false,
        "answer": {
          "type": "ANSWER_WITH_ATTACHMENT",
          "answer": "SIMPLE"
        }
      }
    }
  ]
}
```

4. PUT /api/homeworks-management/solvers/{solverId}/answers/{answerId}

   Add evaluate on student answer. Example JSON DTO:

```json
{
    "evaluate":{
        "grade":123,
        "feedback":"test"
    },
    "tasksAnswer":[{
        "taskId":"64b94eeb59660a742f278632",
        "evaluate":{
        "grade":123,
        "feedback":"test"
        }
    }]
}
```