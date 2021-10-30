package com.sushobhan.politicalparty.controller;

import com.sushobhan.politicalparty.entity.PartyEntity;
import com.sushobhan.politicalparty.payload.PartyDto;
import com.sushobhan.politicalparty.service.PartyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(PartyController.class)
class PartyControllerTest {

    @MockBean
    private PartyService partyService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createPoliticalParty() throws Exception {
        String json = "{\n" +
                "    \"partyName\": \"ABC India party\",\n" +
                "    \"founderName\": \"Kumar1\",\n" +
                "    \"foundationYear\": 1995\n" +
                "}";
        PartyDto partyDto = new PartyDto("ABC India party", "Kumar1", 1995D);

        Mockito.when(partyService.createParty(ArgumentMatchers.any(PartyDto.class))).thenReturn(partyDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/politics/api/v1/addParty")
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    }

    @Test
    void getAllPoliticalParties() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/politics/api/v1/parties/get/all"))
                .andExpect(MockMvcResultMatchers.status().is(200));

        PartyEntity partyEntity1 = new PartyEntity(123L, "ABC India party", "Kumar1", 1995D);
        PartyEntity partyEntity2 = new PartyEntity(125L, "MNO India party", "Sushobhan1", 1985D);
        PartyEntity partyEntity3 = new PartyEntity(126L, "PQR India party", "Kumar2", 2001D);
        PartyEntity partyEntity4 = new PartyEntity(127L, "IJK India party", "Sushobhan2", 1996D);
        PartyEntity partyEntity5 = new PartyEntity(128L, "POK India party", "Kumar3", 1989D);
        PartyEntity partyEntity6 = new PartyEntity(129L, "DEF India party", "Sushobhan3", 1990D);
        PartyEntity partyEntity7 = new PartyEntity(130L, "Kumar India party", "Sushobhan4", 1982D);
        PartyEntity partyEntity8 = new PartyEntity(131L, "Sushobhan India party", "Sushobhan5", 1992D);

    }
}