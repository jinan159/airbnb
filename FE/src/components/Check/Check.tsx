import React, { useState, useRef } from 'react';

import { InputText } from 'components/InputText';
import { CalendarModal } from 'components/CalendarModal';

import { CHECK_INFOS } from 'constant';

import { CheckContainer } from './Check.styled';

export function Check(): JSX.Element {
  const [checkIn, setCheckIn] = useState<string>();
  const [checkOut, setCheckOut] = useState<string>();
  const [show, setShow] = useState<boolean>(false);

  // const handleClickCalendarDate = (date: string): void => {
  //   if (calendarClickCount.current === 0) {
  //     setCheckIn(date);
  //   } else if (calendarClickCount.current === 1) {
  //     setCheckOut(date);
  //   } else if (calendarClickCount.current === 2) {
  //     // 체크인, 체크아웃이 모두 설정되어 있는 경우
  //   }
  // };

  const checkMenu = CHECK_INFOS.map(el =>
    el.id === 1 ? (
      <InputText key={el.id} info={el} check={checkIn} />
    ) : (
      <InputText key={el.id} info={el} check={checkOut} />
    ),
  );

  const handleClickShow = (flag: boolean): void => setShow(flag);

  return (
    <>
      <CheckContainer onClick={() => handleClickShow(true)}>
        {checkMenu}
      </CheckContainer>
      <CalendarModal show={show} handleClickShow={handleClickShow} />
    </>
  );
}
