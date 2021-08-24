//package com.nyash.travellizermono.api.controller;
//
//import static org.hamcrest.Matchers.equalTo;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import com.nyash.travellizermono.api.TravellizerMonoApplicationTests;
//import com.nyash.travellizermono.api.dto.OrderDTO;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.json.JacksonTester;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//@SpringJUnitWebConfig(TravellizerMonoApplicationTests.class)
//@AutoConfigureMockMvc
//@AutoConfigureJsonTesters
//public class OrderControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//    JacksonTester<OrderDTO> orderTester;
//
//    @Test
//    void findAll_noOrdersCreated_returnsEmptyList() throws Exception {
//        ResultActions result = mockMvc.perform(get("api/orders"));
//
//        result.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.length()", equalTo(0)));
//    }
//
//    @Test
//    void findByUser_noOrdersExist_returnsEmptyList() throws Exception {
//        ResultActions result = mockMvc.perform(get("api/users/11/orders"));
//
//        result.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.length()", equalTo(0)));
//    }
//
//    @Test
//    void findByCurrentUser_noOrdersExist_returnsEmptyList() throws Exception {
//        ResultActions result = mockMvc.perform(get("api/users/orders").header("X-USER-ID", "12"));
//
//        result.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.length()", equalTo(0)));
//    }
//
//    @Test
//    void saveOrder_validOrder_returnsCreated() throws Exception {
//        OrderDTO order = new OrderDTO();
//        order.setTripId(2L);
//        order.setClientName("John");
//        order.setClientPhone("111");
//        String userId = "a22";
//
//        ResultActions result = mockMvc.perform(post("api/orders").contentType(MediaType.APPLICATION_JSON)
//                .content(orderTester.write(order).getJson()).header("X-USER-ID", userId));
//
//        result.andExpect(status().isCreated());
//    }
//}
