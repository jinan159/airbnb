import React from 'react';

import { Check } from 'components/Check';
import { Fare } from 'components/Fare';
import { Personnel } from 'components/Personnel';
import { FareProvider } from 'contexts/farecontext/farecontext';
import { Stick } from 'common/util.styled';
import { SearchBar, SearchBtn, SearchForm } from './Search.styeld';

function Search(): JSX.Element {
  return (
    <SearchBar>
      <SearchForm action="">
        <Check />
        <Stick />
        <FareProvider>
          <Fare />
        </FareProvider>
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
