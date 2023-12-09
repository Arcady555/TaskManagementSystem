package ru.parfenov.utility;

import ru.parfenov.dto.TaskDtoOut;
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
    public static final String TASK_NEW_COMMENT_URL = "/{id}/new_comment";

    public static List<TaskDtoOut> getTaskOutsFromTasks(List<Task> list) {
        List<TaskDtoOut> listDTO = new ArrayList<>();
        for (Task task : list) {
            TaskDtoOut taskDTO = new TaskDtoOut();
            taskDTO.setId(task.getId());
            taskDTO.setAuthor(task.getAuthor().getName());
            taskDTO.setDescription(task.getDescription());
            taskDTO.setStatus(task.getStatus().getInfo());
            taskDTO.setPriority(task.getPriority().getInfo());
            taskDTO.setExecutor(task.getExecutor().getName());
            taskDTO.setCommentAmount(task.getComments().size());
            listDTO.add(taskDTO);
        }
        return listDTO;
    }
}
