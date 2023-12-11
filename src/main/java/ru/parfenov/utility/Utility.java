package ru.parfenov.utility;

import ru.parfenov.dto.PersonDto;
import ru.parfenov.dto.TaskDtoOut;
import ru.parfenov.model.Person;
import ru.parfenov.model.Task;

import java.util.ArrayList;
import java.util.List;

public class Utility {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
    public static final String ALL_TASKS_URL = "/tasks/";
    public static final String ALL_PERSONS_URL = "/persons/all";
    public static final String TASK_COMMENTS_URL = "/tasks/{id}/comments";
    public static final String TASK_NEW_COMMENT_URL = "/tasks/{id}/new_comment";
    public static final String EXCEPTION_HANDLER_MASSAGE = "Something went wrong))";
    public static final String EXCEPTION_TASK_MASSAGE = "The task does not exist or is completed!";
    public static final String EXCEPTION_AUTHOR_MASSAGE = "The author's data is not on the server!";
    public static final String EXCEPTION_EXECUTOR_MASSAGE = "The executor's data is not on the server!";
    public static final String EXCEPTION_RIGHTS_NOT_ENOUGH = "Insufficient rights to perform the operation!";
    public static final String SWAGGER_URL = "/swagger-ui/**";
    public static final String SWAGGER_V3_URL = "/v3/**";

    public static List<TaskDtoOut> getTaskOutsFromTasks(List<Task> list) {
        List<TaskDtoOut> listDTO = new ArrayList<>();
        for (Task task : list) {
            TaskDtoOut taskDTO = getTaskOutFromTask(task);
            listDTO.add(taskDTO);
        }
        return listDTO;
    }

    public static TaskDtoOut getTaskOutFromTask(Task task) {
        TaskDtoOut taskDTO = new TaskDtoOut();
        taskDTO.setId(task.getId());
        taskDTO.setAuthor(task.getAuthor().getName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(task.getStatus().getInfo());
        taskDTO.setPriority(task.getPriority().getInfo());
        if (task.getExecutor() == null) {
            taskDTO.setExecutor("Не назначен");
        } else {
            taskDTO.setExecutor(task.getExecutor().getName());
        }
        taskDTO.setCommentAmount(task.getComments().size());
        return taskDTO;
    }

    public static PersonDto getPersonDtoFromPerson(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setName(person.getName());
        personDto.setCreatedTasks(person.getCreatedTasks().size());
        personDto.setExecuteTasks(person.getExecutedTasks().size());
        return personDto;
    }
}
