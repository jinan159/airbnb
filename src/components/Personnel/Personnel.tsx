import React from 'react';
import InputText from 'components/InputText/InputText';
import { PERSONNEL_INFOS } from 'constant/constant';

function Personnel(): JSX.Element {
  return <InputText info={PERSONNEL_INFOS} />;
}

export default Personnel;
