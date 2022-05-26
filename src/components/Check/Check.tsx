import React from 'react';
import InputText from 'components/InputText/InputText';
import { CHECK_INFOS } from 'constant/constant';
import { CheckContainer } from './Check.styled';

function Check(): JSX.Element {
  const checkMenu = CHECK_INFOS.map(el => <InputText key={el.id} info={el} />);
  return <CheckContainer>{checkMenu}</CheckContainer>;
}

export default Check;
