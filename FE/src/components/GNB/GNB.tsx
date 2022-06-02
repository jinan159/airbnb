import React from 'react';

import { GNBS } from 'constant';

import { GNBMenu, GNBBtn } from './GNB.styled';

export function GNB(): JSX.Element {
  const gnbBtns = GNBS.map(gnb => <GNBBtn key={gnb.id}>{gnb.text}</GNBBtn>);

  return <GNBMenu>{gnbBtns}</GNBMenu>;
}
