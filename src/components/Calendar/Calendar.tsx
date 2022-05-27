import React from 'react';

import ModalPortal from 'common/portal';

import { Backdrop } from './Calendar.styled';

import { CalendarProps } from './Calendar.types';

function Calendar({ show, handleClickHide }: CalendarProps): JSX.Element {
  return (
    <ModalPortal>{show && <Backdrop onClick={handleClickHide} />}</ModalPortal>
  );
}

export default Calendar;
