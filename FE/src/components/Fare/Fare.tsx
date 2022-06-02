import React from 'react';

import { InputText } from 'components/InputText';

import { FARE_INFO } from 'constant';

export function Fare(): JSX.Element {
  return <InputText info={FARE_INFO} />;
}
