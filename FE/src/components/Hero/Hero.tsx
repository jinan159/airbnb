import React from 'react';
import { Outlet } from 'react-router-dom';

import { Header } from 'components/Header';
import { Search } from 'components/Search';

import { Container, Wrapper } from './Hero.styled';

export function Hero(): JSX.Element {
  return (
    <Wrapper>
      <Container>
        <Header />
        <Search />
      </Container>
      <Outlet />
    </Wrapper>
  );
}
