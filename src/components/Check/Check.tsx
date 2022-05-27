import React, { useState } from 'react';

import InputText from 'components/InputText/InputText';
import Calendar from 'components/Calendar/Calendar';

import { CHECK_INFOS } from 'constant/constant';

import { CheckContainer } from './Check.styled';

function Check(): JSX.Element {
  const [show, setShow] = useState<boolean>(false);

  const checkMenu = CHECK_INFOS.map(el => <InputText key={el.id} info={el} />);

  const handleClickShow = () => setShow(true);

  const handleClickHide = () => setShow(false);

  return (
    <>
      <CheckContainer onClick={handleClickShow}>{checkMenu}</CheckContainer>
      <Calendar show={show} handleClickHide={handleClickHide} />
    </>
  );
}

export default Check;
