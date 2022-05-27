import React from 'react';
import InputText from 'components/InputText/InputText';
import { FARE_INFOS } from 'constant/constant';

function Fare(): JSX.Element {
  return <InputText info={FARE_INFOS} />;
}

export default Fare;
