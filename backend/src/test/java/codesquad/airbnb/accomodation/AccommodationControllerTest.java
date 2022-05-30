package codesquad.airbnb.accomodation;

import codesquad.airbnb.ApiDocumentUtils;
import codesquad.airbnb.accomodation.dto.PriceAndCount;
import codesquad.airbnb.accomodation.dto.PriceAndCountStatistics;
import codesquad.airbnb.accomodation.service.AccommodationStatisticsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static codesquad.airbnb.ApiDocumentUtils.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccommodationController.class)
@AutoConfigureRestDocs(uriScheme = SCHEME, uriHost = HOST)
class AccommodationControllerTest {

    private static final String BASE_URL = "/accommodation";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccommodationStatisticsService accommodationStatisticsService;

    @Test
    void 숙소_가격_데이터가_있을때_조회하면_가격_통계를_반환한다 () throws Exception {
        // given
        long priceRange0 = 10000L;
        long priceRange1 = 20000L;
        long priceRange2 = 30000L;
        int count0 = 5;
        int count1 = 10;
        int count2 = 15;

        PriceAndCountStatistics statistics = new PriceAndCountStatistics(List.of(
                new PriceAndCount(priceRange0, count0),
                new PriceAndCount(priceRange1, count1),
                new PriceAndCount(priceRange2, count2)
        ));

        given(accommodationStatisticsService.getCachedPriceAndCountStatistics())
                .willReturn(statistics);

        // when
        ResultActions performResult = mockMvc.perform(get(BASE_URL + "/statistics/price"));

        // then
        performResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.priceAndCountList").exists())
                .andExpect(jsonPath("$.priceAndCountList[0].priceRange").value(priceRange0))
                .andExpect(jsonPath("$.priceAndCountList[0].accommodationCount").value(count0))
                .andExpect(jsonPath("$.priceAndCountList[1].priceRange").value(priceRange1))
                .andExpect(jsonPath("$.priceAndCountList[1].accommodationCount").value(count1))
                .andExpect(jsonPath("$.priceAndCountList[2].priceRange").value(priceRange2))
                .andExpect(jsonPath("$.priceAndCountList[2].accommodationCount").value(count2))

                .andDo(document("accommodation-statistics-price",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(
                                fieldWithPath("priceAndCountList.[].priceRange").type(JsonFieldType.NUMBER).description("가격대"),
                                fieldWithPath("priceAndCountList.[].accommodationCount").type(JsonFieldType.NUMBER).description("숙소 수")
                        )
                        ));
    }
}