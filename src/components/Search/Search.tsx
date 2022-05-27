import React from 'react';

import Check from 'components/Check/Check';
import Fare from 'components/Fare/Fare';
import Personnel from 'components/Personnel/Personnel';
import Calendar from 'components/Calendar/Calendar';

import { Stick } from 'common/util.styled';
import { SearchBar, SearchBtn, SearchForm } from './Search.styeld';

function Search(): JSX.Element {
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
      <Calendar />
    </SearchBar>
  );
}

export default Search;
