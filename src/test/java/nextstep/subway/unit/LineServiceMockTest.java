package nextstep.subway.unit;

import nextstep.config.fixtures.LineFixture;
import nextstep.config.fixtures.SectionFixture;
import nextstep.config.fixtures.StationFixture;
import nextstep.subway.application.LineService;
import nextstep.subway.dto.SectionRequest;
import nextstep.subway.entity.Station;
import nextstep.subway.entity.repository.LineRepository;
import nextstep.subway.entity.repository.StationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LineServiceMockTest {

    Long 이호선_아이디;
    Long 선릉역_번호;
    Long 삼성역_번호;
    Long 신천역_번호;

    Station 선릉역;
    Station 삼성역;
    Station 신천역;

    LineService lineService;

    @Mock
    StationRepository stationRepository;

    @Mock
    LineRepository lineRepository;


    @BeforeEach
    void 서비스_객체_생성() {
        lineService = new LineService(stationRepository, lineRepository);
    }

    @BeforeEach
    void 사전_스텁_설정() {
        이호선_아이디 = 1L;
        선릉역_번호 = 1L;
        삼성역_번호 = 2L;
        신천역_번호 = 3L;

        선릉역 = StationFixture.선릉.updateId(선릉역_번호);
        삼성역 = StationFixture.삼성.updateId(삼성역_번호);
        신천역 = StationFixture.신천.updateId(신천역_번호);

        when(lineRepository.findById(이호선_아이디)).thenReturn(Optional.of(LineFixture.이호선_생성()));
        when(stationRepository.findById(선릉역_번호)).thenReturn(Optional.of(선릉역));
        when(stationRepository.findById(삼성역_번호)).thenReturn(Optional.of(삼성역));
    }

    /**
     * Given 지하철 노선이 생성되고
     * When  지하철 구간을 추가하면
     * Then  지하철 노선에 구간이 추가된다.
     */
    @Test
    void 지하철_구간_추가() {
        // given
        SectionRequest 선릉_삼성_구간 = SectionRequest.mergeForCreateLine(
                        이호선_아이디, SectionFixture.지하철_구간(선릉역_번호, 삼성역_번호, 10));

        // when
        lineService.addSection(선릉_삼성_구간);

        // then
        assertThat(lineService.findLineById(이호선_아이디).getStations())
                .containsAnyOf(선릉역, 삼성역);
    }

    /**
      * Given 지하철 노선이 생성되고 구간을 추가한다
      * When  지하철 구간을 삭제하면
      * Then  지하철 노선에 구간이 삭제된다
      */
    @Test
    void 지하철_구간_삭제() {
        // given
        SectionRequest 선릉_삼성_구간 = SectionRequest.mergeForCreateLine(
                이호선_아이디, SectionFixture.지하철_구간(선릉역_번호, 삼성역_번호, 10));
        SectionRequest 삼성_신천_구간 = SectionRequest.mergeForCreateLine(
                이호선_아이디, SectionFixture.지하철_구간(삼성역_번호, 신천역_번호, 10));

        when(stationRepository.findById(신천역_번호)).thenReturn(Optional.of(신천역));

        lineService.addSection(선릉_삼성_구간);
        lineService.addSection(삼성_신천_구간);

        // when
        lineService.deleteSection(이호선_아이디, 신천역_번호);

        // then
        assertThat(lineService.findLineById(이호선_아이디).getStations())
                .containsAnyOf(선릉역, 삼성역);
    }
}
