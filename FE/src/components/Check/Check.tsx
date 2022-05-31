import React, { useState, useContext, useRef } from 'react';

import { InputText } from 'components/InputText';
import { CalendarModal } from 'components/CalendarModal';

import { CHECK_INFOS } from 'constant';

import { CheckContext } from 'contexts/checkcontext/checkContext';
import { CheckContainer, CheckClearBtn } from './Check.styled';

export function Check(): JSX.Element {
  const calendarClickCount = useRef<number>(0);
  const checkContext = useContext(CheckContext);
  const [show, setShow] = useState<boolean>(false);

  const checkMenu = CHECK_INFOS.map(el =>
    el.id === 1 ? (
      <InputText key={el.id} info={el} check={checkContext?.checkIn} />
    ) : (
      <InputText key={el.id} info={el} check={checkContext?.checkOut} />
    ),
  );

  const handleClickShow = () => {
    setShow(prev => !prev);
  };

  const handleClickCheckClearBtn = () => {
    checkContext?.setCheckIn('');
    checkContext?.setCheckOut('');
    calendarClickCount.current = 0;
  };

  return (
    <>
      <CheckContainer onClick={handleClickShow}>
        {checkMenu}
        <CheckClearBtn
          type="button"
          onClick={handleClickCheckClearBtn}
          checkIn={checkContext?.checkIn}
        >
          <img
            src="./assets/images/x-circle.svg"
            alt="체크인, 체크아웃 초기화 버튼"
          />
        </CheckClearBtn>
      </CheckContainer>
      <CalendarModal
        show={show}
        handleClickShow={handleClickShow}
        calendarClickCount={calendarClickCount}
      />
    </>
  );
}
