import React, { useState, useMemo, useReducer } from 'react';

import { Check } from 'components/Check';
import { Fare } from 'components/Fare';
import { Personnel } from 'components/Personnel';

import { CheckContext } from 'contexts/checkcontext/checkContext';
import { PersonnelContext } from 'contexts/personnelcontext/personnelContext';

import { ICheckContext } from 'contexts/checkcontext/checkContext.types';
import { IPersonnelContext } from 'contexts/personnelcontext/personnelContext.types';

import { initialPersonnelState, personnelReducer } from 'store/personnelStore';

import { Stick } from 'common/util.styled';
import {
  SearchBar,
  SearchBtn,
  SearchForm,
} from 'components/Search/Search.styeld';

export function Search(): JSX.Element {
  const [checkIn, setCheckIn] = useState<string>('');
  const [checkOut, setCheckOut] = useState<string>('');
  const [mouseOverCheckOut, setMouseOverCheckOut] = useState<string>('');
  const [personnelState, personnelDispatch] = useReducer(
    personnelReducer,
    initialPersonnelState,
  );

  const checkContext: ICheckContext = useMemo(
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

  const personnelContext: IPersonnelContext = useMemo(
    () => ({
      personnelState,
      personnelDispatch,
    }),
    [personnelState],
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
        <PersonnelContext.Provider value={personnelContext}>
          <Personnel />
        </PersonnelContext.Provider>
        <SearchBtn type="submit">
          <img src="./assets/images/search.svg" alt="검색" />
        </SearchBtn>
      </SearchForm>
    </SearchBar>
  );
}
