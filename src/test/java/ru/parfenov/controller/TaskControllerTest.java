package ru.parfenov.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.parfenov.Main;
import ru.parfenov.dto.TaskDtoIn;
import ru.parfenov.dto.TaskDtoOut;
import ru.parfenov.service.TaskService;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@SpringBootTest(classes = Main.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class TaskControllerTest {

    @MockBean
    private TaskService taskService;
    @MockBean
    private Authentication authentication;
    @Autowired
    private MockMvc mockMvc;

    private final TaskDtoOut taskDtoOut1 = new TaskDtoOut(
            1, "Вася", "Описание1", "В ожидании",
            "Низкий", "Петя", 0);
    private final TaskDtoOut taskDtoOut2 = new TaskDtoOut(
            2, "НеВася", "Описание2", "В ожидании",
            "Низкий", "НеПетя", 0);

    private final TaskDtoIn taskDtoIn1 = new TaskDtoIn(
            1, 1, "Описание1", 1, 1, 3);
    private final TaskDtoIn taskDtoIn2 = new TaskDtoIn(
            2, 1, "Описание2", 1, 1, 4);

    @Test
    @WithMockUser
    void findAll() throws Exception {
        var tasks = List.of(taskDtoOut1, taskDtoOut2);
        when(taskService.findAll()).thenReturn(tasks);
        mockMvc.perform(get("/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser
    void findAllOfAuthor() throws Exception {
        var tasks = List.of(taskDtoOut1);
        when(taskService.findAllOfAuthor(1)).thenReturn(tasks);
        mockMvc.perform(get("/tasks/author/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser
    void findAllOfExecutor() throws Exception {
        var tasks = List.of(taskDtoOut1);
        when(taskService.findAllOfExecutor(3)).thenReturn(tasks);
        mockMvc.perform(get("/tasks/executor/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}