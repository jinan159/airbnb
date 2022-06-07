import React, { useEffect, useState } from 'react';

import { LodgingElement } from '../LodgingElement';

import {
  LodgingContainer,
  LodgingSearchInfoList,
  LodgingSearchInfoItem,
  LodgingTitle,
  LodgingList,
} from './Lodging.styled';

const temp = [
  '300개 이상의 숙소',
  '5월 12일 - 5월 18일',
  '$100,000~$1,000,000',
  '게스트 3명',
];

const temp2 = [
  {
    id: 1,
    src: './assets/images/thumbnail.png',
    exp: '서초구의 아파트 전체',
    title: 'Spacious and Comfortable cozy house #4',
    infoFirst: ['최대인원 3명', '원룸', '침대 1개', '욕실 1개'],
    infoSecond: ['주방', '무선 인터넷', '에어컨', '헤어드라이어'],
    star: 4.8,
    review: 127,
    price: 82953,
    totalPrice: 1493159,
  },
  {
    id: 2,
    src: './assets/images/thumbnail.png',
    exp: '서초구의 아파트 전체',
    title: 'Spacious and Comfortable cozy house #4',
    infoFirst: ['최대인원 3명', '원룸', '침대 1개', '욕실 1개'],
    infoSecond: ['주방', '무선 인터넷', '에어컨', '헤어드라이어'],
    star: 4.8,
    review: 127,
    price: 82953,
    totalPrice: 1493159,
  },
  {
    id: 3,
    src: './assets/images/thumbnail.png',
    exp: '서초구의 아파트 전체',
    title: 'Spacious and Comfortable cozy house #4',
    infoFirst: ['최대인원 3명', '원룸', '침대 1개', '욕실 1개'],
    infoSecond: ['주방', '무선 인터넷', '에어컨', '헤어드라이어'],
    star: 4.8,
    review: 127,
    price: 82953,
    totalPrice: 1493159,
  },
  {
    id: 4,
    src: './assets/images/thumbnail.png',
    exp: '서초구의 아파트 전체',
    title: 'Spacious and Comfortable cozy house #4',
    infoFirst: ['최대인원 3명', '원룸', '침대 1개', '욕실 1개'],
    infoSecond: ['주방', '무선 인터넷', '에어컨', '헤어드라이어'],
    star: 4.8,
    review: 127,
    price: 82953,
    totalPrice: 1493159,
  },
  {
    id: 5,
    src: './assets/images/thumbnail.png',
    exp: '서초구의 아파트 전체',
    title: 'Spacious and Comfortable cozy house #4',
    infoFirst: ['최대인원 3명', '원룸', '침대 1개', '욕실 1개'],
    infoSecond: ['주방', '무선 인터넷', '에어컨', '헤어드라이어'],
    star: 4.8,
    review: 127,
    price: 82953,
    totalPrice: 1493159,
  },
];

export function Lodging(): JSX.Element {
  const [isFetching, setIsFetching] = useState(false);

  useEffect(() => {
    setTimeout(() => {
      setIsFetching(true);
    }, 5000);
  }, [isFetching]);

  const lodgingSearchInfos = temp.map(text => (
    <LodgingSearchInfoItem key={text}>{text}</LodgingSearchInfoItem>
  ));

  const lodginElements = temp2.map(info => (
    <LodgingElement key={info.id} lodgingInfo={info} isFetching={isFetching} />
  ));

  return (
    <LodgingContainer>
      <LodgingSearchInfoList>{lodgingSearchInfos}</LodgingSearchInfoList>
      <LodgingTitle>지도에서 선택한 지역의 숙소</LodgingTitle>
      <LodgingList>{lodginElements}</LodgingList>
    </LodgingContainer>
  );
}
