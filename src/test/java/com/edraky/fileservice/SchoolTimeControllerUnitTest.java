
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import java.util.Collections;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.http.ResponseEntity.status;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(SchoolTimeController.class)
//public class SchoolTimeControllerUnitTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private SchoolTimeService schoolTimeService;
//    @MockBean
//    private ClassService classService;
//
//    @Test
//    public void testGetOrdersList() throws Exception {
//        MvcResult result = mockMvc.perform(get("/schoolTime/getClassesBy?subject=math&classTime=2023-03-12 14:12").contentType(MediaType.APPLICATION_JSON).)
//                .andDo(print())
//                .andExpect(status().is)
//
//                .andDo(print())
//                .andReturn();
//    }
//}
