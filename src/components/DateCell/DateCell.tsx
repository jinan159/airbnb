import React, { useState } from 'react';

import { DateCellInterface } from 'components/DateCell/DateCell.types';
import { DateCellContent } from 'components/DateCell/DateCell.styled';

function DateCell({ date, past }: DateCellInterface): JSX.Element {
  const [flag, setFlag] = useState<boolean>(false);

  const handleClickDateCellContent = () => setFlag(prev => !prev);

  return (
    <DateCellContent
      date={date}
      past={past}
      flag={flag}
      onClick={handleClickDateCellContent}
    >
      {date}
    </DateCellContent>
  );
}

export default DateCell;
