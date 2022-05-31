import React, { useReducer, useState, useRef } from 'react';

import ModalPortal from 'common/portal';
import { Calendar } from 'components/Calendar';
import {
  initialCalendarState,
  calendarReducer,
} from 'store/calendarModalStore';

import { CALENDAR_BUTTON_INFOS } from 'constant';

import {
  Backdrop,
  Modal,
  Carousel,
  CarouselItemContainer,
  Button,
} from './CalendarModal.styled';

import { CalendarProps } from './CalendarModal.types';

export function CalendarModal({
  show,
  handleClickShow,
}: CalendarProps): JSX.Element {
  const calendarClickCount = useRef<number>(0);
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
    <Calendar
      key={el.id}
      dateInfo={el}
      calendarClickCount={calendarClickCount}
    />
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
          <Backdrop onClick={handleClickShow} />
        </>
      </ModalPortal>
    );
  }

  return <ModalPortal />;
}
