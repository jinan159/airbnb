/* eslint-disable no-param-reassign */
import React, { useContext, useEffect, useState } from 'react';

import { DateCellInterface } from 'components/DateCell/DateCell.types';
import {
  DateCellContent,
  DateCellCircle,
} from 'components/DateCell/DateCell.styled';
import { CheckContext } from 'contexts/checkcontext/checkContext';

export function DateCell({
  dateInfo,
  past,
  calendarClickCount,
}: DateCellInterface): JSX.Element {
  const [selcetFlag, setSelcetFlag] = useState<boolean>(false);
  const [rangeFlag, setRangeFlag] = useState<boolean>(false);
  const checkContext = useContext(CheckContext);

  useEffect(() => {
    if (
      calendarClickCount.current === 1 &&
      new Date(`${dateInfo?.year}, ${dateInfo?.month}, ${dateInfo?.date}`) <
        new Date(`${checkContext?.mouseOverCheckOut}`) &&
      new Date(`${dateInfo?.year}, ${dateInfo?.month}, ${dateInfo?.date}`) >
        new Date(`${checkContext?.checkIn}`)
    )
      setRangeFlag(true);
    else setRangeFlag(false);

    if (
      new Date(`${dateInfo?.year}, ${dateInfo?.month}, ${dateInfo?.date}`) <
        new Date(`${checkContext?.checkOut}`) &&
      new Date(`${dateInfo?.year}, ${dateInfo?.month}, ${dateInfo?.date}`) >
        new Date(`${checkContext?.checkIn}`) &&
      calendarClickCount.current === 2
    )
      setRangeFlag(prev => !prev);

    if (
      new Date(
        `${dateInfo?.year}, ${dateInfo?.month}, ${dateInfo?.date}`,
      ).getTime() === new Date(`${checkContext?.checkIn}`).getTime()
    )
      setSelcetFlag(true);
    else setSelcetFlag(false);

    if (
      new Date(
        `${dateInfo?.year}, ${dateInfo?.month}, ${dateInfo?.date}`,
      ).getTime() === new Date(`${checkContext?.checkOut}`).getTime()
    )
      setSelcetFlag(true);
  }, [
    checkContext?.mouseOverCheckOut,
    checkContext?.checkOut,
    checkContext?.checkIn,
  ]);

  const handleMouseOverDateCellContent = () => {
    if (calendarClickCount.current === 1) {
      checkContext?.setMouseOverCheckOut(
        `${dateInfo?.year}, ${dateInfo?.month}, ${dateInfo?.date}`,
      );
    }
  };

  const handleClickDateCellContent = () => {
    if (calendarClickCount.current === 0) {
      checkContext?.setCheckIn(
        `${dateInfo?.year}, ${dateInfo?.month}, ${dateInfo?.date}`,
      );
      calendarClickCount.current += 1;
    } else if (calendarClickCount.current === 1) {
      if (
        new Date(`${checkContext?.checkIn}`) <
        new Date(`${dateInfo?.year}, ${dateInfo?.month}, ${dateInfo?.date}`)
      ) {
        checkContext?.setCheckOut(
          `${dateInfo?.year}, ${dateInfo?.month}, ${dateInfo?.date}`,
        );
        calendarClickCount.current += 1;
      } else if (
        new Date(`${checkContext?.checkIn}`) >
        new Date(`${dateInfo?.year}, ${dateInfo?.month}, ${dateInfo?.date}`)
      ) {
        checkContext?.setCheckIn(
          `${dateInfo?.year}, ${dateInfo?.month}, ${dateInfo?.date}`,
        );
        checkContext?.setCheckOut('');
        calendarClickCount.current = 1;
      }
    } else if (calendarClickCount.current === 2) {
      if (
        new Date(`${dateInfo?.year}, ${dateInfo?.month}, ${dateInfo?.date}`) <
        new Date(`${checkContext?.checkIn}`)
      ) {
        checkContext?.setCheckIn(
          `${dateInfo?.year}, ${dateInfo?.month}, ${dateInfo?.date}`,
        );
        checkContext?.setCheckOut('');
      } else {
        checkContext?.setCheckOut(
          `${dateInfo?.year}, ${dateInfo?.month}, ${dateInfo?.date}`,
        );
      }
    }
  };

  return (
    <DateCellContent
      dateInfo={dateInfo}
      past={past}
      selectFlag={selcetFlag}
      rangeFlag={rangeFlag}
      onClick={handleClickDateCellContent}
      onMouseOver={handleMouseOverDateCellContent}
    >
      <DateCellCircle past={past} dateInfo={dateInfo} rangeFlag={rangeFlag}>
        {dateInfo?.date}
      </DateCellCircle>
    </DateCellContent>
  );
}
