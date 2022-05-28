import React from 'react';

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

import { CalendarProps } from './CalendarModal.types';

function CalendarModal({ show, handleClickHide }: CalendarProps): JSX.Element {
  const buttons = CALENDAR_BUTTON_INFOS.map(el => (
    <Button key={el.id} type="button" className={el.className}>
      <img src={el.src} alt={el.alt} />
    </Button>
  ));

  if (show) {
    return (
      <ModalPortal>
        <>
          <Modal>
            <Carousel>
              <CarouselItemContainer>
                <Calendar />
                <Calendar />
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
