package codesquad.airbnb.accomodation;

import codesquad.airbnb.accomodation.domain.ProvideName;
import codesquad.airbnb.accomodation.dto.AccommodationResponse;
import codesquad.airbnb.accomodation.dto.PriceAndCount;
import codesquad.airbnb.accomodation.dto.PriceAndCountStatistics;
import codesquad.airbnb.accomodation.dto.ProvideResponse;
import codesquad.airbnb.accomodation.service.AccommodationService;
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

import static codesquad.airbnb.ApiDocumentUtils.HOST;
import static codesquad.airbnb.ApiDocumentUtils.SCHEME;
import static codesquad.airbnb.ApiDocumentUtils.getDocumentRequest;
import static codesquad.airbnb.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
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

    private static final String BASE_URL = "/api/accommodations";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccommodationStatisticsService accommodationStatisticsService;

    @MockBean
    private AccommodationService accommodationService;

    @Test
    void 숙소_가격_데이터가_있을때_조회하면_가격_통계를_반환한다 () throws Exception {
        // given
        long priceRange0 = 10000L;
        long priceRange1 = 20000L;
        long priceRange2 = 30000L;
        int count0 = 5;
        int count1 = 10;
        int count2 = 15;
        double average = (priceRange0 * count0) + (priceRange1 * count1) + (priceRange2 * count2);

        PriceAndCountStatistics statistics = new PriceAndCountStatistics(List.of(
                new PriceAndCount(priceRange0, count0),
                new PriceAndCount(priceRange1, count1),
                new PriceAndCount(priceRange2, count2)
        ), average);

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
                .andExpect(jsonPath("$.averagePrice").exists())
                .andExpect(jsonPath("$.averagePrice").value(average))

                .andDo(document("accommodation-statistics-price",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(
                                fieldWithPath("priceAndCountList.[].priceRange").type(JsonFieldType.NUMBER).description("가격대"),
                                fieldWithPath("priceAndCountList.[].accommodationCount").type(JsonFieldType.NUMBER).description("숙소 수"),
                                fieldWithPath("averagePrice").type(JsonFieldType.NUMBER).description("숙소 가격 평균")
                        )
                        ));
    }

    @Test
    void 예약_가능한_숙소가_있을때_시작종료일과_인원수로_조회하면_숙소목록이_반환된다() throws Exception {
        // given
        List<AccommodationResponse> responseList = createNewAccommodationResponseList();
        given(accommodationService.findNotReservedAccommodations(any()))
                .willReturn(responseList);

        // when
        ResultActions performResult = mockMvc.perform(get(BASE_URL)
                .queryParam("startDate", "2022-06-01")
                .queryParam("endDate", "2022-06-03")
                .queryParam("visitors", "3"));

        // then
        performResult.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(responseList.get(0).getId()))
                .andExpect(jsonPath("$[0].title").value(responseList.get(0).getTitle()))
                .andExpect(jsonPath("$[0].price").value(responseList.get(0).getPrice()))
                .andExpect(jsonPath("$[0].imageUrl").value(responseList.get(0).getImageUrl()))
                .andExpect(jsonPath("$[0].x").value(responseList.get(0).getX()))
                .andExpect(jsonPath("$[0].y").value(responseList.get(0).getY()))
                .andExpect(jsonPath("$[0].address").value(responseList.get(0).getAddress()))

                .andExpect(jsonPath("$[1].id").value(responseList.get(1).getId()))
                .andExpect(jsonPath("$[1].title").value(responseList.get(1).getTitle()))
                .andExpect(jsonPath("$[1].price").value(responseList.get(1).getPrice()))
                .andExpect(jsonPath("$[1].imageUrl").value(responseList.get(1).getImageUrl()))
                .andExpect(jsonPath("$[1].x").value(responseList.get(1).getX()))
                .andExpect(jsonPath("$[1].y").value(responseList.get(1).getY()))
                .andExpect(jsonPath("$[1].address").value(responseList.get(1).getAddress()))

                .andExpect(jsonPath("$[2].id").value(responseList.get(2).getId()))
                .andExpect(jsonPath("$[2].title").value(responseList.get(2).getTitle()))
                .andExpect(jsonPath("$[2].price").value(responseList.get(2).getPrice()))
                .andExpect(jsonPath("$[2].imageUrl").value(responseList.get(2).getImageUrl()))
                .andExpect(jsonPath("$[2].x").value(responseList.get(2).getX()))
                .andExpect(jsonPath("$[2].y").value(responseList.get(2).getY()))
                .andExpect(jsonPath("$[2].address").value(responseList.get(2).getAddress()))
                .andDo(document("not-reserved-accommodations",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(
                                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("숙소 아이디"),
                                fieldWithPath("[].title").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("[].price").type(JsonFieldType.NUMBER).description("숙박 가격"),
                                fieldWithPath("[].imageUrl").type(JsonFieldType.STRING).description("대표 이미지"),
                                fieldWithPath("[].x").type(JsonFieldType.NUMBER).description("x 좌표"),
                                fieldWithPath("[].y").type(JsonFieldType.NUMBER).description("y 좌표"),
                                fieldWithPath("[].address").type(JsonFieldType.STRING).description("주소"),
                                fieldWithPath("[].provides.[].name").type(JsonFieldType.STRING).description("옵션 이름"),
                                fieldWithPath("[].provides.[].count").type(JsonFieldType.NUMBER).description("옵션 개수")
                        )
                ));
    }

    private List<AccommodationResponse> createNewAccommodationResponseList() {
        List<ProvideResponse> provideResponses = List.of(
                new ProvideResponse(ProvideName.CAPACITY, 3),
                new ProvideResponse(ProvideName.BED_ROOM, 2),
                new ProvideResponse(ProvideName.BED, 1),
                new ProvideResponse(ProvideName.KITCHEN, 1),
                new ProvideResponse(ProvideName.WASH_ROOM, 1)
        );

        return List.of(
                new AccommodationResponse(1,
                        "강남스테이힐",
                        140000,
                        "http://tong.visitkorea.or.kr/cms/resource/28/2573328_image2_1.jpg",
                        127.0301236613,
                        37.5034291706,
                        "서울특별시 강남구 테헤란로13길 65",
                        provideResponses),
                new AccommodationResponse(2,
                        "게더링 앳 홍대",
                        22000,
                        "http://tong.visitkorea.or.kr/cms/resource/93/2571393_image2_1.PNG",
                        126.9248537833,
                        37.5632120044,
                        "서울특별시 마포구 동교로47길 3",
                        provideResponses),
                new AccommodationResponse(3,
                        "게스트하우스 류가",
                        108000,
                        "http://tong.visitkorea.or.kr/cms/resource/07/2573607_image2_1.JPG",
                        126.9187769621,
                        37.5489302228,
                        "서울특별시 마포구 독막로7길 19",
                        provideResponses)
        );
    }
}