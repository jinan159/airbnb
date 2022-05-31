import React, { useState, useContext } from 'react';

import { InputText } from 'components/InputText';
import { CalendarModal } from 'components/CalendarModal';

import { CHECK_INFOS } from 'constant';

import { CheckContext } from 'contexts/checkcontext/checkContext';
import { CheckContainer } from './Check.styled';

export function Check(): JSX.Element {
  const checkContext = useContext(CheckContext);
  const [show, setShow] = useState<boolean>(false);

  const checkMenu = CHECK_INFOS.map(el =>
    el.id === 1 ? (
      <InputText key={el.id} info={el} check={checkContext?.checkIn} />
    ) : (
      <InputText key={el.id} info={el} check={checkContext?.checkOut} />
    ),
  );

  const handleClickShow = () => setShow(prev => !prev);

  return (
    <>
      <CheckContainer onClick={handleClickShow}>{checkMenu}</CheckContainer>
      <CalendarModal show={show} handleClickShow={handleClickShow} />
    </>
  );
}
