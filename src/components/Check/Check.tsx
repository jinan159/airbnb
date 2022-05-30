import React, { useState } from 'react';

import { InputText } from 'components/InputText';
import { CalendarModal } from 'components/CalendarModal';

import { CHECK_INFOS } from 'constant';

import { CheckContainer } from './Check.styled';

export function Check(): JSX.Element {
  const [show, setShow] = useState<boolean>(false);

  const checkMenu = CHECK_INFOS.map(el => <InputText key={el.id} info={el} />);

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
