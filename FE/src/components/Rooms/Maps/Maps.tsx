/* eslint-disable react/react-in-jsx-scope */
import React from 'react';

import { MapContainer } from './Maps.styled';

import { Map } from '../Map/Map';

export function Maps(): JSX.Element {
  // useEffect(() => {
  //   const mapdiv = document.querySelector('#map');
  //   const options = {
  //     center: new kakao.maps.LatLng(33.450701, 126.570667),
  //     level: 3,
  //   };
  //   const map = new kakao.maps.Map(mapdiv, options);
  // }, []);

  return (
    <MapContainer>
      <Map />
      {/* <HeadBtnContainer>
      <Btn>
      </Btn>
      <BtnTitle></BtnTitle>
    </HeadBtnContain>
    <Map></Map> */}
    </MapContainer>
  );
}
