package nextstep.config.fixtures;

import nextstep.subway.dto.LineRequest;
import nextstep.subway.entity.Line;

public class LineFixture {

    public static LineRequest 이호선(Long upStationId, Long downStationId, int distance) {
        return new LineRequest("이호선", "bg-green-600", upStationId, downStationId, distance);
    }
    public static LineRequest 신분당선(Long upStationId, Long downStationId, int distance) {
        return new LineRequest("신분당선", "bg-red-600", upStationId, downStationId, distance);
    }
    public static LineRequest 삼호선(Long upStationId, Long downStationId, int distance) {
        return new LineRequest("삼호선", "bg-orange-600", upStationId, downStationId, distance);
    }
    public static LineRequest 사호선(Long upStationId, Long downStationId, int distance) {
        return new LineRequest("사호선", "bg-blue-600", upStationId, downStationId, distance);
    }


    public static LineRequest 신분당선(Long upStationId, Long downStationId) {
        return new LineRequest("신분당선", "bg-red-600", upStationId, downStationId, 10);
    }

    public static LineRequest 분당선(Long upStationId, Long downStationId) {
        return new LineRequest("분당선", "bg-green-600", upStationId, downStationId, 20);
    }

    public static LineRequest 신림선(Long upStationId, Long downStationId) {
        return new LineRequest("신림선", "bg-blue-600", upStationId, downStationId, 20);
    }

    public static LineRequest 수정된_신분당선(Long upStationId, Long downStationId) {
        return new LineRequest("수정된_신분당선", "bg-blue-100", upStationId, downStationId, 10);
    }

    public static LineRequest 호남선(Long upStationId, Long downStationId, Integer distance) {
        return new LineRequest("호남선", "bg-blue-100", upStationId, downStationId, distance);
    }

    public static Line 이호선_생성() {
        return new Line("이호선", "그린");
    }
}
