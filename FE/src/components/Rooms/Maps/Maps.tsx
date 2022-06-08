/* eslint-disable react/react-in-jsx-scope */
import React from 'react';

import { MapContainer, CButtonGroup, MapBtn } from './Maps.styled';

import { Map } from '../Map/Map';

export function Maps(): JSX.Element {
  return (
    <MapContainer>
      <Map />
      {/* <CButtonGroup orientation="vertical">
        <MapBtn variant="contained" href="#contained-buttons" disableRipple>
          +
        </MapBtn>
        <MapBtn variant="contained" href="#contained-buttons" disableRipple>
          -
        </MapBtn>
      </CButtonGroup> */}
    </MapContainer>
  );
}
