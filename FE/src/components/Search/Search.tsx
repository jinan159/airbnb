import React, { useState, useMemo } from 'react';

import { Check } from 'components/Check';
import { Fare } from 'components/Fare';
import { Personnel } from 'components/Personnel';

import { CheckContext } from 'contexts/checkcontext/checkContext';
import { checkContextInterface } from 'contexts/checkcontext/checkContext.types';

import { Stick } from 'common/util.styled';
import {
  SearchBar,
  SearchBtn,
  SearchForm,
} from 'components/Search/Search.styeld';

function Search(): JSX.Element {
  const [checkIn, setCheckIn] = useState<string>('');
  const [checkOut, setCheckOut] = useState<string>('');
  const [mouseOverCheckOut, setMouseOverCheckOut] = useState<string>('');

  const checkContext: checkContextInterface = useMemo(
    () => ({
      checkIn,
      checkOut,
      setCheckIn,
      setCheckOut,
      mouseOverCheckOut,
      setMouseOverCheckOut,
    }),
    [checkIn, checkOut, mouseOverCheckOut],
  );

  return (
    <SearchBar>
      <SearchForm>
        <CheckContext.Provider value={checkContext}>
          <Check />
        </CheckContext.Provider>
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
