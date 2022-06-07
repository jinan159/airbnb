package codesquad.airbnb.wish;

import codesquad.airbnb.wish.dto.WishAddRequest;
import codesquad.airbnb.wish.dto.WishResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static codesquad.airbnb.ApiDocumentUtils.HOST;
import static codesquad.airbnb.ApiDocumentUtils.SCHEME;
import static codesquad.airbnb.ApiDocumentUtils.getDocumentRequest;
import static codesquad.airbnb.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WishController.class)
@AutoConfigureRestDocs(uriScheme = SCHEME, uriHost = HOST)
class WishControllerTest {

    private static final String BASE_URL = "/api/wishes";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WishService wishService;

    @Test
    void 존재하는_숙소일때_위시리스트_추가는_성공하고_위시리스트_결과를_반환한다() throws Exception {
        // given
        long savedWishId = 1L;
        long savedAccommodationId = 2L;
        long savedMemberId = 3L;
        WishResponse response = new WishResponse(savedWishId, savedAccommodationId, savedMemberId);
        given(wishService.addWish(any()))
                .willReturn(response);
        // when
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(new WishAddRequest(savedAccommodationId, savedMemberId));

        ResultActions performResult = mockMvc.perform(post(BASE_URL)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        performResult.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(savedWishId))
                .andExpect(jsonPath("$.accommodationId").value(savedAccommodationId))
                .andExpect(jsonPath("$.memberId").value(savedMemberId))

                .andDo(document("wish-list-add",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("위시 리스트 아이디"),
                                fieldWithPath("accommodationId").type(JsonFieldType.NUMBER).description("숙소 아이디"),
                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 아이디")
                        )
                ));
    }

    @Test
    void 존재하는_위시리스트_일때_위시리스트_삭제는_성공한다() throws Exception {
        // given
        long wishId = 1L;

        // when
        String deleteURL = BASE_URL + "/" + wishId;
        ResultActions performResult = mockMvc.perform(delete(deleteURL));

        // then
        verify(wishService, times(1)).removeWish(anyLong());

        performResult.andExpect(status().isOk())
                .andDo(document("wish-list-remove",
                        getDocumentRequest(),
                        getDocumentResponse()
                ));
    }
}