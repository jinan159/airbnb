import React, { useReducer, useState, useRef } from 'react';

import ModalPortal from 'common/portal';
import Calendar from 'components/Calendar/Calendar';

import { CALENDAR_BUTTON_INFOS, BASIC_MONTH_INFOS } from 'constant/constant';

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

const cur: Date = new Date(Date.now());

const initialCalendarState: CalendarInterface[] = [
  {
    id: 1,
    year: cur.getFullYear(),
    month: cur.getMonth() + BASIC_MONTH_INFOS.thisMonth,
  },
  {
    id: 2,
    year: cur.getFullYear(),
    month: cur.getMonth() + BASIC_MONTH_INFOS.nextMonth,
  },
];

function updateCalendarState(state: CalendarInterface[]): CalendarInterface[] {
  const copy = JSON.parse(JSON.stringify(state));
  const recentState = copy[copy.length - 1];

  if (recentState.month === 12) {
    const newObj = {
      id: recentState.id + 1,
      year: recentState.year + 1,
      month: 1,
    };
    return [...copy, newObj];
  }

  const newObj = {
    id: recentState.id + 1,
    year: recentState.year,
    month: recentState.month + 1,
  };

  return [...copy, newObj];
}

function calendarReducer(
  state: CalendarInterface[],
  action: CalendarActionInterface,
): CalendarInterface[] {
  switch (action.type) {
    case 'ADD_CALENDAR':
      return updateCalendarState(state);
    default:
      return state;
  }
}

function CalendarModal({ show, handleClickHide }: CalendarProps): JSX.Element {
  const carouselCounter = useRef<number>(1);
  const [carouselXPos, setCarouselXPos] = useState<number>(0);
  const [calendarState, calendarDispatch] = useReducer(
    calendarReducer,
    initialCalendarState,
  );

  const moveNextCarousel = (carouselUnit: number): void => {
    setCarouselXPos(prev => prev + carouselUnit);
    carouselCounter.current += 1;

    if (calendarState.length === carouselCounter.current)
      calendarDispatch({ type: 'ADD_CALENDAR' });
  };

  const movePrevCarousel = (carouselUnit: number): void => {
    setCarouselXPos(prev => prev + carouselUnit);
    carouselCounter.current -= 1;
  };

  const handleClickButton = (carouselUnit: number): void => {
    if (carouselCounter.current > 1) {
      if (carouselUnit < 0) moveNextCarousel(carouselUnit);
      else movePrevCarousel(carouselUnit);
    } else if (carouselCounter.current === 1) {
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
