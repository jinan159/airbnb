/* eslint-disable no-param-reassign */
import React, { useState } from 'react';

import { DateCellInterface } from 'components/DateCell/DateCell.types';
import { DateCellContent } from 'components/DateCell/DateCell.styled';

export function DateCell({
  date,
  past,
  calendarClickCount,
}: DateCellInterface): JSX.Element {
  const [flag, setFlag] = useState<boolean>(false);

  // const handleMouseOverDateCellContent = () => {};

  const handleClickDateCellContent = () => {
    if (calendarClickCount.current < 2) setFlag(prev => !prev);
    // else if (calendarClickCount.current === 2)
    // const a = { ...calendarClickCount };
    // a.current += 1;
    // calendarClickCount = a;
    console.log(calendarClickCount.current);
  };

  return (
    <DateCellContent
      date={date}
      past={past}
      flag={flag}
      onClick={handleClickDateCellContent}
      // onMouseOver={handleMouseOverDateCellContent}
    >
      {date}
    </DateCellContent>
  );
}
