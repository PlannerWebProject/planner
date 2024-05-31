package com.planiverse.event.repository;

import java.util.ArrayList;

import com.planiverse.event.model.EventDTO;

// 이벤트 관련 DAO 인터페이스
public interface EventDAO {
    /**
     * 새로운 이벤트를 추가합니다.
     * @param dto 추가할 이벤트 정보를 담은 EventDTO 객체
     * @return 추가된 이벤트 시퀀스 번호
     */
    int add(EventDTO dto);

    /**
     * 이벤트 정보를 수정합니다.
     * @param dto 수정할 이벤트 정보를 담은 EventDTO 객체
     * @return 수정 성공 시 1, 실패 시 0
     */
    int change(EventDTO dto);

    /**
     * 이벤트를 삭제합니다.
     * @param eventSeq 삭제할 이벤트 시퀀스 번호
     * @return 삭제 성공 시 1, 실패 시 0
     */
    int delete(String eventSeq);

    /**
     * 이벤트 일정을 변경합니다.
     * @param dto 변경할 이벤트 정보를 담은 EventDTO 객체
     * @return 변경 성공 시 1, 실패 시 0
     */
    int dropchange(EventDTO dto);

    /**
     * 사용자의 이벤트 목록을 조회합니다.
     * @param id 사용자 ID
     * @return 이벤트 목록을 담은 ArrayList
     */
    ArrayList<EventDTO> list(String id);

    /**
     * 특정 캘린더의 이벤트 목록을 조회합니다.
     * @param calSeq 캘린더 시퀀스 번호
     * @return 이벤트 목록을 담은 ArrayList
     */
    ArrayList<EventDTO> shareList(String calSeq);
}