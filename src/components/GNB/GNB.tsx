import React from 'react';

import { GNB_TEXTS } from 'constant';

import { GNBMenu, GNBBtn } from './GNB.styled';

export function GNB(): JSX.Element {
  const gnb = GNB_TEXTS.map(el => <GNBBtn key={el.id}>{el.text}</GNBBtn>);

  return <GNBMenu>{gnb}</GNBMenu>;
}
