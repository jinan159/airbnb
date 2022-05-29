import React, { useReducer, useState, useRef } from 'react';

import ModalPortal from 'common/portal';
import Calendar from 'components/Calendar/Calendar';

import { CALENDAR_BUTTON_INFOS } from 'constant/constant';

import {
  Backdrop,
  Modal,
  Carousel,
  CarouselItemContainer,
  Button,
} from './CalendarModal.styled';

import {
  CalendarProps,
  CalendarInterface,
  CalendarActionInterface,
} from './CalendarModal.types';

function calendarReducer(
  state: CalendarInterface[],
  action: CalendarActionInterface,
) {
  switch (action.type) {
    case 'ADD_CALENDAR':
      console.log(1);
      return [...state];
    // case 'ADD_CALENDAR_MONTH_UP':
    // 맨 마지막 배열의 값을 받아와 date의 값으로 만들고 해당 년수를 가져와 +1하고 다시 반환
    default:
      return state;
  }
}

const cur = new Date(Date.now());
const initialCalendarState: CalendarInterface[] = [
  { id: 1, year: cur.getFullYear(), month: cur.getMonth() + 1 },
  { id: 2, year: cur.getFullYear(), month: cur.getMonth() + 2 },
  { id: 3, year: cur.getFullYear(), month: cur.getMonth() + 3 },
];

function CalendarModal({ show, handleClickHide }: CalendarProps): JSX.Element {
  const carouselCounter = useRef<number>(0);
  const [carouselXPos, setCarouselXPos] = useState<number>(0);
  const [calendarState, calendarDispatch] = useReducer(
    calendarReducer,
    initialCalendarState,
  );

  const moveNextCarousel = (carouselUnit: number): void => {
    setCarouselXPos(prev => prev + carouselUnit);
    carouselCounter.current += 1;
  };

  const movePrevCarousel = (carouselUnit: number): void => {
    setCarouselXPos(prev => prev + carouselUnit);
    carouselCounter.current -= 1;
  };

  const handleClickButton = (carouselUnit: number): void => {
    if (carouselCounter.current > 0) {
      if (carouselUnit < 0) moveNextCarousel(carouselUnit);
      else movePrevCarousel(carouselUnit);
    } else if (carouselCounter.current === 0) {
      if (carouselUnit < 0) moveNextCarousel(carouselUnit);
    }
  };

  const calendars = calendarState.map(el => (
    <Calendar key={el.id} dateInfos={el} />
  ));

  const buttons = CALENDAR_BUTTON_INFOS.map(el => (
    <Button
      key={el.id}
      type="button"
      className={el.className}
      onClick={() => handleClickButton(el.carouselUnit)}
    >
      <img src={el.src} alt={el.alt} />
    </Button>
  ));

  if (show) {
    return (
      <ModalPortal>
        <>
          <Modal>
            <Carousel>
              <CarouselItemContainer pos={carouselXPos}>
                {calendars}
              </CarouselItemContainer>
            </Carousel>
            {buttons}
          </Modal>
          <Backdrop onClick={handleClickHide} />
        </>
      </ModalPortal>
    );
  }

  return <ModalPortal />;
}

export default CalendarModal;
