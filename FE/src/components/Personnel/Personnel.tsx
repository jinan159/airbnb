import React from 'react';

import { InputText } from 'components/InputText';

import { PERSONNEL_INFOS } from 'constant';

export function Personnel(): JSX.Element {
  return <InputText info={PERSONNEL_INFOS} />;
}
