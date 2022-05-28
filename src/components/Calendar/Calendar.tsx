import React from 'react';

import ModalPortal from 'common/portal';

import { CALENDAR_BUTTON_INFOS } from 'constant/constant';

import { Backdrop, Modal, Button } from './Calendar.styled';

import { CalendarProps } from './Calendar.types';

function Calendar({ show, handleClickHide }: CalendarProps): JSX.Element {
  const buttons = CALENDAR_BUTTON_INFOS.map(el => (
    <Button key={el.id} type="button" className={el.className}>
      <img src={el.src} alt={el.alt} />
    </Button>
  ));

  if (show) {
    return (
      <ModalPortal>
        <>
          <Modal>{buttons}</Modal>
          <Backdrop onClick={handleClickHide} />
        </>
      </ModalPortal>
    );
  }

  return <ModalPortal />;
}

export default Calendar;
