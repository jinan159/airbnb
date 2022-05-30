import React, { useState, useRef } from 'react';

import { Check } from 'components/Check';
import { Fare } from 'components/Fare';
import { Personnel } from 'components/Personnel';

import { Stick } from 'common/util.styled';
import { SearchBar, SearchBtn, SearchForm } from './Search.styeld';

function Search(): JSX.Element {
  const calendarClickCount = useRef<number>(0);
  const [checkIn, setCheckIn] = useState<Date>();
  const [checkOut, setCheckOut] = useState<Date>();

  const handleClickCalendarDate = (date: Date): void => {
    if (calendarClickCount.current === 1) {
      setCheckIn(date);
    } else if (calendarClickCount.current === 2) {
      setCheckOut(date);
    }
  };

  return (
    <SearchBar>
      <SearchForm action="">
        <Check />
        <Stick />
        <Fare />
        <Stick />
        <Personnel />
        <SearchBtn type="submit">
          <img src="./assets/images/search.svg" alt="검색" />
        </SearchBtn>
      </SearchForm>
    </SearchBar>
  );
}

export { Search };
