import React, { useState, useContext } from 'react';

import { Check } from 'components/Check';
import { Fare } from 'components/Fare';
import { Personnel } from 'components/Personnel';

import { SearchCtx } from 'contexts/searchContext';

import { Stick } from 'common/util.styled';
import { SearchBar, SearchBtn, SearchForm } from './Search.styeld';
import {SearchConetextInterface} from

function Search(): JSX.Element {
  const [checkIn, setCheckIn] = useState<string>();
  const [checkOut, setCheckOut] = useState<string>();

  // const SearchContext: = {

  // };

  return (
    <SearchBar>
      <SearchForm action="">
        <SearchCtx.Provider value={(checkIn, checkOut)}>
          <Check />
          <Stick />
          <Fare />
          <Stick />
          <Personnel />
        </SearchCtx.Provider>
        <SearchBtn type="submit">
          <img src="./assets/images/search.svg" alt="검색" />
        </SearchBtn>
      </SearchForm>
    </SearchBar>
  );
}

export { Search };
